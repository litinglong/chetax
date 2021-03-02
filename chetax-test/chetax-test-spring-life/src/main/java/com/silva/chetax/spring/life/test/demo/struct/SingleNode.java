package com.silva.chetax.spring.life.test.demo.struct;

public class SingleNode {
	private SingleNode next;
	private Object value;
	public SingleNode copy(){
		SingleNode node = new SingleNode();
		node.value = this.value;
		return node;
	}
	public SingleNode getNext() {
		return next;
	}
	public void setNext(SingleNode next) {
		this.next = next;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	
}
