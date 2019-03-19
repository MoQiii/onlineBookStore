<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>管理员登录页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
	  <link rel="stylesheet" href="/bootstrap-3.3.7-dist/css/bootstrap.css"
			crossorigin="anonymous">
	  <script src="/jQuery/jquery-3.1.1.min.js"
			  crossorigin="anonymous"></script>
	  <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	  <script src="/bootstrap-3.3.7-dist/js/bootstrap.js"
			  crossorigin="anonymous"></script>
	  <link rel="stylesheet" href="/css/signin.css">
	<script type="text/javascript" src="<c:url value='/jQuery/jquery-3.1.1.min.js'/>"></script>
	<script type="text/javascript">
		function checkForm() {
			if(!$("#adminname").val()) {
				alert("管理员名称不能为空！");
				return false;
			}
			if(!$("#adminpwd").val()) {
				alert("管理员密码不能为空！");
				return false;
			}
			return true;
		}
	</script>
  </head>
  
<%--  <body>
<h1>管理员登录页面</h1>
<hr/>
  <p style="font-weight: 900; color: red">${msg }</p>
<form action="<c:url value='/admin/login'/>" method="post" onsubmit="return checkForm()" target="_top">
	<input type="hidden" name="method" value="login"/>
	管理员账户：<input type="text" name="adminname" value="" id="adminname"/><br/>
	密　　　码：<input type="password" name="adminpwd" id="adminpwd"/><br/>
	<input type="submit" value="进入后台"/>
</form>
  </body>--%>

  <body class="text-center">
  <form class="form-signin" action="/admin/login">
	  <img class="mb-4" src="/pic/bootstrap-solid.svg" alt="" width="72" height="72">
	  <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
	  <label for="adminname" class="sr-only">用户名</label>
	  <input type="text" id="adminname" name="adminname" class="form-control" value="" placeholder="用户名" required autofocus>
	  <label for="adminpwd" class="sr-only">密码</label>
	  <input type="password" id="adminpwd" name="adminpwd" class="form-control" placeholder="密码" required>
	  <div class="checkbox mb-3">
		  <label>
			  <input type="checkbox" value="remember-me"> Remember me
		  </label>
	  </div>
	  <button class="btn btn-lg btn-primary btn-block" type="submit">进入后台</button>
	  <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
  </form>
  </body>

</html>
