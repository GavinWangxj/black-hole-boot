package cn.cincout.tech.springdatajpa.application;

import cn.cincout.tech.springdatajpa.domain.User;

import java.util.List;

/**
 * Created by zhaoyu on 17-8-7.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public interface UserService {

    User save(User user);

    void delete(String name);
    void delete(Integer id);

    List<User> findAll();
    User findById(Integer id);
    User findByName(String name);
    User findByEmail(String email);
    User findByEmailAndEmail(User user);
}
