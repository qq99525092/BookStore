package com.geeklin.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author Lin
 * @date 2020/7/30 16:19
 */
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("UTF-8");
       doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决中文响应乱码
        request.setCharacterEncoding("UTF-8");

        //鉴别业务的字符串
        String action = request.getParameter("action");

//        if ("login".equals(action)) {
//            System.out.println("这是一个登录操作！");
//
//            login(request,response);
//
//        } else if ("regist".equals(action)) {
//            System.out.println("这是一个注册操作！");
//
//            regist(request,response);
//        }

        //方法二
        try {
            //通过action 业务鉴别字符串 ，得到对应的业务方法
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);

            //设置业务方法(对象 ，。。。。参数列表）
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();

            throw  new RuntimeException(e);

        }
    }



}
