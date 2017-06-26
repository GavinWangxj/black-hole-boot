package cn.cincout.tech.black.hole.springmybatis;

import cn.cincout.tech.black.hole.springmybatis.config.AppConfig;
import cn.cincout.tech.black.hole.springmybatis.config.MybatisConfig;
import cn.cincout.tech.black.hole.springmybatis.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-25
 * @sine 1.8
 */
public class SpringMybatisWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {AppConfig.class, MybatisConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
