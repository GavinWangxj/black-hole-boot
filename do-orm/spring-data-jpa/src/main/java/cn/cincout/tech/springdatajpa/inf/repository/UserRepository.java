package cn.cincout.tech.springdatajpa.inf.repository;

import cn.cincout.tech.springdatajpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zhaoyu on 17-8-7.
 *
 * @author zhaoyu
 * @sine 1.8
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
    User findByEmail(String email);
    User findByNameAndEmail(String name, String email);
}
