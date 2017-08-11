package cn.cincout.tech.springsecurityboot.inf.repository;

import cn.cincout.tech.springsecurityboot.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 16-12-28
 * @sine 1.0
 */
public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
