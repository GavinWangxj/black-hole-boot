package cn.cincout.black.hole.dohbase.application;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-10
 * @sine 1.8
 */
public interface InsertService {
    void insert(String rowKey, String colFamily, String col, String value);
}
