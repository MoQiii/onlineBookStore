<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		background: #15B69A;
		margin: 0px;
		color: #ffffff;
	}
	a {
		text-decoration:none;
		color: #ffffff;
		font-weight: 900;
	} 
	a:hover {
		text-decoration: underline;
		color: #ffffff;
		font-weight: 900;
	}
</style>
  </head>
  
  <body>
<h1 style="text-align: center;">网上书城系统</h1>
<div style="font-size: 10pt; line-height: 10px;">

<%-- 根据用户是否登录，显示不同的链接 --%>
<c:choose>
	<c:when test="${empty sessionScope.sessionUser }">
		  <a href="<c:url value='/login/login'/>" target="_parent">书城会员登录</a> |&nbsp;
		  <a href="<c:url value='/register/registerUser'/>" target="_parent">注册书城会员</a>
		<a href="<c:url value='/OldBook/welcome'/>" target="_parent">旧书商城</a>
	</c:when>
	<c:otherwise>
		      书城会员：${sessionScope.sessionUser.loginname }&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="<c:url value='/user/index'/>" target="_parent">首页</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/Cart/myCart'/>" target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/order/myOrders'/>" target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/JSP/jsps/user/pwd.jsp'/>" target="body">修改密码</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/user/quit'/>" target="_parent">退出</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="#" target="_top">联系我们</a>
	</c:otherwise>
</c:choose>

	<%--<c:forEach items="" var="">
	<div class="inner" style="border: 3px solid rgb(255, 255, 255);">
		<p class="publishing_time"><span>出版时间：</span></p><a class="pic" href=""><img src="" border="0"></a>
		<p class="price">
			<span class="price_n"></span>
			<span class="price_r"></span>
			(<span class="price_s"></span>)
		</p>
		<p><a id="bookname" title="J" href=""></a></p>
		<p><a href="" name="" title=""></a></p>
		<p class="publishing">
			<span>出 版 社：</span><a href=""></a>
		</p>

	</div>

</c:forEach>--%>


</div>
  </body>
</html>
