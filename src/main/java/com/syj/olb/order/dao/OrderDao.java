package com.syj.olb.order.dao;

import com.syj.olb.book.pojo.Expression;
import com.syj.olb.book.pojo.PageBean;
import com.syj.olb.order.pojo.Order;
import com.syj.olb.order.pojo.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    /**
     * 查询订单状态
     * @param oid
     * @return
     */
    public int findStatus(String oid);
    /**
     * 修改订单状态
     * @param oid
     * @param status
     */
    public void updateStatus(String oid, int status);
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
    public void add(Order order);
    /**
     * 按用户查询订单
     * @param uid
     * @param pc
     * @return
     */
    public PageBean<Order> findByUser(String uid, int pc);
    /**
     * 查询所有
     */
    public List<Order> findAll(int pc);
    /**
     * 按状态查询
     * @param status
     * @param pc
     * @return
     */
    public PageBean<Order> findByStatus(int status, int pc);
    public PageBean<Order> findByCriteria(List<Expression> exprList, int pc);
    /*
     * 为指定的order载它的所有OrderItem
     */
    public void loadOrderItem(Order order);
    /**
     * 把多个Map转换成多个OrderItem
     * @param mapList
     * @return
     */
    public List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList);
    /*
     * 把一个Map转换成一个OrderItem
     */
    public OrderItem toOrderItem(Map<String, Object> map);
}
