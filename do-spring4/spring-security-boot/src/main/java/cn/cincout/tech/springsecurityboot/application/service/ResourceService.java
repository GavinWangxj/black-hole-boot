package cn.cincout.tech.springsecurityboot.application.service;


import cn.cincout.tech.springsecurityboot.domain.Resource;
import cn.cincout.tech.springsecurityboot.domain.Role;

import java.util.List;
import java.util.Set;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 16-12-29
 * @sine 1.0
 */
public interface ResourceService extends AbstractEntityService<Resource, Long> {
    /**
     * find the resource related role
     * @param resourceId
     * @return
     */
    Set<Role> findRolesOfResource(Long resourceId);

    /**
     * batch save resource
     * @param resources resource list
     * @return result
     */
    List<Resource> save(List<Resource> resources);
}
