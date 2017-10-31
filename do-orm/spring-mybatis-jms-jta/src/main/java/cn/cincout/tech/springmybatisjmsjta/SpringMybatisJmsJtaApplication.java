package cn.cincout.tech.springmybatisjmsjta;

import cn.cincout.tech.springmybatisjmsjta.service.MessageService;
import org.apache.activemq.command.ActiveMQQueue;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class})
public class SpringMybatisJmsJtaApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringMybatisJmsJtaApplication.class, args);
	}

	@Autowired
    @Qualifier("mainDataSource")
	private DataSource dataSource;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
    private MessageService messageService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("datasource : " + dataSource.getClass().getName());
        System.out.println("tx : " + transactionManager.getClass().getName());

        messageService.sendMessage();
    }
}
