package com.silva.chetax.demo.design.patterns.creational.prototype;

public class PrototypeObject implements Cloneable {

	private String id;
	protected String type;
	
	private PrototypeObject ref;
	
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

	public PrototypeObject getRef() {
		return ref;
	}

	public void setRef(PrototypeObject ref) {
		this.ref = ref;
	}

	public Object clone() {
		Object clone = null;
		try {
			clone = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}

	@Override
	public String toString() {
		return "PrototypeObject [id=" + id + ", type=" + type + ", ref=" + ref + "]";
	}

	
	
}
