package com.clone.airbnb.admin.form.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MapSelectBoxForm {
	boolean blank() default true;
	String defaultOption() default "";
	String method();
}
