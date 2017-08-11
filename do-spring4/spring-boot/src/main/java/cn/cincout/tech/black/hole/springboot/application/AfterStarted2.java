package cn.cincout.tech.black.hole.springboot.application;

import cn.cincout.tech.black.hole.springboot.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by zhaoyu on 17-8-4.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
@Order(9)
public class AfterStarted2 implements CommandLineRunner {
    private final static Logger LOG = LoggerFactory.getLogger(AfterStarted2.class);

    @Autowired
    private AppConfig appConfig;

    @Override
    public void run(String... args) throws Exception {
        LOG.info("CommandLineRunner, args {}.", args);

        LOG.info("app config {}.", appConfig.toString());
    }
}
