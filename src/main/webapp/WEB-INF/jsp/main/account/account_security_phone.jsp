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
<style type="text/css">

    #operate { display:none;}
    #success{ display:none;}

</style>
<script type="text/javascript">
    function change_div(id){
        if (id == 'check' )
        {
            document.getElementById("operate").style.display = 'none' ;
            document.getElementById("success").style.display = 'none' ;
            document.getElementById("check").style.display = 'block' ;
        }
        else if(id=='operate')
        {
            document.getElementById("check").style.display = 'none' ;
            document.getElementById("success").style.display = 'none' ;
            document.getElementById("operate").style.display = 'block' ;
        }else if(id=='success'){
            document.getElementById("check").style.display = 'none' ;
            document.getElementById("operate").style.display = 'none' ;
            document.getElementById("success").style.display = 'block' ;
        }
    }
    function check() {
        // alert(""form1.ymz.value"");
        // if(form1.ymz.value==null||form1.ymz.value==''){
        //     document.getElementById("spErr1").innerHTML="&nbsp;&nbsp;请输入验证码" ;
        // }
        // return;
        var ymz1=form1.ymz_1_1.value;

        alert(ymz1);
        window.open("${pageContext.request.contextPath }/account/security/phone");

    }
    function miao() {

        document.getElementById("yzm1").value="60s后再获取";
    }
    function next(){


    }
</script>

<body>
<a href="${pageContext.request.contextPath }/account/security">账号安全</a>>设置手机<br><br><br>
<a href="#" onclick="change_div('check')">验证身份</a>————————
<a href="#" onclick="change_div('operate')">绑定手机</a>————————
<a href="#" onclick="change_div('success')">绑定成功</a>
<div id="check">

    <form action="${pageContext.request.contextPath }/account/security/phone/set" method="post" name="form1">
        <input type="text" placeholder="请输入您的手机号" name="phone" value="${phone}" disabled><br>
        <input type="text" name="yzm_1_1" placeholder="请输入验证码" >
        <input type="button" value="获取验证码" onclick="miao()" id="yzm1_2"><BR>
        <span id="spErr1" style="color:red">4345</span><br>
        <input type="button" value="下一步" onclick="check()">
    </form><br>
</div>
<div id="operate">
    <form action="${pageContext.request.contextPath }/account/security/phone/set" method="post">
        <input type="text" placeholder="请输入您的手机号" name="phone"><br>
        <input type="text" name="yzm_2_1" placeholder="请输入验证码">
        <input type="button" value="获取验证码" onclick="miao()" id="yzm_2_2"><BR>
        <input type="submit" value="确定绑定">
    </form><br>
</div>
<div id="success">
    <form action="${pageContext.request.contextPath }/account/security/phone/success" method="post">
    <span>绑定手机号成功</span><br>
    <input type="submit" value="确定绑定">
    </form>
</div>
</body>
</html>
