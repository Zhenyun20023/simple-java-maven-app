package com.mycompany.app;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Random;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.BlockBasedTableConfig;
import org.rocksdb.Cache;
public class RocksDBKeys {
  public static int report_freq_Millions = 1*1000 * 1000;

  public static void testAddingKeys(int numM, int id) {
    RocksDB.loadLibrary();
    BlockBasedTableConfig tableOptions = new BlockBasedTableConfig();
    tableOptions.setBlockCacheSize(1073741824L); // 1 GB block cache size

    Options options = new Options();
    options.setMaxOpenFiles(-1); // -1 means use the OS default
    options.setCreateIfMissing(true);
    options.setAllowMmapWrites(true);
    options.setAllowMmapReads(true);
    options.setTableFormatConfig(tableOptions);
    options.setWriteBufferSize(267108864L); // 264 MB write buffer size
    options.setMaxWriteBufferNumber(3); // 3 write buffers
    options.setMinWriteBufferNumberToMerge(2);
    options.setTargetFileSizeBase(268435456L); // 256 MB
    options.setTargetFileSizeMultiplier(1);
    options.setNumLevels(2);

    String dbPath = "./rocksdb/rocksdb." + Integer.toString(id);
    Random rand = new Random(System.currentTimeMillis());
    int count = 0;

    try (final RocksDB db = RocksDB.open(options, dbPath)) {

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
