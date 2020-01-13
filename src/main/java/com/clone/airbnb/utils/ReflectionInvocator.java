package com.clone.airbnb.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>Reflection 을 이용해 메소드를 호출하는 유틸리티 클래스
 * 
 * <p>JSP에서의 사용을 위해 인스턴스를 가지고 있다.
 * 
 * @author jjw
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReflectionInvocator {
	
	private static class Singleton {
		private static final ReflectionInvocator instance = new ReflectionInvocator();
	}
	
	
	
	
	
	public static ReflectionInvocator getInstance() {
		return Singleton.instance;
	}
	
	
	
	
	
	/**
	 * Java Reflection 을 이용해 get 메소드를 실행한다.
	 * 
	 * @param o
	 * @param fieldName
	 * @return
	 */
	public static Object get(Object o, String fieldName) {
		return invoke(o, "get" + WordUtils.capitalize(fieldName));
	}
	
	
	
	
	
	/**
	 * Java Reflection 을 이용해 set 메소드를 실행한다.
	 * 
	 * @param o
	 * @param fieldName
	 * @param parameterType
	 * @param arg
	 * @return
	 */
	public static Object set(Object o, String fieldName, Class<?> parameterType, Object arg) {
		return invoke(o, "set" + WordUtils.capitalize(fieldName), new Class<?>[] { parameterType }, arg );
	}
	
	
	
	
	
	/**
	 * Java Reflection 을 이용해 메소드를 실행한다.
	 * 
	 * @param obj 메소드 실행 객체
	 * @param methodName 실행할 메소드 이름
	 * @param parameterTypes 실행할 메소드의 파라미터 타입 배열
	 * @param args 실행할 메소드에 넘겨줄 인자
	 * @return 실행 메소드 반환 객체
	 */
	public static Object invoke(Object obj, String methodName, Class<?>[] parameterTypes, Object... args) {
		if (obj == null) {
			throw new NullPointerException("Null Pointer Exception! Object is Null. Method " + methodName);
		}
		
		Method m = null;
		
		try {
			Class<?> cls = obj.getClass();
			m = cls.getMethod(methodName, parameterTypes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		Object o = null;
		
		try {
			o = m.invoke(obj, args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return o;
	}
	
	
	
	
	
	/**
	 * <p>매개변수를 하나만 가지는 메소드를 호출할 때 사용한다.
	 * 
	 * @param obj
	 * @param methodName
	 * @param parameterType
	 * @param arg
	 * @return
	 */
	public static Object invoke(Object obj, String methodName, Class<?> parameterType, Object arg) {
		return invoke(obj, methodName, new Class<?>[] { parameterType }, arg);
	}
	
	
	
	
	
	/**
	 * Java Reflection 을 이용해 인자 없는 메소드를 실행한다.
	 * 
	 * @param obj 메소드 실행 객체
	 * @param methodName 실행할 메소드 이름
	 * @return 실행 메소드 반환 객체
	 */
	public static Object invoke(Object obj, String methodName) {
		return invoke(obj, methodName, null);
	}
	
	
	
	
	
	/**
	 * <p>객체 없이 클래스로 메소드를 호출한다.
	 * 
	 * <p>객체가 필요없는 메소드 호출시 사용한다.
	 * 
	 * @param cls
	 * @param methodName
	 * @param parameterTypes
	 * @param args
	 * @return
	 */
	public static Object invoke(Class<?> cls, String methodName, Class<?>[] parameterTypes, Object... args) {
		Method m = null;
		
		try {
			m = cls.getMethod(methodName, parameterTypes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		Object o = null;
		
		try {
			o = m.invoke(null, args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return o;
	}
	
	
	
	
	
	/**
	 * <p>객체 없이 클래스로 메소드를 호출한다.
	 * 
	 * <p>객체가 필요없는 클래스 메소드(static) 호출시 사용한다.
	 * 
	 * @param cls
	 * @param methodName
	 * @return
	 */
	public static Object invoke(Class<?> cls, String methodName) {
		return invoke(cls, methodName, null);
	}
	
	
	
	
	
	/**
	 * <p>인자로 넘어온 클래스의 인스턴스를 강제로 생성한다.
	 * 
	 * @param cls
	 * @return
	 */
	public static Object forceNewInstance(Class<Object> cls) {
		try {
			Constructor<Object> constructor = cls.getDeclaredConstructor();
			constructor.setAccessible(true);
			Object obj = constructor.newInstance();
			constructor.setAccessible(false);
			return obj;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	public static Method getDeclaredMethod(Class<Object> cls, String methodName, Class<?>[] parameterTypes) {
		try {
			return cls.getDeclaredMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	public static Method getDeclaredMethod(Class<Object> cls, String methodName, Class<?> parameterType) {
		return getDeclaredMethod(cls, methodName, new Class<?>[] { parameterType });
	}
	
	
	
	public static Method getDeclaredMethod(Class<Object> cls, String methodName) {
		try {
			return cls.getDeclaredMethod(methodName);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	public static Object invoke(Object obj, Method method, Object... args) {
		try {
			return method.invoke(obj, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	public static Object invoke(Method method) {
		try {
			return method.invoke(null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	
}
