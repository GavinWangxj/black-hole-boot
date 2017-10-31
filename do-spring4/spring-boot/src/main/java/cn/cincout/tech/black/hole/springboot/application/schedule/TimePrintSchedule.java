package cn.cincout.tech.black.hole.springboot.application.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zhaoyu on 17-8-30.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
public class TimePrintSchedule {
    private final static Logger LOG = LoggerFactory.getLogger(TimePrintSchedule.class);

    @Scheduled(fixedDelay = 10000)
    public void printCurrentTime() {
        LOG.info("current time {}", new Date());
    }
}
