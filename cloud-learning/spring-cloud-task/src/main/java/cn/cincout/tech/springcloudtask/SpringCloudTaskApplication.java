package cn.cincout.tech.springcloudtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableTask
@EnableBatchProcessing
public class SpringCloudTaskApplication {
    private final static Logger LOG = LoggerFactory.getLogger(SpringCloudTaskApplication.class);

	public static class HelloWordCommandLineRunner implements CommandLineRunner {
        @Override
        public void run(String... strings) throws Exception {
            LOG.info("Hello World!");
        }
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
	    return new HelloWordCommandLineRunner();
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudTaskApplication.class, args);
	}
}

