package cn.cincout.tech.black.hole.springboot.application.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by zhaoyu on 17-8-4.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
public class BasicApplicationListener implements ApplicationListener<ApplicationEvent> {
    private final static Logger LOG = LoggerFactory.getLogger(BasicApplicationListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        LOG.info("event is {}.", event.getClass().getCanonicalName());
    }
}
