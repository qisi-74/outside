$(function() {
    var capacity=3;
    var usercapacity=20;
    var uid=$("#uid").val();//session中的id,也就是当前用户
    var acid=$("#acid").val();//访问别人的id
    var videolist=0;
    var followlist=0;
    var video_div=0;
    var follow_div=0;
    var lastpage="#video-list1";
    var lastfollowpage="#follow-list1";

    $(".space-face").init(function () {
        if($("#switch").val()=="off"){
            $(".item").html("TA的视频")
            $("#head").addClass("off");
            $(".space-input").addClass("off");
            $(".follow").css("display","block")
            $(".security-title").html("TA的关注")
        }
    })
    $("#follow-main-content").init(function () {
       fan()
  })

    function fan() {
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/follow/fan/follow?accessid=" + acid,
            data: {},
            processData: false,
            contentType: false,
            success: function (data) {
                if(data.length>0){
                    $("#follow-list").html("")
                    var page = Math.ceil(data.length / usercapacity);
                    setFollowList(page);
                    $.each(data, function (i, item) {
                        $.ajax({
                            type: "post",
                            url: $("#PageContext").val() + "/follow/fan/search?uid="+ item,
                            data: {},
                            processData: false,
                            contentType: false,
                            success: function (data) {
                                setfollowdiv(data.uid,data.touxiang,data.name,data.qianming)
                            }})
                    })
                }
            }
        })
    }
    $("#following").click(function () {
        $("#follow-list").html("")
        fan()
    })
    function setFollowList(page) {
        $("#follow-pager").html("")
        for(var current=1;current<=page;current++){
            var div="<div class='video-list-info' id='follow-list"+current+"' >" +
                "</div>"
            var div_btn="<div class='btn_page' id='follow_btn_page"+current+"'>" +
                current +
                "</div>"
            var id="#follow-list"+current+"";
            $("#follow-list").append(div);
            $("#follow-pager").append(div_btn);

            $(".btn_page").click(function () {
                $(".btn_page").removeClass("btn_page_active");
                var _this=this.id;
                var this_=_this.substring(15,_this.length)
                var follow_list='#follow-list'+this_
                $("#"+_this).addClass("btn_page_active")
                $(lastfollowpage).css("display","none")
                $(follow_list).css("display","block")
                lastfollowpage=follow_list;
            })
            if(current>1){
                $(id).css("display","none")
            }
        }
    }
    $("#fan").click(function () {
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/follow/fan/follower?accessid=" + acid,
            data: {},
            processData: false,
            contentType: false,
            success: function (data) {
                $("#follow-list").html("")
                if(data.length>0){
                    var page = Math.ceil(data.length / usercapacity);
                    setFollowList(page);
                    $.each(data, function (i, item) {
                        $.ajax({
                            type: "post",
                            url: $("#PageContext").val() + "/follow/fan/search?uid="+ item,
                            data: {},
                            processData: false,
                            contentType: false,
                            success: function (data) {
                                setfollowdiv(data.uid,data.touxiang,data.name,data.qianming)
                            }})
                    })
                }
            }
        })
    })
    function setfollowdiv(uid,head,name,sign){
        followlist++;
        var div="<div class='follow-card'>" +
            "<div class='follow-card-left'>" +
            "<a href='"+$("#PageContext").val()+"/space/preson?uid="+uid+"' title='"+name+"' " +
            "target='_blank'>" +
            "<img src='"+$("#PageContext").val()+head+"' class='follow-user-head-img' >" +
            "</a> " +
            "</div>" +
            "<div class='follow-main-content'>" +
            "<div class='follow-info'>" +
            "<a href='"+$("#PageContext").val()+"/space/preson?uid="+uid+"' class='follow-name' title='"+name+"' " +
            "target='_blank'>" +
            "<span>"+name+"</span>" +
            "</a> " +
            "<div class='follow-sign'>" +
            "<span>"+sign+"</span>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>"
         follow_div=Math.ceil(followlist/usercapacity);
        var vvv='#follow-list'+follow_div;
        // $(vvv).html("null")

        $(vvv).append(div);
    }
    $(".follow").init(function () {
        if(uid!=acid){
            $.ajax({
                type: "post",
                url: $("#PageContext").val() + "/follow/query?accessid=" + acid,
                data: {},
                processData: false,
                contentType: false,
                success: function (data) {
                    $("#follow").html(data.toString())
                }
            })
        }

    })
    $(".btn_follow").click(function () {
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/follow/update?accessid=" + acid,
            data: {},
            processData: false,
            contentType: false,
            success: function (data) {
                $("#follow").html(data.toString())
            }
        })
    })
   $("#h-sign").focus(function () {
       $("#h-sign").css("background","white");
   })
    $("#h-sign").focusout(function () {
        $("#h-sign").css("background","#cae3ef45");
    })
    $("#upload").click(function () {
        $("#post-page").css("display","none")
        $("#main-page").css("display","none")
        $("#video").css("display","block")
        $("#a_video").trigger("click");
    })
    $("#search1").click(function () {
        search();
    })

    $("#a_video").click(function () {
        search();
    })

    $("#main").click(function () {
        $("#post-page").css("display","none")
        $("#video").css("display","none")
        $("#main-page").css("display","block")
    })
    $("#post").click(function () {
        //显示动态区
        $("#video").css("display","none")
        $("#main-page").css("display","none")
        $("#post-page").css("display","block")
        //
        getPost()
    })
    function getPost() {
       $("#post-page-left").html("")
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/space/post?uid=" + acid ,
            data: {},
            processData: false,
            contentType: false,
            success: function (data) {
                $.each(data.reverse(), function (i, item) {
                   var user=getheadimg(acid)
                    var index = user.lastIndexOf(":");
                    var head=$("#PageContext").val()+user.substr(0,index)
                    var name= user.substr(index+1);
                    create_post_card(item.pid,head,name, item.pbirth,item.pcontext)
                })
            }
        })
    }

    function getheadimg(uid) {
        var t=""
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/space/gethead?uid=" + uid ,
            data: {},
            async:false,
            processData: false,
            contentType: false,
            success: function (data) {
                 t=""+data.touxiang+":"+data.name+"";
            }
        })
        return t;
    }
    function create_post_card(pid,headimg,name,birthtime,postcontext) {
        var post_card="<div class='post-card' id='post"+pid+"'>" +
            "<div >" +
            "<img src='"+headimg+"' class='head-img'> " +
            "</div>" +
            "<div class='post-main-content'>" +
            "<div class='post-card-name'>" +
            name +
            "</div>" +
            "<div class='post-birthtime'>" +
            birthtime +
            "</div>" +
            "<a href='"+$("#PageContext").val()+"/t/post/watching?pid="+pid+"' >" +
            "<div class='post-content content-enough' id='page-content"+pid+"' >" +
            postcontext +
            "</div>" +
            "</a>" +
            "<div class='post-img' id='post-img" +pid+"'>" +
            "" +
            "</div>" +
            "</div>" +
            "" +
            "</div>"
        $("#post-page-left").append(post_card);

        getpostimage(pid)
        setpostvideo(pid)
    }
    function setpostvideo(pid) {
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/space/post/video?pid=" + pid,
            data: {},
            processData: false,
            contentType: false,
                success: function (data) {
                if(data.vid>0){
                    var ovid=data.vid;
                    var face=$("#PageContext").val()+data.vface;
                    var vname=data.vname;
                    var play="/outside_war_exploded/video/play?ovid="+ovid
                    var vinfo=data.vinfo;
                    var video_div="<div class='post-video'>" +
                        "<a href='"+play+"' target='_blank'>" +
                        "<div class='video-card'>" +
                        "<img src='"+face+"' title='"+vname+"'  alt='"+vname+"' " +
                        "class='video-card-face'> " +
                        "</div>" +
                        "<div class='post-video-info'>" +
                        "<span class='post-video-name'>" +
                        vname +
                        "</span> " +
                        "<span class='post-video-info-text'>" +
                         vinfo +
                        "</span>" +
                        "</div>" +
                        "</a> " +
                        "</div>"
                    $("#post"+pid).append(video_div)
                }

                }
        }
        )

    }
    function getpostimage(pid) {

        $.ajax({
            type: "post",
            url: $("#PageContext").val()+ "/space/post/img?pid=" + pid ,
            data: {},
            processData: false,
            async:false,
            contentType: false,
            success: function (data) {
                var id=data.length;
                var path=$("#PageContext").val()+"/statics/post/image/"
                $.each(data, function (i, item) {
                    var img_div="<div >" +
                        "<img src='"+path+item+"' class='post-img-card' id='post-img-card"+pid+id+"' >" +
                    "</div>"
                    var post_img="#post-img"+pid+""
                    var zhang=Math.ceil(id/3)*120
                    $(post_img).css("height",zhang)


                    $(post_img).append(img_div);
                    $(".post-img-card").click(function () {
                        var _this=this.id;
                        var id="#"+_this
                        $(id).after("<div  class='enlargeImg_wrapper'></div>");
                        var imgSrc = $(this).attr('src');
                        $(".enlargeImg_wrapper").css("background-image", "url(" + imgSrc + ")");
                        $('.enlargeImg_wrapper').fadeIn(200);
                        $('.enlargeImg_wrapper').click(function () {
                            $('.enlargeImg_wrapper').fadeOut(200).remove();
                        })

                    })

                })


            }
        })
        
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

    function setvideodiv(page) {
        for(var current=1;current<=page;current++){
            var div="<div class='video-list-info' id='video-list"+current+"' >" +
                "</div>"
            var div_btn="<div class='btn_page' id='btn_page"+current+"'>" +
                current +
                "</div>"
            var id="#video-list"+current+"";
            $("#video_box").append(div);
            $("#pager").append(div_btn);

                        $(".btn_page").click(function () {
                            $(".btn_page").removeClass("btn_page_active")
                            var _this=this.id;
                            var this_=_this.substring(8,9)
                            var video_list="#video-list"+this_+""
                            $("#"+_this).addClass("btn_page_active")
                            $(lastpage).css("display","none")
                            $(video_list).css("display","block")
                            lastpage=video_list;
                        })
            if(current>1){
                $(id).css("display","none")
            }
        }

    }
    function search(){
        videolist=0;
        var str=$("#search_input").val()
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/space/video?uid="+acid+"&vname="+str,
            data: {},
            processData: false,
            contentType: false,
            success: function (data) {
                if (data == "error") {
                    alert("文件提交失败!");
                } else {
                    //赋值总数
                    $("#total_v_n").html(data.length)
                    //清空
                    $("#video_box").html("");
                    $("#pager").html("")
                    //构建大div，即分页
                    var page=Math.ceil(data.length / capacity);
                    setvideodiv(page);
                    //构建小div，即每页
                    $.each(data.reverse(), function (i, item) {
                        setvideolist(item.vid,item.vface,item.vname)
                    })

                }
            }
        });
    }
    $("#h-avatar").click(function () {
        $("#head-upload").trigger("click");
    })
    $("#h-sign").blur(function () {
        var text=$("#h-sign").val()
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/account/update/sign?sign=" + text,
            data: {},
            processData: false,
            contentType: false,
            success: function (data) {

            }
        })
    })
})