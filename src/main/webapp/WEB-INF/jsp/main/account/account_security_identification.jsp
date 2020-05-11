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
    <title>Title</title>
</head>
<script language="JavaScript" type="text/javascript">
    function id_check() {
        var realname=form.realname.value;
        var idcard=form.idcard.value;
        document.getElementById("spErr1").innerHTML="";
        document.getElementById("spErr2").innerHTML="";
        if((user_name=="")||(user_name==null)){
            document.getElementById("spErr1").innerHTML="&nbsp;&nbsp;请输入昵称" ;
            form.name.focus();
            return;
        }
        if((user_pwd=="")||(user_pwd==null)){
            document.getElementById("spErr2").innerHTML="&nbsp;&nbsp;请输入密码" ;
            form.password.focus();
            return;}
        if((phone=="")||(phone==null)){
            document.getElementById("spErr3").innerHTML="&nbsp;&nbsp;请输入手机号" ;
            form.phone.focus();
            return;
        }
        if(!(phone.length== 11 && /^[1][3,4,5,7,8][0-9]{9}$/.test(phone))) {
            document.getElementById("spErr3").innerHTML="&nbsp;&nbsp;您的手机号是非法的";
            form.phone.focus();
            return;
        }
        form.submit();
        window.open("${pageContext.request.contextPath }/check/registercheck");

    }
</script>
<body>
<form action="${pageContext.request.contextPath }/account/security" method="post">
    您的真实姓名：<input type="text" name="realname">
    您的身份证号：<input type="text" name="idcard">
    <input type="button" onclick="id_check()" value="进行认证">
</form>
</body>
</html>
