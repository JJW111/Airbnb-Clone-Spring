package com.clone.airbnb.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.clone.airbnb.validator.ExistsUsernameValidator;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsUsernameValidator.class)
public @interface ExistsUsername {
    String message() default "존재하지 않는 유저입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}