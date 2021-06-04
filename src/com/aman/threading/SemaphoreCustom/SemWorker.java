package com.aman.threading.SemaphoreCustom;

public class SemWorker implements  Runnable{
//    SempahoreMain sm ;
    SharedClass sc;

//    public SemWorker(SempahoreMain sm, SharedClass sc){
    public SemWorker( SharedClass sc){
//        this.sm= sm;
        this.sc= sc;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
//            sc.sharedMethod(sm);
        sc.sharedMethod();
    }
}
