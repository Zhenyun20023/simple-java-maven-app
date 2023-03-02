package com.mycompany.app;

public class MyRunnable implements Runnable {

  private final int threadId;

  public MyRunnable(int threadId) {
    this.threadId = threadId;
  }

  @Override
  public void run() {
   // System.out.println("Thread " + threadId + " started");

    try {
      Thread.sleep( 1000 * 1000);
    } catch (Exception e) {

    }
    // Your code here
  }
}
