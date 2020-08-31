package com.geeklin.filter;

import com.geeklin.util.JdbcUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Lin
 * @date 2020/8/9 9:33
 */

/**
 *  urlPatterns = {"/*"} ： 表示拦截http://ip:port/工程路径/所有请求
 */
@WebFilter(urlPatterns = {"/*"})
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            // 间接给所有 XxxService.xxx()方法都加上try..catch
            chain.doFilter(request,response);

            //提交事务
            JdbcUtils.commitAndClose();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
            //回滚
            JdbcUtils.rollbackAndClose();

            //把异常抛给tomcat服务器
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
