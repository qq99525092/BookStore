package com.geeklin.web;

import com.geeklin.pojo.Book;
import com.geeklin.pojo.Page;
import com.geeklin.service.BookService;
import com.geeklin.service.impl.BookServiceImpl;
import com.geeklin.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Lin
 * @date 2020/8/3 10:03
 */
@WebServlet(name = "ClientBookServlet" ,urlPatterns = "/client/bookServlet")
public class ClientBookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();



    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        //1.获取请求的参数 pageNo和pageSize
        Integer pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        Integer pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.调用bookService.page(pageNo,pagSize);
        Page<Book> page = bookService.page(pageNo, pageSize);

        //设置分页条的请求地址
        page.setUrl("client/bookServlet?action=page");

        //3.把page分页对象保存到request域中
        request.setAttribute("page",page);
        //4.请求转发到/pages/manager/book_manager.jsp

        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }

    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");


        //1.获取请求的参数 pageNo和pageSize
        Integer pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        Integer pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Integer min = WebUtils.parseInt(request.getParameter("min"), 0);
        Integer max  = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);

        //2.调用bookService.page(pageNo,pagSize);
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");

        if (request.getParameter("min") != null){
            sb.append("&min=").append(request.getParameter("min"));
        }
        if (request.getParameter("max") != null){
            sb.append("&max=").append(request.getParameter("max"));
        }

        //设置分页条的请求地址
        page.setUrl(sb.toString());

        //3.把page分页对象保存到request域中
        request.setAttribute("page",page);
        //4.请求转发到/pages/manager/book_manager.jsp

        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }

}
