<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>cartlist.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	 <script type="text/javascript" src="<c:url value='/jQuery/jquery-3.1.1.min.js'/>"></script>
	<script src="<c:url value='/js/round.js'/>"></script>
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/JSP/jsps/css/cart/list.css'/>">
<script type="text/javascript">
$(function() {
	showTotal();//计算总计
	
	/*
	给全选添加click事件
	*/
	$("#selectAll").click(function() {
		/*
		1. 获取全选的状态
		*/
	//	var bool = $("#selectAll").attr("checked");
	//	alert($("#selectAll").attr("checked"));
        if ($("#selectAll").attr("checked")=="checked") {
            $("#selectAll").attr("checked",false);
            $("input[name='checkboxBtn']").attr("checked", false);
            setJieSuan(false);
            showTotal();
        } else {
            $("#selectAll").attr("checked",true);
            $("input[name='checkboxBtn']").attr("checked",true );
            setJieSuan(true);
            showTotal();
        }
		/*
		2. 让所有条目的复选框与全选的状态同步
		*/
//		setItemCheckBox(bool);
		/*
		3. 让结算按钮与全选同步
		*/
   //     setJieSuan(bool);
		/*
		4. 重新计算总计
		*/

	});
	
	/*
	给所有条目的复选框添加click事件
	*/
	$("input[name='checkboxBtn']").click(function() {
		var all = $("input[name='checkboxBtn']").length;//所有条目的个数
		var select = $("input[name='checkboxBtn'][checked=true]").length;//获取所有被选择条目的个数
        var select2 = $("input[name='checkboxBtn'][checked='checked']").length;//获取所有被选择条目的个数

		if(all == select || all == select2) {//全都选中了
			$("#selectAll").attr("checked", true);//勾选全选复选框
		//	setJieSuan(true);//让结算按钮有效
		} else if(select == 0 || select2 == 0) {//谁都没有选中
			$("#selectAll").attr("checked", false);//取消全选
	//		setJieSuan(false);//让结算失效
		} else {
			$("#selectAll").attr("checked", false);//取消全选
		//	setJieSuan(true);//让结算有效
		}
		showTotal();//重新计算总计
	});
	
	/*
	给减号添加click事件
	*/
	$(".jian").click(function() {
		// 获取cartItemId
		var id = $(this).attr("id").substring(0, 32);
		// 获取输入框中的数量
		var quantity = $("#" + id + "Quantity").val();
		// 判断当前数量是否为1，如果为1,那就不是修改数量了，而是要删除了。
		if(quantity == 1) {
			if(confirm("您是否真要删除该条目？")) {
                $.ajax({
                    url:"http://localhost:8080/Cart/batchDelete",
					data:{cartItemIds:id},
					type: "post",
					success:function () {
                        window.location.href="http://localhost:8080/Cart/myCart";
                    }
                });
			}
		} else {
            var price=$("#currPrice").text();
			sendUpdateQuantity(id, quantity-1,price);
		}
	});
	
	// 给加号添加click事件
	$(".jia").click(function() {
		// 获取cartItemId
		var id = $(this).attr("id").substring(0, 32);
		// 获取输入框中的数量
		var quantity = $("#" + id + "Quantity").val();
		var price=$("#currPrice").text();
		sendUpdateQuantity(id, Number(quantity)+1,price);
	});
});

// 请求服务器，修改数量。
function sendUpdateQuantity(id, quantity,price) {
    var subtotal="";
	$.ajax({
		async:false,
		cache:false,
		url:"http://localhost:8080/Cart/updateQuantity",
		data:{cartItemId:id,quantity:quantity,price:price},
		type:"POST",
		dataType:"json",
		success:function(result) {
            //1. 修改数量
			$("#" + id + "Quantity").val(result.quantity);
			//2. 修改小计
			$("#" + id + "Subtotal").text(result.subTotal);
			//3. 重新计算总计
			showTotal();
		},
		error:function () {
			alert("error");
        }
	});
}

/*
 * 计算总计
 */
function showTotal() {
	var total1 = 0;
    var total2 = 0;
	/*
	1. 获取所有的被勾选的条目复选框！循环遍历之
	*/
	$("input[name='checkboxBtn'][checked='checked']").each(function() {
		//2. 获取复选框的值，即其他元素的前缀
		var id = $(this).val();
		//3. 再通过前缀找到小计元素，获取其文本
		var text = $("#" + id + "Subtotal").text();
		//4. 累加计算
		total1 += Number(text);
	});
    $("input[name='checkboxBtn'][checked=true]").each(function() {
        //2. 获取复选框的值，即其他元素的前缀
        var id = $(this).val();
        //3. 再通过前缀找到小计元素，获取其文本
        var text = $("#" + id + "Subtotal").text();
        //4. 累加计算
        total2 += Number(text);
    });
	// 5. 把总计显示在总计元素上
	if(total1!=0){
        $("#total").text(round(total1, 2));//round()函数的作用是把total保留2位
    }else{
        $("#total").text(round(total2, 2));//round()函数的作用是把total保留2位
    }
}

/*
 * 统一设置所有条目的复选按钮
 */
function setItemCheckBox(bool) {
	$("input[name='checkboxBtn']").attr("checked", bool);
}

/*
 * 设置结算按钮样式
 */
function setJieSuan(bool) {
	if(bool) {
		$("#jiesuan").removeClass("kill").addClass("jiesuan");
		$("#jiesuan").unbind("click");//撤消当前元素止所有click事件
	} else {
		$("#jiesuan").removeClass("jiesuan").addClass("kill");
		$("#jiesuan").click(function() {return false;});
	}
	
}

/*
 * 批量删除
 */
function batchDelete() {
	// 1. 获取所有被选中条目的复选框
	// 2. 创建一数组，把所有被选中的复选框的值添加到数组中
	// 3. 指定location为CartItemServlet，参数method=batchDelete，参数cartItemIds=数组的toString()
	var cartItemIdArray = new Array();
	$("input[name='checkboxBtn'][checked=true]").each(function() {
		cartItemIdArray.push($(this).val());//把复选框的值添加到数组中
	});
	if(cartItemIdArray.length==0){
        $("input[name='checkboxBtn'][checked='checked']").each(function() {
            cartItemIdArray.push($(this).val());//把复选框的值添加到数组中
        });
	}
	$.ajax({
	    url : "http://localhost:8080/Cart/batchDelete?cartItemIds=" + cartItemIdArray.join(),
		type : "post",
		data: {cartItemIds : cartItemIdArray.join()},
		success:function () {
			alert("删除成功");
			window.location.href="http://localhost:8080/Cart/myCart";
        },
		error:function () {
			alert("操作失败");
        }
});
//	location = "http://localhost/goods/Cart/batchDelete?cartItemIds=" + cartItemIdArray.join();
}

/*
 * 结算
 */
function jiesuan() {
	// 1. 获取所有被选择的条目的id，放到数组中
	var cartItemIdArray = new Array();
	$("input[name='checkboxBtn'][checked=true]").each(function() {
		cartItemIdArray.push($(this).val());//把复选框的值添加到数组中
	});	
	// 2. 把数组的值toString()，然后赋给表单的cartItemIds这个hidden
	$("#cartItemIds").val(cartItemIdArray.toString());
	// 把总计的值，也保存到表单中
	$("#hiddenTotal").val($("#total").text());
	// 3. 提交这个表单
	$("#jieSuanForm").submit();
}

function sleep(n) { //n表示的毫秒数
    var start = new Date().getTime();
    while (true) if (new Date().getTime() - start > n) break;
}

</script>
  </head>
  <body>

<c:choose>
	<c:when test="${empty cartItemList }">
	<table width="95%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td align="right">
				<img align="top" src="<c:url value='/images/icon_empty.png'/>"/>
			</td>
			<td>
				<span class="spanEmpty">您的购物车中暂时没有商品</span>
			</td>
		</tr>
	</table>  
	</c:when>
	<c:otherwise>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
	<tr align="center" bgcolor="#efeae5">
		<td align="left" width="50px">
			<input type="checkbox" id="selectAll" name="selectAll" checked="false"/><label for="selectAll">全选</label>
		</td>
		<td colspan="2">商品名称</td>
		<td>单价</td>
		<td>数量</td>
		<td>小计</td>
		<td>操作</td>
	</tr>



<c:forEach items="${cartItemList }" var="cartItem">
	<tr align="center">
		<td align="left">
			<input value="${cartItem.cartItemId }" type="checkbox" id="checkboxBtn" name="checkboxBtn" checked="true"/>
		</td>
		<td align="left" width="70px">
			<a class="linkImage" href="<c:url value='/JSP/jsps/book/desc.jsp'/>"><img border="0" width="54" align="top" src="<c:url value='${cartItem.book.image_b }'/>"/></a>
		</td>
		<td align="left" width="400px">
		    <a href="<c:url value='/JSP/jsps/book/desc.jsp'/>"><span>${cartItem.book.bname }</span></a>
		</td>
		<td><span>&yen;<span id="currPrice" class="currPrice">${cartItem.book.currPrice }</span></span></td>
		<td>
			<a class="jian" id="${cartItem.cartItemId }Jian"></a><input class="quantity" readonly="readonly" id="${cartItem.cartItemId }Quantity" type="text" value="${cartItem.quantity }"/><a class="jia" id="${cartItem.cartItemId }Jia"></a>
		</td>
		<td width="100px">
			<span class="price_n">&yen;<span class="subTotal" id="${cartItem.cartItemId }Subtotal">${cartItem.subTotal }</span></span>
		</td>
		<td>
			<a href="<c:url value='/Cart/batchDelete?cartItemIds=${cartItem.cartItemId }'/>">删除</a>
		</td>
	</tr>
</c:forEach>
	<tr>
		<td colspan="4" class="tdBatchDelete">
			<a href="javascript:batchDelete();">批量删除</a>
		</td>
		<td colspan="3" align="right" class="tdTotal">
			<span>总计：</span><span class="price_t">&yen;<span id="total"></span></span>
		</td>
	</tr>
	<tr>
		<td colspan="7" align="right">
			<a href="javascript:jiesuan();" id="jiesuan" class="jiesuan"></a>
		</td>
	</tr>
</table>
	<form id="jieSuanForm" action="<c:url value='/Cart/loadCartItems'/>" method="post">
		<input type="hidden" name="cartItemIds" id="cartItemIds"/>
		<input type="hidden" name="total" id="hiddenTotal"/>
		<%--<input type="hidden" name="method" value="loadCartItems"/>--%>
	</form>

	</c:otherwise>
</c:choose>
  </body>
</html>
