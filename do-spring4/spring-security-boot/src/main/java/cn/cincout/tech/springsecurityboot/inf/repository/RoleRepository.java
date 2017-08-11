package cn.cincout.tech.springsecurityboot.inf.repository;

import cn.cincout.tech.springsecurityboot.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 16-12-29
 * @sine 1.0
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
