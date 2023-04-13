package com.mycompany.app;

import java.util.Random;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class RocksDBKeys {
  public static int report_freq_Millions = 1*1000 * 1000;

  public static void testAddingKeys(int numM, int id) {
    RocksDB.loadLibrary();
    String dbPath = "./rocksdb/rocksdb." + Integer.toString(id);
    Random rand = new Random(System.currentTimeMillis());
    int count = 0;

    try (final Options options = new Options().setCreateIfMissing(true);
        final RocksDB db = RocksDB.open(options, dbPath)) {

     while(true) {
         int keyid = rand.nextInt(numM * 1000 * 1000);
         byte[] key1 = Integer.toString(keyid).getBytes();

         int value = rand.nextInt(numM * 1000 * 1000);
         byte[] value1 = Integer.toString(value).getBytes();
         db.put(key1, value1);

         count ++;
         if (count == report_freq_Millions) {
           System.out.printf("Just added %d M keys. \n", report_freq_Millions /1000/1000);
           count = 0;
         }

         int randnum = rand.nextInt(10000);
         if(randnum == 2 ) {
           try {
             Thread.sleep( 1 * 1000);
           } catch (Exception e) {
           }
         }
     }

    } catch (RocksDBException e) {
      System.err.println("Error in RocksDBExample: " + e.getMessage());
    }
  }
}
