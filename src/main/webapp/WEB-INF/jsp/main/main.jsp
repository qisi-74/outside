<%--
  Created by IntelliJ IDEA.
  User: Mr.Hao
  Date: 2019/10/25
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes" />
    <title>OutSide，新的世界</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/main/main.css">
    <script src="${pageContext.request.contextPath}/statics/js/jQuery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/main.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/banner.js"></script>
    <link href="${pageContext.request.contextPath}/statics/css/header.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/banner.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/public.css" rel="stylesheet">
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
            <div class="remain"></div>
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
    <div class="area">
            <a href="#" class="a">基础教育</a>
            <a href="#" class="a" >高等教育</a>
            <a href="#" class="a">国家政治</a>
            <a href="#" class="a">宣传热点</a>
            <a href="#" class="a">奇闻轶事</a>
    </div>
    <div class="first-screen">
        <div class="banner">
            <div id="rotate" >
                <div id ="photo" class="rotate" style="left: 0px">
                    <img src="${pageContext.request.contextPath}/statics/img/banner/1.png">
                    <img src="${pageContext.request.contextPath}/statics/img/banner/2.jpeg">
                    <img src="${pageContext.request.contextPath}/statics/img/banner/3.jpg">
                    <img src="${pageContext.request.contextPath}/statics/img/banner/4.jpeg">
                    <img src="${pageContext.request.contextPath}/statics/img/banner/5.jpeg">
                    <img src="${pageContext.request.contextPath}/statics/img/banner/1.png">
                </div>
                <div id="buttons">
                    <span index=1 class="on"></span>
                    <span index=2></span>
                    <span index=3></span>
                    <span index=4></span>
                    <span index=5></span>
                </div>
                <a href="javascript:;" id="prev" class="arrow" style="text-decoration: none;">&lt;</a>
                <a href="javascript:;" id="next" class="arrow" style="text-decoration: none;">&gt;</a>
            </div>
        </div>
        <div class="recommend-box">


        </div>
    </div>

    <div class="extension">
        <h2>推广</h2>
        <div class="ext-video">
        </div>
    </div>
    <input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" />

</body>
</html>
