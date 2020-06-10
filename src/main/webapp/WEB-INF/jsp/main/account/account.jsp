<%--
  Created by IntelliJ IDEA.
  User: Mr.Hao
  Date: 2020/4/24
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes" />
    <title>个人中心</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/cropper.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/account/account.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/header.css"/>
    <script src="${pageContext.request.contextPath}/statics/js/jQuery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/head.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/cropper.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/account.js"></script>
</head>
<body>
<div class="header">
    <div class="nav nav_left">
        <div class="main" >
            <a href="${pageContext.request.contextPath }/outside/main" class="title"> OutSide</a>
        </div>

    </div>
    <div class="nav nav_middle">
        <div class="nav-search">
            <form action="${pageContext.request.contextPath }/search/video" method="get" class="search-form">
                <input type="text" class="search-keyword" id="search_txt" name="search_text">
                <img src="${pageContext.request.contextPath}/statics/img/search.png" title="搜索" alt="搜索" id="search" class="search">
            </form>
        </div>
    </div>
    <div class="nav nav_right">
        <div class="user-head">
            <a href="${pageContext.request.contextPath }/space/preson?uid=${uid}" title="个人空间" alt="个人空间" target="_self">
                <img class="user-head-img" id="user-head"  src=${pageContext.request.contextPath}${pageContext.session.getAttribute("head_img")} >
            </a><br>
        </div>
        <div class="tabb">
            <a href="${pageContext.request.contextPath }/notify/message" class="a">消息</a>
            <a href="${pageContext.request.contextPath }/t/post" target="_blank" class="a" >动态</a>
            <a href="${pageContext.request.contextPath}/outside/destory" class="a">退出</a>
        </div>
        <div class="upload">
            <a href="${pageContext.request.contextPath }/upload/frame" target="_blank" class=" upload_btn a">投稿</a>
        </div>

    </div>
</div>
<div class="box">
    <div class="contribution-list-container">
        <span class="security-title">个人中心</span>
        <ul class="contribution-list">
            <li class="contribution-item">
                <a href="${pageContext.request.contextPath}/account/info"  class="tab">个人信息</a>
            </li>
            <li class="contribution-item">
                <a href="${pageContext.request.contextPath}/account/face" class="tab">更换头像</a>
            </li>
            <li class="contribution-item">
                <a href="${pageContext.request.contextPath}/account/security/pwd" class="tab">修改密码</a>
            </li>
            <li class="contribution-item">
                <a href="${pageContext.request.contextPath}/account/security/email" class="tab">绑定邮箱</a>
            </li>
        </ul>
    </div>
    <div class="main-content">
        <div class="acc" id="account">
            <div class="security-right-title">
                <span class="security-right-title-icon"></span>
                <span  class="security-right-title-text">我的信息</span>
            </div>
            <form action="${pageContext.request.contextPath }/account/updatesetting" id="form-info" method="post">
                <div class="info-form">
                    <div class="span">
                        <span class="text">昵称：</span>  <input type="text" name="name" id="name" class="el-input__inner" >${error}<br>
                    </div>
                    <div class="span">
                        <span class="text">用户ID：</span><input type="text" class="uid" value="org_<%=request.getSession().getAttribute("uid").toString()%>" readonly="readonly"><br>
                    </div>
                        <div class="span">
                        <span class="text">签名：</span> <textarea class="el-textarea__inner" id="sign" name="qianming"></textarea><br>
                        </div>
                    <div class="span">
                    性别：男<input type="radio" checked id="male" name="sex" value="1"/>
                    女<input type="radio" id="female" name="sex" value="0"/>
                    </div>

                </div>
                <div class="reset">
                    <div class="face-button" id="submit_info">
                        <span style="margin-top: 10px;">提交</span>
                    </div>
                </div>
                <input type="hidden" name="uid" value="<%=request.getSession().getAttribute("uid").toString()%>">
            </form>
        </div>
        <div  class="acc"  id="face">
            <div class="face">
                <div class="pre">
                    <div class="upload-face">

                    </div>
                    <div class="current-face">
                        <img src="${pageContext.request.contextPath}${head}" class="face-img" >
                    </div>
                    <input type="file" id="up-face" >
                    <div class="tip">
                        <p>请选择图片上传：大小180 * 180像素支持JPG、PNG等格式，图片需小于2M</p>
                    </div>
                </div>
                <div class="cur">
                    <div class="crop">
                        <div class="img-container">
                            <img src="${pageContext.request.contextPath}/statics/img/register.jpg" id="cur_head"/>
                        </div>
                        <div class="view">

                        </div>
                    </div>
                    <div class="reset">
                        <div class="face-button" id="submit">
                            <span style="margin-top: 10px;">更新</span>
                        </div>
                    </div>

                </div>
            </div>

        </div>
        <div  class="acc"  id="pwd">
            <form action="${pageContext.request.contextPath }/account/update/pwd" id="form-pwd" method="post">
            <div class="span">
                <span class="text">原密码：</span>  <input type="text"  name="oldpwd" class="el-input__inner" >${error}
                <a href="${pageContext.request.contextPath}/account/findpwd"  class="forget">忘记密码？</a><br>
            </div>
            <div class="span">
                <span class="text">新密码：</span>  <input type="text" id="newpwd" name="newpwd"  class="el-input__inner" ><br>
            </div>
            <div class="face-button" id="submit_pwd">
                    <span style="margin-top: 10px;">修改密码</span>
            </div>
            </form>
        </div>
        <div  class="acc"  id="email">
            <form action="${pageContext.request.contextPath }/account/update/email" id="form-email" method="post">
                <div class="info-form">
                    <div class="span" id="from">
                        <input type="text" class="el-input__inner"  title="已经绑定的邮箱" disabled name="email" id="yuanemail" value=${email}  ><br>
                    </div>
                    <div class="span">
                        <input type="text" class="el-input__inner"  placeholder="请输入要绑定的邮箱" name="toemail" id="toemail"  ><br>
                        <span id="spErr1" ></span><br>
                    </div>
                    <div class="span">
                        <input type="text" name="email_yzm" class="el-input__inner"  placeholder="请输入验证码" id="email_yzm" maxlength="6">
                        <input type="button"  value="获取验证码" id="yzm_btn"><BR>
                        <span id="spErr2" ></span><br>
                    </div>

                    <div class="face-button" id="submit_email">
                        <span style="margin-top: 10px;">确定</span>
                    </div>
                </div>
            </form>
        </div>

    </div>

</div>
<input type="hidden" id="pagecontext" value="${pageContext.request.contextPath}">
<input type="hidden" id="type" value="${type}">
<input type="hidden" id="fromemail" value="${email}">
<a href="${pageContext.request.contextPath}/account/security/yuan">还未设计好</a>
</body>
</html>
