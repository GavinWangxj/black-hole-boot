package cn.cincout.tech.springmulticache.service;

import cn.cincout.tech.springmulticache.domain.User;

/**
 * Created by zhaoyu on 17-10-27.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public interface UserService {
    User save(User user);
    void delete(int id);
    User get(int id);
}
