package cn.cincout.tech.black.hole.springboot.config;

import cn.cincout.tech.black.hole.springboot.application.lifecycle.LifeCycleBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhaoyu on 17-8-17.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Configuration
public class RootConfig {

    @Bean
    public LifeCycleBean lifeCycleBean() {
        return new LifeCycleBean();
    }
}
