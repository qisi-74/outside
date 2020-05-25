<%--
  Created by IntelliJ IDEA.
  User: Mr.Hao
  Date: 2020/4/19
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes" />
    <title>${name}的个人空间</title>
    <link href="${pageContext.request.contextPath}/statics/css/header.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/public.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/personal_space.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/statics/js/jQuery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/head.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/error.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/create.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/person_space.js"></script>

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
    <div class="space-face" style="background-image: url('${pageContext.request.contextPath}/statics/img/runningman.png')">
        <div class="h-title">

        </div>
        <div class="user">
            <div class="touxiang">

                <a target="_blank" id="head" href="${pageContext.request.contextPath }/account/face" >
                    <img src="${headimg}" class="head" title="更换头像" alt="更换头像" id="h-avatar">
                </a>
            </div>
            <div class="h-basic">
                <div class="h-name">
                    <span class="name">${name}</span>
                </div>
                <div class="h-basic-spacing">
                    <input id="h-sign" type="text" placeholder="这个人不懒，但还是什么都没写..." maxlength="60" class="space-input"  value="${sign}" autocomplete="off">
                </div>
            </div>
            <div class="follow">
                <div class="btn_follow">
                    <span id="follow"></span>
                </div>
            </div>
        </div>
    </div>
    <div class="list">
        <div class="tab-list">
            <div class="ttab">
                <a id="main" class="tab">主页</a>
                <a id="post" class="tab">动态</a>
                <a id="upload" class="tab">投稿</a>
                <a href="#" class="tab">收藏</a>
            </div>
            <div class="search_video">
                <input type="text" placeholder="搜索视频" id="search_input" class="space_input">
                <img src="${pageContext.request.contextPath}/statics/img/search.png" title="搜索" alt="搜索" id="search1" class="search">
            </div>
        </div>
    </div>
        <div class="wrapper" id="page-index">
<%--            主页--%>
            <div id="main-page" class="main-page">
                <div class="contribution-list-container">
                    <span class="security-title">我的关注</span>
                    <ul class="contribution-list">
                        <li class="contribution-item">
                            <a href="#"  class="tab" id="following">全部关注</a>
                        </li>
                        <li class="contribution-item">
                            <a href="#" class="tab" id="fan">全部粉丝</a>
                        </li>
                    </ul>
                </div>
                <div class="main-content" id="follow-main-content">
                    <div class="follow-content" id="follow-list">

                    </div>
                    <div class="pager" id="follow-pager">
                    </div>
                </div>
            </div>
<%--            动态--%>
            <div  class="post-page" id="post-page">
                <div  class="post-page-left" id="post-page-left">

                </div>
                <div class="post-page-right" id="post-page-right">
                    uid:${accessid}
                </div>
            </div>
<%--           视频界面--%>
            <div id="video" class="video-page">
                <div class="contribution-list-container">
                        <ul class="contribution-list">
                            <li class="contribution-item">
                                <a id="a_video"  class="tab">视频</a>
                                <span class="num" id="total_v_n"></span>
                            </li>
                            <li class="contribution-item">
                                <a href="#" class="tab">专栏</a>
                                <span class="num">0</span>
                            </li>
                            <li class="contribution-item">
                                <a href="#" class="tab">相簿</a>
                                <span class="num">0</span>
                            </li>
                        </ul>
                    </div>
                    <div class="main-content">
                        <div class="breadcrumb">
                            <p class="item">我的视频</p>
                        </div>
                        <div class="video-box" id="video_box">
                        </div>
                        <div class="pager" id="pager">
                        </div>
                    </div>
            </div>

        </div>
    </div>

    <input type="hidden" value="${uid}" id="uid">
    <input type="hidden" value="${accessid}" id="acid">
    <input type="hidden" value="${own}" id="switch">
    <input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" />

</div>
</body>
</html>
