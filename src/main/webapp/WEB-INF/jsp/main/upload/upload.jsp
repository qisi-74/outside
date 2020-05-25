<%@ page import="po.ProgressEntity" %>
<%@ page import="com.sun.org.apache.xpath.internal.operations.Div" %>
<%@ page import="javafx.scene.control.Alert" %><%--
  Created by IntelliJ IDEA.
  User: Mr.Hao
  Date: 2020/3/17
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes" />
    <title>upload</title>

    <script src="${pageContext.request.contextPath}/statics/js/jQuery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/head.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/upload.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/upload/upload_video.css">
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
            <a href="#" class="a">消息</a>
            <a href="${pageContext.request.contextPath }/t/post" target="_blank" class="a" >动态</a>
            <a href="${pageContext.request.contextPath}/outside/destory" class="a">退出</a>
        </div>
        <div class="upload">
            <a href="${pageContext.request.contextPath }/upload/frame" target="_blank" class=" upload_btn a">投稿</a>
        </div>

    </div>
</div>
<div class="bar-left">

</div>
<div class="container">
    <div class="tab">
        <a href="${pageContext.request.contextPath }/upload/frame">视频投稿</a>
    </div>
    <div id="div_frame" class="upload_frame">
        <form id="frame" name="frame" action="${pageContext.request.contextPath }/upload/contribution"  method="post" class="box">
            <div id="video" class="video">
                <div id="swap" class="swap">
                </div>
                <div class="div_click">
                    <div id="upload_div" class="upload1">
                        上传视频
                    </div>
                    <input type="file" class="upload-file" id="up-video" name="up-file"/>
                </div>
            </div>
            <div class="video-list">
                <div class="multi-upload">
                    <span class="text1">文件上传</span>
                    <span class="text2">（单次最多允许上传100p视频，推荐采用mp4、flv格式，可有效缩短审核转码耗时）</span>
                    <br><br>
                    <div class="progress-bar">
                        <div class="video-icon">
                        </div>
                        <div class="video-info">
                            <div class="video-name">
                                adsf
                            </div>
                            <div class="video-other">
                                <span id="video-pause" class="item-status-click">暂停</span>
                                <span id="video-delete" class="item-status-click">删除</span>
                                <span class="video-percent">0%</span>
                            </div>
                            <br><br><br>
                            <div class="info">
                                <span  class="upload-status-intro">已经上传：15.3MB/35.1MB</span>
                                <span  class="upload-speed">当前速度：20.1KB/s</span>
                                <span  class="remain-time">剩余时间：17.0分钟</span>
                            </div>
                            <div class="progress">
                            </div>
                        </div>
                    </div>
                    <div class="bottom-line"/>
                </div>
                <div class="upload-info">
                    <div class="base-info">
                        <p class="text1">基本信息</p>
                        <div class="face-info">
                            <span >视频封面设置</span>
                            <span class="text2">(格式jpeg、png，文件大小≤5MB，建议尺寸≥1146*717，最低尺寸≥960*600）</span>
                        </div>
                        <div class="face">
                            <div class="upload-face">
                                    <img id="imghead" src="${pageContext.request.contextPath}/statics/img/out-tv.jpg" />
                                    <span class="face-tip">上传封面</span>
                            </div>
                        </div>
                            <div class="kind">
                                <p>类型:</p>
                                <input type="radio" class="switch" checked name="radio_kind" id="zizhi" value="0"><span>自制</span>
                                <input type="radio" class="switch"  name="radio_kind" id="zhuanzai" value="1"><span>转载</span>
                            </div>
                            <div class="area">
                                <p>分区：</p>
                                    <div class="area-value" id="area-input">
<%--                                        <input  type="text" maxlength="80" placeholder="请点击选择" id="area" name="area" class="input-box-title">--%>
                                        <div class="area-value-p" name="video_area" id="area">
                                            点击选择
                                        </div>
                                    </div>
                                <div class="area-select">
                                        <div class="area-first">
                                            <div class="first" id="kind1">基础教育</div>
                                            <div class="first" id="kind2">高等教育</div>
                                            <div class="first" id="kind3">奇闻轶事</div>
                                            <div class="first" id="kind4">国家政治</div>
                                            <div class="first" id="kind5">宣传热点</div>
                                            <div class="first" id="kind6">其他</div>
                                        </div>
                                        <div class="area-second">

                                        </div>
                                </div>

                                <div class="title">
                                    <p>标题：</p>
                                    <div id="title-input" class="title-input">
                                        <input  type="text" maxlength="80" placeholder="请输入稿件标题" id="input-title" name="title" class="input-box-title">
                                    </div>
                                </div>
                                <div class="main-info">
                                    <p>简介：</p>
                                    <div id="main-input" class="main-input">
                                        <textarea  maxlength="250" placeholder="请输入更多的描述信息，让更多人知道你的视频吧！" id="input-main" name="main" class="text-area-box-v2-val"></textarea>
                                    </div>
                                </div>
                            </div>
                    </div>
                    <div class="bottom-line"/>
                </div>
                <div class="fan">
                    <span class="text1">粉丝动态</span>
                    <div id="fan-dyna-input" class="main-input">
                        <textarea  maxlength="250" placeholder="有趣的动态描述，会吸引更多的目光哦！" name="fan" id="fan-input" class="text-area-box-v2-val"></textarea>
                    </div>
                </div>
                <input type="checkbox" class="switch" id="watermark" name="watermark" value=1><span>视频水印</span>
                <div class="up-list-submit" id="submit">
                    <span  class="submit-btn-group-add">立即投稿</span>
                </div>
            </div>
        </form>
    </div>

</div>
<input id="PageContext" type="hidden" value="${pageContext.request.contextPath}" />
</body>
</html>
