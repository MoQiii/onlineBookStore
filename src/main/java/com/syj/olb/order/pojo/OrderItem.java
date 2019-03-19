package com.syj.olb.order.pojo;

import com.syj.olb.book.pojo.Book;
import lombok.Data;

@Data
public class OrderItem {
	private String orderItemId;//主键
	private int quantity;//数量
	private double subTotal;//小计
	private Book book;//所关联的Book
	private Order order;//所属的订单
	private Double currPrice;
	private String bid;
	private String oid;
	private String image_b;
}
