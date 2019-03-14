package com.syj.olb.order.dao.impl;

import com.syj.olb.book.pojo.Expression;
import com.syj.olb.book.pojo.PageBean;
import com.syj.olb.book.pojo.PageConstants;
import com.syj.olb.order.dao.OrderDao;
import com.syj.olb.order.pojo.Order;
import com.syj.olb.order.pojo.OrderItem;
import com.syj.olb.order.pojo.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("OrderDaoImpl")
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderMapper orderMapper;
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
     * 修改订单状态
     *
     * @param oid
     * @param status
     */
    @Override
    public void updateStatus(String oid, int status) {

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
    public void add(Order order) {

    }

    /**
     * 按用户查询订单
     *
     * @param uid
     * @param pc
     * @return
     */
    @Override
    public PageBean<Order> findByUser(String uid, int pc) {
        List<Order> beanList = orderMapper.findByUser(uid, pc);
        for(Order order : beanList) {
            loadOrderItem(order);
        }
        int tr = orderMapper.countByUser(uid);
        /*
         * 5. 创建PageBean，设置参数
         */
        PageBean<Order> pb = new PageBean<Order>();
        /*
         * 其中PageBean没有url，这个任务由Servlet完成
         */
        int ps = PageConstants.ORDER_PAGE_SIZE;//每页记录数
        pb.setBeanList(beanList);
        pb.setPc(pc);
        pb.setPs(ps);
        pb.setTr(tr);
        return pb;
    }

    /**
     * 查询所有
     *
     * @param pc
     */
    @Override
    public PageBean<Order> findAll(int pc) {
        return null;
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

    @Override
    public PageBean<Order> findByCriteria(List<Expression> exprList, int pc) {
        return null;
    }

    @Override
    public void loadOrderItem(Order order) {
        /*
         * 1. 给sql语句select * from t_orderitem where oid=?
         * 2. 执行之，得到List<OrderItem>
         * 3. 设置给Order对象
         */
        List<OrderItem> orderItems = orderMapper.loadOrderItem(order);
        order.setOrderItemList(orderItems);
    }

    /**
     * 把多个Map转换成多个OrderItem
     *
     * @param mapList
     * @return
     */
    @Override
    public List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
        return null;
    }

    @Override
    public OrderItem toOrderItem(Map<String, Object> map) {
        return null;
    }
}
