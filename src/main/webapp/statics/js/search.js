$(function() {
    var capacity=6;
    var lastpage="#video-list1";
    var videolist=0;
    var video_div=0;
    var type=$("#type").val();
    var search=$("#PageContext").val()+"/search/"
    $("#video-list").init(function () {
        var tab='#search_'+type
        $(tab).addClass("tab_active")
        search_list()
    })
    function search(str) {
        videolist = 0;
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/search/result?key="+ str,
            data: {},
            processData: false,
            contentType: false,
            success: function (data) {
                if (data == "error") {
                    alert("因不可抗力原因加载失败!");
                } else {
                    if(data.length>0){
                        //清空
                        $("#video-list").html("");
                        $("#pager").html("")
                        //构建大div，即分页
                        var page = Math.ceil(data.length / capacity);
                        setvideodiv(page);
                        $.each(data, function (i, item) {
                            setvideolist(item.vid, item.vface, item.vname)
                        })
                    }else {
                        $("#video-list").html("没有您想要的视频呢")
                    }

                }
            }
        });
    }
    function search_list() {
        var txt=$("#text").val()
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/search/result/"+type+"?key=" +txt,
            data: {},
            processData: false,
            contentType: false,
            success: function (data) {
                if (data == "error") {
                    alert("因不可抗力原因加载失败!");
                } else {
                    if(type=="video"){
                        if(data.length>0){
                            //清空
                            $("#video-list").html("");
                            $("#pager").html("")
                            //构建大div，即分页
                            var page = Math.ceil(data.length / capacity);
                            setvideodiv(page);
                            $.each(data, function (i, item) {
                                setvideolist(item.vid, item.vface, item.vname)
                            })
                        }else {
                            $("#video-list").html("没有您想要的视频呢")
                        }
                    }
                    if(type=="user"){
                        if(data.length>0){
                            //清空
                            $("#video-list").html("");
                            $("#pager").html("")
                            //构建大div，即分页
                            var page = Math.ceil(data.length / capacity);
                            setvideodiv(page);
                            $.each(data, function (i, item) {
                                setuserdiv(item.uid,item.touxiang,item.name,item.qianming)
                            })
                        }else {
                            $("#video-list").html("找不到您搜索的用户呢")
                        }
                    }



                }
            }}
        )
    }
    function setuserdiv(uid,head,name,sign) {
        videolist++;
        var div="<div class='user-card'>" +
            "<div class='user-card-left'>" +
            "<a href='"+$("#PageContext").val()+"/space/preson?uid="+uid+"' title='"+name+"' " +
            "target='_blank'>" +
            "<img src='"+$("#PageContext").val()+head+"' class='search-user-head-img' >" +
            "</a> " +
            "</div>" +
            "<div class='user-main-content'>" +
            "<div class='user-info'>" +
            "<a href='"+$("#PageContext").val()+"/space/preson?uid="+uid+"' class='user-name' title='"+name+"' " +
            "target='_blank'>" +
            "<span>"+name+"</span>" +
            "</a> " +
            "<div class='user-sign'>" +
            "<span>"+sign+"</span>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>"
        video_div=Math.ceil(videolist/capacity);
        var vvv='#video-list'+video_div;
        // $(vvv).html("null")

        $(vvv).append(div);
    }
    function setvideodiv(page) {
        for (var current = 1; current <= page; current++) {
            var div = "<div class='video-list-info' id='video-list" + current + "' >" +
                "</div>"
            var div_btn = "<div class='btn_page' id='btn_page" + current + "'>" +
                current +
                "</div>"
            var id = "#video-list" + current + "";
            $("#video-list").append(div);
            $("#pager").append(div_btn);

            $(".btn_page").click(function () {
                $(".btn_page").removeClass("btn_page_active")
                var _this = this.id;
                var this_ = _this.substring(8, 9)
                var video_list = "#video-list" + this_ + ""
                $("#" + _this).addClass("btn_page_active")
                $(lastpage).css("display", "none")
                $(video_list).css("display", "block")
                lastpage = video_list;
            })
            if (current > 1) {
                $(id).css("display", "none")
            }
        }

    }
    function setvideolist(ovid,face,vname) {
        videolist++;
        var div="<div class='video-card' id='video"+videolist+"'>" +
            "<a href='/outside_war_exploded/video/play?ovid="+ovid+"' target='_blank'>" +
            "<img src='/outside_war_exploded/"+face+"' title='"+vname+"' alt='"+vname+"' class='video-card-face'>" +
            "</a>" +
            "<div class='video-info'>" +
            "<a href='/outside_war_exploded/video/play?ovid="+ovid+"' target='_blank' " +
            "title='"+vname+"' alt='"+vname+"'>" +
            vname  +
            "</a>"
        "</div>"
        video_div=Math.ceil(videolist/capacity);
        var vvv="#video-list"+video_div+"";
        $(vvv).append(div);

    }
    $("#search-btn").click(function () {
        var txt=$("#key").val()
        if(txt.length<2){
            alert("最少输入两个字符哦")
        }else{
            $("#search-form").submit();
        }
    })
    function ngram(){
        var data=$("#text").val();
        console.log(data)
    }
    $("#search_user").click(function () {
        $(".aa").removeClass("tab_active")
        $("#search_user").addClass("tab_active");
        $("#search-form").attr("action",search+"user")
    })
    $("#search_video").click(function () {
        $(".aa").removeClass("tab_active")
        $("#search_video").addClass("tab_active");
        $("#search-form").attr("action",search+"video")
    })
})