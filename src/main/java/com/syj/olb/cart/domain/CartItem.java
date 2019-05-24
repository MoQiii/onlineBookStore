package com.syj.olb.cart.domain;

import com.syj.olb.book.pojo.Book;
import com.syj.olb.user.pojo.User;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

@Data
@Alias("CartItem")
public class CartItem {
	private String cartItemId;// 主键
	private int quantity;// 数量
	private Book book;// 条目对应的图书
	private User user;// 所属用户
	private String bid;// 条目对应的图书
	private String uid;// 所属用户
	private BigDecimal price;// 所属用户
	private BigDecimal subTotal;
	private String subTotalStr;
	private int orderBy;
}
