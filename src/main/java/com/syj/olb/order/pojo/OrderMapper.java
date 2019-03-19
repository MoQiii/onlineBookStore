package com.syj.olb.order.pojo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {
    @Select("select * from t_order where uid=#{uid}")
    public List<Order> findByUser(@Param("uid")String uid, int pc);

    @Select("select count(*) from t_order where uid=#{uid}")
    public int countByUser(@Param("uid")String uid);
    @Select("select * from t_orderitem where oid=#{order.oid}")
    public List<OrderItem> loadOrderItem(@Param("order") Order order);
    @Insert("insert into t_order values(#{order.oid},#{order.ordertime},#{order.total},#{order.status},#{order.address},#{order.uid})")
    public void add(@Param("order") Order order);
    @Select("select * from t_order where oid=#{oid}")
    public Order load(@Param("oid") String oid);
    @Select("select status from t_order where oid=#{oid}")
    public int findStatus(@Param("oid") String oid);
}
