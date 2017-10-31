package cn.cincout.tech.springdatajpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.*;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhaoyu on 17-8-7.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    @Bean
    @Override
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        return cacheManager;
    }

    @Override
    @Bean
    public CacheResolver cacheResolver() {
        return new SimpleCacheResolver(cacheManager());
    }

    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    @Bean
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandlerImpl();
    }

    public class  CacheErrorHandlerImpl implements CacheErrorHandler {

        @Override
        public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {

        }

        @Override
        public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {

        }

        @Override
        public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {

        }

        @Override
        public void handleCacheClearError(RuntimeException exception, Cache cache) {

        }
    }
}
