package com.geeklin.test;

import com.geeklin.pojo.Cart;
import com.geeklin.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author Lin
 * @date 2020/8/4 16:23
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"母猪产后护理I",
                1,new BigDecimal(10000),new BigDecimal(10000)));

        cart.addItem(new CartItem(1,"母猪产后护理I",
                1,new BigDecimal(10000),new BigDecimal(10000)));

        cart.addItem(new CartItem(2,"母猪产后护理II",
                1,new BigDecimal(20000),new BigDecimal(20000)));

        cart.addItem(new CartItem(3,"ThinkPad T490",
                1,new BigDecimal(15000),new BigDecimal(15000)));

        System.out.println(cart);

    }

    @Test
    public void deleteItem() {

        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"母猪产后护理I",
                1,new BigDecimal(10000),new BigDecimal(10000)));

        cart.addItem(new CartItem(1,"母猪产后护理I",
                1,new BigDecimal(10000),new BigDecimal(10000)));

        cart.addItem(new CartItem(2,"母猪产后护理II",
                1,new BigDecimal(20000),new BigDecimal(20000)));

        cart.addItem(new CartItem(3,"ThinkPad T490",
                1,new BigDecimal(15000),new BigDecimal(15000)));


        cart.deleteItem(2);
        System.out.println(cart);

    }

    @Test
    public void clear() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"母猪产后护理I",
                1,new BigDecimal(10000),new BigDecimal(10000)));

        cart.addItem(new CartItem(1,"母猪产后护理I",
                1,new BigDecimal(10000),new BigDecimal(10000)));

        cart.addItem(new CartItem(2,"母猪产后护理II",
                1,new BigDecimal(20000),new BigDecimal(20000)));

        cart.addItem(new CartItem(3,"ThinkPad T490",
                1,new BigDecimal(15000),new BigDecimal(15000)));

        cart.clear();

        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"母猪产后护理I",
                1,new BigDecimal(10000),new BigDecimal(10000)));

        cart.addItem(new CartItem(1,"母猪产后护理I",
                1,new BigDecimal(10000),new BigDecimal(10000)));

        cart.addItem(new CartItem(2,"母猪产后护理II",
                1,new BigDecimal(20000),new BigDecimal(20000)));

        cart.addItem(new CartItem(3,"ThinkPad T490",
                1,new BigDecimal(15000),new BigDecimal(15000)));

        cart.updateCount(3,10);

        System.out.println(cart);

    }
}