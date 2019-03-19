package com.syj.olb.order.dao;

import com.syj.olb.order.pojo.Order;
import com.syj.olb.order.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    public void batchInsert(List<OrderItem> list);
    public List<OrderItem> findByOrder(Order order);
}
