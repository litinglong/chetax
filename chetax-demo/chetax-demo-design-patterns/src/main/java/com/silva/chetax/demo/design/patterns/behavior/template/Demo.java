package com.silva.chetax.demo.design.patterns.behavior.template;

import java.io.File;
import java.io.FilenameFilter;

public class Demo {

	public static void main(String[] args) {
//		File parentFile = new File("F:\\project_hub\\chetax\\chetax-sso\\chetax-sso-client\\src\\main\\java\\com\\ltl");
//		System.out.println(parentFile.getPath());
//		System.out.println(parentFile);
//		System.out.println(file);
//		System.out.println(file.isDirectory());
//		System.out.println(file.getName());
//		File file2 =file.getAbsoluteFile();
//		File[] childFiles = parentFile.listFiles();
//		System.out.println(file2);
//		System.out.println(file2);
//		parentFile.renameTo(new File("F:\\project_hub\\chetax\\chetax-sso\\chetax-sso-client\\src\\main\\java\\com\\silva"));
//	System.out.println("F:\\project_hub\\chetax\\chetax-sso\\chetax-sso-client\\src\\main\\java\\com\\silva".replace("silva", "ltl"));
	rename(new File("F:\\project_hub\\chetax"));
//	File file = new File("F:\\project_hub\\chetax");
//	File[] files = file.listFiles();
//	System.out.println(files);
	}
	
	public static void rename(File file){
		if(file.isDirectory()) {
			//rename
//			if("ltl".equals(file.getName())) {
//				String s = file.getPath();
//				s = s.replace("ltl", "silva");
//				file.renameTo(new File(s));
//			}
			if("matrix".equals(file.getName())) {
				String s = file.getPath();
				s = s.replace("matrix", "chetax");
				file.renameTo(new File(s));
			}
			File[] files = file.listFiles();
			if(files!=null&&files.length>0) {
				for (File file2 : files) {
					if(file2.isDirectory()) {
						rename(file2);
					}
				}
			}
			
		}
		
	}

}
