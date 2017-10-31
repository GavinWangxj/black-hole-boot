package cn.cincout.tech.quartzschedule.job;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.util.Date;

/**
 * Created by zhaoyu on 17-9-19.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Configuration
public class TriggerConfig {

    @Autowired
    @Qualifier("printTimeJob")
    private JobDetail printTimeJob;

    @Bean(name = "printTimeTrigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(printTimeJob);
        cronTriggerFactoryBean.setCronExpression("0/10 * * * * ?");
        return cronTriggerFactoryBean;
    }

    @Autowired
    @Qualifier("scanFileJobDetail")
    private JobDetail scanFileJobDetail;

    @Bean(name = "scanFileTrigger")
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
        simpleTriggerFactoryBean.setJobDetail(scanFileJobDetail);
        simpleTriggerFactoryBean.setBeanName("scanFileTrigger");
        simpleTriggerFactoryBean.setGroup("SimpleTrigger");
        simpleTriggerFactoryBean.setStartTime(new Date());

        simpleTriggerFactoryBean.setRepeatInterval(2000);
        return simpleTriggerFactoryBean;
    }
}
