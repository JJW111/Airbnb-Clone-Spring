package com.clone.airbnb.admin.form.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TextForm {
	boolean blank() default true;
	int maxlength() default 255;
	String placeholder() default "";
}
