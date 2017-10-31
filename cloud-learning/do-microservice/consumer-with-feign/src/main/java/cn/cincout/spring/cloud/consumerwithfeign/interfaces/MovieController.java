package cn.cincout.spring.cloud.consumerwithfeign.interfaces;

import cn.cincout.spring.cloud.consumerwithfeign.config.UserFeignClient;
import cn.cincout.spring.cloud.consumerwithfeign.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-5
 * @sine 1.8
 */
@RestController
public class MovieController {
    private final static Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Value("${user.service.provider}")
    private String providerUri;

    @Value("${user.service.instance.name}")
    private String providerService;

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping(value = "/user/{id}")
    public User findById(@PathVariable(name = "id") Integer id) {
        LOGGER.info("user id is {}.", id);
        //return userFeignClient.findById(id);
        return userFeignClient.getById(id);
    }

}
