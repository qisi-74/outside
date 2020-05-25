<%--
  Created by IntelliJ IDEA.
  User: Mr.Hao
  Date: 2020/4/26
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes" />
    <title>审核界面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/account/account.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/header.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/public.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/verify.css"/>
    <script src="${pageContext.request.contextPath}/statics/js/jQuery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/account.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/verify.js"></script>
</head>
<body>

<h1>工号：${pageContext.session.getAttribute("ver_id")},欢迎您</h1>
<div class="box">
    <div class="contribution-list-container">
        <span class="security-title">审核界面</span>
        <ul class="contribution-list">
            <li class="contribution-item">
                <a href="#"  id="ver_video" style="color: blue" class="tab">审核视频</a>
            </li>
            <li class="contribution-item">
                <a href="#" id="ver_post" class="tab">审核动态</a>
            </li>
            <li class="contribution-item">
                <a href="#" id="ver_user" class="tab">审核用户</a>
            </li>
        </ul>
    </div>
    <div class="main-content">
        <div class="ver_search">
            <div class="nav nav_middle">
                <div class="nav-search">
                    <form action="#" method="get" class="search-form">
                        <input type="text" class="search-keyword" id="search_txt" name="search_text">
                        <img src="${pageContext.request.contextPath}/statics/img/search.png" title="搜索" alt="搜索" id="search" class="search">
                    </form>
                </div>
            </div>
        </div>
        <div class="content">
            <div class="ver_video verify"   id="video">
                <div class="ver_info">
                    <span class="ver_text">视频id：<span id="vid"></span></span><br>
                    <span class="ver_text">视频名称:<span id="vname"></span></span><br>
                    <span class="ver_text">视频状态:<span id="vstate"></span></span><br>
                    <p class="ver_text">视频链接：</p><br>
                </div>
                <div class="video-card" id="video1">
                    <a href="#" id="ovid" target="_blank">
                        <img src="#" id="ovface" class="video-card-face">
                    </a>
                    <div class="video-info">
                        <a href="#" id="ovname" target="_blank" >

                        </a>
                    </div>
                </div>
            </div>
            <div  class="verify acc"  id="post">
                <span class="ver_text">用户id：<span id="puid"></span></span><br>
                <span class="ver_text">动态id：<span id="pid"></span></span><br>
                <span class="ver_text">动态内容:<div id="pcontent"></div></span><br>
                <span class="ver_text">动态状态:<span id="pstate"></span></span><br>
            </div>
            <div  class="verify acc"  id="user">
                <span class="ver_text">用户id：<span id="uid"></span></span><br>
                <span class="ver_text">用户昵称:<span id="uname"></span></span><br>
                <span class="ver_text">用户状态:<span id="ustate"></span></span><br>
                <span class="ver_text">用户头像:</span><br>
                <img src="#" id="uhead" class="follow-user-head-img">
            </div>
            <div class="block">
                <div class="face-button" id="action">
                    <span  style="margin-top: 10px;">封禁</span>
                </div>
            </div>
        </div>

    </div>

</div>
<input type="hidden" id="PageContext" value="${pageContext.request.contextPath}">
</body>
</html>
