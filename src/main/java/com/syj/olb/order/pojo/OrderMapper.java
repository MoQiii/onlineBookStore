package com.syj.olb.order.pojo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OrderMapper {
    @Select("select * from t_order where uid=#{uid} order by ordertime desc")
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
    @Select("select * from t_order where 1=1 order by ordertime desc limit #{pc},#{ps}")
    public List<Order> findAll(@Param("pc") int pc,@Param("ps")int ps);
    @Select("select * from t_order where status=#{status} order by ordertime desc limit #{pc},#{ps}")
    public List<Order> findByStatus(@Param("status")int status, @Param("pc")int pc,@Param("ps")int ps);
    @Update("update t_order set status=#{status} where oid=#{oid}")
    public void updateStatus(@Param("oid") String oid , @Param("status")int status);
}
