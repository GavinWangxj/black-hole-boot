package cn.cincout.tech.springdynamicschedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;

/**
 * Created by zhaoyu on 17-8-18.
 *
 * @author zhaoyu
 * @sine 1.8
 */
//@Configuration
//@EnableScheduling
public class ScheduleCallbackConfig implements SchedulingConfigurer {

    @Bean(name = ScheduledAnnotationBeanPostProcessor.DEFAULT_TASK_SCHEDULER_BEAN_NAME)
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        // self define error handler
        //threadPoolTaskScheduler.setErrorHandler(null);
        return new ThreadPoolTaskScheduler();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(threadPoolTaskScheduler());
        taskRegistrar.addTriggerTask(new TriggerTask(new Runnable() {
            @Override
            public void run() {
                System.out.println("task implements");
            }
        }, new CronTrigger("")));
    }
}
