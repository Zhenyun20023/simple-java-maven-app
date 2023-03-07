package com.mycompany.app;

import java.nio.ByteBuffer;
import java.util.Vector;


public class RunnerAllocate implements Runnable {

  private final int threadId;

  public RunnerAllocate(int threadId) {
    this.threadId = threadId;
  }

  @Override
  public void run() {
    // System.out.println("Thread " + threadId + " started");

    try {
      allocateDirect();
      for(int i=0; i< 1000 *100; i++) {
        Thread.sleep(5 * 1000);
        App.result += i;
        App.result = App.result % 1000;
      }
    } catch (Exception e) {

    }
    // Your code here
  }
  public static void allocateDirect() {
    // Allocate a direct byte buffer with capacity of 1024 bytes
    System.out.printf("Allocating %d MB of direct Buffer. \n", App.directMB);

    Vector<ByteBuffer> buffers = new Vector<ByteBuffer>();
    for (int i = 0; i < App.directMB; i++) {
      ByteBuffer buff = ByteBuffer.allocate((1024 * 1024));
      buffers.add(buff);

      String data = "some data. ";
      byte[] bytes = data.getBytes();
      int loopNum = 1024*1024/bytes.length;
      for(int m=0; m< loopNum; m++)
        buff.put(bytes);
    }
    System.out.printf("I allocated %d MB of allocate().\n", App.directMB);
  }
}
