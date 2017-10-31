package cn.cincout.tech.springdatajpa.application.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by zhaoyu on 17-8-9.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
public class CachePrintTask {
    private final static Logger LOG = LoggerFactory.getLogger(CachePrintTask.class);

    @Autowired
    private CacheManager cacheManager;

    @Scheduled(cron = "0/20 * * * * ?")
    public void cachesPrint() {
        LOG.info("begin print caches...");

        Collection<String> cacheNames = cacheManager.getCacheNames();
        cacheNames.forEach(cacheName -> {
            Cache cache = cacheManager.getCache(cacheName);
            LOG.info("cache name : {}, value {}.", cacheName, cache.getNativeCache());
        });
        LOG.info("end print caches...");
        System.out.println("\n\n");
    }
}
