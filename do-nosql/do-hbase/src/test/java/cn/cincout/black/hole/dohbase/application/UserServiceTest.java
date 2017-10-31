package cn.cincout.black.hole.dohbase.application;

import cn.cincout.black.hole.dohbase.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhaoyu on 17-7-31.
 *
 * @author zhaoyu
 * @date 17-7-31
 * @sine 1.8
 */
public class UserServiceTest {

    private UserService userService = new UserServiceImpl("10.230.135.130");

    @Test
    public void save() throws Exception {
        userService.save(new User("zhangzhaoyu", "zhang@abchina.com", "123"));
    }

    @Test
    public void get() throws Exception {
    }

}