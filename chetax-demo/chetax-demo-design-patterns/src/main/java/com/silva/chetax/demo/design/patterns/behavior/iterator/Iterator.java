package com.silva.chetax.demo.design.patterns.behavior.iterator;

/**
 * 抽象迭代器
 * 
 * @author Administrator
 *
 */
public interface Iterator {
	Object first();

	Object next();

	boolean hasNext();
}
