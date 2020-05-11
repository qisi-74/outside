<%--
  Created by IntelliJ IDEA.
  User: Mr.Hao
  Date: 2019/10/25
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登录</title>
    <script src="${pageContext.request.contextPath}/statics/js/jQuery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/login.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/login.css">
</head>
<body background="${pageContext.request.contextPath}/statics/img/outside.jpeg">
<div class="header">
</div>
<div  class="title-line"  >
    <p class="tit" style="font-size: 38px;">登录</p>
</div>
<div class="frame">
    <div class="qrcode-tips" >
        <img class="qr" src="${pageContext.request.contextPath}/statics/img/app.gif" alt="">
        <p class="qr-info">扫描二维码登录</p>
    </div>
    <div class="line" ></div>
    <div class="login_frame" >
        <h1>OutSide：</h1>
        <form action="${pageContext.request.contextPath }/check/logincheck" method="post" id="form1" name="form1">
            <input type="text" name="zhanghao" id="zh" placeholder="请输入手机号或者邮箱"><br>
            <span id="sp_zh" ></span><br>
            <input type="password" name="password" id="pwd" placeholder="请输入密码"><br>
            <span id="sp_pwd" ></span><br>
            <span id="spErr">${error}</span><br>
            <a href="${pageContext.request.contextPath}/account/findpwd">忘记密码？</a>
            <div class="btn-box">
                <a id="log_btn" class="btn btn-login">登录</a>
                <a href="${pageContext.request.contextPath }/index/register" class="btn btn-reg">注册</a></div>
        </form>
        <input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" />

    </div>
</div>

</body>
</html>
