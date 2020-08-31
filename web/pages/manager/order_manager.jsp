<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>

	<%-- 这是静态包含 公共的 base标签，css样式，jQuery文件 --%>
	<%@include file="/pages/common/header.jsp"%>


</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>

		<%-- 静态包含 ，管理菜单模块 --%>
		<%@include file="/pages/common/manager_menu.jsp" %>


	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>
			<%--遍历循环输出订单详情--%>
			<c:forEach items="${requestScope.orders}" var="order">
				<tr>
					<td>${order.createTime}</td>
					<td>${order.price}</td>
					<td><a href='orderServlet?action=orderDetails&orderId=${order.orderId}'>查看详情</a></td>
					<td>
						<c:choose>
							<%--order.status == 0 是未发货状态--%>
							<c:when test="${order.status == 0}">
								<a href="orderServlet?action=sendOrder&orderId=${order.orderId}">点击发货</a>
							</c:when>
							<%--order.status == 1 是发货状态--%>
							<c:when test="${order.status == 1}">
								已发货，待签收
							</c:when>
							<%--order.status == 2 是已签收状态--%>
							<c:when test="${order.status == 2}">
								已签收
							</c:when>
						</c:choose>
					</td>
				</tr>

			</c:forEach>

		</table>
	</div>


	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>