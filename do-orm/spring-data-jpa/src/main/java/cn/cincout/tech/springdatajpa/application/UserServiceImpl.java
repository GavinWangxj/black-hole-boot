package cn.cincout.tech.springdatajpa.application;

import cn.cincout.tech.springdatajpa.domain.User;
import cn.cincout.tech.springdatajpa.inf.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhaoyu on 17-8-7.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @CacheEvict(cacheNames = {"users"}, allEntries = true)
    @Caching(
            put = {
                @CachePut(cacheNames = "user", key = "#result.id"),
                @CachePut(cacheNames = "user", key = "#user.name"),
                @CachePut(cacheNames = "user", key = "#user.email")
            },
            evict = {
                    @CacheEvict(cacheNames = "users", allEntries = true)
            }
    )
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @CacheEvict(cacheNames = {"users", "user"}, allEntries = true)
    public void delete(String name) {
        LOG.info("delete user {}.", name);
        User user = userRepository.findByName(name);
        userRepository.delete(user);
    }

    @Override
    @CacheEvict(cacheNames = {"users", "user"}, allEntries = true)
    public void delete(Integer id) {
        LOG.info("delete user, id is {}.", id);
        userRepository.delete(id);
    }

    @Override
    @Cacheable(cacheNames = {"users"})
    public List<User> findAll() {
        LOG.info("find all user from DB.");
        return userRepository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "user", key = "#id")
    public User findById(Integer id) {
        LOG.info("find user {} from DB.", id);
        return userRepository.findOne(id);
    }

    @Override
    @Cacheable(cacheNames = "user", key = "#name")
    public User findByName(String name) {
        LOG.info("find user {} from DB.", name);
        return userRepository.findByName(name);
    }

    @Override
    @Cacheable(cacheNames = "user", key = "#email")
    public User findByEmail(String email) {
        LOG.info("find user {} from DB.", email);
        return userRepository.findByEmail(email);
    }

    @Override
    @Cacheable(cacheNames = "user")
    public User findByEmailAndEmail(User user) {
        return userRepository.findByNameAndEmail(user.getName(), user.getEmail());
    }
}
