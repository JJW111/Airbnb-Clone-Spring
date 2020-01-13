package com.clone.airbnb.utils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AnnotationScanner {
	
	/**
	 * scanPackage 에서 annotationClass 가 붙은 클래스들을 찾아 클래스 이름을 키로 해당 클래스를 값으로 가지는 맵을 반환한다.
	 * 
	 * @param scanPackage
	 * @return
	 */
	public static <T> List<Class<T>> findAnnotatedClasses(String scanPackage, Class<? extends Annotation> annotationClass) {
        ClassPathScanningCandidateComponentProvider provider = createComponentScanner(annotationClass);
        List<Class<T>> classes = new ArrayList<Class<T>>();
        
        try {
	        for (BeanDefinition beanDef : provider.findCandidateComponents(scanPackage)) {
	        	@SuppressWarnings("unchecked")
				Class<T> cls = (Class<T>) Class.forName(beanDef.getBeanClassName());
	        	classes.add(cls);
	        }
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
        
        return classes;
    }
 
	
    private static ClassPathScanningCandidateComponentProvider createComponentScanner(Class<? extends Annotation> annotationClass) {
        ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(false) {
        	@Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return beanDefinition.getMetadata().isInterface() || beanDefinition.getMetadata().isConcrete();
            }
        };
        provider.addIncludeFilter(new AnnotationTypeFilter(annotationClass));
        return provider;
    }
}
