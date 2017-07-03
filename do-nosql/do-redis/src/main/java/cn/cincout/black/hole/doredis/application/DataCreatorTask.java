package cn.cincout.black.hole.doredis.application;

import cn.cincout.black.hole.doredis.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-3
 * @sine 1.8
 */
@Component
public class DataCreatorTask {
    private final static Logger LOG = LoggerFactory.getLogger(DataCreatorTask.class);
    @Autowired
    private RedisTemplate redisTemplate;
    Random random = new Random(100);

    @Scheduled(cron = "*/5 * * * * ?")
    public void redisDataPublishTask() {
        User user = new User(
                random.nextInt(100),
                "zhang" + random.nextInt(),
                "zhang@cincout.cn"
        );
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
        redisTemplate.convertAndSend("pinpoint.collector.channel", user);

        LOG.info("send user {} to redis channel.", user.toString());
    }
}
