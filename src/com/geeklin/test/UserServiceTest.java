package com.geeklin.test;

import com.geeklin.pojo.User;
import com.geeklin.service.UserService;
import com.geeklin.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Lin
 * @date 2020/7/28 20:59
 */
public class UserServiceTest {

    UserService userService = new UserServiceImpl();
    @Test
    public void login() {
        User loginUser = userService.login(new User(null, "admin", "admin", null));
        if (loginUser == null){
            System.out.println("用户名或密码错误！");
        }else {
            System.out.println("登录成功");
        }

    }

    /**
     * 查看用户名是否存在
     */
    @Test
    public void existsUsername() {
        boolean existsUsername = userService.existsUsername("Lin");
        if(existsUsername ){
            System.out.println("用户名已存在，不可用！");
        }else {
            System.out.println("用户名不存在，可用!");
        }
    }

    @Test
    public void registUser() {

        userService.registUser(new User(null,"Li","456","456@qq.com"));

    }
}