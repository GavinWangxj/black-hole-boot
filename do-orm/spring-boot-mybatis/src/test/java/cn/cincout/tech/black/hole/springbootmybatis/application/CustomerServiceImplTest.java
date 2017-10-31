package cn.cincout.tech.black.hole.springbootmybatis.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by zhaoyu on 17-10-21.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void insert() throws Exception {
        customerService.insert();
    }

}