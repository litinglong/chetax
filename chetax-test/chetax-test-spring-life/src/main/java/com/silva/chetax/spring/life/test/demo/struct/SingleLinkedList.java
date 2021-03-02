package com.silva.chetax.spring.life.test.demo.struct;

public class SingleLinkedList {
	private SingleNode first;
	private SingleNode last;
	private int size;
	
	public void add(Object value){
		if(this.first == null) {
			this.first = new SingleNode();
			this.first.setValue(value);
			this.last = first;
		}else {
			SingleNode node = new SingleNode();
			node.setValue(value);
			this.last.setNext(node);
			this.last = node;
		}
		this.size++;
	}
	
	public void reverse(){
		SingleNode preNode = this.first.getNext();
		SingleNode copyNextNode = this.first.copy();
		this.first = doReverse(preNode, copyNextNode);
		this.last = copyNextNode;
	}
	private SingleNode doReverse(SingleNode preNode, SingleNode copyNextNode){
		if(!hasNext(preNode)) {
			SingleNode copyPreNode = preNode.copy();
			copyPreNode.setNext(copyNextNode);
			return copyPreNode;
		}else {
			SingleNode copyPreNode = preNode.copy();
			copyPreNode.setNext(copyNextNode);
			SingleNode nextNode = preNode.getNext();
			return doReverse(nextNode, copyPreNode);
		}
	}
	
	public SingleNode getLast(){
		return this.last;
	}
	
	public SingleNode getFirst(){
		return this.first;
	}
	
	public int size(){
		return this.size;
	}
	
	boolean hasNext(SingleNode node){
		if(node.getNext() == null) {
			return false;
		}else {
			return true;
		}
	}
	
	void print(){
		doPrint(this.first);
	}
	
	void doPrint(SingleNode node){
		System.out.println(node.getValue());
		if(!hasNext(node)) {
			return;
		}else {
			doPrint(node.getNext());
			return;
		}
	}
	
	public static void main(String[] args) {
		SingleLinkedList list = new SingleLinkedList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.print();
//		Node last = node.getLast();
//		System.out.println(node.count);
//		System.out.println(last.value);
		System.out.println("-------------");
		
		list.reverse();
		list.print();
		System.out.println("-------------");
		System.out.println("last:"+list.getLast().getValue());
		System.out.println("first:"+list.getFirst().getValue());
	}
	
}
