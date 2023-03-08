package com.mycompany.app;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;


public class App {
    public static int numThreads = 50;
    public static int directMB = 10; // 10MB, each thread;
    public static int mmapMB = 400; // mmap file;
    public static Thread[] threads = new Thread[numThreads];

    public static int result = 0; // output this;

    public App() {
    }

    public static void allocateMMAP() {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("/tmp/file.400MB", "rw");
            FileChannel channel = file.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
            System.out.printf("mmap file size: %d MB\n", channel.size()/1024/1024);
            channel.position(0);
            String str = "some data.";
            for(int i=0; i<mmapMB*1024*1024/str.length(); i++) {
                buffer.put(str.getBytes());
                buffer.force();
            }
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
        allocateMMAP();
        //
        System.out.println("Program is running. ");
        for(int i=0; i<1000*100; i++) {
            try {
                Thread.sleep(10 * 1000);
            } catch (Exception e) {
            }
            System.out.printf("NumThreads= %d, each %d MB; The result is %d .\n", numThreads, directMB, result);
        }
    }
}
