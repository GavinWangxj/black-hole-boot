package cn.cincout.black.hole.doredis.application;

import cn.cincout.black.hole.doredis.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CacheService {

    private final static Logger LOG = LoggerFactory.getLogger(CacheService.class);

    @Autowired
    private RedisTemplate redisTemplate;

    public void save(User user) {
        redisTemplate.opsForList().leftPush(user.getName(), user);
    }

    public User get(String name) {
        return (User) redisTemplate.opsForList().rightPop(name);
    }

}
