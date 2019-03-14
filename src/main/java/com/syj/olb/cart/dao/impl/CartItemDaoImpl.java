package com.syj.olb.cart.dao.impl;

import com.syj.olb.cart.dao.CartItemDao;
import com.syj.olb.cart.domain.CartItem;
import com.syj.olb.cart.domain.CartItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("cartItemDaoImpl")
public class CartItemDaoImpl implements CartItemDao {
    @Autowired
    private CartItemMapper cartItemMapper;
    @Override
    public String toWhereSql(int len) {
        return null;
    }

    /**
     * 加载多个CartItem
     *
     * @param cartItemIds
     * @return
     */
    @Override
    public List<CartItem> loadCartItems(String cartItemIds) {
        return null;
    }

    /**
     * 按id查询
     *
     * @param cartItemId
     * @return
     */
    @Override
    public CartItem findByCartItemId(String cartItemId) {
        return null;
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public void batchDelete(List<String> ids) {
        cartItemMapper.batchDelete(ids);
    }

    /**
     * 查询某个用户的某本图书的购物车条目是否存在
     *
     * @param uid
     * @param bid
     */
    @Override
    public CartItem findByUidAndBid(String uid, String bid) {
        CartItem byUidAndBid = cartItemMapper.findByUidAndBid(uid, bid);
        return byUidAndBid;
    }

    /**
     * 修改指定条目的数量
     *
     * @param cartItemId
     * @param quantity
     */
    @Override
    public void updateQuantity(String cartItemId, int quantity) {
        cartItemMapper.updateQuantity(cartItemId,quantity);
    }

    /**
     * 添加条目
     *
     * @param cartItem
     */
    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemMapper.addCartItem(cartItem);
    }

    @Override
    public CartItem toCartItem(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<CartItem> toCartItemList(List<Map<String, Object>> mapList) {
        return null;
    }

    /**
     * 通过用户查询购物车条目
     *
     * @param uid
     * @return
     */
    @Override
    public List<CartItem> findByUser(String uid) {
        List<CartItem> byUser = cartItemMapper.findByUser(uid);
        return byUser;
    }

    @Override
    public CartItem findById(String id) {
        CartItem cartItem = cartItemMapper.findById(id);
        return cartItem;
    }
}
