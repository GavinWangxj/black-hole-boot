package cn.cincout.tech.springbootmybatisjta;

import cn.cincout.tech.springbootmybatisjta.service.MessageService;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;

//import javax.jms.ConnectionFactory;

@SpringBootApplication(
		exclude = {
				DataSourceAutoConfiguration.class,
				MybatisAutoConfiguration.class,
                //JmsAutoConfiguration.class,
                //ActiveMQAutoConfiguration.class
		}
)
public class SpringBootMybatisJtaApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisJtaApplication.class, args);
	}

	@Autowired
	private ConnectionFactory connectionFactory;

	@Autowired
	private PlatformTransactionManager platformTransactionManager;

	@Autowired
	private MessageService messageService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ssx" + connectionFactory.getClass().getName());
        System.out.println(platformTransactionManager.getClass().getName());
        for (int i = 0; i < 10; i++) {
            messageService.sendMessage("message " + i);
        }

    }
}
