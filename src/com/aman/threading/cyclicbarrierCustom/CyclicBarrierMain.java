package com.aman.threading.cyclicbarrierCustom;

import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierMain {

    int cnt;
    private final AtomicInteger count;
    private final Object o= new Object();

    public CyclicBarrierMain(int cnt){
        this.cnt=cnt;
        count= new AtomicInteger(cnt);
    }

    public void await() throws InterruptedException {
        synchronized (o){
            if(count.decrementAndGet()>0) {
                o.wait();
            }else if (count.get()==0){
                o.notifyAll();
            }else {
                System.out.println("Barrier size was " +cnt+ "!! Out of scope of Cyclic Barrier to handle this thread !!! Keep Waiting now !!!");
                o.wait();
            }
        }
    }

    public void reset(){
        count.set(cnt);
    }

    public int getParties(){
        return count.get();
    }

}
