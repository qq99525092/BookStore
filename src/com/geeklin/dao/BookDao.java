package com.geeklin.dao;

import com.geeklin.pojo.Book;

import java.util.List;

/**
 * @author Lin
 * @date 2020/7/30 20:45
 */
public interface BookDao {

    /**
     * 添加图书
     * @param book
     * @return
     */
    public int addBook(Book book);

    /**
     * 根据id删除图书
     * @param id
     * @return
     */
    public int deleteBookById(Integer id);

    /**
     * 根据id更新图书
     * @param book
     * @return
     */

    public int  updateBookById(Book book);

    /**
     * 根据指定的id查询指定的图书
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 查询全部图书
     * @return
     */
    public List<Book> queryBooks();


    /**
     *
     * @return
     */
   public   Integer queryForPageTotalCount();

    /**
     *
     * @param begin
     * @param pageSize
     * @return
     */
   public List<Book> queryForPageItems(int begin, Integer pageSize);

 public    Integer queryForPageTotalCountByPrice(int min, int max);


  public   List<Book> queryForPageItemsByPrice(int begin, Integer pageSize, Integer min, Integer max);
}
