package cn.cincout.tech.black.hole.springsecurity.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-27
 * @sine 1.8
 */
@Component
public class CornScheduleTask {

    private final static Logger LOG = LoggerFactory.getLogger(CornScheduleTask.class);

    //@Scheduled(cron = "*/10 * * * * ?")
    public void cronSchedule() {
        try {
            TimeUnit.SECONDS.sleep(11);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOG.info("hello world");
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void cronOnMinuteTask() {
        LOG.info("cron on minute task");
    }

    @Scheduled(cron = "0 0 */1 * * ?")
    public void cronOnHourTask() {
        LOG.info("cron on hour task");
    }
}
