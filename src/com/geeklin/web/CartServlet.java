package com.geeklin.web;

import com.geeklin.pojo.Book;
import com.geeklin.pojo.Cart;
import com.geeklin.pojo.CartItem;
import com.geeklin.service.BookService;
import com.geeklin.service.impl.BookServiceImpl;
import com.geeklin.util.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lin
 * @date 2020/8/4 16:35
 */
@WebServlet(name = "CartServlet",value = "/cartServlet")
public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 添加商品到购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html; charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");

        //1 获取商品的编号
        Integer id = WebUtils.parseInt(request.getParameter("id"),0);

        //2 通过BookService.queryBookById(id):Book图书信息
        Book book = bookService.queryBookById(id);

        //3 把Book转换为CartItem
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());

        //4 从Session域中 获取 Cart购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }

        //5 调用 cart.addItem( CartItem ) 添加商品项
        cart.addItem(cartItem);
        System.out.println(cart);

        //把最后一个商品保存添加到session域中
        request.getSession().setAttribute("last_name",cartItem.getName());

        //6 跳转回添加商品的页面，获取请求头，然后根据请求头跳回原来页面
        response.sendRedirect(request.getHeader("referer"));

    }

    /**
     * 使用AJAX把商品加入购物车，并回传数据给客户端
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html; charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        request.setCharacterEncoding("UTF-8");

        //1 获取商品的编号
        Integer id = WebUtils.parseInt(request.getParameter("id"),0);

        //2 通过BookService.queryBookById(id):Book图书信息
        Book book = bookService.queryBookById(id);

        //3 把Book转换为CartItem
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());

        //4 从Session域中 获取 Cart购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }

        //5 调用 cart.addItem( CartItem ) 添加商品项
        cart.addItem(cartItem);
        System.out.println(cart);

        //把最后一个商品的名字保存添加到session域中
        request.getSession().setAttribute("last_name",cartItem.getName());

        Map<String,Object> map = new HashMap<>();
        //购物车的总数量
        map.put("totalCount",cart.getTotalCount());
        //最后一个添加的商品名称
        map.put("last_name", cartItem.getName());

        //把map集合中转换成json字符串
        Gson gson = new Gson();
        String toJson = gson.toJson(map);

        //通过响应流回传数据给客户端
        response.getWriter().write(toJson);


    }


    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html; charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        request.setCharacterEncoding("UTF-8");

        //1.获取购物车对象
      Cart cart = (Cart) request.getSession().getAttribute("cart");

      //调用cart.delete()方法清空购物车
        if (cart != null){
            //清空购物车
            cart.clear();
            //重定向跳回原来购物车页面
            response.sendRedirect(request.getHeader("referer"));
        }
    }

    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html; charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        request.setCharacterEncoding("UTF-8");

        //获取请求的参数 商品编号和新数量
        Integer id = WebUtils.parseInt(request.getParameter("id"), 0);

        Integer count = WebUtils.parseInt(request.getParameter("count"),1);

        // 获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart != null) {
            // 修改商品数量
            cart.updateCount(id, count);
            // 重定向回购物车页面
            response.sendRedirect(request.getHeader("referer"));
        }

    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html; charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        request.setCharacterEncoding("UTF-8");

        //1.获取请求的参数，图书编号
        Integer id = WebUtils.parseInt(request.getParameter("id"), 0);

        //2.获取Cart购物车对象
        Cart cart =(Cart) request.getSession().getAttribute("cart");

        //3.调用cart.delete()删除商品项目
        if (cart!= null){
            cart.deleteItem(id);
            //重定向跳回原来购物车页面
            response.sendRedirect(request.getHeader("referer"));

        }

    }
}
