package cn.cincout.tech.quartzschedule.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Created by zhaoyu on 17-9-15.
 *
 * @author zhaoyu
 * @sine 1.8
 */
//@Component
public class QuartzStart {

    private final static Logger LOG = LoggerFactory.getLogger(QuartzStart.class);

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void quartzStart() throws SchedulerException {
        //Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //scheduler.start();
        scheduler.scheduleJob(jobDetail(), trigger());
    }

    public JobDetail jobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("author", "zhangzhaoyu");

        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .setJobData(jobDataMap)
                .build();
        return jobDetail;
    }

    public Trigger trigger() {
        ScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(scheduleBuilder)
                .build();

        return trigger;
    }

    public static class MyJob implements Job {

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            JobDetail jobDetail = context.getJobDetail();
            LOG.info("jobName {}", jobDetail.getKey().toString());
            LOG.info("job class {}", jobDetail.getJobClass().getName());

            JobDataMap jobDataMap = jobDetail.getJobDataMap();
            jobDataMap.forEach((key, value) -> {
                LOG.info("{} : {}", key, value);
            });

            LOG.info("MyJob, current time {}", new Date().toString());
        }
    }
}
