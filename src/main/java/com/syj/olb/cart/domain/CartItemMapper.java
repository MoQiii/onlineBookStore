package com.syj.olb.cart.domain;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface CartItemMapper {
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
    @Select("select * from t_cartitem where uid=#{uid} and bid=#{bid}")
    public CartItem findByUidAndBid(@Param("uid") String uid, @Param("bid") String bid);

    /**
     * 修改指定条目的数量
     * @param cartItemId
     * @param quantity
     */
    @Update("update t_cartitem set quantity=#{quantity} where cartItemId=#{cartItemId}")
    public void updateQuantity(@Param("cartItemId") String cartItemId, @Param("quantity") int quantity);
    /**
     * 添加条目
     * @param cartItem
     */
    @Insert("insert into t_cartitem(cartItemId, quantity, bid, uid) values(#{cartItem.cartItemId},#{cartItem.quantity},#{cartItem.book.bid},#{cartItem.user.uid})")
    public void addCartItem(@Param("cartItem") CartItem cartItem);

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
    @Select("select * from t_cartitem c, t_book b where c.bid=b.bid and uid=#{uid} order by c.orderBy")
    public List<CartItem> findByUser(String uid);

   // @Select("select * from t_cartitem where cartItemId=#{id}")
    public CartItem findById(/*@Param("id")*/ String id);
}
