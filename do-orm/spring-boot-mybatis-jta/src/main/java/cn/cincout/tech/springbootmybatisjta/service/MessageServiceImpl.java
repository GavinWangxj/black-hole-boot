package cn.cincout.tech.springbootmybatisjta.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Destination;

/**
 * Created by zhaoyu on 17-10-21.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {


    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    @Transactional
    public void sendMessage(String message) {
        jmsTemplate.convertAndSend(new ActiveMQQueue("msg.queue"), message);
    }

    @JmsListener(destination = "msg.queue")
    public void processMessage(String message) {
        System.out.println("get message " + message);
    }
}
