package com.aman.threading.countdownLatchCustom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchExample {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);
        CountdownLatchMain countdownLatch= new CountdownLatchMain(5);

        for (int a= 1; a<=5; a++){
            executor.submit(new CountdownWorkers(countdownLatch));
        }
        countdownLatch.await();
        executor.shutdown();
        System.out.println("Eurekkkaaaa!!!!!!!!!!!");
    }

    static class CountdownWorkers implements Runnable{

        CountdownLatchMain cl ;

        CountdownWorkers(CountdownLatchMain cl){
            this.cl=cl;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+ " Waiting for all threads to Come here!!!");
            cl.countDown();
        }

    }



}
