package cn.cincout.tech.quartzschedule.joblistener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by zhaoyu on 17-9-19.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
public class PrintTimeJobListener implements JobListener {
    private final static Logger LOG = LoggerFactory.getLogger(PrintTimeJobListener.class);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().getName();
        LOG.info("PrintTimeJobListener {} is about to be executed.", jobName);
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().getName();
        LOG.info("PrintTimeJobListener {} is about to be executed.", jobName);
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        String jobName = context.getJobDetail().getKey().toString();
        LOG.info("{} PrintTimeJobListener jobWasExecuted.", jobName);
    }
}
