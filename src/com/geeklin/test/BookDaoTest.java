package com.geeklin.test;

import com.geeklin.dao.BookDao;
import com.geeklin.dao.impl.BookDaoImpl;
import com.geeklin.pojo.Book;
import com.geeklin.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Lin
 * @date 2020/7/30 21:27
 */
public class BookDaoTest {

    BookDao    bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "整蛊专家", "lin",
                new BigDecimal(9999), 50000, 50, null));

    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(19);
    }

    @Test
    public void updateBookById() {
        bookDao.updateBookById(new Book(2, "整蛊专家", "lin",
                new BigDecimal(9999), 50000, 50, null));
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(21);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        bookDao.queryBooks().forEach(System.out::println);
    }

    /**
     *
     * @return
     */
    @Test
    public  void    queryForPageTotalCount(){
        System.out.println(bookDao.queryForPageTotalCount());
    }


    @Test
    public void queryForPageItems(){

        bookDao.queryForPageItems(2, Page.PAGE_SIZE).forEach(System.out::println);
    }

    @Test
    public void queryForPageItemsByPrice(){

        bookDao.queryForPageItemsByPrice(2, Page.PAGE_SIZE,10,500).forEach(System.out::println);

    }

    @Test
    public  void    queryForPageTotalCountByPrice(){

        System.out.println(bookDao.queryForPageTotalCountByPrice(10, 50));



        }


    }


