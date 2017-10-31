package cn.cincout.black.hole.dohbase.inf;

import org.apache.hadoop.hbase.client.ResultScanner;

/**
 * Created by zhaoyu on 17-7-19.
 *
 * @author zhaoyu
 * @date 17-7-19
 * @sine 1.8
 */
public interface ResultsExtractor<T> {
    T extractData(ResultScanner results) throws Exception;
}
