package cn.cincout.tech.springsecurityboot.application.service;

import java.util.Set;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 16-12-30
 * @sine 1.0
 */
public interface GroupService<T, TD> extends AbstractEntityService<T, TD> {
    T findByName(String name);

    T addRole(long groupId, long roleId);
    T addRole(long groupId, Set<Long> roleIds);
}
