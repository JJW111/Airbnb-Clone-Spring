package com.clone.airbnb.admin;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.WebRequestDataBinder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.admin.entity.FileEntity;
import com.clone.airbnb.admin.exception.IllegalFieldTypeException;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.AdminEntityProvider;
import com.clone.airbnb.admin.schema.EntityProvider;
import com.clone.airbnb.admin.schema.RepositoryMapperProvider;
import com.clone.airbnb.admin.schema.vo.AdminEntity;
import com.clone.airbnb.admin.schema.vo.Column;
import com.clone.airbnb.admin.schema.vo.Columns;
import com.clone.airbnb.admin.schema.vo.Entity;
import com.clone.airbnb.admin.schema.vo.FieldType;
import com.clone.airbnb.utils.FileUtils;
import com.clone.airbnb.utils.ReflectionInvocator;
import com.clone.airbnb.utils.WordUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AdminWebPage {
	private final AdminEntityProvider adminEntityProvider;
	private final EntityProvider entityProvider;
	private final RepositoryMapperProvider repositoryMapperProvider;
	
	
	
	public static final FileUploadAndDeleteStatement DEFAULT_UPLOAD_STATMENT = (obj) -> {
		if (!FileEntity.class.isAssignableFrom(obj.getClass())) {
			throw new IllegalFieldTypeException("파일 업로드 필드는 " + FileEntity.class.getName() + " 클래스를 상속받아야 합니다.");
		}
		
		MultipartFile file = (MultipartFile) ReflectionInvocator.get(obj, "file");
		if (file != null) {
			String uploadPath = (String) ReflectionInvocator.get(obj, "uploadPath");
			if (uploadPath != null) {
				FileUtils.save(file, uploadPath);
			}
		}
	};
	
	
	
	
	public static final FileUploadAndDeleteStatement DEFAULT_DELETE_STATMENT = (obj) -> {
		if (!FileEntity.class.isAssignableFrom(obj.getClass())) {
			throw new IllegalFieldTypeException("파일 업로드 필드는 " + FileEntity.class.getName() + " 클래스를 상속받아야 합니다.");
		}
		
		String uploadPath = (String) ReflectionInvocator.get(obj, "uploadPath");
		if (uploadPath != null) {
			FileUtils.delete(uploadPath);
		}
	};
	
	
	
	
	public Page<Object> findAll(String entityName, Pageable pageable) {
		return (Page<Object>) repositoryMapperProvider.getRepositoryMapper().get(entityName).findAll(pageable);
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public Page<Object> findAllBy(String entityName, Pageable pageable, Class<?> clazz) {
		return (Page<Object>) this.find(entityName, "findAllBy", new Class<?>[] { Pageable.class, Class.class }, pageable, clazz);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Page<Object> findByOrderBy(String entityName, String orderBy, String ascDesc, Pageable pageable) {
		return (Page<Object>) this.find(entityName, "findByOrderBy" + WordUtils.capitalize(orderBy) + WordUtils.capitalize(ascDesc),
				new Class<?>[] { Pageable.class }, pageable);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Page<Object> findByOrderBy(String entityName, String orderBy, String ascDesc, Pageable pageable, Class<?> clazz) {
		return (Page<Object>) this.find(entityName, "findByOrderBy" + WordUtils.capitalize(orderBy) + WordUtils.capitalize(ascDesc),
				new Class<?>[] { Pageable.class, Class.class }, pageable, clazz);
	}
	
	
	
	
	
	public Object findById(String entityName, Integer id) {
		return repositoryMapperProvider.getRepositoryMapper().get(entityName).findById(id).get();
	}
	
	
	
	
	public Object findById(String entityName, Integer id, Class<?> clazz) {
		return this.find(entityName, "findById", new Class<?>[] { Integer.class, Class.class }, id, clazz);
	}
	
	
	
	
	public Object find(String entityName, String methodName, Class<?>[] parameterTypes, Object... args) {
		return ReflectionInvocator.invoke(repositoryMapperProvider.getRepositoryMapper().get(entityName), methodName, parameterTypes, args);
	}
	
	
	
	
	public BindingResult bind(String entityName, Object entityObj, WebRequest webRequest) {
		AdminEntity adminEntity = adminEntityProvider.getAdminDefinitionObject().getAdminEntity();
		AdminEntityConfiguration config = adminEntity.get(entityName);

		WebRequestDataBinder binder = new WebRequestDataBinder(entityObj);
		
		config.initWebRequestDataBinder(binder);
		
		binder.bind(webRequest);
		binder.validate();

		return binder.getBindingResult();
	}
	
	
	
	
	
	
	public BindingResult validate(String entityName, Object entityObj, WebRequest webRequest) {
		AdminEntity adminEntity = adminEntityProvider.getAdminDefinitionObject().getAdminEntity();
		AdminEntityConfiguration config = adminEntity.get(entityName);

		WebRequestDataBinder binder = new WebRequestDataBinder(entityObj);
		
		config.initWebRequestDataBinder(binder);
		
		binder.validate();

		return binder.getBindingResult();
	}
	

	
	
	
	
	public Object save(String entityName, Object entityObj) {
		return this.save(entityName, entityObj, DEFAULT_UPLOAD_STATMENT);
	}
	
	
	
	
	
	public Object save(String entityName, Object entityObj, FileUploadAndDeleteStatement stat) {
		Object obj = repositoryMapperProvider.getRepositoryMapper().get(entityName).save(entityObj);
		fileUploadAndDeleteContext(entityName, entityObj, stat);
		return obj;
	}
	
	
	
	
	public void delete(String entityName, Integer id) {
		this.delete(entityName, id, DEFAULT_DELETE_STATMENT);
	}
	
	
	
	
	public void delete(String entityName, Integer id, FileUploadAndDeleteStatement stat) {
		Object entityObj = findById(entityName, id);
		repositoryMapperProvider.getRepositoryMapper().get(entityName).deleteById(id);
		fileUploadAndDeleteContext(entityName, entityObj, stat);
	}
	
	
	
	
	
	public void update(String entityName, Object newObj, Integer id) {
		this.update(entityName, newObj, id, DEFAULT_UPLOAD_STATMENT, DEFAULT_DELETE_STATMENT);
	}
	
	
	
	
	
	public void update(String entityName, Object entityObj, Integer id, FileUploadAndDeleteStatement uploadStat, FileUploadAndDeleteStatement deleteStat) {
		PagingAndSortingRepository<Object, Integer> pagingAndSortingRepository = repositoryMapperProvider.getRepositoryMapper().get(entityName);
		
		Object foundObj = pagingAndSortingRepository.findById(id).get();
		Object clonedObj = ReflectionInvocator.invoke(foundObj, "deepClone");
		
		Entity entity = entityProvider.getEntities().get(entityName);
		ReflectionInvocator.invoke(foundObj, "override", entity.getEntityClass(), entityObj);
		
		pagingAndSortingRepository.save(foundObj);
		this.fileUploadAndDeleteContext(entityName, clonedObj, entityObj, uploadStat, deleteStat);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	private void fileUploadAndDeleteContext(String entityName, Object entityObj, FileUploadAndDeleteStatement stat) {
		this.fileUploadAndDeleteContext(entityName, (key) -> {
			Object column = ReflectionInvocator.get(entityObj, key);
			
			if (column != null) {
				if (Collection.class.isAssignableFrom(column.getClass())) {
					for (Object o : (Collection<Object>) column) {
						stat.execute(o);
					}
				} else {
					stat.execute(column);
				}
			}
		});
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	private void fileUploadAndDeleteContext(String entityName, Object entityObj, Object newObj, FileUploadAndDeleteStatement uploadStat, FileUploadAndDeleteStatement deleteStat) {
		this.fileUploadAndDeleteContext(entityName, (key) -> { 
			Object column = ReflectionInvocator.get(entityObj, key);
			Object newColumn = ReflectionInvocator.get(newObj, key);
			
			if (column == null) {
				if (newColumn != null) {
					if (Collection.class.isAssignableFrom(newColumn.getClass())) {
						for (Object newO : (Collection<Object>) newColumn) {
							uploadStat.execute(newO);
						}
					} else {
						uploadStat.execute(newColumn);
					}
				}
			} else {
				if (newColumn != null) {
					if (Collection.class.isAssignableFrom(column.getClass())) {
						Collection<Object> collection = (Collection<Object>) column;
						Collection<Object> newCollection = (Collection<Object>) newColumn;

						for (Object o : collection) {
							if (!newCollection.contains(o)) {
								deleteStat.execute(o);
							}
						}
						
						for (Object newO : newCollection) {
							uploadStat.execute(newO);
						}
					} else {
						if (!column.equals(newColumn)) {
							deleteStat.execute(column);
							uploadStat.execute(newColumn);
						}
					}
				} else {
					if (Collection.class.isAssignableFrom(column.getClass())) {
						for (Object o : (Collection<Object>) column) {
							deleteStat.execute(o);
						}
					} else {
						deleteStat.execute(column);
					}
				}
			}
		});
	}
	
	
	
	
	private void fileUploadAndDeleteContext(String entityName, FileConditionContextStatement stat) {
		Entity entity = entityProvider.getEntities().get(entityName);
		Columns columns = entity.getColumns();
		
		for (String key : columns.keySet()) {
			Column column = columns.get(key);
			FieldType fieldType = column.getFormType().getFieldType();
			if (
					fieldType == FieldType.FILE_UPLOAD_FORM ||
					fieldType == FieldType.IMAGE_UPLOAD_FORM ||
					fieldType == FieldType.MULTIPLE_FILE_UPLOAD_FORM ||
					fieldType == FieldType.MULTIPLE_IMAGE_UPLOAD_FORM) {
				stat.execute(key);
			}
		}
	}
	
	
	
	
	public boolean hasErrorsForUpdate(String entityName, BindingResult result) {
		boolean hasErrors = false;
		if (result.hasErrors()) {
			Entity entity = entityProvider.getEntities().get(entityName);
			if (entity.isUserEntity()) {
				for (Column c : entity.getColumns().values()) {
					if (result.hasFieldErrors(c.getName())) {
						FieldType t = c.getFormType().getFieldType();
						if (!(t == FieldType.USERNAME || t == FieldType.PASSWORD)) {
							hasErrors = true;
							break;
						}
					}
				}
			} else {
				hasErrors = true;
			}
		}
		return hasErrors;
	}
	
}
