package com.syj.olb.order.service;

import com.syj.olb.order.pojo.Order;
import com.syj.olb.order.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
    public List<OrderItem> findByOrder(Order order);
}
