$(function() {
    //******验证表单是否成功所需的全局变量
    var video_success=-1;
    var upload_error="";
    var image_success=-1;
    var lastdance=-1;
    //******
    $(".progress-bar").hover(
        function () {
            $(".item-status-click").css("color", "#000000")
        }, function () {
            $(".item-status-click").css("color", "#1fff18")
        }
    )
    $("#video-pause").hover(
        function () {
            $("#video-pause").css("color", "#0e84ff")
        }, function () {
            $("#video-pause").css("color", "#000000")
        })
    $("#video-delete").hover(
        function () {
            $("#video-delete").css("color", "#0e84ff")
        }, function () {
            $("#video-delete").css("color", "#000000")
        })
    
    var InterValObj;
    var where;
    $("#upload_div").click(function () {
        $("#extend").html("video");
        where = "video";
        $("#up-video").trigger("click");
    });
    $(".upload-face").click(function () {
        $("#extend").html("face");
        where = "face";
        $("#up-video").trigger("click");
    });
    $("#up-video").change(function () {
        //判断是face触发的上传还是video触发的上传
        var filename = $("#up-video")[0].files[0].name;
        var filename_extend = filename.substring(filename.lastIndexOf('.'));
        var formData = new FormData();//这里需要实例化一个FormData来进行文件上传
        formData.append("file", $("#up-video")[0].files[0]);
        var imgUrl="";
        if (extend(where, filename_extend) == "face") {
            imgUrl= window.URL.createObjectURL($("#up-video")[0].files[0]);

        } else if (extend(where, filename_extend) == "video") {
            var size=$("#up-video")[0].files[0].size;
            if(size>208715200){
                return;
            }
            console.log($("#up-video")[0].files[0].size)
            InterValObj = window.setInterval(getStatus, 100);
            $("#video").css("display", "none");
            $(".video-list").css("display", "block");
            $(".video-name").html($("#up-video")[0].files[0].name);
        } else if (extend(where, filename_extend) == "fail") {
            return;
        }
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/upload/ajax",
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data == "error") {
                    alert("文件提交失败!");
                } else {
                    if(imgUrl!=""){
                        $("#imghead").attr("src", imgUrl);
                        image_success=1;
                    }
                }
            }
        });
    });
    //获取文件上传进度条信息
    function getStatus() {
        $.post($("#PageContext").val() + "/upload/ajax/getStatus/map", {}, function (data) {
            $(".upload-status-intro").html("已经上传：" + data.up_read + "/" + data.up_total);
            $(".upload-speed").html("当前速度：" + data.up_speed + "/s");
            $(".remain-time").html("剩余时间：" + data.up_time);
            var pre = (parseFloat(data.up_precent) * 100).toString().substr(0, 5) + "%";
            $(".progress").css("width", pre);
            $(".video-percent").html(pre);
            if (data.up_precent == "1.0") {
                video_success=1;
                window.clearInterval(InterValObj);
            }
        });
    }
    //异步获取数据库中二级区域的值
    function getarea(kindid) {
        $.post($("#PageContext").val() + "/upload/area/ajax", {"first": kindid}, function (data) {
            $(".area-second").html("");
            $.each(data, function (i, item) {
                setsecondarea(item.vgid,item.name,item.info)
                $(".second").click(function () {
                    var _this=this.id;
                    var second_area=$("#"+_this).text();
                    var this_=_this.substring(4,5)
                    var first_area=$("#kind"+this_).text();
                    console.log(first_area)
                    var input_text=first_area+"::"+second_area;
                    $("#area").html(input_text);
                    lastdance=_this.substring(4,6);
                    console.log(lastdance)
                    $("#area-input").trigger("click");
                    // $("#"+_this).css("background-color","red");
                })
            });
        });
    }
    //前台显示二级区域的值
    function setsecondarea(vgid,name,info) {
        console.log("sencond area")
        var div="<div class='second' id='kind"+vgid+"' >\n" +
            "</div>";
        var kind="#kind"+vgid;
        $(".area-second").append(div);
        $(kind).html(name+"<span class='text2' >"+"（"+info+")</span>")
    }
    //验证谁触发的文件上传以及扩展名是否正确
    function extend(wh, ext) {
        var faceRegex = "(.jpg|.png|.jpeg)$"; //用于验证图片扩展名的正则表达式
        var videoRegex = "(.mp4|.flv|.mov|.ts|.avi)$";
        if (wh == "video") {
            var re = new RegExp(videoRegex);
            if (re.test(ext.toLowerCase())) {
                return "video";
            } else {
                return "fail";
            }
        } else if (wh == "face") {
            var re = new RegExp(faceRegex);
            if (re.test(ext.toLowerCase())) {
                return "face";
            } else {
                return "fail";
            }
        } else {
            return "fail";
        }
    }
    $("#submit").click(function () {
        var xz=document.getElementById("watermark")
        var value="";
        if(!xz.checked){
            value="off"
        }else {
            value="on"
        }
        if(check_upload()){
            console.log($("#area").text())
            var formData = new FormData();//这里需要实例化一个FormData来进行文件上传
            formData.append("lastdance",lastdance)//分区
            formData.append("title",$("#input-title").val())//视频来源
            formData.append("main",$("#input-main").val())//简介
            formData.append("fan",$("#fan-input").val())//动态
            formData.append("watermark",value)//水印
            $.ajax({
                type: "post",
                url: $("#PageContext").val() + "/upload/contribution/ajax",
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    if (data == "error") {
                        alert("文件提交失败!");
                    }
                }
            });
            // $("#frame").submit();
            var dizhi=$("#PageContext").val()+"/outside/main"
            window.location.href=$("#PageContext").val()+"/outside/main";
        }else {
            alert(upload_error);
        }

    })
    $("#area-input").click(function () {
        var left=$("#area-input").position().left;
        var top=$("#area-input").position().top;
        $(".area-first").css("left",left);
        $(".area-first").css("top",top+40);
        $(".area-select").slideToggle();
    })
    $("#div_frame").scroll(function(event){
        var left=$("#area-input").position().left;
        var top=$("#area-input").position().top;
        $(".area-first").css("left",left);
        $(".area-first").css("top",top+40);
        var left=$(".area-first").position().left;
        var top=$(".area-first").position().top;
        $(".area-second").css("left",left+$(".area-first").width);
        $(".area-second").css("top",top);
    })
    var first=0;
    $(".first").click(function () {
        var _this=this.id;
        console.log(_this);
        var left=$(".area-first").position().left;
        var top=$(".area-first").position().top;
        $(".area-second").css("left",left+$(".area-first").width);
        $(".area-second").css("top",top);
        getarea(_this);
        if(first==1){
            $(".area-second").slideToggle();
            first++;
        }else {

        }

    })
    
    //验证表单是否填写完整
    function check_upload() {
        // 视频上传成功
        if(video_success!=1){
            upload_error="视频还没有上传完成哦"
            return false;
        }
        //封面上传成功
        if(image_success!=1){
            upload_error="封面还没有上传完成哦"
            return false;
        }
        //正确选择分区
        if(lastdance==-1){
            upload_error="您还没有为您的稿件选择分区"
            return false;
        }
        //标题已输入
        if($("#input-title").val()==""){
            upload_error="请为您的稿件取一个响亮的名字"
            return false;
        }
        //简介已输入
        if($("#input-main").val()==""){
            upload_error="简单介绍一下您的稿件"
            return false;
        }
        //粉丝动态已输入
        if($("#fan-input").val()==""){
            upload_error="不想对您的粉丝说点什么嘛"
            return false;
        }
        return true;
    }
})