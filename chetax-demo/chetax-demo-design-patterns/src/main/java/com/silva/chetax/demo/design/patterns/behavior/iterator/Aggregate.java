package com.silva.chetax.demo.design.patterns.behavior.iterator;

/**
 * 抽象聚合
 * 
 * @author Administrator
 *
 */
public interface Aggregate {
	public void add(Object obj);

	public void remove(Object obj);

	public Iterator getIterator();
}
