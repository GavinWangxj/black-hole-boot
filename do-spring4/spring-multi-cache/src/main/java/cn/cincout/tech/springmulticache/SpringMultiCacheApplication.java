package cn.cincout.tech.springmulticache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class SpringMultiCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMultiCacheApplication.class, args);
	}
}
