package cn.cincout.tech.springbootmybatisjta.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by zhaoyu on 17-10-21.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource.main")
@Data
public class MainDbProperties {
    private String url;
    private String username;
    private String password;

}
