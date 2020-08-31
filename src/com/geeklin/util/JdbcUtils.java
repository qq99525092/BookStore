package com.geeklin.util;

/**
 * @author Lin
 * @date 2020/7/28 18:47
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 操作数据库（从连接池中获取）
 */
public class JdbcUtils {

    static DruidDataSource dataSource;

    //创建ThreadLocal对象
    static  ThreadLocal<Connection> cons = new ThreadLocal<>();

    static {

        try {
            Properties properties = new Properties();
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");

            properties.load(inputStream);
            // 根据属性连接信息创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 从数据连接池中获取连接
     *
     * @return
     */
    public static Connection getConnection() {

        // 先从threadLocal中获取连接
        Connection conn = cons.get();
        //如果连接为空，则表示之前还未获取连接
        if (conn == null){
            try {

                conn = dataSource.getConnection();
                //把连接保存到threadLocal本地
                cons.set(conn);

                //设置手动事务
                conn.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

            return conn;
    }

    /**
     * 提交事务，并关闭连接
     */
    public static void commitAndClose(){
        //从threadLocal中获取连接
        Connection connection = cons.get();

        // 如果为null,说明之前没有使用过连接,不需要处理事务
        if (connection !=null){

            try {
                //提交事务
                connection.commit();
                //关闭连接，把连接放进连接池中
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //解除线程和连接的关联，一定要接触连接，否则会报错
        cons.remove();
    }

    /**
     * 事务回滚，并关闭连接
     */
    public static void rollbackAndClose(){
        //从threadLocal中获取连接
        Connection connection = cons.get();

        // 如果为null,说明之前没有使用过连接,不需要处理事务
        if (connection !=null){

            try {
                //事务回滚
                connection.rollback();
                //关闭连接，把连接放进连接池中
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        //解除线程和连接的关联
        cons.remove();

    }


    /**
     * 关闭连接，放回连接池中
     *
     * @param conn
     */
    public static void closeConnection(Connection conn) {

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
