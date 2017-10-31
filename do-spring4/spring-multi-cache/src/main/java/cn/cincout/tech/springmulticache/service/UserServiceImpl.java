package cn.cincout.tech.springmulticache.service;

import cn.cincout.tech.springmulticache.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by zhaoyu on 17-10-27.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Service
@CacheConfig
public class UserServiceImpl implements UserService {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @CachePut(cacheNames = "user", key = "#user.id.toString()")
    public User save(User user) {
        LOG.info("saved user {}", user);
        return user;
    }

    @Override
    @CacheEvict(cacheNames = "user")
    public void delete(int id) {
        LOG.info("delete {}", id);
    }

    @Override
    @Cacheable(cacheNames = "user")
    public User get(int id) {
        LOG.info("get user {}", id);
        return new User(1, "zhang");
    }
}
