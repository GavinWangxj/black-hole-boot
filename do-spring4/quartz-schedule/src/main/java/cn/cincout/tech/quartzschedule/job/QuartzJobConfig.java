package cn.cincout.tech.quartzschedule.job;

import cn.cincout.tech.quartzschedule.joblistener.*;
import org.quartz.*;
import org.quartz.impl.matchers.KeyMatcher;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by zhaoyu on 17-9-14.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Configuration
public class QuartzJobConfig implements InitializingBean {


    @Autowired
    private SpringBootJobFactory springBootJobFactory;

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        // must call it
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Autowired
    private GlobalJobListener globalJobListener;

    @Autowired
    private GlobalTriggerListener globalTriggerListener;

    @Autowired
    private PrintTimeJobListener printTimeJobListener;

    @Autowired
    private SimpleScheduleListener scheduleListener;

    @Autowired
    @Qualifier("printTimeTrigger")
    private CronTrigger printTimeTrigger;

    @Autowired
    @Qualifier("scanFileTrigger")
    private SimpleTrigger scanFileTrigger;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws Exception {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(printTimeTrigger, scanFileTrigger);
        schedulerFactoryBean.setQuartzProperties(quartzProperties());

        // add global listener
        //schedulerFactoryBean.setGlobalJobListeners(globalJobListener);
        //schedulerFactoryBean.setGlobalTriggerListeners(globalTriggerListener);
        //schedulerFactoryBean.setSchedulerListeners(scheduleListener);

        //schedulerFactoryBean.setJobFactory(springBootJobFactory);
        return schedulerFactoryBean;
    }

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SimpleFileScanListener simpleFileScanListener;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (scheduler != null) {
            ListenerManager listenerManager = scheduler.getListenerManager();
            if (listenerManager == null) {
                System.out.println("fuck null.");
            }
            else {
                // add job listener
                listenerManager.addJobListener(printTimeJobListener, KeyMatcher.keyEquals(new JobKey("printTime", "time")));
                // add file scan listener
                scheduler.getContext().put(SimpleFileScanListener.class.getName(), simpleFileScanListener);
            }
        }
    }
}
