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

            while(count.get()>0) {
                count.set(count.get()-1);
                if (count.get()==0){
                    o.notifyAll();
                }else {
                    o.wait();
                }
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
