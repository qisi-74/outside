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
    <title>还未设计好</title>
</head>
<body>
uid:<%=request.getSession().getAttribute("uid")%><br>
    绑定手机：<a href="${pageContext.request.contextPath }/account/security/phone">更换手机</a><br>
    绑定邮箱：<a href="${pageContext.request.contextPath }/account/security/email">绑定邮箱</a><br>
    修改密码：<a href="${pageContext.request.contextPath }/account/security/password">更换密码</a><br>
    实名认证：<a href="${pageContext.request.contextPath }/account/security/identification">实名认证</a><br>
</body>
</html>
