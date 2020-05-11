<%--
  Created by IntelliJ IDEA.
  User: Mr.Hao
  Date: 2020/4/21
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>${name}的动态</title>
    <link href="${pageContext.request.contextPath}/statics/css/header.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/public.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/post_watching.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/statics/js/jQuery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/head.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/post_watching.js"></script>

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
<div class="main_content">
    <div class="post-card"  id="post"${pid}>
        <div>

            <img class="head-img" src=${pageContext.request.contextPath}${headimg} >
        </div>
        <div class="post-main-content">
            <div class="post-card-name">
                ${name}
            </div>
            <div class="post-birthtime">
                ${birthtime}
            </div>
            <div class="post-content content-enough" id="page-content"+${pid}>
                ${postcontext}
            </div>
            <div class="post-img" id="post-img">

            </div>
            <div id="video">

            </div>
        </div>
    </div>
    <div class="comment" id="comment${pid}">
            <div class="comment-publish" id="comment-publish${pid}">
                    <div>
                        <img class="user-face" id="headimg" src=${pageContext.request.contextPath}${session_headimg} >
                    </div>
                <div class="textarea-container">
                    <textarea cols="80" name="msg" rows="5"
                              placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。"
                              class="textarea-container-textarea" maxlength="255" id="comment_edit">
                    </textarea>
                    <button type="submit" class="comment-submit" id="send-comment">发表评论</button>
                </div>
            </div>
    </div>
    <div id="commentList">
<%--        <div class="comment-card">--%>
<%--            <div class="">--%>
<%--                <img src=${pageContext.request.contextPath}${headimg} alt="1321323" class="user-face">--%>
<%--            </div>--%>
<%--            <div class="comment-main-content">--%>
<%--                <div class="comment-card-name">--%>
<%--                    ${name}--%>
<%--                </div>--%>
<%--                <div class="comment-content content-enough" id="page-content2">--%>
<%--                    ${postcontext}--%>
<%--                </div>--%>
<%--                <div class="comment-birthtime">--%>
<%--                    ${birthtime}--%>
<%--                    <span class="reply_text" id="reply_btn1">回复</span>--%>
<%--                </div>--%>
<%--                <div class="reply_reply">--%>
<%--                        <div class="comment-publish" >--%>
<%--                            <div>--%>
<%--                                <img class="user-face" src=${pageContext.request.contextPath}${session_headimg}>--%>
<%--                            </div>--%>
<%--                            <div class="textarea-container">--%>
<%--                                <textarea   placeholder="" class="textarea-container-textarea" maxlength="255" >--%>
<%--                                </textarea>--%>
<%--                                <button type="submit" class="comment-reply-submit" id="send-raply12">发表评论</button>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                </div>--%>
<%--            </div>--%>

<%--        </div>--%>
    </div>

</div>
<input type="hidden" value="${pid}" id="pid">
<input type="hidden" value=${pageContext.session.getAttribute("uid")} id="uid">
<input type="hidden" value="${session_name}" id="name">
<input type="hidden" value="${session_head}" id="head">
<input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" />
</body>
</html>
