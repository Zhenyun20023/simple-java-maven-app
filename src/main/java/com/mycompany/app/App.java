package com.mycompany.app;
import java.nio.ByteBuffer;
import java.util.Vector;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;


public class App {
    public static int numThreads = 100;
    public static int directMB = 10; // 10MB, each thread;
    public static int mmapMB = 100; // mmap file;
    public static int sleepSeconds = 5 * 3600;
    public static Thread[] threads = new Thread[numThreads];

    public App() {
    }

    public static void allocateMMAP() {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("/tmp/file.400MB", "rw");
            FileChannel channel = file.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
            System.out.printf("mmap file size: %d MB\n", channel.size()/1024/1024);
            int offset = mmapMB * 1024 * 1024; //
            channel.position(offset);
            buffer.put("some data".getBytes());
            buffer.force();
            file.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
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
        startThreads();
        allocateMMAP();
        //
        System.out.println("Program is running. ");
        try {
            Thread.sleep( sleepSeconds * 1000);
        } catch (Exception e) {
        }
        System.out.println("Program exited. ");

    }
}
