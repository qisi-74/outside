$(function() {
    var div_n=0;//post页面img九宫格的标号
    var id="";//post页面img九宫格的标号id
    var init_upload_img=$(".upload-img").html();
    $("#publish").click(function () {
        var val=$("#editor").html();
        alert(val);
        var formData = new FormData();
        formData.append("editor",val);
        formData.append("div_n",div_n+0);

        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/t/post/publish",
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                init_img();
            }
        });


    //恢复九宫格初始化状态
    function init_img(){
        //清除编辑器里的内容
        $("#editor").html("");
        //清除九宫格中的内容
        var nine="#nine";
        var removeid;
        alert(div_n);
        for(var i=1;i<=div_n;i++){
            $("#nine"+i).remove();
        }
        //九宫格标识置0
        div_n=0;
        //清除上传按钮的内容
        $("#upload_img").css("display","block");
        //恢复计数器的内容
        $(".text2").html("0/9");
    }
    window.location.href=$("#PageContext").val()+"/t/post"
    })
        $("#post-img").click(function () {
            $(".upload-img").slideToggle();
    })
    $("#feed-card").init(function () {
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/t/feed",
            data: {},
            processData: false,
            contentType: false,
            success: function (data) {
                $.each(data.reverse(), function (i, item) {
                    var user=getheadimg(item.uid)
                    var index = user.lastIndexOf(":");
                    var head=$("#PageContext").val()+user.substr(0,index)
                    var name= user.substr(index+1);
                    create_post_card(item.pid,head,name, item.pbirth,item.uid,item.pcontext);
                    console.log(item.pid+" "+item.pcontext)
                })

            }
        }
        )
    })
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
    function create_post_card(pid,headimg,name,birthtime,uid,postcontext) {
        var post_card="<div class='post-card' id='post"+pid+"'>" +
            "<div >" +
            "<a href='"+$("#PageContext").val()+"/space/preson?uid="+uid+"' target='_blank'>" +
            "<img src='"+headimg+"' class='head-img'>" +
            "</a> " +
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
        $("#feed-card").append(post_card);
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
        $("#upload_img").click(function(){
            $(".file").trigger("click");
        })
        function add_div(){
            div_n++;
            if(div_n<=9){
                var div="<div >" +
                    "<img class='post-img-card' id='nine"+div_n+"' >" +
                    "</div>";
                $("#nine").prepend(div);
                id="#nine"+div_n;
                $(id).html(div_n);
                if(div_n==9){
                    $("#upload_img").css("display","none");
                }
            }
            $(".text2").html(div_n+"/9");

        }
    $(".file").change(function () {
        var filename = $(".file")[0].files[0].name;
        var filename_extend = filename.substring(filename.lastIndexOf('.'));
        if(extend(filename_extend)=="true"){
            console.log("success");
            var formData = new FormData();//这里需要实例化一个FormData来进行文件上传
            formData.append("file", $(".file")[0].files[0]);
            formData.append("div_n",div_n+1);
            var imgUrl = window.URL.createObjectURL($(".file")[0].files[0]);
            console.log(imgUrl);
            console.log(id);
            $.ajax({
                type: "post",
                url: $("#PageContext").val() + "/t/post/ajax/img",
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    if (data == "error") {
                        alert("文件提交失败!");
                    } else {
                        add_div();
                        console.log(id+imgUrl)
                        $(id).attr("src",imgUrl);
                    }
                }
            });
        }

    })

        $("#toggle").click(function() {
            console.log(this.id)
            $(this).text($("#content").is(":hidden") ? "收起" : "展开");
            $("#content").slideToggle();
        });
    function extend(ext) {
        var faceRegex = "(.jpg|.png|.jpeg)$"; //用于验证图片扩展名的正则表达式
            var re = new RegExp(faceRegex);
            if (re.test(ext.toLowerCase())) {
                return "true";
            } else {
                return "false";
            }

    }
})