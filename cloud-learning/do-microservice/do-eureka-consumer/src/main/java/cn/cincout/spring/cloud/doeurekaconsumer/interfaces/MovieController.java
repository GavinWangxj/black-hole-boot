package cn.cincout.spring.cloud.doeurekaconsumer.interfaces;

import cn.cincout.spring.cloud.doeurekaconsumer.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${user.service.provider}")
    private String providerUri;

    @Value("${user.service.instance.name}")
    private String providerService;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @HystrixCommand(fallbackMethod = "findByIdFallback")
    @GetMapping(value = "/user/{id}")
    public User findById(@PathVariable(name = "id") Integer id) {
        LOGGER.info("user id is {}.", id);
        //return restTemplate.getForObject(providerUri + id, User.class);
        return restTemplate.getForObject(providerService + "user/" + id, User.class);
    }

    @GetMapping("/log-instance")
    public void findByIdRibbon() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("eureka-service-provider");
        System.out.println(serviceInstance + "xxx");
        LOGGER.info("{} : {} : {}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
    }

    @GetMapping(value = "/user-instance")
    public List<ServiceInstance> showInfo() {
        return this.discoveryClient.getInstances("eureka-service-provider");
    }

    public User findByIdFallback(int id) {
        return new User(-1, "default", "default@email.com");
    }
}
