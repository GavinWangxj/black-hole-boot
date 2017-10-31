package cn.cincout.tech.doelasticjob.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by zhaoyu on 17-10-11.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Configuration
@ImportResource(locations = {"classpath:elastic-job.xml"})
public class ElasticJobConfig {
}
