<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>left</title>
    <base target="body"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/jQuery/jquery-3.1.1.min.js'/>"></script>
	  <script type="text/javascript" src="<c:url value='/bootstrap-3.3.7-dist/js/bootstrap.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
	<link rel="stylesheet" type="text/css" href="<c:url value='/JSP/jsps/css/left.css'/>" />
	  <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap-3.3.7-dist/css/bootstrap.css'/>"/>
	  <script language="javascript">
          /*
           * 1. 对象名必须与第一个参数相同！
             2. 第二个参数是显示在菜单上的大标题
           */
          var bar = new Q6MenuBar("bar", "二手书城");
          $(function() {
              bar.colorStyle = 4;//指定配色样式，一共0,1,2,3,4
              bar.config.imgDir = "<c:url value='/menu/img/'/>";//小工具所需图片的路径
              bar.config.radioButton=true;//是否排斥，多个一级分类是否排斥

              /*
              1. 程序设计：一级分类名称
              2. Java Javascript：二级分类名称
              3. /goods/jsps/book/list.jsp：点击二级分类后链接到的URL
              4. body:链接的内容在哪个框架页中显示
              */
              <c:forEach items="${parents}" var="parent">
              <c:forEach items="${parent.children}" var="child">
              bar.add("${parent.cname}", "${child.cname}", "${PageContext.request.contextPath}/OldBook/findByCategory?cid=${child.cid}", "body");
              </c:forEach>
              </c:forEach>
              //  alert(bar.toString());
              $("#menu").html(bar.toString());
          });
	  </script>
</head>
  
<body>  
	<%--<input type="button" name="添加" id="addOldBook()" value="增加旧书" class="btn btn-info" style="width:100%"/>--%>
	<%--<div>
		<span>
		旧书出售列表
	  	</span>
	</div>--%>
	<div id="menu"></div>
	<%--<c:if test="${empty sessionScope.sessionUser }">--%>
		<div id="add"  class="menuTitle" style="background-color:#15B69A;border-right-color:gray;border-bottom-color:gray;">
			<span class="menuTitleText" style="font-size:10pt;float: left;text-align: center"><a href="<c:url value='/OldBook/addView'/>"  style="text-align: center" target="body">上架旧书</a></span>
		</div>
	<div id="add"  class="menuTitle" style="background-color:#15B69A;border-right-color:gray;border-bottom-color:gray;">
		<span class="menuTitleText" style="font-size:10pt;float: left;text-align: center"><a href="<c:url value='/OldBook/addView'/>"  style="text-align: center" target="body">出售列表</a></span>
	</div>
	<%--</c:if>--%>


</body>
</html>
