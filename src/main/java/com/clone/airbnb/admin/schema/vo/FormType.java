package com.clone.airbnb.admin.schema.vo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

import com.clone.airbnb.admin.exception.EntitySchemaInvalidEntityClassException;
import com.clone.airbnb.admin.exception.InvalidReturnTypeException;
import com.clone.airbnb.admin.exception.NonStaticMethodException;
import com.clone.airbnb.admin.form.annotation.CheckBoxForm;
import com.clone.airbnb.admin.form.annotation.DateForm;
import com.clone.airbnb.admin.form.annotation.DatetimeForm;
import com.clone.airbnb.admin.form.annotation.FileUploadForm;
import com.clone.airbnb.admin.form.annotation.ImageUploadForm;
import com.clone.airbnb.admin.form.annotation.IntegerForm;
import com.clone.airbnb.admin.form.annotation.JoinManyForm;
import com.clone.airbnb.admin.form.annotation.JoinOneTextForm;
import com.clone.airbnb.admin.form.annotation.MapSelectBoxForm;
import com.clone.airbnb.admin.form.annotation.MultipleFileUploadForm;
import com.clone.airbnb.admin.form.annotation.MultipleImageUploadForm;
import com.clone.airbnb.admin.form.annotation.JoinOneForm;
import com.clone.airbnb.admin.form.annotation.PasswordForm;
import com.clone.airbnb.admin.form.annotation.SelectBoxForm;
import com.clone.airbnb.admin.form.annotation.TextAreaForm;
import com.clone.airbnb.admin.form.annotation.TextForm;
import com.clone.airbnb.admin.form.annotation.TimeForm;
import com.clone.airbnb.admin.form.annotation.UserEntity;
import com.clone.airbnb.admin.form.annotation.UsernameForm;
import com.clone.airbnb.admin.form.info.CheckBoxInfo;
import com.clone.airbnb.admin.form.info.DateInfo;
import com.clone.airbnb.admin.form.info.DatetimeInfo;
import com.clone.airbnb.admin.form.info.FileUploadFormInfo;
import com.clone.airbnb.admin.form.info.FormInfo;
import com.clone.airbnb.admin.form.info.ImageUploadFormInfo;
import com.clone.airbnb.admin.form.info.IntegerInfo;
import com.clone.airbnb.admin.form.info.JoinOneTextInfo;
import com.clone.airbnb.admin.form.info.MapSelectBoxInfo;
import com.clone.airbnb.admin.form.info.MultipleFileUploadFormInfo;
import com.clone.airbnb.admin.form.info.MultipleImageUploadFormInfo;
import com.clone.airbnb.admin.form.info.NoneInfo;
import com.clone.airbnb.admin.form.info.JoinOneInfo;
import com.clone.airbnb.admin.form.info.PasswordInfo;
import com.clone.airbnb.admin.form.info.PlaceholderTextInfo;
import com.clone.airbnb.admin.form.info.SelectBoxInfo;
import com.clone.airbnb.admin.form.info.TextAreaInfo;
import com.clone.airbnb.admin.form.info.TimeInfo;
import com.clone.airbnb.admin.form.info.UsernameInfo;
import com.clone.airbnb.exception.InvalidFormTypeException;
import com.clone.airbnb.utils.ReflectionInvocator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = "formInfo")
public class FormType {
	private final FieldType fieldType;
	private final FormInfo formInfo;
	
	private FormType(FieldType fieldType) {
		this(fieldType, null);
	}
	
	
	public static FormType getType(Field f, Class<Object> entityClass) {
		FormType type = null;
		
		if 		((type = text(f)) 							!= null) {}
		else if ((type = integer(f)) 						!= null) {}
		else if ((type = selectbox(f)) 						!= null) {}
		else if ((type = mapSelectbox(f, entityClass))		!= null) {}
		else if ((type = checkbox(f)) 						!= null) {}
		else if ((type = textarea(f)) 						!= null) {}
		else if ((type = fileUploadPath(f)) 				!= null) {}
		else if ((type = imageUploadPath(f)) 				!= null) {}
		else if ((type = multipleImageUploadPath(f)) 		!= null) {}
		else if ((type = multipleFileUploadPath(f)) 		!= null) {}
 		else if ((type = date(f)) 							!= null) {}
		else if ((type = datetime(f)) 						!= null) {}
		else if ((type = time(f)) 							!= null) {}
		else if ((type = joinOneText(f))					!= null) {}
		else if ((type = joinOne(f, entityClass))			!= null) {}
		else if ((type = joinMany(f, entityClass))			!= null) {}
		else if ((type = username(f, entityClass))			!= null) {}
		else if ((type = password(f, entityClass))			!= null) {}
		else { type = none(f); }
		
		return type;
	}
	
	
	
	private static FormType integer(Field field) {
		FormType type = null;
		
		IntegerForm form = field.getAnnotation(IntegerForm.class);
		
		if (form != null) {
			
			type = new FormType(FieldType.INTEGER, 
					new IntegerInfo(
							form.formName().isEmpty() ? field.getName() : form.formName(),
							form.blank(),
							form.maxlength(),
							form.placeholder(),
							form.min(),
							form.max()
							));
		}
		
		return type;
	}
	
	
	private static FormType text(Field field) {
		FormType type = null;
		
		TextForm form = field.getAnnotation(TextForm.class);
		
		if (form != null) {
			type = new FormType(FieldType.TEXT, 
					new PlaceholderTextInfo(
							form.formName().isEmpty() ? field.getName() : form.formName(),
							form.blank(),
							form.maxlength(), 
							form.placeholder()
							));
		}
		
		return type;
	}
	
	
	private static FormType selectbox(Field field) {
		FormType type = null;
		
		SelectBoxForm form = field.getAnnotation(SelectBoxForm.class);
		
		if (form != null) {
			Class<?> ft = field.getType();
			
			if (ft.isEnum()) {
				
				type = new FormType(FieldType.SELECT_BOX, 
						new SelectBoxInfo(
								form.formName().isEmpty() ? field.getName() : form.formName(),
								form.blank(),
								form.defaultOption(),
								(Object[]) ReflectionInvocator.invoke(ft, "values")
								));
				
			} else {
				throw new InvalidFormTypeException("SelectBoxForm 타입은 반드시 Enum 타입이여야 합니다.");
			}
		}
		
		return type;
	}
	
	
	@SuppressWarnings("unchecked")
	private static FormType mapSelectbox(Field field, Class<Object> entityClass) {
		FormType type = null;
		
		MapSelectBoxForm form = field.getAnnotation(MapSelectBoxForm.class);
		
		if (form != null) {
			String valuesMethodName = form.method();
			
			Object obj = ReflectionInvocator.forceNewInstance(entityClass);
			
			Method method = ReflectionInvocator.getDeclaredMethod(entityClass, valuesMethodName);
			
			if (method == null) {
				throw new NullPointerException("MapSelectBoxForm 의 method 가 존재하지 않습니다. Field: " + field.getName());
			}
			
			if (Modifier.isStatic(method.getModifiers())) {
				throw new NonStaticMethodException("MapSelectBoxForm 의 method 가 non-static 이 아닙니다. Field: " + field.getName());
			}
			
			if (!Map.class.isAssignableFrom(method.getReturnType())) {
				throw new InvalidReturnTypeException("MapSelectBoxForm 의 method 의 리턴타입이 Map 이 아닙니다. 리턴타입은 Map 이여야 합니다. Field: " + field.getName());
			}
			
			type = new FormType(FieldType.MAP_SELECT_BOX, 
					new MapSelectBoxInfo(
							form.formName().isEmpty() ? field.getName() : form.formName(),
							form.blank(),
							form.defaultOption(),
							(Map<String, String>) ReflectionInvocator.invoke(obj, valuesMethodName)
							));
				
		}
		
		return type;
	}

	
	private static FormType checkbox(Field field) {
		FormType type = null;
		
		CheckBoxForm form = field.getAnnotation(CheckBoxForm.class);
		
		if (form != null) {
			type = new FormType(FieldType.CHECK_BOX,
					new CheckBoxInfo(
							form.formName().isEmpty() ? field.getName() : form.formName()
							));
		}
		
		return type;
	}
	
	
	private static FormType textarea(Field field) {
		FormType type = null;
		
		TextAreaForm form = field.getAnnotation(TextAreaForm.class);
		
		if (form != null) {
			type = new FormType(FieldType.TEXT_AREA, 
					new TextAreaInfo(
							form.formName().isEmpty() ? field.getName() : form.formName(),
							form.blank(),
							form.maxlength(), 
							form.placeholder(), 
							form.rows()
							));
		}
		
		return type;
	}
	
	
	
	private static FormType fileUploadPath(Field field) {
		FormType type = null;
		
		FileUploadForm form = field.getAnnotation(FileUploadForm.class);
		
		if (form != null) {
			type = new FormType(FieldType.FILE_UPLOAD_FORM, 
					new FileUploadFormInfo(
							form.formName().isEmpty() ? field.getName() : form.formName(),
							form.blank(),
							form.labelText(),
							form.accept()
							));
			
		}
		
		return type;
	}
	
	
	private static FormType imageUploadPath(Field field) {
		FormType type = null;
		
		ImageUploadForm form = field.getAnnotation(ImageUploadForm.class);
		
		if (form != null) {
			type = new FormType(FieldType.IMAGE_UPLOAD_FORM, 
					new ImageUploadFormInfo(
							form.formName().isEmpty() ? field.getName() : form.formName(),
							form.blank(),
							form.labelText()
							));
		}
		
		return type;
	}
	
	
	private static FormType multipleImageUploadPath(Field field) {
		FormType type = null;
		
		MultipleImageUploadForm form = field.getAnnotation(MultipleImageUploadForm.class);
		
		if (form != null) {
			type = new FormType(FieldType.MULTIPLE_IMAGE_UPLOAD_FORM, 
					new MultipleImageUploadFormInfo(
							form.formName().isEmpty() ? field.getName() : form.formName(),
							form.blank(),
							form.labelText()
							));
		}
		
		return type;
	}
	
	
	private static FormType multipleFileUploadPath(Field field) {
		FormType type = null;
		
		MultipleFileUploadForm form = field.getAnnotation(MultipleFileUploadForm.class);
		
		if (form != null) {
			type = new FormType(FieldType.MULTIPLE_FILE_UPLOAD_FORM, 
					new MultipleFileUploadFormInfo(
							form.formName().isEmpty() ? field.getName() : form.formName(),
							form.blank(),
							form.labelText(),
							form.accept()
							));
		}
		
		return type;
	}
	
	
	private static FormType date(Field field) {
		FormType type = null;
		
		DateForm form = field.getAnnotation(DateForm.class);
		
		if (form != null) {
			type = new FormType(FieldType.DATE,
					new DateInfo(
							form.formName().isEmpty() ? field.getName() : form.formName(),
							form.blank()
							));
		}
		
		return type;
	}
	
	
	private static FormType datetime(Field field) {
		FormType type = null;
		
		DatetimeForm form = field.getAnnotation(DatetimeForm.class);
		
		if (form != null) {
			type = new FormType(FieldType.DATETIME,
					new DatetimeInfo(
							form.formName().isEmpty() ? field.getName() : form.formName(),
							form.blank()
							));
		}
		
		return type;
	}
	
	
	private static FormType time(Field field) {
		FormType type = null;
		
		TimeForm form = field.getAnnotation(TimeForm.class);
		
		if (form != null) {
			type = new FormType(FieldType.TIME,
					new TimeInfo(
							form.formName().isEmpty() ? field.getName() : form.formName(),
							form.blank()
							));
		}
		
		return type;
	}
	
	
	private static FormType username(Field field, Class<Object> entityClass) {
		FormType type = null;

		UsernameForm form = field.getAnnotation(UsernameForm.class);
		
		if (form != null) {
			if (entityClass.getAnnotation(UserEntity.class) == null) {
				throw new EntitySchemaInvalidEntityClassException("UsernameForm 타입은 반드시 UserEntity 어노테이션이 붙은 클래스에서만 사용할 수 있습니다. Field: " + field.getName());
			}
			
			type = new FormType(FieldType.USERNAME, 
					new UsernameInfo(
							form.formName().isEmpty() ? field.getName() : form.formName(),
							form.maxlength(), 
							form.placeholder()
							));
		}
		
		return type;
	}
	
	
	private static FormType password(Field field, Class<Object> entityClass) {
		FormType type = null;
		
		PasswordForm form = field.getAnnotation(PasswordForm.class);
		
		if (form != null) {
			if (entityClass.getAnnotation(UserEntity.class) == null) {
				throw new EntitySchemaInvalidEntityClassException("PasswordForm 타입은 반드시 UserEntity 어노테이션이 붙은 클래스에서만 사용할 수 있습니다. Field: " + field.getName());
			}
			
			type = new FormType(FieldType.PASSWORD, 
					new PasswordInfo(
							form.formName().isEmpty() ? field.getName() : form.formName(),
							form.maxlength(),
							form.placeholder()
								));
		}
		
		return type;
	}
	
	
	private static FormType joinOneText(Field field) {
		FormType type = null;
		
		JoinOneTextForm form = field.getAnnotation(JoinOneTextForm.class);
		
		if (form != null) {
			type = new FormType(FieldType.JOIN_ONE_TEXT, 
					new JoinOneTextInfo(
							form.formName().isEmpty() ? field.getName() : form.formName(),
							form.blank(),
							form.maxlength(), 
							form.placeholder(),
							form.field()
							));
		}
		
		return type;
	}
	
	
	private static FormType joinOne(Field field, Class<Object> entityClass) {
		FormType type = null;
		
		JoinOneForm form = field.getAnnotation(JoinOneForm.class);
		
		if (form != null) {
			type = new FormType(FieldType.JOIN_ONE, 
					new JoinOneInfo(
							form.formName().isEmpty() ? field.getName() : form.formName(),
							form.blank(),
							form.field(),
							form.defaultOption(),
							form.repository(),
							form.findAllMethod()
							));
				
		}
		
		return type;
	}
	
	
	private static FormType joinMany(Field field, Class<Object> entityClass) {
		FormType type = null;
		
		JoinManyForm form = field.getAnnotation(JoinManyForm.class);
		
		if (form != null) {
			type = new FormType(FieldType.JOIN_MANY, 
					new JoinOneInfo(
							form.formName().isEmpty() ? field.getName() : form.formName(),
							form.blank(),
							form.field(),
							form.defaultOption(),
							form.repository(),
							form.findAllMethod()
							));
				
		}
		
		return type;
	}
	
	
	private static FormType none(Field field) {
		return new FormType(FieldType.NONE, new NoneInfo(field.getType()));
	}
	
}
