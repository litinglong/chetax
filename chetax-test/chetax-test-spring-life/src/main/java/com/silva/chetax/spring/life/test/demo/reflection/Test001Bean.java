package com.silva.chetax.spring.life.test.demo.reflection;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@Test001Annotation(AnnotationPosition.CLAZZ)
public class Test001Bean {
	private String value1;
	String value2;
	protected String value3;
	@Test001Annotation(AnnotationPosition.FIELD)
	public String value4;
	
	@Test001Annotation(AnnotationPosition.CONSTRUCTOR)
	public Test001Bean() {
		
	}
	
	private Test001Bean(String value1) {
		super();
		this.value1 = value1;
	}

	protected Test001Bean(String value1, String value2) {
		super();
		this.value1 = value1;
		this.value2 = value2;
	}

	
	Test001Bean(String value1, String value2, String value3) {
		super();
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
	}

	public Test001Bean(String value1, String value2, String value3, String value4) {
		super();
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.value4 = value4;
	}

	@Test001Annotation(AnnotationPosition.METHOD)
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	public String getValue4() {
		return value4;
	}
	public void setValue4(String value4) {
		this.value4 = value4;
	}
	
	protected void test1(String test) {
	}
	private void test2(String test) {
	}
	void test3(String test) {
	}
	
	public String smethod(String name) {
		return name + ":"+toString();
	}
	
	@Override
	public String toString() {
		return "Test001Bean [" + getFormateString() +"]";
	}
	
	private String getFormateString(){
		String[] keys = {"value1","value2","value3","value4"};
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("value1", value1);
		map.put("value2", value2);
		map.put("value3", value3);
		map.put("value4", value4);
		String s = "";
		for (String key : keys) {
			Object value = map.get(key);
			if(value!=null && !"".equals(value)) {
				s += key + "=" + map.get(key) + ",";
			}
		}
		if(s.length()>0) {
			s = s.substring(0, s.lastIndexOf(","));
		}
		return s;
	}
	
}
