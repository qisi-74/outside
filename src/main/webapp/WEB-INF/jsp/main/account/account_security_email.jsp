<%--
  Created by IntelliJ IDEA.
  User: Mr.Hao
  Date: 2019/10/25
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes" />
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/statics/js/jQuery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/AccountEmail.js"></script>
</head>
<body>
<form action="${pageContext.request.contextPath }/account/security" method="post">
<div>
    uid:<%=request.getSession().getAttribute("uid")%><br>
    <input type="text" placeholder="请输入您的邮箱" name="email" id="email" value="${email}"><br>
    <span id="spErr1" ></span><br>
    <input type="text" name="email_yzm" placeholder="请输入验证码" id="email_yzm" maxlength="6">
    <input type="button" value="获取验证码" id="yzm_btn"><BR>
    <span id="spErr2" ></span><br>
    <input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" />
    <input type="button" value="提交" id="button">
</div>
</form>
</body>
</html>
