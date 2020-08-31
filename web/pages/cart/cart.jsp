<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>

	<%-- 这是静态包含 公共的 base标签，css样式，jQuery文件 --%>
	<%@include file="/pages/common/header.jsp"%>

	<script type="text/javascript">

		$(function (){

			//设置内容发生改变事件 input 中name属性的值为count
			$("input[name='count']").change(function () {

				var text = $(this).parent().parent().find("td:first").text();
				var itemId = $(this).attr("itemId");
				var newCount = this.value;

				if (confirm("你确定要修改 [" + text + "] 数量为: " + newCount + " 吗?")) {

					location.href = "${basePath}cartServlet?action=updateCount&id="+itemId+"&count="+newCount;

				} else {
					// defaultValue是原表单项的默认值
					this.value = this.defaultValue;
				}
			});

			//给删除的a标签绑定单击事件
			$("a.deleteItemClass").click(function () {
				//取父元素的父元素的第一个里面的文本内容 :text()
				return confirm("你确定要删除【"+$(this).parent().parent().find("td:first").text()+"】吗？");


			});

			//给清空购物车按钮绑定单击事件
			$("#clearCart").click(function () {
				return confirm("你确定要清空购物车吗？");

			});

		})


	</script>


</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>

		<%-- 静态包含，登录成功后的菜单--%>
		<%@include file="/pages/common/login_success_menu.jsp"%>


	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<%-- 遍历购物车中的图书
			session域中为map集合 ，所以值var使用entry
			--%>
			<c:forEach items="${sessionScope.cart.items}" var="entry">
				<tr>
					<td>${entry.value.name}</td>
					<td>
						<input type="text" name="count" id="count"
							   style="width: 50px" itemId="${entry.value.id}"
							   value="${entry.value.count}">
					</td>
					<td>${entry.value.price}</td>
					<td>${entry.value.totalPrice}</td>
					<td><a class="deleteItemClass"
							href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
				</tr>
			</c:forEach>
			
		</table>

		<%--如果购物车为空就给用户一个提示--%>
		<c:if test="${empty sessionScope.cart.items}">
			<tr>
				<td colspan="100">
					<a href="index.jsp" line-height:100px; text-align:center;>亲,当前购物车为空，快去浏览商品吧！</a>
				</td>
			</tr>

		</c:if>

		<%-- 购物车非空才显示以下信息--%>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>

				<span class="cart_span"><a  id="clearCart"
							href="cartServlet?action=clear">清空购物车</a></span>

				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
		

	
	</div>


	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>