package cn.cincout.tech.springdynamicschedule.web;

import cn.cincout.tech.springdynamicschedule.config.ScheduleDynamicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by zhaoyu on 17-8-16.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@RestController
public class TaskController {
    private final static Logger LOG = LoggerFactory.getLogger(TaskController.class);

    //@Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @RequestMapping(value = "/stop")
    public Map<String, Object> stop() {
        Map<String, Object> result = new HashMap<>();

        for (ScheduledFuture future : ScheduleDynamicConfig.scheduledFutureVector) {
            if (future.isDone()) {
                LOG.info("task is done");
            }
            else if (future.isCancelled()) {
                LOG.info("task is cancelled");
            }
            else {
                future.cancel(false);
                LOG.info("task just be cancelled.");
                //ScheduleConfig.scheduledFutureVector.remove(future);
            }
        }

        result.put("status", "ok");
        return result;
    }

    @RequestMapping(value = "/start")
    public Map<String, Object> start() {
        Map<String, Object> result = new HashMap<>();

        ScheduleDynamicConfig.Task task = new ScheduleDynamicConfig.Task("002");
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(task, new CronTrigger( "0/30 * * * * ?"));
        ScheduleDynamicConfig.scheduledFutureVector.add(future);

        result.put("status", "ok");
        return result;
    }
}
