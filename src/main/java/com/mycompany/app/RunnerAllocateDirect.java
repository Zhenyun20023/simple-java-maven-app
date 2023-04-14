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

    //    RocksDBKeys.testAddingKeys(App.numDBKeysM, threadId);
    //   System.out.println("done with rocksdb allocations.");

    while (true) {
      allocateDirect();
    }
  }

  public void allocateDirect() {
    Random rand = new Random(System.currentTimeMillis());
    Vector<ByteBuffer> buffers = new Vector<ByteBuffer>();

    int allocatedBytes = 0;
    while (allocatedBytes < App.directMB * 1024 * 1024) {
      int curAlloc = rand.nextInt(10000);
      ByteBuffer buff = ByteBuffer.allocateDirect(curAlloc);
      buffers.add(buff);

      allocatedBytes += curAlloc;
      byte[] bytes = new byte[curAlloc];
      buff.put(bytes);
    }

    System.out.printf("I allocated %d MB of allocateDirect().\n", App.directMB);

    try {
      int delay = rand.nextInt(10);
      Thread.sleep(delay * 1000);
    } catch (Exception e) {
    }

    for (ByteBuffer bf : buffers) {
      bf.clear();
      bf = null;
    }

    System.out.printf("I released %d MB of allocateDirect().\n", App.directMB);
  }
}
