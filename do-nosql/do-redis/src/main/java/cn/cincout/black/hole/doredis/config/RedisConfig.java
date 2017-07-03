package cn.cincout.black.hole.doredis.config;

import cn.cincout.black.hole.doredis.application.listener.MessageDelegate;
import cn.cincout.black.hole.doredis.application.listener.UserMsgListener;
import cn.cincout.black.hole.doredis.application.listener.UserMsgListenerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-3
 * @sine 1.8
 */
@Configuration
public class RedisConfig {

    private String channel = "pinpoint.collector.channel";

    @Autowired
    private UserMsgListener userMsgListener;

    @Bean
    public MessageListenerAdapter messageListenerAdapter(
            @Autowired MessageDelegate messageDelegate) {
        return new MessageListenerAdapter(messageDelegate);
    }

    @Bean
    public ChannelTopic channelTopic() {
        return new ChannelTopic(channel);
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            @Autowired RedisConnectionFactory redisConnectionFactory,
            @Autowired MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();

        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        //redisMessageListenerContainer.addMessageListener(userMsgListener, channelTopic());
        System.out.println("messageListenerAdapter : " + messageListenerAdapter);
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter, channelTopic());
        return redisMessageListenerContainer;
    }

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    public void sendData() {
        RedisConnection connection = redisConnectionFactory.getConnection();
        byte[] msg = "hello world".getBytes();
        byte[] channel = "test.chaneel".getBytes();

        connection.publish(msg, channel);
        connection.close();
    }

}
