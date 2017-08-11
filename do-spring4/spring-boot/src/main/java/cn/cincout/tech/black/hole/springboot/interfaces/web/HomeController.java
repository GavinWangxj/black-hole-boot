package cn.cincout.tech.black.hole.springboot.interfaces.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoyu on 17-8-4.
 *
 * @author zhaoyu
 * @date 17-8-4
 * @sine 1.8
 */
@RestController
public class HomeController {

    @Value("${val.name}")
    private String name;
    @Value("${val.password}")
    private String password;

    @GetMapping(value = {"", "/"})
    public Map<String, Object> home() {
        Map<String, Object> result = new HashMap<>();
        result.put("status", "good");
        result.put("name", name);
        result.put("password", password);
        return result;
    }

}
