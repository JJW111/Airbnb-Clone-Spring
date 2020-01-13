package com.clone.airbnb.common;

import java.lang.annotation.Annotation;
import java.util.List;

import com.clone.airbnb.utils.AnnotationScanner;

import lombok.Getter;

@Getter
public class ClassLoader<T> {
	
	private List<Class<T>> classes;
	
	
	/**
	 * <p>basePackage에서 annotationClass 어노테이션이 붙은 클래스를 로드한다.
	 * 
	 * @param basePackage 클래스를 로드할 페키지명
	 * @param annotationClass 로드할 클래스에 붙은 어노테이션 클래스
	 */
	public ClassLoader(String basePackage, Class<? extends Annotation> annotationClass) {
		this.classes = AnnotationScanner.<T>findAnnotatedClasses(basePackage, annotationClass);
	}
	
}
