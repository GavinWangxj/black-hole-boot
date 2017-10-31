package cn.cincout.tech.springmulticache.service;

import cn.cincout.tech.springmulticache.domain.Api;

/**
 * Created by zhaoyu on 17-10-27.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public interface ApiMetaService {
    Api save(Api api);
    Api get(Integer id);
}
