package com.geeklin.web;

import com.geeklin.pojo.User;
import com.geeklin.service.UserService;
import com.geeklin.service.impl.UserServiceImpl;
import com.geeklin.util.WebUtils;
import com.google.code.kaptcha.Constants;
import com.google.gson.Gson;
import org.junit.Test;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lin
 * @date 2020/7/30 14:29
 */
@WebServlet(name = "UserServlet", value= "/userServlet")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 退出登录系统
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        // 1 销毁Session( Session中保存的数据也一起被销毁.也就是用户登录的信息 )
        //session对象马上销毁：invalidate()
        request.getSession().invalidate();

        // 2 重定向到网站首页：request.getContextPath()
        // 登陆页面：request.getContextPath()+"/pages/user/login.jsp"
        response.sendRedirect(request.getContextPath());

    }

    /**
     * 使用AJAX请求验证登录页面的账号是否已存在
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        //获取请求参数用户名
        String username = request.getParameter("username");

        //调用userService.existsUsername() 验证用户名是否已存在
        boolean existsUsername = userService.existsUsername(username);

        //把要返回的数据保存到map集合中
        Map<String,Object> map = new HashMap<>();
        map.put("existsUsername",existsUsername);

        //把map集合转换成json字符串
        Gson gson = new Gson();
        String toJson = gson.toJson(map);

        //通过响应流回传字符串
        response.getWriter().write(toJson);

    }


    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        //1.获取表单信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = WebUtils.copyParamToBean(new User(), request.getParameterMap());


        //2.验证账号密码
      //  User userLogin = userService.login(new User(null, username, password, null));
        User userLogin = userService.login(user);
        //用户存在 就不为空
        if (userLogin != null) {
            //用户名存在就不为空
            System.out.println("用户登录成功！");
            //保存用户登录成功的信息到session域中
            request.getSession().setAttribute("user",userLogin);
            //重定向
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);

        } else {
            System.out.println("您的账号或密码错误！");

            //保存错误信息和表单信息 到request域中，给jsp页面回显使用
            request.setAttribute("msg", "用户名或密码错误");
            request.setAttribute("username", username);

            //请求转发回登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);

        }


    }

    /**
     * 用户注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        //加入goolge提供的验证码
        //取出Session中的验证码 Constants.KAPTCHA_SESSION_KEY ： google提供的jar中的常量
        // 验证码 token
        String token = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

        //删除Session域中的验证码
        // 获取验证码后一定要删除 ，需要以后再重新获取
        // request.getSession() ： 获取当前Session对象
        request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);


        //1.获取表单信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        // code为验证码
        String code = request.getParameter("code");

        User user = WebUtils.copyParamToBean(new User(), request.getParameterMap());

        //2. 验证验证码是否填写正确
        if (token != null && token.equalsIgnoreCase(code)) {
            //3.验证 账户名是否存在
            if (userService.existsUsername(username)) {

                //当我们进行一个请求转发（forward）的时候，可以使用request.setAttribute传递参数。

                System.out.println("用户名 [" + username + "] 已存在！");
                request.setAttribute("msg", "用户名已存在！");
                request.setAttribute("username", username);
                request.setAttribute("email", email);

                //请求转发，跳回注册页面
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else {
                //用户名可用,注册账号后，保存账号到数据库
                userService.registUser(user);
                //跳转到成功页面
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);

            }

        } else {

            // System.out.println("验证码 ["+code+"] 错误！");

            request.setAttribute("msg", "验证码错误！");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("code", code);

            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }

    }



}
