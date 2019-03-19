package com.syj.olb.order.pojo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderItemMapper {
    @Insert("<script>insert into t_orderitem(orderItemId,quantity,subtotal,bid,oid,currPrice,image_b) values" +
            "<foreach collection='list' item='item' index='index' separator=','>" +
            "(" +
            "#{item.orderItemId},#{item.quantity},#{item.subTotal},#{item.bid},#{item.oid},#{item.currPrice},#{item.image_b}" +
            ")</foreach></script>")
    public void batchInsert(@Param("list") List<OrderItem> list);
    @Select("select * from t_orderitem where oid=#{order.oid}")
    public List<OrderItem> findByOrder(@Param("order") Order order);
}
