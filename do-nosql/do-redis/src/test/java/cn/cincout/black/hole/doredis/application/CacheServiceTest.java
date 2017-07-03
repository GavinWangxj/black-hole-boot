package cn.cincout.black.hole.doredis.application;

import cn.cincout.black.hole.doredis.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-3
 * @sine 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheServiceTest {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;
    @Before
    public void before() {
        System.out.println(redisTemplate);

        if (cacheService == null) {
            System.out.println(CacheService.class.getCanonicalName() + " is null.");
        }
    }

    @Test
    public void save() throws Exception {
        cacheService.save(new User(1, "zhang", "zhang@cincout.cn"));
    }

    @Test
    public void get() throws Exception {
        User user  = cacheService.get("zhang");
        Assert.assertNotNull(user);
        System.out.println(user);
    }

}