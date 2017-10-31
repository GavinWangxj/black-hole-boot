package cn.cincout.tech.springdatajpa.interfaces;

import cn.cincout.tech.springdatajpa.application.UserService;
import cn.cincout.tech.springdatajpa.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhaoyu on 17-8-7.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public User findByName(@RequestParam("name") String name) {
        return userService.findByName(name);
    }

    /*@GetMapping(value = "/users")
    public User findByEmail(@RequestParam("email") String email) {
        return userService.findByEmail(email);
    }*/

    @GetMapping(value = "/users/{id}")
    public User findById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }


    @PostMapping(value = "/users")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping(value = "/users/all")
    public List<User> findAll() {
        return userService.findAll();
    }

    @DeleteMapping(value = "/users/{id}")
    public String delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "success";
    }

    @GetMapping(value = "/findByNameAndEmail")
    public User findByNameAndEmail(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email) {
        User user = new User(name, email);
        return userService.findByEmailAndEmail(user);
    }
}
