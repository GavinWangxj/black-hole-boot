package cn.cincout.tech.doelasticjob.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zhaoyu on 17-10-11.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
public class HelloService {
    private final static Logger LOG = LoggerFactory.getLogger(HelloService.class);

    public void printCurrentTime() {
        LOG.info("current Time is {}.", new Date());
    }
}
