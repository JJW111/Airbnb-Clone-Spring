package com.clone.airbnb.admin.form.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JoinManyForm {
	String formName() default "";
	boolean blank() default true;
	String field();
	String defaultOption() default "";
	Class<?> repository();
	String findAllMethod() default "";
}
