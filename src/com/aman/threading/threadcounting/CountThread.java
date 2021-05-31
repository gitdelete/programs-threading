package com.aman.threading.threadcounting;

import java.util.concurrent.atomic.AtomicInteger;

public class CountThread implements Runnable{

    private static int maxCounting=20;
    private static AtomicInteger count= new AtomicInteger(1);
    private static AtomicInteger nextThreadCount=new AtomicInteger(1);
    private static String nextThread = "Thread"+nextThreadCount;
    private static Object o= new Object();
    public CountThread(int maxCounting) {
//        this.maxCounting=maxCounting;
    }

    @Override
    public void run() {
        counting();
    }

    private void counting() {
        while( count.get()<maxCounting){
//           System.out.println(nextThread+" "+ count);
//           System.out.println(Thread.currentThread().getName().equals(nextThread));
            synchronized (o) {
                if (Thread.currentThread().getName().equals(nextThread)) {
                    System.out.println(nextThread + "    " + count);
                    if (nextThreadCount.get() == 3) {
                        nextThreadCount.set(1);
                    }else{
                        nextThreadCount.incrementAndGet();
                    }

                    nextThread = "Thread" + nextThreadCount;
                    count.incrementAndGet();
                    o.notifyAll();
                }else{
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
