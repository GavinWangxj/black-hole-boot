package cn.cincout.black.hole.doredis.application.listener;

import cn.cincout.black.hole.doredis.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-3
 * @sine 1.8
 */
@Component
public class UserMsgListener implements MessageListener {

    private final static Logger LOG = LoggerFactory.getLogger(UserMsgListener.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel());
        User user = (User) redisTemplate.getDefaultSerializer().deserialize(message.getBody());
        LOG.info("received message channel {}, body is {}.", channel, user);
    }
}
