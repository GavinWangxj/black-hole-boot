package cn.cincout.black.hole.dohbase.application;

import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-10
 * @sine 1.8
 */
public class HBaseInsertTest {

    public static void main(String[] args) {
        InsertService insertService = new InsertServiceImpl();

        long insertNum = 10000;
        long begin = System.currentTimeMillis();
        //InsertTaskThread insertTaskThread = new InsertTaskThread(insertService, 0, insertNum, begin);
        //Thread thread = new Thread(insertTaskThread);
        //thread.start();
        //insertService.insert("1", "f1", "col1", "1");
        insertOne(1L);
    }

    @Test
    public void testLongToByte() {
        System.out.println(Arrays.toString(Bytes.toBytes(24)));
    }

    public static void insertOne(long i) {
        try (Connection connection = ConnectionFactory.createConnection(InsertServiceImpl.configuration); Table table = connection.getTable(InsertServiceImpl.TABLE_NAME)) {
            Put put = new Put(Bytes.toBytes(i));
            put.addColumn(Bytes.toBytes(InsertServiceImpl.colFamily), Bytes.toBytes(InsertServiceImpl.colName), Bytes.toBytes(i));
            table.put(put);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
