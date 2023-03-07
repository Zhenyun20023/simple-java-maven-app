package com.mycompany.app;

import java.nio.ByteBuffer;
import java.util.Random;
import java.util.Vector;


public class RunnerRandomDirect implements Runnable {

  private final int threadId;

  public RunnerRandomDirect(int threadId) {
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
    Random random = new Random();
    while(true) {
      int rand = random.nextInt(10000);
      ByteBuffer buff = ByteBuffer.allocateDirect(rand);
      buffers.add(buff);
      try {
        Thread.sleep(2 * 1000);
      } catch (Exception e) {
      }
    }
    // no op
  }
}
