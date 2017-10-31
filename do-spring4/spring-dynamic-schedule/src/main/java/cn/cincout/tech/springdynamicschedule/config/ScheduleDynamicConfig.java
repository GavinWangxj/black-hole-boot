package cn.cincout.tech.springdynamicschedule.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.Resource;
import java.util.Vector;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhaoyu on 17-8-16.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Configuration
@EnableScheduling
public class ScheduleDynamicConfig implements InitializingBean {

    public static Vector<ScheduledFuture<?>> scheduledFutureVector = new Vector<>();

    @Bean(name = ScheduledAnnotationBeanPostProcessor.DEFAULT_TASK_SCHEDULER_BEAN_NAME)
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        // self define error handler
        //threadPoolTaskScheduler.setErrorHandler(null);
        return new ThreadPoolTaskScheduler();
    }

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Override
    public void afterPropertiesSet() throws Exception {
        ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.schedule(new Task("001"), new CronTrigger( "0/30 * * * * ?"));
        scheduledFutureVector.add(scheduledFuture);
    }

    public static class Task implements Runnable {
        private final static Logger LOG = LoggerFactory.getLogger(Task.class);
        private String taskName;

        public Task(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            LOG.info("task begin..., task name is " + taskName);
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOG.info("task end...");
        }
    }
}
