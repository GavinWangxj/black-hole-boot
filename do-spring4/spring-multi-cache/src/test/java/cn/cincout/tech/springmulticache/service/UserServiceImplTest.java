package cn.cincout.tech.springmulticache.service;

import cn.cincout.tech.springmulticache.domain.Api;
import cn.cincout.tech.springmulticache.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by zhaoyu on 17-10-27.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ApiMetaService apiMetaService;

    @Test
    public void save() throws Exception {
        userService.save(new User(1, "zhang"));
        apiMetaService.save(new Api(1, "ssss"));
        System.out.println(apiMetaService.get(1));
        printEhCache();
    }

    @Test
    public void delete() throws Exception {
        userService.delete(1);
    }

    @Test
    public void get() throws Exception {
        User user = userService.get(1);
        System.out.println(user);
    }

    @Resource
    private EhCacheCacheManager ehCacheCacheManager;

    public void printEhCache() {
        Collection<String> cacheNames = ehCacheCacheManager.getCacheNames();
        cacheNames.forEach(name -> {
            System.out.println(name);
            System.out.println(ehCacheCacheManager.getCache(name).getNativeCache());
        });
    }

}