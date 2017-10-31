package cn.cincout.tech.springdynamicschedule.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by zhaoyu on 17-8-16.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
public class ScheduleService {

    private final static Logger LOG = LoggerFactory.getLogger(ScheduleService.class);

    @Scheduled(fixedDelay = 5000)
    public void task() {
        LOG.warn("hello world");
    }
}
