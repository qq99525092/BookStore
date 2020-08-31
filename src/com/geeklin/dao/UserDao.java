package com.geeklin.dao;

import com.geeklin.pojo.User;

/**
 * @author Lin
 * @date 2020/7/28 19:59
 */
public interface UserDao {



    /**
     * 注册，保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);



    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    public User queryUserByUsernameAndPassword(String username,String password);



    /**
     * 根据用户名查询用户
     * @param username 用户米
     * @return 查询到的用户
     */
    public User queryUserByUsername(String username);

}
