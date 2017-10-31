package cn.cincout.spring.cloud.doeurekaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker // or @EnableHystrix
public class DoEurekaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoEurekaConsumerApplication.class, args);
	}
}
