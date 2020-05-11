$(function() {
    var textswith=false;
    var replyswith=false;
    var reply_btnumber=0;
    var create=true;
    $("#comment_edit").html("");
    $(".post-img").init(function () {
        getpostimage($("#pid").val())
    })
    $("#video").init(function () {
        setpostvideo($("#pid").val())
    })
    $("#page-content").click(function () {
        if(textswith){
            $("#page-content").addClass("content-enough");
        }else {
            $("#page-content").removeClass("content-enough");
        }
        textswith=(!textswith);
    })
    $("#send-comment").click(function () {
        var head=$(".user-face").attr("src");
        var name=$("#name").val();
        var text=$("#comment_edit").val();
        var formData = new FormData();
        formData.append("pid",$("#pid").val());
        formData.append("context",text);
        formData.append("headimg",head);
        formData.append("name",name);

        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/t/comment/send",
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                if(data.toString()=="ok"){
                    $("#comment_edit").val("");
                    $("#commentList").html("")
                    getcomment($("#pid").val())
                }
            }
        });


    })

    $("#commentList").init(function () {
        //获取评论
        getcomment($("#pid").val())
    })
    function getpostimage(pid) {
        $.ajax({
            type: "post",
            url: $("#PageContext").val()+ "/t/post/img?pid=" + pid ,
            data: {},
            processData: false,
            contentType: false,
            success: function (data) {
                var id=data.length;
                var path=$("#PageContext").val()+"/statics/post/image/"
                $.each(data, function (i, item) {
                    var img_div="<div >" +
                        "<img src='"+path+item+"' class='post-img-card' id='post-img-card"+pid+id+"' >" +
                        "</div>"
                    var zhang=Math.ceil(id/3)*120
                    $("#post-img").css("height",zhang)

                    $("#post-img").append(img_div);
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
                        $("#video").append(video_div)
                    }

                }
            }
        )

    }
    function getcomment(pid) {
        $.ajax({
            type: "post",
            url: $("#PageContext").val()+ "/t/post/comment?pid=" + pid ,
            data: {},
            processData: false,
            contentType: false,
            success: function (data) {
                $.each(data.reverse(),function (i,item){
                    var commentList_div="" +
                        "<div class='comment-card' id='comment-card"+item.id+"'>" +
                            "<a href='"+$("#PageContext").val()+"/space/preson?uid="+item.from_uid+"'  target='_blank'>" +
                            "<img src='"+item.from_headimg+"' class='user-face' >" +
                            "</a>" +
                                "<div class='comment-main-content' >" +
                                    "<div class='comment-card-name' id='comment-"+item.id+"'>" +
                                    item.from_name +
                                    "</div>" +
                                    "<div class='delete' id='delcom_"+item.from_uid+"-"+item.id+"c'>" +
                                    "删除" +
                                    "</div>" +
                                    "<div class='comment-content'>" +
                                    item.content +
                                    "</div>" +
                                    "<div class='comment-birthtime'> " +
                                    item.birthtime +
                                    "<span class='reply_text' id='reply_btn"+item.id+"_' >回复</span>" +
                                    "</div> " +
                                "</div> " +
                        "</div>"
                    $("#commentList").append(commentList_div)
                    getreplyList(item.id);
                })

            }
        })
    }


    function removereply() {
        var ccid='#reply_reply'+reply_btnumber
        $(ccid).remove()
    }
    function createreply_input(commentid,replyid) {
        var reply_input="" +
            "<div class='reply_reply' id='reply_reply" + commentid + "' >"+
                "<div class='comment-publish'>" +
                    "<div>" +
                    "<img class='user-face' src='"+$("#headimg").attr("src")+"'>" +
                    "</div>" +
                        "<div class='textarea-container'>" +
                        "<textarea id='replytext-"+commentid+"_"+replyid+"' class='textarea-container-textarea' maxlength='255' >" +
                        "</textarea>" +
                    "<button type='submit' class='comment-reply-submit' id='send-reply-" +
            commentid +
                    "_" +
            replyid +
            "'>" +

            "发表评论</button>" +
            "</div>" +
                "</div>" +
            "</div>"
        var ccid='#comment-card'+commentid
        var rid='#reply_reply'+commentid;

        if(create){
            $(ccid).append(reply_input)
        }else {
            $(rid).remove()
        }
        create=(!create);


    }
    function getreplyList(commentid) {
        $.ajax({
                type: "post",
                url: $("#PageContext").val() + "/t/post/reply?commentid=" + commentid,
                data: {},
                processData: false,
                contentType: false,
                success: function (data) {
                    if(data.length>0){
                        var div="<div class='reply-list' id='reply-list-"+commentid+"'>" +
                            "</div>"
                        var comment='#comment-card'+commentid
                        $(comment).append(div)
                    }
                    $.each(data.reverse(), function (i, item) {
                        var reply_div="" +
                            "<div class='reply-card' id='reply-card"+commentid+"_"+item.id+"'>" +
                            "<a href='"+$("#PageContext").val()+"/space/preson?uid="+item.from_uid+"' target='_blank'>" +
                            "<img src='"+item.from_headimg+"' class='user-face' >" +
                            "</a>" +
                            "<div class='comment-main-content' >" +
                            "<div class='comment-card-name' id='reply-"+commentid+"_"+item.id+"'>" +
                            item.from_name +
                            "</div>" +
                            "<div class='delete' id='delrep_"+item.from_uid+"-"+item.id+"c"+commentid+"'>" +
                            "删除" +
                            "</div>" +
                            "<div class='comment-content'>" +
                            item.content +
                            "</div>" +
                            "<div class='comment-birthtime'> " +
                            item.birthtime +
                            "<span class='reply_text' id='reply_btn"+commentid+"_"+item.id+"'>回复</span>" +
                            "</div> " +
                            "</div> " +
                            "</div>"
                        var reply='#reply-list-'+commentid
                        $(reply).append(reply_div)

                    })


                }
            }
        )

    }
    function reply(btnid) {
        var this_=btnid;
        var maohao=this_.lastIndexOf("_");
        var commentid=this_.substring(11,maohao)
        var replyid=this_.substring(maohao+1,this_.length)
            var reply='#replytext-'+commentid+"_"+replyid
            var replytxt=$(reply).val()

            var replyname='#reply-'+commentid+"_"+replyid
            //
            var reply_name=$(replyname).text();
            //
            console.log(reply+" "+replytxt)
            var head=$("#headimg").attr("src")
            var fromname=$("#name").val()
        var newreplyid=replyid==""?0:replyid
        var reply_name=reply_name==""?"no":reply_name
            var formData = new FormData();
        formData.append("cid",commentid);
        formData.append("rid",newreplyid);
        formData.append("name",reply_name);
        formData.append("context",replytxt);
        formData.append("headimg",head);
        formData.append("fromname",fromname)

        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/t/reply/send",
            data: formData,
            async:false,
            processData: false,
            contentType: false,
            success: function (data) {
                if(data.toString()=="ok"){

                }
            }
        });
        $(reply).val("")

        unrealreply(commentid)
    }
    function unrealreply(commentid){
            $.ajax({
                    type: "post",
                    url: $("#PageContext").val() + "/t/post/reply?commentid=" + commentid,
                    data: {},
                    async:false,
                    processData: false,
                    contentType: false,
                    success: function (data) {

                            var unreal='#reply-list-'+commentid;
                            if($(unreal).length>0){
                                $(unreal).html("")
                            }else {
                                var div="<div class='reply-list' id='reply-list-"+commentid+"'>" +
                                    "</div>"
                                var comment='#comment-card'+commentid
                                $(comment).append(div)
                            }

                        $.each(data.reverse(), function (i, item) {
                            var reply_div="" +
                                "<div class='reply-card' id='reply-card"+commentid+"_"+item.id+"'>" +
                                "<a href='"+$("#PageContext").val()+"/space/preson?uid="+item.from_uid+"' target='_blank'>" +
                                "<img src='"+item.from_headimg+"' class='user-face' >" +
                                "</a>" +
                                "<div class='comment-main-content' >" +
                                "<div class='comment-card-name' id='reply-"+commentid+"_"+item.id+"'>" +
                                item.from_name +
                                "</div>" +
                                "<div class='delete' id='delrep_"+item.from_uid+"-"+item.id+"c"+commentid+"'>" +
                                "删除" +
                                "</div>" +
                                "<div class='comment-content'>" +
                                item.content +
                                "</div>" +
                                "<div class='comment-birthtime'> " +
                                item.birthtime +
                                "<span class='reply_text' id='reply_btn"+commentid+"_"+item.id+"'>回复</span>" +
                                "</div> " +
                                "</div> " +
                                "</div>"
                            var reply='#reply-list-'+commentid
                            $(reply).append(reply_div)
                        })
                    }
                }
            )


    }
    $(document).on("click",".reply_text",function(){
            var this_=this.id;
            var maohao=this_.lastIndexOf("_")
            var commentid=this_.substring(9,maohao)
            var replayid=this_.substring(maohao+1,this.id.length)
            removereply()
            reply_btnumber=commentid;
            createreply_input(commentid,replayid);
    });
    $(document).on("click",".comment-reply-submit",function(){

            reply(this.id)
            removereply()
            create=true;

    });
    $(document).on("mouseover mouseout",".delete",function(event){
            var del=this.id;
            var delid='#'+del
            var del_=this.id.lastIndexOf("_")
            var del__=this.id.lastIndexOf("-")

            if(event.type == "mouseover"){
                if($("#uid").val()==del.substring(del_+1,del__)){
                    $(delid).addClass("delete_show")
                }
                //鼠标悬浮
            }else if(event.type == "mouseout"){
                $(delid).removeClass("delete_show")
                //鼠标离开
            }
    });
    $(document).on("click",".delete",function(){
            var del=this.id;
            var delid='#'+del
            var del_=del.substring(3,6)
            var del__=del.lastIndexOf("-")
            var dell=del.lastIndexOf("c")
            var table="";
            if(del_=="rep"){
                table="reply"
            }else if(del_=="com"){
                table="comment"
            }

            var id=del.substring(del__+1,dell)
            var comment=del.substring(dell+1,del.length)
        console.log(table)

        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/t/delete?table="+table+"&id="+id,
            data: {},
            processData: false,
            contentType: false,
            success: function (data) {
                if(data.toString()=="ok"){
                    if(table=="comment"){
                        $("#comment-card"+id).remove()
                    }
                    if(table=="reply"){
                        $("#reply-card"+comment+"_"+id).remove()
                    }

                }
            }
        });
    });
    $(document).on("click",".zhankai",function(){
        var _this=this.id;
        var id=_this.substring(7,_this.length)

        var this_='#reply-list-'+id;
        if(replyswith){
            $(this_).addClass("reply-enough");
        }else {
            $(this_).removeClass("reply-enough");
        }
        replyswith=(!replyswith);
    });

})