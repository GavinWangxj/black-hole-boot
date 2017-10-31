package cn.cincout.tech.springbootmybatismd.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by zhaoyu on 17-10-21.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
@ConfigurationProperties(prefix = "")
public class MainDbProperties {
    private String url;
    private String username;
    private String password;

}
