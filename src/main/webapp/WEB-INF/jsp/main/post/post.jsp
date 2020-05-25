<%--
  Created by IntelliJ IDEA.
  User: Mr.Hao
  Date: 2020/4/17
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes" />
    <title>动态首页-OutSie</title>
    <link href="${pageContext.request.contextPath}/statics/css/post/post.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/header.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/post-card.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/public.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/statics/js/jQuery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/head.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/person_space.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/post.js"></script>
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
<div class="homepage">
    <div class="left-panel">
        <div class="card">
            <a href="#" id="toggle">展开</a>
            <div id="content" style="display: none;">
                <a href="${pageContext.request.contextPath}/space/preson?uid=${pageContext.session.getAttribute("uid")}">


                <p>我的关注<p></a>
            </div>
        </div>
    </div>
    <div class="middle-panel">
        <div class="post card">
            <div class="editor card" id="editor" name="editor" contenteditable="true">

            </div>
            <div class="toolbar" id="tooll">
                <div class="post-img-submit c-pointer" id="post-img" title="上传图片">
                </div>
                <input type="file" class="file">
                <div class="post-submit" id="publish">
                    发布
                </div>
                <div class="upload-img">
                    <div class="title">
                        <span>图片上传</span>
                        <span class="text2">0/9</span>
                    </div>
                    <div  id="nine">
                    <div class="upload-img1 c-pointer" id="upload_img">
                        9
                    </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="feed-card" id="feed-card">
        </div>
    </div>
    <div class="right-panel">
    </div>
</div>
<input id="PageContext" name="page" type="hidden" value="${pageContext.request.contextPath}" />
</body>
</html>
