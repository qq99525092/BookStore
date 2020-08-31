<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>411会员注册页面</title>

    <%-- 这是静态包含 公共的 base标签，css样式，jQuery文件 --%>
    <%@include file="/pages/common/header.jsp" %>


    <style type="text/css">
        .login_form {
            height: 450px;
            margin-top: 25px;
        }

    </style>

    <script type="text/javascript">

        $(function () {


            //给用户名绑定失去焦点事件
            $("#username").blur(function () {
                //获取用户名
                var usernameText = this.value;
                //设置正则表达式
                var usernamePatt = /^\w{5,12}$/;
                //发ajax请求给服务器验证用户名是否可用
                $.getJSON("${basePath}userServlet",
                    "action=ajaxExistsUsername&username=" + usernameText,
                    function (data) {
                        console.log(data)
                        if (data.existsUsername) {
                            $("span.errorMsg").text("用户名已存在！")

                        } else {
                            if (!usernamePatt.test(usernameText)) {
                                //4.提示验证结果
                                $("span.errorMsg").text("用户名不合法！")
                                //阻止表单提交
                                return false;
                            }else {
                                $("span.errorMsg").text("用户名可用！")

                            }
                        }
                    }
                )

            })

            /*给验证码图片加上单击事件*/
            $("#codeImg").click(function () {
                // 在事件响应的function函数中有一个this对象.这个this对象是当前正在响应事件的dom对象
                // img标签的src属性是图片的连接地址
                // src属性可读,可写
                <%--this.src = "${basePath}kaptcha.jpg?d=" + new Date();--%>

                this.src = "${basePath}kaptcha.jpg?d=" + Math.random();

            })


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

                // 验证确认密码：和密码相同
                //1.获取确认密码文本框的内容
                var repedText = $("#repwd").val();
                //2.与密码进行对比

                if (repedText != passwordText) {
                    //4.提示验证结果
                    $("span.errorMsg").text("确认密码和密码不一致！")
                    //阻止表单提交
                    return false;
                }


                // 邮箱验证：xxxxx@xxx.com
                //1.读取邮箱文本框中的内容
                var emailText = $("#email").val();
                //2.创建正则表达式
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                //3.test()验证是否匹配正则表达式
                if (!emailPatt.test(emailText)) {
                    //4.提示验证结果
                    $("span.errorMsg").text("邮箱格式不合法！")
                    //阻止表单提交
                    return false;
                }


                // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
                //1.获取验证码文本框的内容
                var codeText = $("#code").val();
                // 去掉文本框中的内容的前后字符串
                var codeText = $.trim(codeText);
                //2.与图片进行对比
                if (codeText == "") {
                    $("span.errorMsg").text("验证码不能为空！")
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
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">

        <div class="login_form">

            <div class="login_box">
                <a href="pages/user/login.jsp" style="color: red">返回登录</a>
                <a href="index.jsp" style="color: red">返回首页</a>
                <div class="tit">
                    <h1>注册411会员</h1>
。
                    <span class="errorMsg">
                        ${requestScope.msg }

<%--
                          <%= request.getAttribute("msg") != null ? request.getAttribute("msg"):"用户名为空!" %>
--%>
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="regist"/>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1"
                               name="username" id="username"
                               value="${requestScope.username}"
                        />
                        <%--
                                                value="<%=request.getAttribute("username") == null ? "": request.getAttribute("username")%>"
                        --%>

                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码"
                               autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址"
                               autocomplete="off" tabindex="1"
                               name="email" id="email"
                               value="${requestScope.email}"
                        />
                        <%--
          value="<%=request.getAttribute("email") == null ? "": request.getAttribute("email")%>"
                        --%>

                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 125px;" name="code" id="code"/>
                        <img alt="" src="kaptcha.jpg" id="codeImg"
                             style="float: right; margin-right: 40px;width: 110px;height: 32px;">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

<%@include file="/pages/common/footer.jsp" %>


</body>
</html>