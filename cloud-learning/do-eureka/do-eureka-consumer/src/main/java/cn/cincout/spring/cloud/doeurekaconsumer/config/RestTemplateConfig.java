package cn.cincout.spring.cloud.doeurekaconsumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-5
 * @sine 1.8
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    // add load balanced for spring cloud ribbon
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
