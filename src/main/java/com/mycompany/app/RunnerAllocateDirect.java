package com.mycompany.app;

import java.nio.ByteBuffer;
import java.util.Random;
import java.util.Vector;


public class RunnerAllocateDirect implements Runnable {

  private final int threadId;

  public RunnerAllocateDirect(int threadId) {
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
      Random rand = new Random(System.currentTimeMillis());
      Vector<ByteBuffer> buffers = new Vector<ByteBuffer>();
      int allocatedBytes = 0;
      while(allocatedBytes < App.directMB * 1024* 1024) {
        int curAlloc = rand.nextInt(10000);
        ByteBuffer buff = ByteBuffer.allocateDirect(curAlloc);
        buffers.add(buff);
        allocatedBytes += curAlloc;
        byte[] bytes = new byte[curAlloc];
        buff.put(bytes);
      }
      System.out.printf("I allocated %d MB of allocateDirect().\n", App.directMB);
    }
}
