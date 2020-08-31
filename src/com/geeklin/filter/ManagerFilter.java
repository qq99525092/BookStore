package com.geeklin.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Lin
 * @date 2020/8/8 21:53
 */
@WebFilter(value = {"/pages/manager/*","/manager/bookServlet","/pages/cart/*","/cartServlet"})
public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //先强转
        HttpServletRequest Request = (HttpServletRequest) request;
        //检查用户是否登录
        Object user = Request.getSession().getAttribute("user");
         if (user == null){
             //如果用户未登录则跳转到登录页面
             request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);

         }else {
             //如果用户登录了，就放行
             chain.doFilter(request,response);
         }


    }

    @Override
    public void destroy() {

    }
}
