package cn.cincout.tech.black.hole.springboot.application.service;

import cn.cincout.tech.black.hole.springboot.domain.User;

/**
 * Created by zhaoyu on 17-8-25.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public interface UserService {
    User find(int id);
}
