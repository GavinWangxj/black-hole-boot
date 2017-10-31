package cn.cincout.spring.cloud.doeurekaprovider.inf.repository;

import cn.cincout.spring.cloud.doeurekaprovider.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-5
 * @sine 1.8
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
