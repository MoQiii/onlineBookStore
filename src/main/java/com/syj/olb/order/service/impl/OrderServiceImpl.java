package com.syj.olb.order.service.impl;

import com.syj.olb.book.pojo.PageBean;
import com.syj.olb.order.dao.OrderDao;
import com.syj.olb.order.pojo.Order;
import com.syj.olb.order.service.OrderServcie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("OrderServiceImpl")
public class OrderServiceImpl implements OrderServcie {
    @Resource(name="OrderDaoImpl")
    private OrderDao orderDao;
    /**
     * 修改订单状态
     *
     * @param oid
     * @param status
     */
    @Override
    public void updateStatus(String oid, int status) {

    }

    /**
     * 查询订单状态
     *
     * @param oid
     * @return
     */
    @Override
    public int findStatus(String oid) {
        return 0;
    }

    /**
     * 加载订单
     *
     * @param oid
     * @return
     */
    @Override
    public Order load(String oid) {
        return null;
    }

    /**
     * 生成订单
     *
     * @param order
     */
    @Override
    public void createOrder(Order order) {

    }

    /**
     * 我的订单
     *
     * @param uid
     * @param pc
     * @return
     */
    @Override
    public PageBean<Order> myOrders(String uid, int pc) {
        PageBean<Order> byUser = orderDao.findByUser(uid, pc);
        return byUser;
    }

    /**
     * 按状态查询
     *
     * @param status
     * @param pc
     * @return
     */
    @Override
    public PageBean<Order> findByStatus(int status, int pc) {
        return null;
    }

    /**
     * 查询所有
     *
     * @param pc
     * @return
     */
    @Override
    public PageBean<Order> findAll(int pc) {
        return null;
    }
}
