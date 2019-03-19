package com.syj.olb.cart.service;

import com.syj.olb.cart.domain.CartItem;

import java.util.List;

public interface CartItemService {

    /*
     * 加载多个CartItem
     */
    public List<CartItem> loadCartItems(String cartItemIds);

    /**
     * 修改购物车条目数量
     * @return
     */
    public void updateQuantity(CartItem cartItem);

    /**
     * 批量删除功能
     * @param ids
     */
    public void batchDelete(List<String> ids);

    /**
     * 添加条目
     * @param cartItem
     */
    public void add(CartItem cartItem);
    /**
     * 我的购物车功能
     * @param uid
     * @return
     */
    public List<CartItem> myCart(String uid);

    public CartItem findById( String id);
}
