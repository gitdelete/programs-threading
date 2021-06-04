package com.aman.threading.ReentrantLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ReentrantLockExample {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        ExecutorService es = Executors.newFixedThreadPool(5);
        IntStream.range(1,5).forEach(a -> es.submit(new ReentrantWorker(lock)));

        es.shutdown();
    }

    static class ReentrantWorker implements  Runnable{

        ReentrantLock lock;
        public ReentrantWorker(ReentrantLock lock){
            this.lock=lock;
        }

        @Override
        public void run() {
            boolean isDone = false;
            System.out.println(Thread.currentThread().getName() + "  Arrived!!!!!");
            while(!isDone) {
                if (lock.tryLock()) {         // tryLock return boolean(true) if lock is available
                    System.out.println(Thread.currentThread().getName() + "  Lock is available  using tryLock()");

                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " Locked !!!!");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " Unlocking !!!!");
                    isDone=true;
                    lock.unlock();
                    lock.unlock();          // Lock unlocked twice(once for tryLock() and once for lock.lock()) else other threads wont be able to take the lock
                    checkExtrinsic();
                } else {
                    System.out.println(Thread.currentThread().getName() + " Try Locked Failed !!!!");
                }
            }
        }

        private void checkExtrinsic() {
                System.out.println(Thread.currentThread().getName()+"  Check Extrinsic !!!!!");
        }
    }

}
