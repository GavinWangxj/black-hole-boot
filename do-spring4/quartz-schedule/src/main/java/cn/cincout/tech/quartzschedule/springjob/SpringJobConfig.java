package cn.cincout.tech.quartzschedule.springjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhaoyu on 17-9-15.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Configuration
@EnableScheduling
@EnableAsync
public class SpringJobConfig {

    private final static Logger LOG = LoggerFactory.getLogger(SpringJobConfig.class);

    @Bean(name = ScheduledAnnotationBeanPostProcessor.DEFAULT_TASK_SCHEDULER_BEAN_NAME)
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(10);
        // self define error handler
        //threadPoolTaskScheduler.setErrorHandler(null);
        return threadPoolTaskScheduler;
    }

    //@Scheduled(cron = "0/5 * * * * ?")
    //@Scheduled(fixedRate = 5000)
    @Async
    public void printHelloWord() { LOG.info("{} print hello world", Thread.currentThread().getName());
       try {
            LOG.info("Thread {} begin to sleep ...", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(10);
            LOG.info("Thread {} end to sleep ...", Thread.currentThread().getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //@Scheduled(cron = "0/15 * * * * ?")
    public void printHelloWord2() {
        LOG.info("{} print hello world2", Thread.currentThread().getName());
    }

}
