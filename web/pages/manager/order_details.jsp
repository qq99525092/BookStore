<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>

    <%-- 这是静态包含 公共的 base标签，css样式，jQuery文件 --%>
    <%@include file="/pages/common/header.jsp" %>

    <script type="text/javascript">


    </script>


</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">订单详情</span>

    <%-- 静态包含，登录成功后的菜单--%>
    <%@include file="/pages/common/manager_menu.jsp" %>


</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>商品数量</td>
            <td>商品单价</td>
            <td>商品总金额</td>
        </tr>

        <c:forEach items="${requestScope.orderItems}" var="orderItem">
            <tr>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>￥${orderItem.price}</td>
                <td>￥${orderItem.totalPrice}</td>
            </tr>
        </c:forEach>

    </table>

</div>


<%@include file="/pages/common/footer.jsp" %>


</body>
</html>