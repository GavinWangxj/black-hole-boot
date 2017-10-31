package cn.cincout.tech.black.hole.springboot.interfaces.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoyu on 17-8-4.
 *
 * @author zhaoyu
 * @date 17-8-4
 * @sine 1.8
 */
@Controller
public class HomeController {

    private final static Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Value("${val.name}")
    private String name;
    @Value("${val.password}")
    private String password;

    @GetMapping(value = {"", "/"})
    @ResponseBody
    public Map<String, Object> home() {
        LOG.trace("trace level log");
        LOG.debug("debug level log");
        LOG.info("info level log");
        LOG.warn("warn level log");
        LOG.error("error level log");

        Map<String, Object> result = new HashMap<>();
        result.put("status", "good");
        result.put("name", name);
        result.put("password", password);
        return result;
    }

    @GetMapping(value = "/admin")
    public String shutdown() {
        return "shutdown";
    }
}
