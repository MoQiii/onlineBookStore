package com.syj.olb.cart.dao;

import com.syj.olb.cart.domain.CartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CartItemDao {
    /*
     * 用来生成where子句
     */
    public String toWhereSql(int len);
    /**
     * 加载多个CartItem
     * @param cartItemIds
     * @return
     */
    public List<CartItem> loadCartItems(String cartItemIds);
    /**
     * 按id查询
     * @param cartItemId
     * @return
     */
    public CartItem findByCartItemId(String cartItemId);
    /**
     * 批量删除
     * @param ids
     */
    public void batchDelete(List<String> ids);
    /**
     * 查询某个用户的某本图书的购物车条目是否存在
     */
    public CartItem findByUidAndBid(String uid, String bid);

    /**
     * 修改指定条目的数量
     * @param cartItemId
     * @param quantity
     */
    public void updateQuantity(CartItem cartItem);
    /**
     * 添加条目
     * @param cartItem
     */
    public void addCartItem(CartItem cartItem);

    /*
     * 把一个Map映射成一个Cartitem
     */
    public CartItem toCartItem(Map<String,Object> map);

    /*
     * 把多个Map(List<Map>)映射成多个CartItem(List<CartItem>)
     */
    public List<CartItem> toCartItemList(List<Map<String,Object>> mapList);

    /**
     * 通过用户查询购物车条目
     * @param uid
     * @return
     */
    public List<CartItem> findByUser(String uid);

    public CartItem findById( String id);
}
