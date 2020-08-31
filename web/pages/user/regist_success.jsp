<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>411会员注册页面</title>

	<%-- 这是静态包含 公共的 base标签，css样式，jQuery文件 --%>
	<%@include file="/pages/common/header.jsp"%>


	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >
				<span class="wel_word"></span>

			<%-- 静态包含，登录成功后的菜单--%>
			<%@include file="/pages/common/login_success_menu.jsp"%>


		</div>
		
		<div id="main">
		
			<h1>注册成功! <a href="../index.jsp">转到主页</a></h1>
	
		</div>


		<%@include file="/pages/common/footer.jsp"%>


</body>
</html>