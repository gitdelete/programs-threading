package com.aman.threading.threadcounting;

public class ThreadsCounting {

    private  static int maxCounting = 20;
    private static int maxThread = 3;

    public static void main(String[] args) throws InterruptedException{

        for(int i=1; i<= maxThread; i ++) {
            new Thread(new CountThread(maxCounting), "Thread"+i).start();
        }

    }


}
