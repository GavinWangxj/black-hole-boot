package cn.cincout.tech.springsecurityboot.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.stereotype.Component;

/**
 * Created by zhaoyu on 17-8-10.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
public class SecurityEventListener implements ApplicationListener<AbstractAuthenticationEvent> {
    private final static Logger LOG = LoggerFactory.getLogger(SecurityEventListener.class);

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent event) {
        LOG.info("event is {}", event.getClass().getCanonicalName());
    }
}
