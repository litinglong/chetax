package com.silva.chetax.spring.life.test.demo.concurrent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo {

	public static void main(String[] args) {
//		ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
//		List<String> list = new CopyOnWriteArrayList<String>();
//		Set<String> set = new CopyOnWriteArraySet<String>();
//		LinkedHashMap<K, V>
//		LinkedHashSet<E>
//		hashMapTest();
//		concurrentHashMapTest();
//		countDownLatchTest();
//		cyclicBarrierTest();
//		volatileTest();
//		switchTest();
//		stringTest();
//		ValueObject.staticBlockTest1();
//		Demo.staticBlockTestWhenClassForName();
//		threadPoolExecutorTest();
		checkCycleTest();
	}
	
	private static class Node<T>{
		T value;
		Node next;
	}
	static void checkCycleTest() {
		Node<?> node1 =new Node();
		Node<?> node2 =new Node();
		Node<?> node3 =new Node();
		Node<?> node4 =new Node();
		node1.next=node2;
		node2.next=node3;
		node3.next=node4;
		node4.next=node3;
		System.out.println(checkCycle(node1));
	}
	static int checkCycle(Node<?> node) {
		Node<?> tmpNode=node;
		Map<Node<?>,Integer> map =new HashMap<Node<?>,Integer>();
		int index = 0;
		map.put(tmpNode, index);
		while((tmpNode = tmpNode.next) != null) {
			if(map.containsKey(tmpNode)) {
				return map.get(tmpNode);
			}
			map.put(tmpNode, ++index);
		}
		return -1;
	}

	static Object o = new Object();

	static void threadPoolExecutorTest() {
		new Runnable() {
			public void run() {
				System.out.println("Runnable");
			}
		};
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(20);
		ExecutorService executorService = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, queue);
		for (int i = 0; i < 26; i++) {
			final int x = i;
			executorService.submit(new Runnable() {
				@Override
				public void run() {
//					List<String> list = new ArrayList(20000000);
//					synchronized (o) {
//							System.out.println(x + ":queue.size:" + queue.size());
//							while(true) {
//								list.add("你是我家1232131231232113212312");
//							}
//					}
					try {
						Thread.sleep(300*1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
				}
			});
		}

	}

	static void staticBlockTestWhenClassForName() {
		try {
			String classpath = "com.silva.chetax.spring.life.test.demo.concurrent.ValueObject";
//			Class clazz = Class.forName(classpath);
			// initialize参数设置为false表示加载类时不调用static语句块和ClassLoader的loadClass也是如此，之后在创建实例的时候才会调用static块
			Class clazz = Class.forName(classpath, false, ClassLoader.getSystemClassLoader());
//			Class clazz = ClassLoader.getSystemClassLoader().loadClass("com.silva.chetax.spring.life.test.demo.concurrent.ValueObject");
//			clazz.newInstance();
			clazz.getConstructor().newInstance();
//			ValueObject clazz = new ValueObject();
			System.out.println(clazz);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void stringTest() {
		String s2 = "abc"; // 这个写法跟其他基础类型一样，原理是自动进行了包装
		String s1 = new String(s2);
		System.out.println(s1 == s2); // 比较内存地址，Object的equals默认是比较这个，但是其他类可能会重写equals方法。
		System.out.println(s1.equals(s2)); // 比较内容或者关键属性是否一样
		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add(new String("a"));
		set.add("b");
		System.out.println(set);
	}

	static void switchTest() {
		int x = 1;
		switch (3) {
		case 1:
			x = x * 2;
		case 2:
			x = x * 2;
		case 3:
			x = x * 2;
		case 4:
			x = x * 2;
		case 5:
			x = x * 2;
		default:
			x = x * 2;
		}
		System.out.println(x);
	}
	
	static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	static void hashMapTest() {
		for (int i = 0; i < 1000; i++) {
			final int x = i;
			new Thread() {
				@Override
				public void run() {
					map.put(x, x);
				}
			}.start();
		}
		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hashMapTest:" + map);
	}
	static Map<Integer, Integer> cmap = new ConcurrentHashMap<Integer, Integer>();
	static void concurrentHashMapTest() {
		for (int i = 0; i < 1000; i++) {
			final int x = i;
			new Thread() {
				@Override
				public void run() {
					cmap.put(x, x);
				}
			}.start();
		}
		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("concurrentHashMapTest:" + cmap);
	}

	static void countDownLatchTest() {
		int targetCount = 5;
		CountDownLatch countDownLatch = new CountDownLatch(targetCount);
		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 500; i++) {
					try {
						Thread.sleep(1000);
						System.out.println("countDown：" + (i + 1));
						countDownLatch.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				final int x = targetCount;
				try {
					System.out.println("CountDownLatch 等待计数:" + x);
					countDownLatch.await();
					System.out.println("CountDownLatch 达到等待的计数:" + x);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	static void cyclicBarrierTest() {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
			int count = 0;

			@Override
			public void run() {
				System.out.println("龙珠已集齐:" + (++count));

			}
		});
		for (int i = 0; i < 10; i++) {
			final int x = i;
			new Thread() {
				@Override
				public void run() {
					try {
						System.out.println("龍珠S:" + (x + 1));
						cyclicBarrier.await();
						System.out.println("龍珠E:" + (x + 1));
					} catch (InterruptedException | BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
		}
	}
	
	volatile static Integer res = 0;
	static void volatileTest() {
		Runnable r = new Runnable() {
			public void run() {
				try {
					Thread.sleep(3 * 1000);
					res = 10;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		new Thread(r).start();
		int index = 0;
		while (res == 0) {
			try {
				System.out.println("休眠中:" + index++);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("休眠结束:" + index++);
	}
}
