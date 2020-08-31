<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>

    <%-- 这是静态包含 公共的 base标签，css样式，jQuery文件 --%>
    <%@include file="/pages/common/header.jsp" %>

    <script type="text/javascript">
        $(function () {
            // 给加入购物车绑定单击事件
            $("button.addItemClass").click(function () {
                var id = $(this).attr("itemId");
                //发AJAX请求到服务器
                $.getJSON("${basePath}cartServlet",
                    "action=ajaxAddItem&id="+id,
                    function (data) {
                    //查询购物车中图书数量的span
                    $("#totalCountSpan").html("【"+data.totalCount+"】");
                    $("#lastNameDiv").html("您刚刚将【"+data.last_name+"】加入到了购物车中");

                    }
                )
            });
        });


    </script>


</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <div>
        <c:if test="${ empty sessionScope.user}">

            <a href="pages/user/login.jsp">登录</a>
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;

        </c:if>
        <c:if test="${ not empty sessionScope.user}">

            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临411书城</span>
            <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;

        </c:if>

        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/bookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>
        <div style="text-align: center">

            <%--购物车为空时--%>
            <c:if test="${empty sessionScope.cart.items}">
                <span id="totalCountSpan"></span>
                <div id="lastNameDiv">
                    <span style="color: red" >当前购物车为空！</span>
                </div>

            </c:if>

            <%--购物车非空时--%>
            <c:if test="${ not empty sessionScope.cart.items}">
                您的购物车中有 <span style="color: red" id="totalCountSpan">${sessionScope.cart.totalCount}</span> 件商品<br/>
                <div id="lastNameDiv">
                    您刚刚将<span style="color: red" >【${sessionScope.last_name}】</span>加入到了购物车中
                </div>
            </c:if>


        </div>
        <c:forEach items="${requestScope.page.items}" var="book">
            <%-- 遍历的开始 --%>
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.imgPath}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">

                        <button itemId="${book.id}"
                                class="addItemClass">加入购物车
                        </button>

                    </div>
                </div>
            </div>

            <%-- 遍历的结束 --%>

        </c:forEach>
    </div>

    <%--静态包含分页条--%>
    <%@include file="/pages/common/page_nav.jsp" %>

</div>

<%@include file="/pages/common/footer.jsp" %>


</body>
</html>