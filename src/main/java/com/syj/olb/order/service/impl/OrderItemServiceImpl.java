package com.syj.olb.order.service.impl;

import com.syj.olb.order.dao.OrderItemDao;
import com.syj.olb.order.pojo.Order;
import com.syj.olb.order.pojo.OrderItem;
import com.syj.olb.order.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Resource(name="orderItemDaoImpl")
    private OrderItemDao orderItemDao;
    @Override
    public List<OrderItem> findByOrder(Order order) {

        return orderItemDao.findByOrder(order);
    }
}
