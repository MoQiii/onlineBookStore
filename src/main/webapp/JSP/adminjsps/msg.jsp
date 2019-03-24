<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'mgs.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">

    function back() {
        history.go(-2);
        self.location=document.referrer;
    }
    function One() {
       window.location.href="http://localhost:8080/JSP/adminjsps/admin/book/main.jsp";
    }
</script>
  </head>
<style type="text/css">
	body {background: rgb(254,238,189);}
</style>
  <body>
<h2>${msg }</h2>



<c:choose>
    <c:when test="${flag == '1'}">
       <%-- <input type="button" value="返回" onclick="One() "/>--%>
        <a href="http://localhost:8080/JSP/adminjsps/admin/book/main.jsp" target="_parent">返回</a>
    </c:when>
    <c:otherwise>
        <input type="button" value="返回" onclick="back() "/>
    </c:otherwise>
</c:choose>
  </body>
</html>
