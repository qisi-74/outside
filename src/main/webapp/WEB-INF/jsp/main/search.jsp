<%--
  Created by IntelliJ IDEA.
  User: Mr.Hao
  Date: 2020/4/23
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes" />
    <title>${search_text}的搜索结果</title>
    <script src="${pageContext.request.contextPath}/statics/js/jQuery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/search.js"></script>
    <link href="${pageContext.request.contextPath}/statics/css/header.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/public.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/search.css">
</head>
<body>
<div class="header">
    <div class="nav nav_left">
        <div class="main" >
            <a href="${pageContext.request.contextPath }/outside/main" class="title"> OutSide</a>
        </div>

    </div>
    <div class="nav nav_middle">

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
            <a href="#" class="a">收藏</a>
        </div>
        <div class="upload">
            <a href="${pageContext.request.contextPath }/upload/frame" target="_blank" class=" upload_btn a">投稿</a>
        </div>

    </div>
</div>
<div class="box">
    <div class="head-contain">
        <div class="search-wrap">
            <form class="search-bar"  id="search-form" action="${pageContext.request.contextPath}/search/video" >
                <div class="form-search">
                    <input type="text" class="search-keyword" id="key" name="search_text">
                </div>
                <div class="search-btn cursor" id="search-btn">
                    <div>搜索</div>
                </div>
            </form>
        </div>
        <div class="header-tabs">
            <div class="htab">
                <a href="#" class="a aa">综合</a>
                <a href="#" id="search_video" class="a aa">视频</a>
                <a href="#" id="search_user" class="a aa">用户</a>
                <a href="#" class="a aa">文章</a>
            </div>
        </div>
    </div>
    <div class="search-list">
<%--        <div class="user-card">--%>
<%--                <div class="user-card-left">--%>
<%--                    <a href="${pageContext.request.contextPath }/space/preson?uid=${uid}" title="个人空间" alt="个人空间" target="_blank">--%>
<%--                        <img src="${pageContext.request.contextPath}/statics/img/register.jpg" class="search-user-head-img">--%>
<%--                    </a>--%>
<%--                </div>--%>
<%--                <div class="user-main-content">--%>
<%--                    <div class="user-info">--%>
<%--                        <a href="${pageContext.request.contextPath }/space/preson?uid=${uid}" class="user-name" title="个人空间" alt="个人空间" target="_blank">--%>
<%--                            <span>漠漠</span>--%>
<%--                        </a>--%>
<%--                        <div class="user-sign">--%>
<%--                            <span>未来谁都说不准</span>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--        </div>--%>
        <div class="video-list" id="video-list">
        </div>
        <div class="pager " id="pager">
        </div>

    </div>
</div>
<input type="hidden" value="${search_text}" id="text">
<input type="hidden" value="${type}" id="type">
<input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" />
</body>
</html>
