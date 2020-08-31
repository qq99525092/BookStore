package com.geeklin.dao;

import com.geeklin.pojo.Order;

import java.util.List;

/**
 * @author Lin
 * @date 2020/8/6 15:35
 */
public interface OrderDao {
    // 保存订单
    public int saveOrder(Order order);

    // 查询全部订单
    public List<Order> queryAllOrders();

    // 根据用户id查询订单
    public List<Order> queryOrdersByUserId(Integer userId);

    // 根据订单号修改订单状态
    public int changeOrderStatus(String orderId, Integer status);
}
