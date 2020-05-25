<%--
  Created by IntelliJ IDEA.
  User: Mr.Hao
  Date: 2020/4/9
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes" />
    <title>${title}ing</title>
    <link href="${pageContext.request.contextPath}/statics/css/video-js.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/video/videoplay.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/header.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/statics/js/jQuery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/head.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/video.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/videoplay.js"></script>
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
<div class="app">
    <div class="v-wrap">
        <div class="l-con">
            <div id="viewbox_report" class="video-info" scrollshow="true">
                <h1 title="${title}" class="video-title"><!---->
                    <span class="tit tr-fix">${title}</span>
                </h1>
                <div class="video-data">
                    <span class="a-crumbs">
                        <a target="_blank" href="#">高等教育</a>
                        <i>-</i>
                        <a target="_blank" href="#">工科</a>
                    </span><span>${birth}</span><!---->
                </div>
                    <div class="video-data">
                    <span title="总播放数442820" class="view">44.3万播放&nbsp;·&nbsp;</span>
                    <span title="历史累计弹幕数2119" class="dm">2119弹幕</span>
                    <span class="copyright">未经作者授权，禁止转载!</span><!---->
                    </div>
            </div>

<%--            </div>--%>
            <div class="video-frame">
                <video id="my-video" class="video-js vjs-fluid" controls preload
                       poster="${cp}" data-setup="{'fluid:ture'}"  >
                    <%--        <source id="video_src" src="${pageContext.request.contextPath}/video/play?ov1" type="video/mp4">--%>
                    <%--        <source id="video_src" src="E:\test\video.mp4" type="video/mp4">--%>
                    <source id="video_src" src=${ov} type="video/mp4">
                    <p class="vjs-no-js">
                        To view this video please enable JavaScript, and consider upgrading to a web browser that
                        <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
                    </p>
                </video>
            </div>
        </div>
        <div class="r-con">
            <div class="user">
                <a href="${pageContext.request.contextPath}/space/preson?uid=${uid}">
                    <div class="touxiang">
                        <img src="${headimg}" class="head" title="${name}" alt="${name}" id="h-avatar">
                    </div>
                    <div class="h-basic">
                        <div class="h-name">
                            <span class="name">${name}</span>
                        </div>
                    </div>
                </a>

            </div>
        </div>
    </div>

</div>

<input id="PageContext" name="PageContext" type="hidden" value="${pageContext.request.contextPath}" />
<input type="hidden" id="pid" value="${pid}">
</body>
</html>

