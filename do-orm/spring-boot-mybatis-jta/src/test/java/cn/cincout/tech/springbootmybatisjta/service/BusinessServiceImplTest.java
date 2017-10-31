package cn.cincout.tech.springbootmybatisjta.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by zhaoyu on 17-10-21.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessServiceImplTest {

    @Resource
    private BusinessService businessService;

    @Test
    public void insert() throws Exception {
        businessService.insert();
    }

}