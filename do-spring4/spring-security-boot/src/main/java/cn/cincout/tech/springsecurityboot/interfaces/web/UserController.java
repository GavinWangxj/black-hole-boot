package cn.cincout.tech.springsecurityboot.interfaces.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by zhaoyu on 17-8-10.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Controller
public class UserController {
    @GetMapping(value = "/user/index")
    public String index() {
        return "user/index";
    }
}
