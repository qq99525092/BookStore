package com.geeklin.service;

import com.geeklin.pojo.Cart;
import com.geeklin.pojo.Order;
import com.geeklin.pojo.OrderItem;
import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.geometry.VPos;

import java.util.List;

/**
 * @author Lin
 * @date 2020/8/6 17:49
 */
public interface OrderService {

    /**
     * 创建订单
     * @param cart 购物车
     * @param userId 用户id
     */
    public String createOrder(Cart cart ,Integer userId);

    /**
     * 管理员查询全部订单
     * @return
     */
    public List<Order> queryAllOrders();

    /**
     * 根据用户id查询个人订单
     * @param userId 用户id
     * @return
     */
    public List<Order> queryMyOrders(Integer userId);

    /**
     * 管理员发货，根据订单号发货
     * @param orderId 订单号
     */
    public void sendOrder(String orderId);

    /**
     * 用户签收货品
     * @param orderId 订单号
     */
    public void receiveOrder(String orderId);


    /**
     * 根据订单号查询订单详情
     * @param orderId 订单号
     * @return
     */
    public List<OrderItem> queryOrderDetails(String orderId);


}
