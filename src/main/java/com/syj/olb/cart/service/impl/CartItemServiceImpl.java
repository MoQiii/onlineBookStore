package com.syj.olb.cart.service.impl;

import com.syj.olb.cart.dao.CartItemDao;
import com.syj.olb.cart.domain.CartItem;
import com.syj.olb.cart.service.CartItemService;
import com.syj.olb.comon.CommonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cartItemServiceImpl")
public class CartItemServiceImpl implements CartItemService {

    @Resource(name="cartItemDaoImpl")
    private CartItemDao cartItemDao;
    @Override
    public List<CartItem> loadCartItems(String cartItemIds) {
        return null;
    }

    /**
     * 修改购物车条目数量
     *
     * @param cartItemId
     * @param quantity
     * @return
     */
    @Override
    public void updateQuantity(String cartItemId, int quantity) {
        cartItemDao.updateQuantity(cartItemId,quantity);
    }

    /**
     * 批量删除功能
     *
     * @param ids
     */
    @Override
    public void batchDelete(List<String> ids) {
        cartItemDao.batchDelete(ids);
    }

    /**
     * 添加条目
     *
     * @param cartItem
     */
    @Override
    public void add(CartItem cartItem) {
        /*
         * 1. 使用uid和bid去数据库中查询这个条目是否存在
         */
        CartItem _cartItem = cartItemDao.findByUidAndBid(
                cartItem.getUser().getUid(),
                cartItem.getBook().getBid());
        if(_cartItem == null) {//如果原来没有这个条目，那么添加条目
            cartItem.setCartItemId(CommonUtils.uuid());
            cartItemDao.addCartItem(cartItem);
        } else {//如果原来有这个条目，修改数量
            // 使用原有数量和新条目数量之各，来做为新的数量
            int quantity = cartItem.getQuantity() + _cartItem.getQuantity();
            // 修改这个老条目的数量
            cartItemDao.updateQuantity(_cartItem.getCartItemId(), quantity);
        }
    }

    /**
     * 我的购物车功能
     *
     * @param uid
     * @return
     */
    @Override
    public List<CartItem> myCart(String uid) {
        List<CartItem> byUser = cartItemDao.findByUser(uid);
        return byUser;
    }

    @Override
    public CartItem findById(String id) {
        CartItem cartItem = cartItemDao.findById(id);
        return cartItem;
    }
}
