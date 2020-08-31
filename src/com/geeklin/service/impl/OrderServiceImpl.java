package com.geeklin.service.impl;

import com.geeklin.dao.BookDao;
import com.geeklin.dao.OrderDao;
import com.geeklin.dao.OrderItemDao;
import com.geeklin.dao.impl.BookDaoImpl;
import com.geeklin.dao.impl.OrderDaoImpl;
import com.geeklin.dao.impl.OrderItemDaoImpl;
import com.geeklin.pojo.*;
import com.geeklin.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Date;
import java.util.List;

/**
 * @author Lin
 * @date 2020/8/6 18:02
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    //用于修改商品库存
    private BookDao bookDao = new BookDaoImpl();

    /**
     * 创建订单信息
     * @param cart 购物车
     * @param userId 用户id
     */
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //保证订单号唯一,时间戳加上用户id就能保证生成的订单号唯一
        String orderId = System.currentTimeMillis()+""+userId;
        //保存订单
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);

//        int  i = 10/0;

        //保存订单项
        //遍历所有的商品项，生成订单项，保存到数据库中
        for (CartItem cartItem : cart.getItems().values()){
            //把购物车商品项转化成订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存订单项
            orderItemDao.saveOrderItem(orderItem);


            //每次保存订单项时，都要修改商品库存
            // 得到原来商品信息，
            Book book = bookDao.queryBookById(cartItem.getId());
            //修改库存 原来库存 - 该商品在购物车中的数量
            book.setStock(book.getStock() - cartItem.getCount());
            //修改销量 原来销量+购物车中的数量
            book.setSales(book.getSales() + cartItem.getCount());
            //保存商品信息
            bookDao.updateBookById(book);
        }

        //清空购物车
        cart.clear();

        //返回订单号
        return orderId;

    }

    /**
     * 查询全部订单
     * @return 全部订单信息
     */
    @Override
    public List<Order> queryAllOrders() {

        return orderDao.queryAllOrders();
    }

    /**
     * 根据用户id查询订单
     * @param userId 用户id
     * @return
     */
    @Override
    public List<Order> queryMyOrders(Integer userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    /**
     * 发货，其实就是修改订单状态
     * @param orderId 订单号
     */
    @Override
    public void sendOrder(String orderId) {

        // 1表示已发货，待签收  2表示已签收  0表示未发货
        orderDao.changeOrderStatus(orderId, 1);
    }

    @Override
    public void receiveOrder(String orderId) {
        // 1表示已发货，待签收  2表示已签收  0表示未发货
        orderDao.changeOrderStatus(orderId, 2);

    }

    /**
     * 根据订单号查询订单信息
     * @param orderId 订单号
     * @return
     */
    @Override
    public List<OrderItem> queryOrderDetails(String orderId) {

        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }
}
