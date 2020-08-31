package com.geeklin.service.impl;

import com.geeklin.dao.UserDao;
import com.geeklin.dao.impl.UserDaoImpl;
import com.geeklin.pojo.User;
import com.geeklin.service.UserService;

/**
 * @author Lin
 * @date 2020/7/28 20:53
 */
public class UserServiceImpl implements UserService {

    // UserService需要UserDao,后面专业的叫法,又叫依赖

    public UserDao userDao = new UserDaoImpl();

    /**
     * 登录方法，验证账号密码
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {

        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());

    }

    /**
     * 查询用户名是否存在
     *
     * @param username 查询的用户名
     * @return
     */
    @Override
    public boolean existsUsername(String username) {

        User user = userDao.queryUserByUsername(username);

        if (user == null) {
            //根据用户名查询用户，存在即用户名不可用
            return false;
        }
        //不存在则用户名可用
        return true;
    }

    /**
     * 注册用户
     *
     * @param user
     */
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }
}
