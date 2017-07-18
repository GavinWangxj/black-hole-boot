package cn.cincout.black.hole.dohbase.inf;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-1-4
 * @sine 1.0
 */
public class HBaseClient {
    private final static Logger LOG = LoggerFactory.getLogger(HBaseClient.class);
    private final static String STR_ENCODER = "utf-8";

    private  Configuration configuration;
    private  Connection connection;
    private  Admin admin;



    public HBaseClient() {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "localhost");
        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void createTable(String tableName, String[] familyNames) throws IOException {
        HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));

        LOG.info("begin create table...");
        if (!admin.tableExists(tableDescriptor.getTableName())) {
            Arrays.stream(familyNames).forEach(familyName -> {
                HColumnDescriptor family = new HColumnDescriptor(familyName).setCompressionType(Compression.Algorithm.GZ);
                tableDescriptor.addFamily(family);
            });
        }

        admin.createTable(tableDescriptor);
        LOG.info("success create table...");
    }

    public void deleteTable(String tableName) throws IOException {
        TableName tb = TableName.valueOf(tableName);
        if (admin.tableExists(tb)) {
            admin.disableTable(tb);
            admin.deleteTable(tb);
            LOG.info("delete table success, {}", tableName);
        }
    }

    public void putRow(String tableName, String rowKey, String colFamily, String col, String val) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(rowKey.getBytes());
        put.addColumn(colFamily.getBytes(), col.getBytes(), val.getBytes());
        table.put(put);
        LOG.info("insert data success.");
        table.close();
    }

    public void deleteRow(String tableName, String rowKey, String colFamily, String col) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(rowKey.getBytes());
        // delete all data of the col family
        // delete.addFamily(colFamily.getBytes());
        // delete all data of the col
        // delete.addColumn(colFamily.getBytes(), col.getBytes());
        // delete the row
        table.delete(delete);
        table.close();
        LOG.info("delete success, {}", rowKey);
    }

    public void getData(String tableName, String rowKey, String colFamily, String col) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(rowKey.getBytes());
        get.addColumn(colFamily.getBytes(), col.getBytes());
        Result result = table.get(get);
        printCell(result);
    }

    public ResultScanner scanTable(String tableName, String familyName, String col, String startRow, String stopRow) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        if (StringUtils.isNotEmpty(familyName) && StringUtils.isNotEmpty(col)) {
            scan.addColumn(familyName.getBytes(), col.getBytes());
        }
        if (StringUtils.isNotEmpty(familyName) && StringUtils.isEmpty(col)) {
            scan.addFamily(familyName.getBytes());
        }
        if (StringUtils.isNotEmpty(startRow)) {
            scan.setStartRow(startRow.getBytes());
        }
        if (StringUtils.isNotEmpty(stopRow)) {
            scan.setStopRow(stopRow.getBytes());
        }
        ResultScanner resultScanner = table.getScanner(scan);
        return resultScanner;
    }

    public List<String> listAllTableNames() throws IOException {
        HTableDescriptor[] tableDescriptors = admin.listTables();
        List<String> tables = new ArrayList<>();
        for (HTableDescriptor descriptor : tableDescriptors) {
            tables.add(descriptor.getTableName().getNameAsString());
        }
        return tables;
    }

    public String describeTable(String tableName) {
        try {
            return admin.getTableDescriptor(TableName.valueOf(tableName)).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void increment(String tableName, String rowKey, String familyName, String  col) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        table.incrementColumnValue(Bytes.toBytes(rowKey), Bytes.toBytes(familyName), Bytes.toBytes(col), 1L);
    }

    public void increment(String tableName, String rowKey, String familyName, String  col, int value) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        table.incrementColumnValue(Bytes.toBytes(rowKey), Bytes.toBytes(familyName), Bytes.toBytes(col), value);
    }

    public void printCell(Result result) {
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            System.out.println("RowName: \t"+new String(CellUtil.cloneRow(cell))+" ");
            System.out.println("Timetamp: \t"+cell.getTimestamp()+" ");
            System.out.println("column Family: \t"+new String(CellUtil.cloneFamily(cell))+" ");
            System.out.println("row Name: \t"+new String(CellUtil.cloneQualifier(cell))+" ");
            System.out.println("value: \t"+Bytes.toLong(CellUtil.cloneValue(cell))+" ");
        }
    }

}
