package com.geeklin.web;

import com.geeklin.dao.BaseDao;
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
import java.util.List;

/**
 * @author Lin
 * @date 2020/8/1 10:21
 */

/**
 * urlPatterns = "/manager/bookServlet" ,地址中加入/manager/是为了后面做权限检查
 */
@WebServlet(name = "BookServlet", urlPatterns = "/manager/bookServlet")
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 添加图书
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取请求的参数，封装成Book对象
        Book book = WebUtils.copyParamToBean(new Book(), request.getParameterMap());
        //2.调用BookService.addBook(Book)方法添加图书
        bookService.addBook(book);
        //避免重复添加问题
        response.sendRedirect(request.getContextPath()+
                "/manager/bookServlet?action=page&pageNo=" + Integer.MAX_VALUE);


    }

    /**
     * 删除图书
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数 id
        Integer id = WebUtils.parseInt(request.getParameter("id"), 0);

        //2.调用bookService.deleteBookById 删除图书
        bookService.deleteBookById(id);
        //3.重定向回图书列表管理页面
        response.sendRedirect(request.getContextPath()+
                "/manager/bookServlet?action=page&pageNo=" +  request.getParameter("pageNo"));


    }

    /**
     * 获取图书
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取请求的参数
        Integer id = WebUtils.parseInt(request.getParameter("id"), 0);
        //2.调用bookService.QueryBokById();
        Book book = bookService.queryBookById(id);
        //3.把图书信息保存到Request域中
        request.setAttribute("book",book);
        //4.请求转发到book_edit.jsp页面
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }

    /**
     * 保存修改后的图书回显给jsp页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //调用请求的全部参数，封装为book对象
        Book book = WebUtils.copyParamToBean(new Book(), request.getParameterMap());
        //调用 bookService.updateBookById (book);
        bookService.updateBookById(book);

        //3.重定向到图书管理页面
        response.sendRedirect(request.getContextPath()+
                "/manager/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
    }
    /**
     * 查询全部图书
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //        1 . 获取请求的参数 ,请求参数已经在父类BaseServlet中
        //        2 . 调用BookService.queryBooks()查询全部图书

                List<Book> books = bookService.queryBooks();
        //        3 . 把图书信息到Request域中
                request.setAttribute("books", books);
        //        4 . 请求转发到 /pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    /**
     * page是分页功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取请求的参数 pageNo :当前所在页数
        // 和pageSize :每页分页的条数
        Integer pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        Integer pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.调用 bookService.page(pageNo,pagSize);
        Page<Book> pages = bookService.page(pageNo, pageSize);

        //设置分页条的请求地址
        pages.setUrl("manager/bookServlet?action=page");

        //3.把page分页对象保存到request域中
        request.setAttribute("page",pages);
        //4.请求转发到/pages/manager/book_manager.jsp
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }
}
