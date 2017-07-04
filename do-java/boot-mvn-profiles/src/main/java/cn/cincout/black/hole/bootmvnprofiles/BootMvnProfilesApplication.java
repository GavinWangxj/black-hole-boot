package cn.cincout.black.hole.bootmvnprofiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class BootMvnProfilesApplication {
	private final static Logger LOG = LoggerFactory.getLogger(BootMvnProfilesApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext =
				SpringApplication.run(BootMvnProfilesApplication.class, args);
		String[] profiles = applicationContext.getEnvironment().getActiveProfiles();
		LOG.info("active profiles are {}.", Arrays.toString(profiles));
	}
}
