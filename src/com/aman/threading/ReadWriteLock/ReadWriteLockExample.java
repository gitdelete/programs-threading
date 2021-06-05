package com.aman.threading.ReadWriteLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

public class ReadWriteLockExample {
    volatile static int check = 0;
    public static void main(String[] args) {

        SharedClass sc = new SharedClass();

        ExecutorService es = Executors.newFixedThreadPool(5);
        IntStream.rangeClosed(1,1).forEach(a -> es.submit(new WriteWorker(sc)));

        IntStream.range(1,10).forEach(a -> es.submit(new ReadWorker(sc)));

        es.shutdown();
    }

    static class ReadWorker implements  Runnable{

        SharedClass sc;
        public ReadWorker(SharedClass sc){

            this.sc= sc;
        }

        @Override
        public void run() {
            String aa = null;
            try {
                aa = sc.readMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(aa);
        }

    }


    static class WriteWorker implements  Runnable {

        SharedClass sc;
        public WriteWorker(SharedClass sc) {
            this.sc= sc;
        }

        @Override
        public void run() {
            IntStream.range(0,15).forEach(a ->{
                try {
                    sc.writeMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } );
        }

    }
}


