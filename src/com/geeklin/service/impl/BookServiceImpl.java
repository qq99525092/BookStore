package com.geeklin.service.impl;

import com.geeklin.dao.BookDao;
import com.geeklin.dao.impl.BookDaoImpl;
import com.geeklin.pojo.Book;
import com.geeklin.pojo.Page;
import com.geeklin.service.BookService;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * @author Lin
 * @date 2020/8/1 10:03
 */
public class BookServiceImpl implements BookService {

    //依赖BookDao去访问数据库

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {

        bookDao.addBook(book);

    }

    @Override
    public void deleteBookById(Integer id) {

        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBookById(Book book) {
        bookDao.updateBookById(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {
        Page page = new Page();


        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);

        //求总页码
        int pageTotal = pageTotalCount / page.getPageSize();
        //如果不能整除，则总页码要加一，以为不够一页的页数也算作一页
        if (pageTotalCount / page.getPageSize() > 0) {
            pageTotal++;
        }
        page.setPageTotal(pageTotal);

        /*
         * 防止超页码查看方法一:
         * 如果当前页码大于总页码，显示最后一页
         * 如果当前页码小于1，显示第一页
         */

        page.setPageNo(pageNo);


        //由公式求得begin的值：（pageNo -1）x 每页数量
        Integer begin = ((page.getPageNo() - 1) * page.getPageSize());

        List<Book> items = bookDao.queryForPageItems(begin, page.getPageSize());
        //设置当前页数据
        page.setItems(items);


        //设置每页显示的数量
        page.setPageSize(pageSize);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page page = new Page();


        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);

        //求总页码
        Integer pageTotal = pageTotalCount / page.getPageSize();
        //如果不能整除，则总页码要加一，以为不够一页的页数也算作一页
        if (pageTotalCount / page.getPageSize() > 0) {
            pageTotal++;
        }
        page.setPageTotal(pageTotal);

        /*
         * 防止超页码查看方法一:
         * 如果当前页码大于总页码，显示最后一页
         * 如果当前页码小于1，显示第一页
         */

        page.setPageNo(pageNo);


        //由公式求得begin的值：（pageNo -1）x 每页数量
        Integer begin = ((page.getPageNo() - 1) * page.getPageSize());

        List<Book> items = bookDao.queryForPageItemsByPrice(begin, page.getPageSize(),min , max);
        //设置当前页数据
        page.setItems(items);


        //设置每页显示的数量
        page.setPageSize(pageSize);

        return page;
    }
}
