package com.aman.threading.cyclicbarrierCustom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);
        CyclicBarrierMain cyclicBarrier= new CyclicBarrierMain(4);

        for (int a= 1; a<=4; a++){
            executor.submit(new CountdownWorkers(cyclicBarrier));
        }
        executor.shutdown();
        System.out.println("Eurekkkaaaa!!!!!!!!!!!");
    }

    static class CountdownWorkers implements Runnable{

        CyclicBarrierMain cb ;

        CountdownWorkers(CyclicBarrierMain cb){
            this.cb=cb;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+ "Waiting for other threads to cross the barrier !!! ");
            try {
                Thread.sleep(10000);
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("All threads reached, Barrier Crossed !!!");

        }

    }



}
