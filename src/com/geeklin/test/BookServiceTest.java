package com.geeklin.test;

import com.geeklin.pojo.Book;
import com.geeklin.pojo.Page;
import com.geeklin.service.BookService;
import com.geeklin.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;

/**
 * @author Lin
 * @date 2020/8/1 10:07
 */
public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"独孤九剑","未知",new BigDecimal(9999),99999,1,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(10);
    }

    @Test
    public void updateBookById() {
        bookService.updateBookById(new Book(12,"Music","国哥",new BigDecimal(55),0   ,20 ,null));
    }

    @Test
    public void queryBookById() {
        Book book = bookService.queryBookById(22);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();

        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }



    @Test
    public void page(){
        Page<Book> page = bookService.page(1, Page.PAGE_SIZE);
        System.out.println(page);
    }


    @Test
    public void pageByPrice(){
        Page<Book> page = bookService.pageByPrice(1, Page.PAGE_SIZE,10,500);
        System.out.println(page);
    }


}