package com.syj.olb.order.pojo;

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
}
