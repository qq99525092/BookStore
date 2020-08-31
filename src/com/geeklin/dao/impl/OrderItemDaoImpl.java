package com.geeklin.dao.impl;

import com.geeklin.dao.BaseDao;
import com.geeklin.dao.OrderItemDao;
import com.geeklin.pojo.Order;
import com.geeklin.pojo.OrderItem;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

/**
 * @author Lin
 * @date 2020/8/6 15:52
 */
public class OrderItemDaoImpl  extends BaseDao implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";

        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {

        String sql = "select `id`,`name`,`count`,`price`,`total_price` totalPrice,`order_id` orderId  " +
                "from t_order_item where order_id = ?";
        return queryForList(OrderItem.class, sql, orderId);
    }
}
