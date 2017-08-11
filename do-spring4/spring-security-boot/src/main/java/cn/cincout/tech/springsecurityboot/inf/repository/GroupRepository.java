package cn.cincout.tech.springsecurityboot.inf.repository;

import cn.cincout.tech.springsecurityboot.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 16-12-29
 * @sine 1.0
 */
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);
}
