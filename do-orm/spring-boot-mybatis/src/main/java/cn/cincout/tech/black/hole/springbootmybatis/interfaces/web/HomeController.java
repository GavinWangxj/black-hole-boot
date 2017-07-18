package cn.cincout.tech.black.hole.springbootmybatis.interfaces.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-6-30
 * @sine 1.8
 */
@RestController
public class HomeController {

    @Value("${initHello}")
    private String initHello;

    @GetMapping(value = "/")
    public Map<String, String> home() {
        Map<String, String> result = new HashMap<>();
        result.put("msg", initHello);
        return result;
    }

}
