package com.aman.threading.SemaphoreCustom;


public class SharedClass {
    SempahoreMain sm = new SempahoreMain(3);

    public void sharedMethod(){
        try {
            sm.acquire();
            System.out.println(Thread.currentThread().getName()+" Lock Acquired");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName()+" Lock Release");
            sm.release();
        }


    }
}
