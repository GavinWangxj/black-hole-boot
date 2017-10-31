package cn.cincout.tech.black.hole.springboot;

import cn.cincout.tech.black.hole.springboot.application.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootStartApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	WebApplicationContext applicationContext;

	@Test
	public void contextLoads() {
        System.out.println(applicationContext);
        System.out.println(userService.find(1));
	}

}
