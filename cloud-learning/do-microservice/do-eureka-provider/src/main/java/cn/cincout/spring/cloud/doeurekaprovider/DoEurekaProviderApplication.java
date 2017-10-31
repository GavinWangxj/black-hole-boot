package cn.cincout.spring.cloud.doeurekaprovider;

import cn.cincout.spring.cloud.doeurekaprovider.domain.User;
import cn.cincout.spring.cloud.doeurekaprovider.inf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DoEurekaProviderApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DoEurekaProviderApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... strings) throws Exception {
		//userRepository.save(new User(null, "zhangsan", "zhangsan@cincout.cn"));
	}
}
