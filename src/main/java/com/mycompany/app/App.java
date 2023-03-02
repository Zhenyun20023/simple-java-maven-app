package com.mycompany.app;
import java.nio.ByteBuffer;

public class App {
    public static int directMB = 300; // 100MB;
    public static int numThreads = 30;
    public static Thread[] threads = new Thread[numThreads];

    public App() {
    }

    public static void allocateDirect() {
        // Allocate a direct byte buffer with capacity of 1024 bytes
        ByteBuffer directBuffer = ByteBuffer.allocateDirect((1024*1024* directMB));

        // Write some data to the buffer
        String data = "some data. ";
        byte[] bytes = data.getBytes();
        directBuffer.put(bytes);

        // Read the data from the buffer
        directBuffer.flip();
        byte[] buffer = new byte[bytes.length];
        directBuffer.get(buffer);
        System.out.printf("Allocated %d MB of direct Buffer. \n", directMB);
    }

    public static void startThreads() {
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(new MyRunnable(i));
            threads[i].start();
        }

        System.out.printf("started %d threads. \n", numThreads);
    }
    public static void main(String[] args) {
        allocateDirect();
        startThreads();




        //
        System.out.println("Program is running. ");
        try {
            Thread.sleep( 2000 * 1000);
        } catch (Exception e) {
        }
        System.out.println("Program exited. ");

    }
}
