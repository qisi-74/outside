<%--
  Created by IntelliJ IDEA.
  User: Mr.Hao
  Date: 2020/4/28
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>消息界面</title>
    <script src="${pageContext.request.contextPath}/statics/js/jQuery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/notify.js"></script>
    <link href="${pageContext.request.contextPath}/statics/css/header.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/notify.css" rel="stylesheet">
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
<div class="box" id="box">
<div class="left">
<div class="title">
    消息中心
</div>

</div>
    <div class="right">
        <div class="title">
            我的消息
        </div>
        <div class="main-content">
            <div id="notify-list">
<%--                <div class="notify-card">--%>
<%--                    <div class="left-box">--%>
<%--                        <img class="user-head-img" src="${pageContext.request.contextPath}/statics/img/QRcode.png" alt="">--%>
<%--                    </div>--%>
<%--                    <div class="center-box">--%>
<%--                        <div class="line">--%>
<%--                            <a href=""><span>name</span></a>--%>
<%--                            <span>回复了***的评论</span>--%>
<%--                        </div>--%>
<%--                        <a href="">--%>
<%--                            <div class="line">--%>
<%--                            <span>--%>
<%--                                回复内容--%>
<%--                            </span>--%>
<%--                            </div>--%>
<%--                            <div class="line">--%>
<%--                                回复时间--%>
<%--                            </div>--%>
<%--                        </a>--%>

<%--                    </div>--%>
<%--                    <div class="right-box">--%>
<%--                        <span title="" alt=""></span>--%>
<%--                    </div>--%>
<%--                </div>--%>

            </div>

        </div>
    </div>
</div>

<input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" />
</body>
</html>
