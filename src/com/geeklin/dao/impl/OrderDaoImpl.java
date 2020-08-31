package com.geeklin.dao.impl;

import com.geeklin.dao.BaseDao;
import com.geeklin.dao.OrderDao;
import com.geeklin.pojo.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

/**
 * @author Lin
 * @date 2020/8/6 15:37
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    /**
     * 保存订单
     *
     * @param order
     * @return
     */
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }

    /**
     * 查询全部订单
     *
     * @return
     */
    @Override
    public List<Order> queryAllOrders() {
        String sql = "select  `order_id` orderId ,`create_time` createTime,`price`,`status`,`user_id` UserID from t_order";

        return queryForList(Order.class, sql);
    }

    /**
     * 根据用户ID查询订单
     * @param userId
     * @return
     */
    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        String sql = "select  `order_id` orderId ,`create_time` createTime,`price`,`status`,`user_id` UserID from t_order where user_id = ?";
        return queryForList(Order.class, sql, userId);
    }

    /**
     * 修改订单状态
     * @param orderId
     * @param status
     * @return
     */
    @Override
    public int changeOrderStatus(String orderId, Integer status) {

        String sql = "update t_order set  status = ? where order_id = ? ";
        return update(sql,status,orderId);
    }
}
