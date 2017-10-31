package cn.cincout.tech.quartzschedule.job;

import cn.cincout.tech.quartzschedule.joblistener.SimpleFileScanListener;
import org.quartz.jobs.FileScanJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoyu on 17-9-19.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Configuration
public class JobDetailConfig {
    @Bean(name = "printTimeJob")
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(PrintTimeJob.class);
        jobDetailFactoryBean.setName("printTime");
        jobDetailFactoryBean.setGroup("time");

        Map<String, Object> jobDataAsMap = new HashMap<>();
        jobDataAsMap.put("timeout", 5);
        jobDetailFactoryBean.setJobDataAsMap(jobDataAsMap);

        return jobDetailFactoryBean;
    }

    @Bean(name = "scanFileJobDetail")
    public JobDetailFactoryBean scanFileJobDetailFactoryBean() throws IOException {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setName("scanFileJob");
        jobDetailFactoryBean.setGroup("Job");
        jobDetailFactoryBean.setJobClass(FileScanJob.class);

        Map<String, String> jobDataMap = new HashMap<>();
        //ClassPathResource resource = new ClassPathResource("/scanfile.txt");
        jobDataMap.put(FileScanJob.FILE_NAME, "/home/zhaoyu/test/hello.txt");
        jobDataMap.put(FileScanJob.FILE_SCAN_LISTENER_NAME, SimpleFileScanListener.class.getName());

        jobDetailFactoryBean.setJobDataAsMap(jobDataMap);
        return jobDetailFactoryBean;
    }
}