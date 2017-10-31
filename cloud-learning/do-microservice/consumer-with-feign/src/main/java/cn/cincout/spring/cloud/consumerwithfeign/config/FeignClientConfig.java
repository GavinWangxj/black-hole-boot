package cn.cincout.spring.cloud.consumerwithfeign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhaoyu on 17-7-27.
 *
 * @author zhaoyu
 * @date 17-7-27
 * @sine 1.8
 */
@Configuration
public class FeignClientConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
