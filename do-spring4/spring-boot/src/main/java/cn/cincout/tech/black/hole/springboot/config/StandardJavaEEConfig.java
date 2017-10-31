package cn.cincout.tech.black.hole.springboot.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhaoyu on 17-8-24.
 *
 * @author zhaoyu
 * @sine 1.8
 */
//@Configuration
@ServletComponentScan(
        basePackages = {" cn.cincout.tech.black.hole.springboot.config"}
)
public class StandardJavaEEConfig {
}
