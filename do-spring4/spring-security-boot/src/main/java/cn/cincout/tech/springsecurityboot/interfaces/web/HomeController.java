package cn.cincout.tech.springsecurityboot.interfaces.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoyu on 17-8-10.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Controller
public class HomeController {
    @GetMapping(value = {"/", ""})
    public String index() {
        return "index";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "home";
    }

    @GetMapping(value = "/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
