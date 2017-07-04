package cn.cincout.black.hole.bootmvnprofiles.interfaces.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-4
 * @sine 1.8
 */

@RestController
public class HomeController {
    private final static Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Value("${self.hello}")
    private String hello;

    @GetMapping(value = "/")
    public String home() {
        LOG.info("hi, {}.", hello);
        return hello;
    }
}
