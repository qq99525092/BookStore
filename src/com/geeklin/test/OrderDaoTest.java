package com.geeklin.test;

import com.geeklin.dao.OrderDao;
import com.geeklin.dao.impl.OrderDaoImpl;
import com.geeklin.pojo.Order;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Lin
 * @date 2020/8/6 15:54
 */
public class OrderDaoTest {

    private OrderDao orderDao = new OrderDaoImpl();



    @Test
    public void saveOrder() {

       // orderDao.saveOrder(new Order("116156156",new Date(),new BigDecimal(555.5),0,4));
        orderDao.saveOrder(new Order("116156157",new Date(),new BigDecimal(445.5),0,5));
        orderDao.saveOrder(new Order("116156158",new Date(),new BigDecimal(5155.5),0,6));
        orderDao.saveOrder(new Order("116156159",new Date(),new BigDecimal(335.5),0,5));
        orderDao.saveOrder(new Order("1161561510",new Date(),new BigDecimal(665.5),0,4));
        orderDao.saveOrder(new Order("1161561511",new Date(),new BigDecimal(855.5),0,4));

    }

    @Test
    public void queryAllOrders() {
        orderDao.queryAllOrders().forEach(System.out::println);
    }

    @Test
    public void queryOrdersByUserId() {
        orderDao.queryOrdersByUserId(4).forEach(System.out::println);

      /*  List<Order> orders = orderDao.queryOrdersByUserId(4);
        for (Order order:orders) {
            System.out.println(order);
        }*/
    }

    @Test
    public void changeOrderStatus() {
        orderDao.changeOrderStatus("116156156",1);
    }
}