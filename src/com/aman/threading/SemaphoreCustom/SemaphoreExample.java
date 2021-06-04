package com.aman.threading.SemaphoreCustom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SemaphoreExample {

    public SemaphoreExample(SempahoreMain sm){

    }

    public static void main(String[] args) {
//        SempahoreMain sm = new SempahoreMain(3);
        SharedClass sc= new SharedClass();
        int threads=5;
        ExecutorService es = Executors.newFixedThreadPool(threads);
        for(int i=1; i<=threads; i++){
//            es.submit(new SemWorker(sm, sc));
            es.submit(new SemWorker(sc));
        }
        es.shutdown();
    }
}
