package com.aman.threading.countdownLatchCustom;

import java.util.concurrent.atomic.AtomicInteger;

public class CountdownLatchMain {

    private AtomicInteger count;
    private Object o = new Object();

    public CountdownLatchMain(int cnt) {
        count = new AtomicInteger(cnt);
    }

    public  void countDown() {
        synchronized (o){
        count.decrementAndGet();
        if (count.get() <= 0) {
            o.notifyAll();
        }
    }
    }

    public void await() throws InterruptedException {
        synchronized (o){
        while (count.get() > 0) {
            o.wait();
        }
    }
}
}
