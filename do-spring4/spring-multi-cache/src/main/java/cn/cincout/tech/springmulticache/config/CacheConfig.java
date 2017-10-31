package cn.cincout.tech.springmulticache.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.AliasFor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhaoyu on 17-10-27.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Configuration
@EnableCaching
public class CacheConfig implements ApplicationRunner {

    @Resource
    private List<CacheManager> cacheManagers;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(cacheManagers.size());
    }

    @Bean(name = "redisCacheManager")
    public RedisCacheManager redisCacheManager(RedisTemplate<Object, Object> redisTemplate) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setCacheNames(Arrays.asList("user"));
        redisCacheManager.setUsePrefix(true);
        return redisCacheManager;
    }

    @Bean(name = "ehcache")
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheBean = new EhCacheManagerFactoryBean();
        cacheBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return cacheBean;
    }

    @Bean("ehCacheCacheManager")
    public EhCacheCacheManager ehCacheCacheManager(@Qualifier("ehcache") net.sf.ehcache.CacheManager ehcacheManager) {
        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager(ehcacheManager);
        return ehCacheCacheManager;
    }

    @Bean(name = "cacheManager")
    //@Primary
    public CompositeCacheManager cacheManager(RedisCacheManager redisCacheManager, EhCacheCacheManager ehCacheCacheManager) {
        CompositeCacheManager cacheManager = new CompositeCacheManager(redisCacheManager, ehCacheCacheManager);
        return cacheManager;
    }
}
