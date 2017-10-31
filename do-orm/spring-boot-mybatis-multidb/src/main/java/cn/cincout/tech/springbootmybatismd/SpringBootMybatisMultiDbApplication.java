package cn.cincout.tech.springbootmybatismd;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@SpringBootApplication(
		exclude = {
		        DataSourceAutoConfiguration.class,
                MybatisAutoConfiguration.class,

		}
)
@EnableTransactionManagement
public class SpringBootMybatisMultiDbApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisMultiDbApplication.class, args);
	}

	@Autowired
	private List<PlatformTransactionManager> transactionManager;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(transactionManager.size());
	}
}
