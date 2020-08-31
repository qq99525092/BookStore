<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>错误页面</title>

    <%-- 这是静态包含 公共的 base标签，css样式，jQuery文件 --%>
    <%@include file="/pages/common/header.jsp" %>

    <script type="text/javascript">

    </script>


</head>
<body>
        很抱歉，您访问的服务器已出错，程序猿小哥正在努力的抢修中！！<br/>
        <a>联系客服</a><br/>
        <a href="index.jsp" style="color: red">返回首页</a> <br/>

</body>
</html>