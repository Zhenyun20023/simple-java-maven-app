package com.mycompany.app;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;


public class RocksDBKeys {

  public static void testAddingKeys(int numM) {
    RocksDB.loadLibrary();
    String dbPath = "/tmp/rocksdb";
    try (final Options options = new Options().setCreateIfMissing(true);
        final RocksDB db = RocksDB.open(options, dbPath)) {


      for(int i=0; i<numM * 1000 * 1000; i++) {
        byte[] key1 = Integer.toString(i).getBytes();
        byte[] value1 = Integer.toString(i+2).getBytes();
        db.put(key1, value1);
      }

      System.out.printf("-----------  Added %d M keys------------------\n", numM);


    } catch (RocksDBException e) {
      System.err.println("Error in RocksDBExample: " + e.getMessage());
    }
  }
}
