package cn.cincout.black.hole.dohbase.application;

import cn.cincout.black.hole.dohbase.domain.User;

/**
 * Created by zhaoyu on 17-7-31.
 *
 * @author zhaoyu
 * @date 17-7-31
 * @sine 1.8
 */
public interface UserService {
    void save(User user);
    User get(String id);
    void delete(String name);
}
