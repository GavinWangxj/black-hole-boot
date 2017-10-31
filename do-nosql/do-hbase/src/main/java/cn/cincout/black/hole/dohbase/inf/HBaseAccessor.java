package cn.cincout.black.hole.dohbase.inf;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Scan;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhaoyu on 17-7-19.
 *
 * @author zhaoyu
 * @date 17-7-19
 * @sine 1.8
 */
public abstract class HBaseAccessor {
    private Configuration configuration;

    private ExecutorService executorService;

    private Set<ParamSet<TableName, Scan, ResultsExtractor<Map>>> paramSetSet;

    public HBaseAccessor() {
        executorService = Executors.newCachedThreadPool();
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public Connection getConnection() {
        try {
            return ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> void addSearchParam(TableName tableName, Scan scan, ResultsExtractor<Map> extractor) {
        if (paramSetSet == null) {
            paramSetSet = new HashSet<>();
        }
        paramSetSet.add(new ParamSet<>(tableName, scan, extractor));
    }

    public Set<ParamSet<TableName, Scan, ResultsExtractor<Map>>> getParamSetSet() {
        return paramSetSet;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
