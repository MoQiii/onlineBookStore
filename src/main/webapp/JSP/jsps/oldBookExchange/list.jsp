<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/JSP/jsps/css/book/list.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/JSP/jsps/pager/pager.css'/>" />
    <%--<script type="text/javascript" src="<c:url value='/JSP/jsps/pager/pager.js'/>"></script>--%>
	<script type="text/javascript" src="<c:url value='/jQuery/jquery-3.1.1.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/JSP/jsps/js/book/list.js'/>"></script>
	  <script type="text/javascript" src="<c:url value='/JSP/jsps/js/oldbook/oldbook.js'/>"></script>
  </head>
  
  <body>

<ul>
<c:forEach items="${pb.beanList }" var="book">
  <li>
  <div class="inner">
    <a class="pic" href="<c:url value='/OldBook/load?bid=${book.bid }'/>"><img src="<c:url value='http://localhost:8082/pic${book.image_b }'/>" border="0"/></a>
    <p class="price">
		<span class="price_n">&yen;${book.currPrice}</span>
		<span class="price_r">&yen;${book.originalPrice }</span>
		<span>&nbsp&nbsp&nbsp&nbsp<input type="button" value="下架" onclick="deleteOldBook('${book.bid}')"/></span>
		<%--(<span class="price_s">${book.discount }折</span>)--%>
	</p>
	<p><a id="bookname" title="${book.bname }" href="<c:url value='/OldBook/load?bid=${book.bid }'/>">${book.bname }</a></p>
	<%-- url标签会自动对参数进行url编码 --%>
	<c:url value="/OldBook/load" var="authorUrl">
		<c:param name="author" value="${book.author }"/>
	</c:url>
	<c:url value="/OldBook/load" var="pressUrl">
		<c:param name="press" value="${book.press }"/>
	</c:url>
	<p>作者：${book.author }</p>
	<p class="publishing">
		<span>出 版 社：${book.press }</span>
	</p>
	<p class="publishing_time"><span>出版时间：</span>${book.publishtime }</p>
	  <p></p>
  </div>
  </li>
</c:forEach>

</ul>

<div style="float:left; width: 100%; text-align: center;">
	<hr/>
	<br/>
	<%@include file="/JSP/jsps/pager/pager.jsp" %>
</div>

  </body>
 
</html>

