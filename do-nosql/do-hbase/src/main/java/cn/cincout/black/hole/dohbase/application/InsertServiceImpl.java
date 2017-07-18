package cn.cincout.black.hole.dohbase.application;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-10
 * @sine 1.8
 */
public class InsertServiceImpl implements InsertService {
    final static TableName TABLE_NAME = TableName.valueOf("t1");
    final static String colFamily = "f1";
    final static String colName = "col1";

    static Configuration configuration;

    static {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "VL-Dev-6097,VL-Dev-6099,VL-Dev-6100");
        //configuration.set("hbase.zookeeper.property.clientPort", "2181");
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public void insert(String rowKey, String colFamily, String col, String value) {
        try (Connection connection = ConnectionFactory.createConnection(configuration)) {
            Table table = connection.getTable(TABLE_NAME);

            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(col), Bytes.toBytes(value));
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
