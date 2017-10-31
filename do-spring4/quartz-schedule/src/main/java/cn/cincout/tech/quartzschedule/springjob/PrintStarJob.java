package cn.cincout.tech.quartzschedule.springjob;

import cn.cincout.tech.quartzschedule.job.PrintTimeJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by zhaoyu on 17-9-15.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
public class PrintStarJob {

    private final static Logger LOG = LoggerFactory.getLogger(PrintTimeJob.class);

    //@Scheduled(fixedRate = 8000)
    public void printStar() {
        LOG.info("********** ********** **********");
    }
}
