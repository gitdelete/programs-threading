package com.aman.threading.SemaphoreCustom;

import java.util.concurrent.atomic.AtomicInteger;

public class SempahoreMain {

    int count;
    private final Object o = new Object();
    AtomicInteger i ;
    public SempahoreMain(int count){
        this.count=count;
        this.i = new AtomicInteger(count);
    }

    public void acquire() throws InterruptedException {
        synchronized (o){
            while(i.get() == 0){
                o.wait();
            }
            i.decrementAndGet();
        }
    }

    public void release(){
        synchronized (o){
            while(i.get() == count ){
                System.out.println("Bad Release !!!!");
            }
            i.incrementAndGet();
            o.notifyAll();
        }
    }
}
