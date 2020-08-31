package com.geeklin.test;

import com.geeklin.util.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author Lin
 * @date 2020/7/28 19:10
 */
public class JdbcUtilsTest {

    @Test
    public void  test1(){
        for (int i = 0; i < 100; i++) {
            Connection conn = JdbcUtils.getConnection();
            System.out.println(conn);
            JdbcUtils.closeConnection(conn);

        }

    }
}
