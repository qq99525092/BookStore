<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPEhtml>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑图书</title>

    <%--这是静态包含公共的base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/header.jsp" %>


    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1a {
            color: red;
        }

        input {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="header">
    <imgc lass="logo_img" alt="" src="static/img/logo.gif">
        <span class="wel_word">编辑图书</span>

        <%--静态包含，管理菜单模块--%>
        <%@include file="/pages/common/manager_menu.jsp" %>


</div>

<div id="main">
    <form action="manager/bookServlet" method="post">
        <%--value的值为方法名--%>
        <input type="hidden" name="action" value="${empty param.id ? "add":"updateBook"}">
        <input type="hidden" name="id" value="${requestScope.book.id}">
        <input type="hidden" name="pageNo" value="${param.pageNo}">
        <table>
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2">操作</td>
            </tr>
            <tr>
                <td><input name="name" type="text" value="${requestScope.book.name}"/></td>
                <td><input name="price" type="text" value="${requestScope.book.price}"/></td>
                <td><input name="author" type="text" value="${requestScope.book.author}"/></td>
                <td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
                <td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
                <td><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>


</div>


<%@include file="/pages/common/footer.jsp" %>


</body>
</html>