<%--
  Created by IntelliJ IDEA.
  User: 林资芙
  Date: 2020/7/30
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
base标签一定要放在title标签下，一般要映射到当前工程idea代码下的web目录
request.getScheme() 返回当前链接使用的协议 ，一般为 http 或 https
getContextPath() : 获取工程路径 带 斜杠 /
pageContext.setAttribute() 作用域整个web的所有页面
-->
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";

    pageContext.setAttribute("basePath", basePath);
%>

<base href="${basePath}">
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script src="static/script/jquery-1.7.2.js"></script>
