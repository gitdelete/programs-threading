package com.aman.threading.BlockingQueueCustom;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingQueueExample {

    public BlockingQueueExample(){

    }

    public static void main(String[] args) throws InterruptedException {

        int threads=15;
        BlockingQueueMain bq = new BlockingQueueMain(10);
        ExecutorService esProducer = Executors.newFixedThreadPool(threads);
        ExecutorService esConsumer = Executors.newFixedThreadPool(threads);
        for (int i=1; i<=threads; i++) {
            esConsumer.submit(new QueueConsumer(bq));
            Thread.sleep(2);
            esProducer.submit(new QueueProducer(bq));

        }

        esConsumer.shutdown();
        esProducer.shutdown();
    }

    static class QueueProducer implements Runnable{
        BlockingQueueMain bq;

        QueueProducer(BlockingQueueMain bq){
            this.bq=bq;
        }
        @Override
        public void run() {
            try {
                bq.enqueue("Aman_"+ Math.random());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class QueueConsumer implements Runnable{
        BlockingQueueMain bq;

        QueueConsumer(BlockingQueueMain bq){
            this.bq=bq;
        }
        @Override
        public void run() {
            try {
                String element = (String)bq.dequeue();
                System.out.println(element);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
