package com.silva.chetax.spring.life.test.demo.reflection;

import java.lang.annotation.Annotation;
import java.lang.constant.ClassDesc;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

public class Demo {
	public static void main(String[] args) {
		String classPath = "com.silva.chetax.spring.life.test.demo.reflection.Test001Bean";
		try {
			Class<?> clazz = Class.forName(classPath);
			Optional<ClassDesc> optional = clazz.describeConstable();
			if(optional.isPresent()) {
				Annotation[] annotations = clazz.getAnnotations();
				printAnnotationInfo(annotations);
				ClassDesc classDesc = optional.get();
				System.out.println("类:ClassDesc:" + classDesc);
				
				Method[] method1 = clazz.getMethods();
				System.out.println("函数:Methods:" + method1.length);
				for (Method method : method1) {
					Annotation[] annotations2 = method.getAnnotations();
					printAnnotationInfo(annotations2);
				}
				
//				Method[] method2 = clazz.getMethods();
//				System.out.println("函数:DeclaredMethods:" + method2.length);
//				for (Method method : method2) {
//					Annotation[] annotations2 = method.getAnnotations();
//					printAnnotationInfo(annotations2);
//				}
				
				// 获取public权限的构造函数
				Constructor[] cs1 = clazz.getConstructors();
				System.out.println("构造器:Constructors:" + cs1.length);
				for (Constructor<?> constructor : cs1) {
					int parameterCount = constructor.getParameterCount();
					System.out.println("参数:ParameterCount:" + parameterCount);
					Annotation[] annotations2 = constructor.getAnnotations();
					printAnnotationInfo(annotations2);
					if(parameterCount == 0) {
						Object object1 = constructor.newInstance();
						System.out.println("实例:" + object1);
						Field[] field2 = clazz.getDeclaredFields();
						System.out.println("属性:DeclaredFields:" + field2.length);
						for (Field field : field2) {
							Annotation[] annotations3 = field.getAnnotations();
							printAnnotationInfo(annotations3);
							if(!field.canAccess(object1)) {
								field.setAccessible(true);
							}
							int max=100,min=1;
						    int ran2 = (int) (Math.random()*(max-min)+min); 
							field.set(object1, ran2 + "");
						}
						System.out.println("实例:赋值:" + object1);
						String methodName = "smethod";
						Method method = clazz.getMethod(methodName, String.class);
						Object result = method.invoke(object1, methodName);
						System.out.println("方法:执行:" + result);
					}else {
						@SuppressWarnings("rawtypes")
						Class[] clazzs = constructor.getParameterTypes();
						System.out.println("参数:ParameterLength:" + clazzs.length);
					}
				}
				
				
				// 获取全部权限的构造函数
				Constructor[] cs2 = clazz.getDeclaredConstructors();
				System.out.println("构造器:DeclaredConstructors:" + cs2.length);
				
				// 必须当前类必须具有构造函数的调用权限,否则抛出异常IllegalArgumentException
				Object object = clazz.newInstance();
				System.out.println("实例:" + object);
				Field[] field1 = clazz.getFields();
				System.out.println("属性:Fields:" + field1.length);
				for (Field field : field1) {
					Annotation[] annotations3 = field.getAnnotations();
					printAnnotationInfo(annotations3);
					if(!field.canAccess(object)) {
						field.setAccessible(true);
					}
					int max=100,min=1;
				    int ran2 = (int) (Math.random()*(max-min)+min);
					field.set(object, ran2 + "");
				}
				System.out.println("实例:赋值:" + object);
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void printAnnotationInfo(Annotation[] annotations) {
		for (Annotation annotation : annotations) {
			if(annotation instanceof Test001Annotation) {
				Test001Annotation test001Annotation = (Test001Annotation)annotation;
				System.out.println("注解:Test001Annotation:" + test001Annotation.value().getCode());
			}
			
		}
	}
}
