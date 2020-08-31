package com.geeklin.test;

import com.geeklin.dao.OrderItemDao;
import com.geeklin.dao.impl.OrderItemDaoImpl;
import com.geeklin.pojo.OrderItem;
import org.junit.Test;

import java.beans.BeanInfo;
import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author Lin
 * @date 2020/8/6 16:02
 */
public class OrderItemDaoTest {

    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"一千年",10000,new BigDecimal(100),new BigDecimal(10000),"116156156"));
        orderItemDao.saveOrderItem(new OrderItem(null,"我爱罗",10000,new BigDecimal(100),new BigDecimal(18800),"116156158"));
        orderItemDao.saveOrderItem(new OrderItem(null,"鸣人",10000,new BigDecimal(100),new BigDecimal(10400),"116156158"));
        orderItemDao.saveOrderItem(new OrderItem(null,"哪路都",10000,new BigDecimal(100),new BigDecimal(10100),"1161561510"));
        orderItemDao.saveOrderItem(new OrderItem(null,"无所谓",10000,new BigDecimal(100),new BigDecimal(20100),"116156156"));
        orderItemDao.saveOrderItem(new OrderItem(null,"一千年",10000,new BigDecimal(100),new BigDecimal(10000),"116156156"));
    }

    @Test
    public void queryOrderItemsByOrderId() {

        orderItemDao.queryOrderItemsByOrderId("116156156").forEach(System.out::println);
    }
}