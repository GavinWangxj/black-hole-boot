package cn.cincout.black.hole.dohbase.inf;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zhaoyu on 17-7-19.
 *
 * @author zhaoyu
 * @date 17-7-19
 * @sine 1.8
 */
public class HBaseTemplate extends HBaseAccessor implements HBaseOperation {
    @Override
    public <T> T find() {
        Map<String, Map> resultMap = new ConcurrentHashMap<>();
        CountDownLatch countDownLatch = new CountDownLatch(getParamSetSet().size());

        for (ParamSet<TableName, Scan, ResultsExtractor<Map>> paramSet : getParamSetSet()) {
            getExecutorService().execute(new QueryTask(
                    paramSet, getConnection(), resultMap, countDownLatch
            ));
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class QueryTask implements Runnable {
        private ParamSet<TableName, Scan, ResultsExtractor<Map>> paramSet;
        private Connection connection;
        private Map<String, Map> resultMap;
        private CountDownLatch latch;

        public QueryTask(ParamSet<TableName, Scan, ResultsExtractor<Map>> paramSet,
                         Connection connection,
                         Map<String, Map> resultMap,
                         CountDownLatch latch) {
            this.paramSet = paramSet;
            this.connection = connection;
            this.resultMap = resultMap;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Table table = connection.getTable(paramSet.table);
                ResultScanner resultScanner = table.getScanner(paramSet.scan);
                Map map = paramSet.extractor.extractData(resultScanner);
                // TODO

                latch.countDown();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
