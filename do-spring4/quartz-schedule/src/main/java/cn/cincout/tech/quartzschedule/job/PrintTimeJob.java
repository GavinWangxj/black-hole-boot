package cn.cincout.tech.quartzschedule.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhaoyu on 17-9-14.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public class PrintTimeJob extends QuartzJobBean {
    private final static Logger LOG = LoggerFactory.getLogger(PrintTimeJob.class);

    private Random random = new Random();

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        long number = random.nextLong();
        LOG.info("random long is {}", number);
        //if (number % 5 == 0) {
            //throw new RuntimeException("%5 == 0");
            try {
                LOG.info("Thread {} begin to sleep ...", Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(60);
                LOG.info("Thread {} end to sleep ...", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        //}
        LOG.info(new SimpleDateFormat("yyyy-MM-hh HH:MM:ss").format(new Date()));
    }
}
