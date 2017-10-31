package cn.cincout.tech.springbootmybatisjta.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by zhaoyu on 17-10-22.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceImplTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void sendMessage() throws Exception {
        messageService.sendMessage("hello world");
    }

}