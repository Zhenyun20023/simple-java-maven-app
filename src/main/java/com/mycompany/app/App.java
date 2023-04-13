package com.mycompany.app;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class App {
    public static int numThreads = 60;
    public static int directMB = 1; // 10MB, each thread;
    public static int mmapMB = 500; // mmap file;
    public static Thread[] threads = new Thread[numThreads];

    public static int result = 0; // output this;

    public static int numDBKeysM = 3; // millions of rocksdb keys;
    public static int totalPutKeys = 0; // total added keys;

    public App() {
    }

    public static void allocateMMAP() {
        try {
            // create a random access file object
            RandomAccessFile file = new RandomAccessFile("/tmp/example.dat", "rw");
            file.setLength(mmapMB * 1024 * 1024);
            // get a file channel from the file
            FileChannel channel = file.getChannel();

            // map the file into memory
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, file.length());

            for (int i = 0; i < mmapMB * 1000*1000  - 10; i += 10)
            {
                buffer.position(i);
                buffer.putInt(i);
            }

            // close the file and channel
            channel.close();
            file.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void startThreads() {
        for (int i = 0; i < numThreads; i++) {

            threads[i] = new Thread(new RunnerAllocateDirect(i));
            threads[i].start();
        }

        System.out.printf("started %d threads. \n", numThreads);
    }
    public static void main(String[] args) {
        startThreads();
        // allocateMMAP();

        System.out.println("Program is running. ");

        for(int i=0; i<1000*100; i++) {
            try {
                Thread.sleep(10 * 1000);
            } catch (Exception e) {
            }
        }


    }
}
