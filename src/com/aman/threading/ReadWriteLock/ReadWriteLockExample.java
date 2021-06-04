package com.aman.threading.ReadWriteLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

public class ReadWriteLockExample {
    volatile static int check = 0;
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();

        ExecutorService es = Executors.newFixedThreadPool(5);
        IntStream.rangeClosed(1,1).forEach(a -> es.submit(new WriteWorker(lock)));

        IntStream.range(1,10).forEach(a -> es.submit(new ReadWorker(lock)));

        es.shutdown();
    }

    static class ReadWorker implements  Runnable{

        ReadWriteLock lock;

        public ReadWorker(ReadWriteLock lock){
            this.lock=lock;

        }

        @Override
        public void run() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(check);
        }

    }


    static class WriteWorker implements  Runnable {

        ReadWriteLock lock;

        public WriteWorker(ReadWriteLock lock) {

            this.lock=lock;
        }

        @Override
        public void run() {
            System.out.println("Write");
            IntStream.range(0,20).forEach(a->{
                check=a;
            });

        }

    }
}


