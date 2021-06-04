package com.aman.threading.BlockingQueueCustom;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueueMain {

    private final Object o = new Object();
    private Queue<String> queue;
    private int size;

    public BlockingQueueMain(int size){
        this.size=size;
        this.queue = new LinkedList<>();
    }

    //1* and 2* statement order doesnot matter as there will be Object O's monitor lock,
    // so any other thread cant enter dequeue if its in enqueue
    public void enqueue(String element) throws InterruptedException {
        synchronized (o) {
            System.out.println("Enqueue--Start--"+Thread.currentThread().getName());
            while (size == queue.size()) {
                System.out.println("Enqueue Waitinggggg!!!");
                o.wait();
            }
            if(queue.size()==0){        //1*
                o.notifyAll();
            }
            queue.add(element);         //2*
            System.out.println("Enqueue--End--"+Thread.currentThread().getName()+" "+queue.size());
        }
    }

    public Object dequeue() throws InterruptedException {
        Object oj;
        synchronized (o){
            System.out.println("Dequeue--Start--"+Thread.currentThread().getName()+" "+queue.size());
            while(queue.size() == 0){
                System.out.println("Dequeue Waitinggggg!!!");
                o.wait();
            }
            if(queue.size()==size){
                o.notifyAll();
            }
            oj=queue.poll();
        }
        System.out.println("Dequeue--End--"+Thread.currentThread().getName()+" "+queue.size());
        return oj;
    }

}
