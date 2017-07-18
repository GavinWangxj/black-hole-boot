package cn.cincout.spring.cloud.doeurekaprovider.interfaces;

import cn.cincout.spring.cloud.doeurekaprovider.domain.User;
import cn.cincout.spring.cloud.doeurekaprovider.inf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-5
 * @sine 1.8
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/user/{id}")
    public User findById(@PathVariable(name = "id") Integer id) {
        return userRepository.findOne(id);
    }
}
