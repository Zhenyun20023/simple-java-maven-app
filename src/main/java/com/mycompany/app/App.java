package com.mycompany.app;
import java.nio.ByteBuffer;
import java.util.Vector;


public class App {
    public static int directMB = 300; // 100MB;
    public static int numThreads = 30;
    public static Thread[] threads = new Thread[numThreads];

    public App() {
    }

    public static void allocateDirect() {
        // Allocate a direct byte buffer with capacity of 1024 bytes
        System.out.printf("Allocating %d MB of direct Buffer. \n", directMB);

        Vector<ByteBuffer> buffers = new Vector<ByteBuffer>();
        for(int i=0; i< directMB; i++) {
            ByteBuffer buff = ByteBuffer.allocateDirect((1024 * 1024));
            buffers.add(buff);

            String data = "some data. ";
            byte[] bytes = data.getBytes();
            buff.put(bytes);
            System.out.print(".");
        }
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
