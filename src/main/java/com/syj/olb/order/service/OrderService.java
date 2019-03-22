package com.syj.olb.order.service;

import com.syj.olb.book.pojo.PageBean;
import com.syj.olb.order.pojo.Order;

import java.util.List;

public interface OrderService {
    /**
     * 修改订单状态
     * @param oid
     * @param status
     */
    public void updateStatus(String oid, int status);
    /**
     * 查询订单状态
     * @param oid
     * @return
     */
    public int findStatus(String oid);
    /**
     * 加载订单
     * @param oid
     * @return
     */
    public Order load(String oid);
    /**
     * 生成订单
     * @param order
     */
    public void createOrder(Order order);
    /**
     * 我的订单
     * @param uid
     * @param pc
     * @return
     */
    public PageBean<Order> myOrders(String uid, int pc);

    /**
     * 按状态查询
     * @param status
     * @param pc
     * @return
     */
    public PageBean<Order> findByStatus(int status, int pc);
    /**
     * 查询所有
     * @param pc
     * @return
     */
    public List<Order> findAll(int pc);



}
