package cn.cincout.spring.cloud.consumerwithfeign.config;

import cn.cincout.spring.cloud.consumerwithfeign.domain.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhaoyu on 17-7-24.
 *
 * @author zhaoyu
 * @date 17-7-24
 * @sine 1.8
 */
@FeignClient(name = "eureka-service-provider", configuration = {FeignClientConfig.class})
public interface UserFeignClient {
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    User findById(@PathVariable("id") int id);

    @GetMapping(value = "/users/{id}")
    User getById(@PathVariable("id") int id);

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    User save(User user);
}
