package com.geeklin.web;

import com.geeklin.dao.BaseDao;
import com.geeklin.pojo.Cart;
import com.geeklin.pojo.Order;
import com.geeklin.pojo.OrderItem;
import com.geeklin.pojo.User;
import com.geeklin.service.OrderService;
import com.geeklin.service.impl.OrderServiceImpl;
import com.geeklin.util.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Lin
 * @date 2020/8/7 10:11
 */
@WebServlet(name = "OrderServlet", urlPatterns = "/orderServlet")

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 结账功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        // orderService.createOrder(Cart cart, Integer userId)
        //获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //获取Session域中保存的用户信息
        User user = (User) request.getSession().getAttribute("user");

        //如果用户为空，还未登录
        if (user == null) {
            //请求转发到登陆页面，注意：在请求转发语句后面，一般不要再写任何代码
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;

        }

        String orderId = orderService.createOrder(cart, user.getId());


        //把订单号保存到request域中
//        request.setAttribute("orderId",orderId);
        //请求转发到checkout.jsp页面 : pages 前面的斜杠表示当前工程路径下的 web文目录
        //  request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request,response);

        //使用重定向，要把数据放到session域中
        request.getSession().setAttribute("orderId", orderId);
        //请求转发有重复提交表单的清空
        //所以使用重定向
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * 发货功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        //1.获取请求的参数
        String orderId = request.getParameter("orderId");

        //2.调用orderService.sendOrder(orderId);
        orderService.sendOrder(orderId);

        //3.考虑是否需要数据 保存到域中
        String referer = request.getHeader("referer");
        //4.页面跳转
        response.sendRedirect(referer);

        //获取请求发起时，原来的页面地址


    }

    /**
     * 签收订单
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void receiveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        //获取请求的参数 orderId
        String orderId = request.getParameter("orderId");

        //调用orderService.receiveOrder(orderId)
        orderService.receiveOrder(orderId);

        //考虑页面是否需要数据===》》 保存域对象
        String referer = request.getHeader("referer");

        //页面跳转
        response.sendRedirect(referer);


    }

    /**
     * 管理员查询全部订单
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void allOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");


        //使用orderService.queryAllOrders()查询全部订单
        List<Order> orders = orderService.queryAllOrders();
        //把数据保存到request域中
        request.setAttribute("orders", orders);
        //请求转发到pages/manager/order_manager.jsp页面
        request.getRequestDispatcher("pages/manager/order_manager.jsp").forward(request, response);

    }

    /**
     * 查看自己的订单
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");


        //获取用户编号
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        Integer id = user.getId();
        //调用orderService.queryMyOrders() 查询用户订单
        List<Order> orders = orderService.queryMyOrders(id);

        //把数据保存到request域中
        request.setAttribute("orders", orders);

        //请求转发到 pages/order/order.jsp页面
        request.getRequestDispatcher("pages/order/order.jsp").forward(request, response);


    }

    /**
     * 管理员查看订单详情
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void orderDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        //获取请求的参数
        String orderId = request.getParameter("orderId");
        //调用 orderService.queryOrderDetails(orderId)
        List<OrderItem> orderItems = orderService.queryOrderDetails(orderId);

        //保存数据到request域中
        request.setAttribute("orderItems", orderItems);

        //请求转发到/pages/manager/order_details.jsp 页面
        request.getRequestDispatcher("/pages/manager/order_details.jsp").forward(request, response);


    }


    /**
     * 用户查看个人订单详情
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void orderDetailsForUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        //获取请求的参数
        String orderId = request.getParameter("orderId");
        //调用 orderService.queryOrderDetails(orderId)
        List<OrderItem> orderItems = orderService.queryOrderDetails(orderId);

        //保存数据到request域中
        request.setAttribute("orderItems", orderItems);

        //请求转发到/pages/manager/order_details.jsp 页面
        request.getRequestDispatcher("/pages/order/order_details.jsp").forward(request, response);


    }

}
