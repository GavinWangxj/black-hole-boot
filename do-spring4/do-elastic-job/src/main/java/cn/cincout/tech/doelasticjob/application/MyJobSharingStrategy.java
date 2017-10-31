package cn.cincout.tech.doelasticjob.application;

import com.dangdang.ddframe.job.lite.api.strategy.JobInstance;
import com.dangdang.ddframe.job.lite.api.strategy.JobShardingStrategy;

import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyu on 17-10-12.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class MyJobSharingStrategy implements JobShardingStrategy {
    @Override
    public Map<JobInstance, List<Integer>> sharding(List<JobInstance> list, String s, int i) {
        return null;
    }
}
