package com.mycompany.app;

import java.nio.ByteBuffer;
import java.util.Vector;


public class MyRunnable implements Runnable {

  private final int threadId;

  public MyRunnable(int threadId) {
    this.threadId = threadId;
  }

  @Override
  public void run() {
    // System.out.println("Thread " + threadId + " started");

    try {
      allocateDirect();
      Thread.sleep(App.sleepSeconds * 1000);
    } catch (Exception e) {

    }
    // Your code here
  }
    public static void allocateDirect() {
      // Allocate a direct byte buffer with capacity of 1024 bytes
      System.out.printf("Allocating %d MB of direct Buffer. \n", App.directMB);

      Vector<ByteBuffer> buffers = new Vector<ByteBuffer>();
      for (int i = 0; i < App.directMB; i++) {
        ByteBuffer buff = ByteBuffer.allocateDirect((1024 * 1024));
        buffers.add(buff);

        String data = "some data. ";
        byte[] bytes = data.getBytes();
        int loopNum = 1024*1024/data.length();
        for(int m=0; m< loopNum; m++)
           buff.put(bytes);
      }
      System.out.printf("I allocated %d MB of allocateDirect().\n", App.directMB);
    }
}
