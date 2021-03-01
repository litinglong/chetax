package com.silva.chetax.spring.life.test.demo.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {
    private static int a = 0;
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        final int threadSize = 100000;
        Thread[] ts = new Thread[threadSize];
        for (int i = 0; i < threadSize; i++) {
            ts[i] = new Thread() {
                public void run() {
                    lock.lock();
                    try{
                        a++;
                    }finally{
                        lock.unlock();
                    }
                }
            };
        }
        long start = System.currentTimeMillis();
        for(Thread t:ts) {
            t.start();
        }
        for(Thread t:ts) {
            t.join();
        }
        long end = System.currentTimeMillis();
        System.out.println(a);
        System.out.println(end-start);
    }
}
