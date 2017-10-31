package cn.cincout.tech.springmulticache.service;

import cn.cincout.tech.springmulticache.domain.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
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
@CacheConfig(cacheManager = "ehCacheCacheManager")
public class ApiMetaServiceImpl implements ApiMetaService {
    private final static Logger LOG = LoggerFactory.getLogger(ApiMetaServiceImpl.class);

    @Override
    @CachePut(cacheNames = "api", key = "#api.id.toString()")
    public Api save(Api api) {
        LOG.info("save {}", api);
        return api;
    }

    @Override
    @Cacheable(cacheNames = "api", key = "#id.toString()")
    public Api get(Integer id) {
        LOG.info("get {}", id);
        return new Api(1, "x");
    }
}
