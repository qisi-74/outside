<%--
  Created by IntelliJ IDEA.
  User: Mr.Hao
  Date: 2020/4/24
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes" />
    <title>找回密码</title>
    <link href="${pageContext.request.contextPath}/statics/css/header.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/findpwd.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/statics/js/jQuery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/findpwd.js"></script>
</head>
<body>
<div class="header">

</div>
<div class="pwd">
    <input type="text" placeholder="请输入您的注册邮箱" class="input" id="email" value="">${error}<br>
    <input type="text" name="email_yzm" placeholder="请输入验证码" class="input" id="email_yzm" maxlength="6">
    <input type="button" value="获取验证码" id="yzm_btn" class="getyzm"><BR>
    <span id="spErr2" ></span><br>
    <input type="text" placeholder="请输入您新的密码" class="input" id="newpwd" ><br>
    <button class="btn" id="btn_pwd">确认修改</button>
</div>
<input type="hidden" id="pagecontext" value="${pageContext.request.contextPath}">
</body>
</html>
