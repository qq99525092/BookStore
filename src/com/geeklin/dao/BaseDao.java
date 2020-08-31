package com.geeklin.dao;

import com.geeklin.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Lin
 * @date 2020/7/28 19:35
 */
public abstract class BaseDao {

    // queryRunner用于执行sql语句
    QueryRunner queryRunner = new QueryRunner();

    /**
     * update() 方法用于执行 insert , update , delete 的sql语句
     *
     * @return
     */
    public int update(String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();

        try {
            // 执行 insert , update , delete 的sql语句
            return queryRunner.update(conn, sql, args);

        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException(e);

        }

    }

    /**
     * 查询并返回一个javaBean的方法
     *
     * @param type 返回的对象类型
     * @param sql  执行的sql语句
     * @param args sql语句对应的参数值
     * @param <T>  具体类型的泛型
     * @return 如果返回null, 说明查询失败, 有值则成功
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {

        Connection conn = JdbcUtils.getConnection();

        try {
            return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }

    }


    /**
     * 执行返回多个JavaBean的sql
     *
     * @param type 每个JavaBean的具体类型
     * @param sql  sql语句
     * @param args sql对应的参数值
     * @param <T>  返回值的具体类型
     * @return 如果返回null, 说明查询失败, 否则查询成功
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();

        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }


    }

    /**
     * 用于执行查询结果是单行单列的情况
     * @param sql   sql语句
     * @param args  sql对应的参数值
     * @return  返回的结果,返回null表示没有查到
     */
    public Object queryForSingleValue(String sql , Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new  ScalarHandler(),args);

        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }
}
