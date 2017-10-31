package cn.cincout.tech.quartzschedule.factorybean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by zhaoyu on 17-9-15.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Configuration
public class CarConfig {
    //@Bean(name = "car")
    @Bean
    public CarFactoryBean carFactoryBean() {
        return new CarFactoryBean();
    }

    @Autowired
    //@Qualifier("car")
    private Car car;

    @Autowired
    @Qualifier("&carFactoryBean")
    private CarFactoryBean carFactoryBean;

    @PostConstruct
    public void postConstruct() {
        System.out.println("car config");
        System.out.println(car);
        System.out.println(carFactoryBean);
    }
}
