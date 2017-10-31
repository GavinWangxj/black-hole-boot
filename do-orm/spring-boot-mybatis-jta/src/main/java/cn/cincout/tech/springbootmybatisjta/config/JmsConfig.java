package cn.cincout.tech.springbootmybatisjta.config;

import com.atomikos.jms.AtomikosConnectionFactoryBean;
import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;
import javax.jms.Session;
import javax.jms.XAConnectionFactory;

/**
 * Created by zhaoyu on 17-10-22.
 *
 * @author zhaoyu
 * @sine 1.8
 */
//Configuration
//@EnableJms
public class JmsConfig {

    @Bean("activeMQQueue")
    public ActiveMQQueue activeMQQueue() {
        return new ActiveMQQueue("msg.queue");
    }

    @Bean("activeMQTopic")
    public ActiveMQTopic activeMQTopic() {
        return new ActiveMQTopic("msg.topic");
    }

    @Bean("jmsXaConnectionFactory")
    public ConnectionFactory xaConnectionFactory() {
        ActiveMQXAConnectionFactory activeMQXAConnectionFactory
                = new ActiveMQXAConnectionFactory();
        activeMQXAConnectionFactory.setUserName("admin");
        activeMQXAConnectionFactory.setPassword("cinc0@ut");
        activeMQXAConnectionFactory.setBrokerURL("tcp://cincout.cn:61616");
        return activeMQXAConnectionFactory;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public AtomikosConnectionFactoryBean atomikosConnectionFactoryBean() {
        AtomikosConnectionFactoryBean atomikosConnectionFactoryBean
                = new AtomikosConnectionFactoryBean();
        atomikosConnectionFactoryBean.setXaConnectionFactory((XAConnectionFactory) xaConnectionFactory());
        atomikosConnectionFactoryBean.setUniqueResourceName("ActiveMQXA");
        atomikosConnectionFactoryBean.setPoolSize(10);
        return atomikosConnectionFactoryBean;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageListenerContainer messageListenerContainer(PlatformTransactionManager platformTransactionManager) {
        DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(xaConnectionFactory());
        messageListenerContainer.setTransactionManager(platformTransactionManager);
        messageListenerContainer.setDestination(activeMQQueue());
        return messageListenerContainer;
    }

    @Bean("jmsTemplate")
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(atomikosConnectionFactoryBean());
        jmsTemplate.setReceiveTimeout(10000);
        jmsTemplate.setSessionTransacted(true);
        jmsTemplate.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        jmsTemplate.setDefaultDestination(activeMQQueue());
        // converter
        jmsTemplate.setMessageConverter(messageConverter());
        //jmsTemplate.setPubSubDomain(false);
        return jmsTemplate;
    }

    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate() {
        JmsMessagingTemplate messagingTemplate = new JmsMessagingTemplate(jmsTemplate());
        return messagingTemplate;
    }
}
