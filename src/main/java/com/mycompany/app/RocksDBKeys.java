package com.mycompany.app;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
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
         App.totalPutKeys ++;
         if (count == report_freq_Millions) {
           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
           LocalDateTime now = LocalDateTime.now();
           System.out.println(dtf.format(now));
           System.out.printf("Totally added: %d M keys. \n", App.totalPutKeys /1000/1000);
           count = 0;
         }
     }

    } catch (RocksDBException e) {
      System.err.println("Error in RocksDBExample: " + e.getMessage());
    }
  }
}
