package cn.cincout.tech.black.hole.springboot.application.service;

import cn.cincout.tech.black.hole.springboot.domain.User;
import org.springframework.stereotype.Service;

/**
 * Created by zhaoyu on 17-8-25.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User find(int id) {
        return new User();
    }
}
