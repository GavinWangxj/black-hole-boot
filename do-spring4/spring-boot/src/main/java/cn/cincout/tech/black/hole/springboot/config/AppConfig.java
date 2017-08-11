package cn.cincout.tech.black.hole.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 17-8-4.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
@ConfigurationProperties(prefix = "self")

@Data
public class AppConfig {

    private List<String> servers = new ArrayList<>();
    private boolean workEnable;
    private int number;

}
