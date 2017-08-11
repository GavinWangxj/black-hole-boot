package cn.cincout.tech.black.hole.springboot.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Created by zhaoyu on 17-8-4.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
public class AfterStarted implements ApplicationRunner, Ordered {

    private final static Logger LOG = LoggerFactory.getLogger(AfterStarted.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("ApplicationRunner, args {}.", args.getOptionNames());
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
