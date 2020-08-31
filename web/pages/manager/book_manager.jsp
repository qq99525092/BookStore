<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>

    <%-- 这是静态包含 公共的 base标签，css样式，jQuery文件 --%>
    <%@include file="/pages/common/header.jsp" %>


</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>

    <%-- 静态包含 ，管理菜单模块 --%>
    <%@include file="/pages/common/manager_menu.jsp" %>

    <script type="text/javascript">
        $(function () {

            $("a.deleteBook").click(function () {

                var name = $(this).parent().parent().find("td:first").text();
                // confirm()是javaScript提供的是一个确认提示操作函数
                //它的参数是提示框的提示内容
                ////它有两个按钮，一个是确定，一个是取消
                //当用户点击确定，它返回true
                //点击取消返回 false
                return confirm('你确定要删除 ' + name + ' 吗？');
            })
        })

    </script>


</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>


        <%-- ESTL标签库进行遍历 items存放的是key键 ， var 存放的是value值 --%>
        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
                <td><a class="deleteBook"
                       href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
        </tr>
    </table>

    <%-- 静态包含分页条 --%>
  <%@ include file="/pages/common/page_nav.jsp"%>

</div>


<%@include file="/pages/common/footer.jsp" %>


</body>
</html>