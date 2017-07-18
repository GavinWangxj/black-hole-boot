package cn.cincout.black.hole.dohbase.application;

import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-10
 * @sine 1.8
 */
public class InsertTaskThread implements Runnable {

    private InsertService insertService;
    private long beginCount;
    private long insertNum;
    private long beginTime;

    public InsertTaskThread(InsertService insertService, long beginCount, long insertNum, long beginTime) {
        this.insertService = insertService;
        this.beginCount = beginCount;
        this.insertNum = insertNum;
        this.beginTime = beginTime;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " insert beginning ...");
        try (Connection connection = ConnectionFactory.createConnection(InsertServiceImpl.configuration);
             Table table = connection.getTable(InsertServiceImpl.TABLE_NAME)) {
            HTable table1 = (HTable) table;
            //List<Put> putList = new ArrayList<>();
            for (long i = beginCount; i < insertNum + beginCount; i++) {
                // insertService.insert(String.valueOf(i), InsertServiceImpl.colFamily, InsertServiceImpl.colName, String.valueOf(i));
                Put put = new Put(Bytes.toBytes(i));
                put.addColumn(Bytes.toBytes(InsertServiceImpl.colFamily), Bytes.toBytes(InsertServiceImpl.colName), Bytes.toBytes(i));
                //putList.add(put);
                table.put(put);
                /*if (i % 10 == 0) {
                    table1.put(putList);
                    table1.flushCommits();
                    //System.out.println("inserted " + i);

                    putList.clear();
                }*/

                /*if (i % 5000 == 0) {
                    System.out.println("inserted " + i);
                }*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double costTime = (System.currentTimeMillis() - beginTime) / 1000.0;
        System.out.println("cost time: " + costTime);
    }
}
