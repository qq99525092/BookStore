package com.geeklin.dao;

import com.geeklin.pojo.OrderItem;

import java.util.List;

/**
 * @author Lin
 * @date 2020/8/6 15:46
 */
public interface OrderItemDao {

    // 保存订单项
    public int saveOrderItem(OrderItem orderItem);

    // 根据订单号,查询订单明细
    public List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
