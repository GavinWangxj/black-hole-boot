package cn.cincout.tech.springmybatisjmsjta.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhaoyu on 17-10-22.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Service
@Transactional
public class MessageService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Transactional
    public void sendMessage() {
        jmsTemplate.convertAndSend(new ActiveMQQueue("jms.queue"), "hello world!");
    }

    @JmsListener(destination = "jms.queue")
    public void processMessage(String message) {
        System.out.println(message);
    }
}
