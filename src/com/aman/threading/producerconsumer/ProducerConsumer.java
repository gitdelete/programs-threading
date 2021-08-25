package com.aman.threading.producerconsumer;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {

    public static void main(String[] args) {


        List<Number> list = new ArrayList<>(1);
        Thread t1 = new Thread(new Producer(list));
        Thread t2 = new Thread(new Consumer(list));
        t1.start();
        t2.start();

    }


     static  class Producer implements Runnable{

        List<Number> list = null;
        Producer(List<Number> list){
            this.list=list;
        }

         @Override
         public void run(){
             try {
                 produce();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }

         public void produce() throws InterruptedException {
             while(true){
                 synchronized (list) {
                     while (list.size() >= 1) {
                         list.wait();
                     }
                     list.add(Math.random());
                     list.notifyAll();
                 }
             }
         }
     }

     static class Consumer implements Runnable{

         List<Number> list = null;
         Consumer(List<Number> list){
             this.list=list;
         }

        @Override
        public void run(){
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void consume() throws InterruptedException {
            while (true) {
                synchronized (list) {
                    while (list.size() <= 0) {
                        list.wait();
                    }

                    System.out.println(list.get(0));
                    list.remove(0);
                    list.notifyAll();
                }
            }
        }
     }
}
