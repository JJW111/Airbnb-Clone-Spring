package com.clone.airbnb.utils;

import com.clone.airbnb.admin.context.ApplicationContextProvider;

public class BeanUtils {
	
	public static Object getBean(String beanId) {
        return  ApplicationContextProvider.getApplicationContext().getBean(beanId);
    }
	
	public static Object getBean(Class<?> cls) {
        return ApplicationContextProvider.getApplicationContext().getBean(cls);
    }
	
}
