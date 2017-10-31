package cn.cincout.tech.websocket.interfaces.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by zhaoyu on 17-8-25.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Controller
public class HomeController {
    @GetMapping(value = {"/"})
    public String index() {
        return "index";
    }

    @GetMapping(value = "/socketJS")
    public String socketJS() {
        return "socketjs";
    }
}
