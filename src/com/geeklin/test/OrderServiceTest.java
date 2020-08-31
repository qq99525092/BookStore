package com.geeklin.test;

import com.geeklin.pojo.Cart;
import com.geeklin.pojo.CartItem;
import com.geeklin.pojo.Order;
import com.geeklin.service.OrderService;
import com.geeklin.service.impl.OrderServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author Lin
 * @date 2020/8/6 18:23
 */
public class OrderServiceTest {

    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {

        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"母猪产后护理I",
                1,new BigDecimal(10000),new BigDecimal(10000)));

        cart.addItem(new CartItem(1,"母猪产后护理I",
                1,new BigDecimal(10000),new BigDecimal(10000)));

        cart.addItem(new CartItem(2,"母猪产后护理II",
                1,new BigDecimal(20000),new BigDecimal(20000)));

        cart.addItem(new CartItem(3,"ThinkPad T490",
                1,new BigDecimal(15000),new BigDecimal(15000)));

        // 购物车对象 ，用户id
        orderService.createOrder(cart,5);

    }

    @Test
    public void queryAllOrders() {
        for (Order order : orderService.queryAllOrders()){
            System.out.println(order);

        }

    }

    /**
     * 根据用户id查询订单号
     */
    @Test
    public void queryMyOrders() {
        orderService.queryMyOrders(5).forEach(System.out::println);
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder("15967098680804");
    }

    @Test
    public void receiveOrder() {
        orderService.receiveOrder("15967098680804");
    }

    @Test
    public void queryOrderDetails() {
        orderService.queryOrderDetails("15967098680804").forEach(System.out::println);
    }
}