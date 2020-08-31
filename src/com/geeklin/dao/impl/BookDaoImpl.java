package com.geeklin.dao.impl;

import com.geeklin.dao.BaseDao;
import com.geeklin.dao.BookDao;
import com.geeklin.pojo.Book;

import java.util.List;

/**
 * @author Lin
 * @date 2020/7/30 20:50
 */
public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public int addBook(Book book) {
        String sql= "insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";

        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBookById(Book book) {

        String sql= "update t_book set `name` = ? ,`author`= ?,`price`= ?,`sales` = ?,`stock` = ?,`img_path`= ? where id = ?";

        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(),book.getId());

    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath ,`id` from t_book where id = ?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select * from t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(id) from t_book";
        Number number = (Number) queryForSingleValue(sql );
        return number.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, Integer pageSize) {

        String sql = "select `name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath ,`id` from t_book limit ?,?";

        return queryForList(Book.class, sql, begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(id) from t_book where price  between ? and ?";
        Number number = (Number) queryForSingleValue(sql,min,max );
        return number.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, Integer pageSize, Integer min, Integer max) {
        String sql = "select `name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath ,`id` " +
                "from t_book where price  between ? and ? order by price  limit ? , ?";

        return queryForList(Book.class, sql,min ,max , begin,pageSize);
    }
}
