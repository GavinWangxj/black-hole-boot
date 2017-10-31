package cn.cincout.tech.doelasticjob.application;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by zhaoyu on 17-10-11.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class TimePrintJob implements SimpleJob {

    private final static Logger LOG = LoggerFactory.getLogger(TimePrintJob.class);

    @Resource
    private HelloService helloService;

    public static int count = 0;

    @Override
    public void execute(ShardingContext shardingContext) {
        String param = shardingContext.getJobParameter();
        int sharingItem = shardingContext.getShardingItem();
        String sharingParam = shardingContext.getShardingParameter();
        int totalCount = shardingContext.getShardingTotalCount();

        LOG.info("job param {}, sharingItem {}, sharingParam {}, sharingTotalCount {}", param, sharingItem, sharingParam, totalCount);
        helloService.printCurrentTime();
        count++;
        if (count % 2 == 0) {
            throw new RuntimeException("count is " + count);
        }
    }

}
