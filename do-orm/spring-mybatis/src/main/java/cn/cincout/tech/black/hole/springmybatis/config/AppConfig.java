package cn.cincout.tech.black.hole.springmybatis.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-25
 * @sine 1.8
 */
@Configuration
@ComponentScan(
        basePackages = {"org.zzy.spring4cn.cincout.tech.black.hole.springmybatis"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
        }
)
public class AppConfig {
}
