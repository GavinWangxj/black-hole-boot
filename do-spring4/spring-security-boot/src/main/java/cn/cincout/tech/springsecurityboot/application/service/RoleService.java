package cn.cincout.tech.springsecurityboot.application.service;


import cn.cincout.tech.springsecurityboot.domain.Role;

import java.util.Set;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 16-12-29
 * @sine 1.0
 */
public interface RoleService extends AbstractEntityService<Role, Long> {

    Role addResource(Long roleId, Long resourceId);
    Role addResource(long roleId, Set<Long> resourceIds);

}
