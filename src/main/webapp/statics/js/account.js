$(function() {
    var InterValObj; //timer变量，控制时间
    var count = 60; //间隔函数，1秒执行
    var curCount;//当前剩余秒数
    var sms = "";
    curCount = count;
    var type=$("#type").val()
    $(".main-content").init(function () {
        var id='#'+type;
        $(id).css("display","block")
    })
    $("#from").init(function () {
        if($("#fromemail").val()==""){
            $("#from").css("display","none");
        }
    })
    $("#submit_info").click(function () {
        if(checkinfo()>=1){
            $("#form-info").submit();
        }else {
            alert("昵称和签名最少修改一项")
            return
        }


    })
    function checkinfo(){
        var i=0;
        if($("#name").val()!=""){
            i++;
        }
        if($("#sign").val()!=""){
            i++;
        }
        return i;
    }
    $("#submit_pwd").click(function () {
        if($("#oldpwd").val()==""){
            alert("你还没有确认当前密码")
            return
        }
        if($("#newpwd").val().length<8){
            alert("密码必须大于8位")
            return
        }
        $("#form-pwd").submit();
    })
    $("#submit_email").click(function () {
        $("#form-email").submit();
    })
    var $image = $('.img-container > img');
    var options = {
        viewMode: 2,
        modal: true,
        //是否在剪裁框上显示虚线
        guides:true,
        //是否在容器上显示网格背景。
        minCropBoxWidth:180 ,
        minCropBoxHeight:180 ,
        background: false,
        //是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域。
        dragCrop: false,
        //是否允许移动剪裁框。
        movable: false,
        //是否允许改变剪裁框的大小。
        resizable: false,
        //设置剪裁容器的比例
        aspectRatio: 1 / 1,
        preview: ".view",
        // 改变容器和图片时的事件函数
        crop: function (data) {
        }
    };


    $(".upload-face").click(function () {
        $("#up-face").trigger("click");
    })
    $("#up-face").change(function () {

        var filename = $("#up-face")[0].files[0].name;
        var filename_extend = filename.substring(filename.lastIndexOf('.'));
        if(extend(filename_extend)=="ok"){
            $(".pre").css("display","none")
            $(".cur").css("display","block")
            var imgUrl = window.URL.createObjectURL($("#up-face")[0].files[0]);
            $("#cur_head").attr("src",imgUrl)
            $image.cropper(options);
        }else {
            alert("请上传jpeg或png的图片")
            return;
        }

    })
    function extend(ext) {
        ext=ext.toLowerCase()
        var faceRegex = "(.jpg|.png|.jpeg)$"; //用于验证图片扩展名的正则表达式
        var re = new RegExp(faceRegex);
        if (re.test(ext.toLowerCase())) {
            return "ok";
        } else {
            return "fail";
        }
    }


    $("#submit").click(function () {
        var $imgData=$image.cropper('getCroppedCanvas')
        var canvasdata = $image.cropper("getCanvasData");
        var cropBoxData = $image.cropper('getCropBoxData');
        var dataURL = $image.cropper('getCroppedCanvas', {
            width:180,
            height:180
        }).toDataURL('image/png');
        var head = dataURL.substring(22);
console.log(head)
        $.ajax({
            type: "POST",
            url: $("#pagecontext").val()+"/account/update/head",
            data: {head:head},
            success: function(data){
                window.location.href=$("#pagecontext").val()+"/outside/main"
            }
        });
    })



    $("#yzm_btn").click(function () {
        if(check_email()){
            $("#yzm_btn").attr("disabled", "true");
            $("#yzm_btn").val(curCount + "秒后可重新发送");
            InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次请求后台发送验证码 TODO
            $.post($("#pagecontext").val()+"/account/ajax/yzm/email",{"email" : $("#toemail").val()},function(data){
                sms=data.toString();
            });
        }

    })


    $("#email_yzm").keyup(function() {
        $("#spErr2").html("");
        var code = $("#email_yzm").val();
        if(code.length==6){
            if (code!=sms) {
                $("#submit_email").attr("disabled", false);
                $("#spErr2").css("color","red");
                $("#spErr2").html("&nbsp;&nbsp;验证码有误");
                $("#email_yzm").focus();
                $("#submit_email").css("display","none");
            }else {
                $("#submit_email").css("display","block");
            }
        }


    })

//timer处理函数
    function SetRemainTime() {
        if (curCount == 0) {
            window.clearInterval(InterValObj);//停止计时器
            $("#yzm_btn").removeAttr("disabled");//启用按钮
            $("#yzm_btn").val("重新发送验证码");
        } else {
            curCount--;
            $("#yzm_btn").val(curCount + "秒后可重新发送");
        }
    }

    function check_email() {
        var email = $("#toemail").val();
        $("#spErr1").html("");
        if ((email == "") || (email == null)) {
            $("#spErr1").css("color", "red");
            $("#spErr1").html("&nbsp;&nbsp;请输入邮箱");
            $("#toemail").focus();
            return false;
        } else if (!(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email))) {
            $("#spErr1").css("color", "red");
            $("#spErr1").html("&nbsp;&nbsp;您的邮箱格式不正确");
            $("#toemail").focus();
            return false;
        } else if(email==$("#yuanemail").val()){
            $("#spErr1").css("color", "red");
            $("#spErr1").html("&nbsp;&nbsp;您已经绑定了这个邮箱");
            $("#toemail").focus();
            return false;
        }else {
            return true;
        }

    }


})