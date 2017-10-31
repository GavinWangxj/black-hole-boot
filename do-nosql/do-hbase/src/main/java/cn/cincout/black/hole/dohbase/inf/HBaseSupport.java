package cn.cincout.black.hole.dohbase.inf;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by zhaoyu on 17-7-31.
 *
 * @author zhaoyu
 * @date 17-7-31
 * @sine 1.8
 */
public abstract class HBaseSupport {
    private static Configuration configuration;

    private static Connection connection;

    private String zookeeperQuorum = "localhost";


    public HBaseSupport() {
        this(null);
    }

    public HBaseSupport(String zookeeperQuorum) {
        if (zookeeperQuorum != null) {
            this.zookeeperQuorum = zookeeperQuorum;
        }

        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", zookeeperQuorum);
        try {
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
