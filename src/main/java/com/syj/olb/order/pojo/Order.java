package com.syj.olb.order.pojo;

import java.util.List;

import com.syj.olb.user.pojo.User;
import lombok.Data;

@Data
public class Order {
	private String oid;//主键
	private String ordertime;//下单时间
	private double total;//总计
	private int status;//订单状态：1未付款, 2已付款但未发货, 3已发货未确认收货, 4确认收货了交易成功, 5已取消(只有未付款才能取消)
	private String address;//收货地址
	private User owner;//订单的所有者
	private List<OrderItem> orderItemList;

	private String uid;
	
}
