package com.silva.chetax.spring.life.test.demo.test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.springframework.util.StringUtils;

public class MyClassLoader extends ClassLoader {

    private String classDir; // 文件目录，例如:"file:/today/javadir/src/main/java/"
    
    public static void main(String[] args) {
    	Class clazz = MyClassLoader.getClassFromJavaFile("F:/WDR", "com.Test");
    	Method[] ms =clazz.getMethods();
    	for (Method method : ms) {
    		System.out.println(method.getName());
		}
    	try {
			Object object = clazz.newInstance();
			try {
				
				Method m  = clazz.getDeclaredMethod("test", null);
				try {
					m.invoke(object, null);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(object);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    @Override
    public Class<?> findClass(String name) {
        String realPath = classDir + name.replace(".","/") + ".class"; //class文件的真实路径
        byte[] cLassBytes = null;
        Path path = null;


            try {
				path = Paths.get(new URI(realPath));
				cLassBytes = Files.readAllBytes(path);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

        Class clazz = defineClass(name, cLassBytes, 0, cLassBytes.length);//调用父类的defineClass方法
        return clazz;
    }

    public MyClassLoader(String classDir) {
        this.classDir = "file:/".concat(classDir).concat("/src/main/java/"); //拼接 “file:/”前缀
    }
    
    
    public static Class getClassFromJavaFile(String dirPath,  String pakagePath)  {
        if  (StringUtils.isEmpty(dirPath) || StringUtils.isEmpty(pakagePath)) //校验参数是否为空
            return null;

        String pakageDir = pakagePath.replaceAll("\\.","/"); // 将路径中的 . 替换为 / , 替换后的pakageDir = com/chenyf/entity
        String filePath = dirPath.concat( "/src/main/java/" ).concat( pakageDir ).concat(".java"); // src/main/java为java文件的特定目录

        //编译
        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        int compilationResult = javac.run(null, null, null, filePath);

        if  (compilationResult != 0) //compilationResult == 0,说明编译成功，在Java文件的同目录下会生成相应的class文件
            throw new IllegalArgumentException("编译失败");

        Class<?> clazz = null;
        try {

            MyClassLoader loader = new MyClassLoader(dirPath); //使用自定义ClassLoader
            clazz = loader.findClass(pakagePath);

        } catch (Exception e) {
            e.printStackTrace();
            //throw e;
        }
        return clazz;
    }

}
