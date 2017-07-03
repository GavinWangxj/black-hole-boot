package cn.cincout.black.hole.doredis.application.listener;

import cn.cincout.black.hole.doredis.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-3
 * @sine 1.8
 */

@Component
public class UserMsgListenerDelegate implements MessageDelegate {

    private final static Logger LOG = LoggerFactory.getLogger(UserMsgListenerDelegate.class);

    private RedisSerializer defaultSerializer = new JdkSerializationRedisSerializer();
    private RedisSerializer stringSerializer = new StringRedisSerializer();

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void handleMessage(Serializable message) {
        System.out.println(message.getClass().getName());
        String msg = (String) message;
        System.out.println(msg);
    }

    /*@Override
    public void handleMessage(String message) {
        System.out.println("string" + message);
    }*/

    /*@Override
    public void handleMessage(Map message) {
        System.out.println("map");
    }*/

    /*@Override
    public void handleMessage(byte[] message) {
        LOG.info("receive msg {}", defaultSerializer.deserialize(message));
    }*/



    /*@Override
    public void handleMessage(Serializable message, String channel) {
        System.out.println("serialize");
    }*/

}
