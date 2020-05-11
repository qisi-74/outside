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
    <title>注册</title>
    <script src="${pageContext.request.contextPath}/statics/js/jQuery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/register.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/pwdstrength.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/register.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/header.css">


</head>

<body background="${pageContext.request.contextPath}/statics/img/register.jpg">
<div class="header">
</div>
<div  class="title-line"  >
    <span class="tit" style="font-size: 38px;">注册</span>
</div>
<div class="form-container">
<form action="${pageContext.request.contextPath }/check/registercheck" method="post" name="form" class="form-1">
    <span id="spErr" style="color:#c156ff">${error}</span><br>
    <input type="text" name="name" id="name" class="input-100"  placeholder="昵称">
    <span id="spErr1"></span><br>
        <input type="text" class="input-2 input-100" maxlength="16" name="password" id="pwd"  placeholder="密码（6-16个字符组成，区分大小写）">
        <span id="spErr2"></span><br>
    <div class="progress-bar_wrap">
        <span class="progress-bar_item progress-bar_item-1"></span>
        <span class="progress-bar_item progress-bar_item-2"></span>
        <span class="progress-bar_item progress-bar_item-3"></span>
    </div>
        <span class="progress-bar_text">密码为空</span><br>
        <input type="text" name="phone" id="phone" class="input-100"  placeholder="请输入常用手机号">
        <span id="spErr3"></span><br>
        <input type="text" class="input-4" id="yzm" maxlength="6" placeholder="验证码">
        <input id="btn_yzm" class="input-5" type="button" value="点击获取验证码" >
        <span id="spErr4"></span><br>
        <input id="button" type="button" value="注册" class="input input-100">
    <input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" />

</form>
</div>
</body>
</html>
