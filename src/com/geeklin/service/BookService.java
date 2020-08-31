package com.geeklin.service;

import com.geeklin.dao.BaseDao;
import com.geeklin.pojo.Book;
import com.geeklin.pojo.Page;

import java.util.List;

/**
 * @author Lin
 * @date 2020/8/1 9:56
 */
public interface  BookService {

    /**
     * 添加图书
     * @param book
     */
    public void addBook(Book book);

    /**
     * 根据id删除图书
     * @param id
     */
    public void deleteBookById(Integer id);

    /**
     * 根据id更新图书
     * @param book
     */
    public void updateBookById(Book book);

    /**
     * 根据id查询图书
     * @param id
     */
    public Book queryBookById(Integer id);

    /**
     * 查询所有图书
     * @return
     */
    public List<Book> queryBooks();

    /***
     * 分页方法
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Book> page(Integer pageNo,Integer pageSize);


  public   Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
