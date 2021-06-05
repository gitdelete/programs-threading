package com.aman.threading.ReadWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedClass {

    ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();

    Map<String, String> hm = new HashMap();
    public String readMethod() throws InterruptedException {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Acquired read lock !!!");
            String aa = hm.get("test");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " Released read lock !!!");
            return aa;
        }finally {
            readLock.unlock();
        }


    }

    public void writeMethod() throws InterruptedException {
        writeLock.lock();
        System.out.println(Thread.currentThread().getName()+" Acquired Write lock !!!");
        hm.put("test","Aman");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+" Releasing Write lock !!!");
        writeLock.unlock();
    }
}
