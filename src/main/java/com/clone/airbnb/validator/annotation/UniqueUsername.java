package com.clone.airbnb.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.clone.airbnb.validator.UniqueUsernameValidator;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UniqueUsername {
    String message() default "아이디가 이미 존재합니다. 다른 아이디를 입력해 주세요.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}