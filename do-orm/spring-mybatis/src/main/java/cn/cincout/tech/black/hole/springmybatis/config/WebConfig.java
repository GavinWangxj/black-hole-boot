package cn.cincout.tech.black.hole.springmybatis.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-25
 * @sine 1.8
 */
@Configuration
@EnableWebMvc
@ComponentScan(value = {
        "cn.cincout.tech.black.hole.springmybatis.interfaces.web"
})
public class WebConfig extends WebMvcConfigurerAdapter {
}
