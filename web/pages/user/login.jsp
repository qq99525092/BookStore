<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>411会员登录页面</title>

    <%-- 这是静态包含 公共的 base标签，css样式，jQuery文件 --%>
    <%@include file="/pages/common/header.jsp" %>


    <script type="text/javascript">

        $(function () {

            //1.给【按钮】绑定单击事件
            $("#sub_btn").click(function () {
                // 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
                //1.先获取文本框的内容
                var usernameText = $("#username").val();
                //2.创建正则表达式
                var usernamePatt = /^\w{5,12}$/;
                //3. test()方法验证是否合法
                // true 表示匹配正则表达式要求
                // false 表示不匹配正则要求
                if (!usernamePatt.test(usernameText)) {
                    //4.提示验证结果
                    $("span.errorMsg").text("用户名不合法！")
                    //阻止表单提交
                    return false;
                }

                // 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
                //1.获取密码文本框的内容：val()
                var passwordText = $("#password").val();
                //2.创建正则表达式
                var passwordPatt = /^\w{5,12}$/;
                //3.test() 方法验证是否合法
                if (!passwordPatt.test(passwordText)) {
                    //4.提示验证结果
                    $("span.errorMsg").text("密码不合法！")
                    //阻止表单提交
                    return false;

                }

                //把错误信息置空
                $("span.errorMsg").text();
            })

        })


    </script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>411会员</h1>
                    <a href="index.jsp" style="color: red">返回首页</a>
                    <a href="pages/user/regist.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
<%--
                    <%=request.getAttribute("msg") == null ? "请输入用户名和密码" : request.getAttribute("msg")%>
--%>
                        ${ empty requestScope.msg ? "请输入用户名和密码" : requestScope.msg}
                    </span>
                </div>
                <div class="form">

                    <form action="userServlet" method="post">
                        <input type="hidden" name="action"  value="login" />
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1"
                               name="username" id="username"
                               value="${requestScope.username }"
                        />

                    <%--
                        value="<%=request.getAttribute("username") == null ? "" : request.getAttribute("username")%>"/>
--%>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1"
                               name="password" id="password"
                        />
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

<%@include file="/pages/common/footer.jsp" %>


</body>
</html>