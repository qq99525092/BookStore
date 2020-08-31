package com.geeklin.pojo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Lin
 * @date 2020/8/4 15:55
 */

// 购物车类
public class Cart {

//    //总商品数量
//    private Integer totalCount;
//    //总商品金额
//    private BigDecimal totalPrice;

    /**
     * 购物车中的商品信息 <br>
     * key是商品编号 <br/>
     * value是商品信息CartItem <br/>
     */
    private Map<Integer, CartItem> items = new HashMap<>();

    /**
     * 在购物车中添加商品项
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        //先判断购物车内是否存在要添加的商品
        CartItem it = items.get(cartItem.getId());
        //如果该对象为空，表示购物车内原先没有该商品
        if (it == null) {
            items.put(cartItem.getId(), cartItem);
        } else {
            //否则，商品存在，修改商品数量和价格
            it.setCount(it.getCount() + 1);
            //修改商品总价格
            it.setTotalPrice(it.getPrice().multiply(new BigDecimal(it.getCount())));
        }

    }

    /**
     * 根据商品号删除商品
     *
     * @param id
     */
    public void deleteItem(Integer id) {
        items.remove(id);

    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }


    /**
     * 更新商品数量
     *
     * @param id    商品号
     * @param count 新的商品数量
     */
    public void updateCount(Integer id, Integer count) {

        //获取商品项
        CartItem it = items.get(id);

        if (it != null) {
            //如果商品存在，就修改商品数量
            it.setCount(count);

            //修改商品价格 .multiply
            it.setTotalPrice(it.getPrice().multiply(new BigDecimal(it.getCount())));
        }

    }


    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }


    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (CartItem cartItem : items.values()) {
            totalCount += cartItem.getCount();
        }

        return totalCount;
    }



    public BigDecimal getTotalPrice() {

        BigDecimal totalPrice = new BigDecimal(0);

        for (CartItem cartItem : items.values()) {

            totalPrice = totalPrice.add(cartItem.getPrice());
        }

        return totalPrice;
    }



    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Cart() {
    }
}
