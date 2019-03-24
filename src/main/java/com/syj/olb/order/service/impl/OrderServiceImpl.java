package com.syj.olb.order.service.impl;

import com.syj.olb.book.pojo.PageBean;
import com.syj.olb.order.dao.OrderDao;
import com.syj.olb.order.pojo.Order;
import com.syj.olb.order.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("OrderServiceImpl")
@Transactional
public class OrderServiceImpl implements OrderService {
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
        orderDao.updateStatus(oid,status);
    }

    /**
     * 查询订单状态
     *
     * @param oid
     * @return
     */
    @Override
    public int findStatus(String oid) {
        return orderDao.findStatus(oid);
    }

    /**
     * 加载订单
     *
     * @param oid
     * @return
     */
    @Override
    public Order load(String oid) {
        return orderDao.load(oid);
    }

    /**
     * 生成订单
     *
     * @param order
     */
    @Override
    public void createOrder(Order order) {
        orderDao.add(order);
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

        return orderDao.findByStatus(status,pc);
    }

    /**
     * 查询所有
     *
     * @param pc
     * @return
     */
    @Override
    public List<Order> findAll(int pc) {

        return orderDao.findAll(pc);
    }
}
