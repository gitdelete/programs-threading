package com.aman.threading.threadcounting;

public class ThreadsCounting {

    public static void main(String[] args) throws InterruptedException{
          int maxCounting = 20;
          int maxThread = 3;
          for(int i=1; i<= maxThread; i ++) {
            new Thread(new CountThread(maxCounting), "Thread"+i).start();
          }

    }


}
