package com.geeklin.test;

import com.geeklin.dao.UserDao;
import com.geeklin.dao.impl.UserDaoImpl;
import com.geeklin.pojo.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Lin
 * @date 2020/7/28 20:14
 */
public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();


    @Test
    public void saveUser() {
        //注册操作
userDao.saveUser(new User(null, "Lin", "123456", "123@qq.com"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        //登录操作
        User user = userDao.queryUserByUsernameAndPassword("Lin", "123456");
        if (user == null){
            System.out.println("用户名不存在，或密码错误！");
        }else {
            System.out.println("登录成功！");
        }
        System.out.println(user);
    }

    @Test
    public void queryUserByUsername() {
        User existsUser = userDao.queryUserByUsername("admin111");
        if (existsUser == null){
            System.out.println("用户名可用！");
        }else {
            System.out.println("用户名不可用！");
        }
    }
}