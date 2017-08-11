package cn.cincout.tech.springsecurityboot.application.service;

import java.util.Set;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 16-12-29
 * @sine 1.0
 */
public interface UserService<T, TD> extends AbstractEntityService<T, TD> {
    T findByName(String name);
    T findByEmail(String email);

    /**
     * add group for the user
     * @param userId user id
     * @param groupId group id
     * @return the updated user
     */
    T addGroup(Long userId, Long groupId);
    T addGroups(Long userId, Set<Long> groupIds);
}
