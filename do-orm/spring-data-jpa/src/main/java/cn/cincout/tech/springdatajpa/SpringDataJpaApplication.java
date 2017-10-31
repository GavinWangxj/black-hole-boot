package cn.cincout.tech.springdatajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication(exclude = {CacheAutoConfiguration.class})
public class SpringDataJpaApplication implements ApplicationRunner {

    @Autowired
	public PlatformTransactionManager platformTransactionManager;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(platformTransactionManager.getClass().getName());
    }
}
