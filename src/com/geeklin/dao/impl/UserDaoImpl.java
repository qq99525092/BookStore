package com.geeklin.dao.impl;

import com.geeklin.dao.BaseDao;
import com.geeklin.dao.UserDao;
import com.geeklin.pojo.User;

/**
 * @author Lin
 * @date 2020/7/28 20:02
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    /**
     * 保存用户
     * @param user 要保存的用户信息
     * @return 影响的行数
    */
    @Override
    public int saveUser(User user) {
        String sql = "INSERT INTO t_user(`username`,`password`,`email`) VALUES(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    /**
     * 根据账号密码查询单个用户
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select  `username`,`password`,`email`,`id`  from  t_user where username= ? and password = ?";

        return queryForOne(User.class, sql, username,password);
    }

    /**
     * 查询用户是否存在
     * @param username
     * @return
     */
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `username`,`password`,`email`,`id` from  t_user where username= ? ";

        return queryForOne(User.class, sql, username);

    }
}
