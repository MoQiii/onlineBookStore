package com.syj.olb.order.dao.impl;

import com.syj.olb.order.dao.OrderItemDao;
import com.syj.olb.order.pojo.Order;
import com.syj.olb.order.pojo.OrderItem;
import com.syj.olb.order.pojo.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Override
    public void batchInsert(List<OrderItem> list) {

    }

    @Override
    public List<OrderItem> findByOrder(Order order) {

        return orderItemMapper.findByOrder(order);
    }
}
