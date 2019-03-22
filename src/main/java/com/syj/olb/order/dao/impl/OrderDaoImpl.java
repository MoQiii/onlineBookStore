package com.syj.olb.order.dao.impl;

import com.syj.olb.book.pojo.*;
import com.syj.olb.order.dao.OrderDao;
import com.syj.olb.order.pojo.Order;
import com.syj.olb.order.pojo.OrderItem;
import com.syj.olb.order.pojo.OrderItemMapper;
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
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private BookMapper bookMapper;
    /**
     * 查询订单状态
     *
     * @param oid
     * @return
     */
    @Override
    public int findStatus(String oid) {

        return orderMapper.findStatus(oid);
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
        Order order = orderMapper.load(oid);
        List<OrderItem> orderItems = orderItemMapper.findByOrder(order);
        order.setOrderItemList(orderItems);
        List<String> list=new ArrayList<>();
        for(OrderItem orderItem:orderItems){
            list.add(orderItem.getBid());
        }
        List<Book> books = bookMapper.findByBids(list);
        for(int i=0;i<orderItems.size();i++){
            orderItems.get(i).setBook(books.get(i));
        }
        return order;
    }

    /**
     * 生成订单
     *
     * @param order
     */
    @Override
    public void add(Order order) {
        orderMapper.add(order);
        orderItemMapper.batchInsert(order.getOrderItemList());
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
    public List<Order> findAll(int pc) {
        List<Order> all = orderMapper.findAll(pc - 1, pc * 8);
        for(Order order:all){
            List<OrderItem> byOrder = orderItemMapper.findByOrder(order);
            List<String> list=new ArrayList<>();
            for (OrderItem orderItem:byOrder)
            {
                list.add(orderItem.getBid());
            }
            List<Book> books = bookMapper.findByBids(list);
            for (int i=0;i<byOrder.size();i++)
            {
                byOrder.get(i).setBook(books.get(i));
            }
            order.setOrderItemList(byOrder);
        }
        return all;
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
        List<Order> byStatus = orderMapper.findByStatus(status, (pc - 1) * 8, 8);
        PageBean<Order> pb=new PageBean();
        pb.setBeanList(byStatus);
        pb.setPc(pc);
        pb.setPs(8);
        return pb;
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
