$(function() {
    var type=$("#type").val()
    $(".main-content").init(function () {
        var id='#'+type;
        $(id).css("display","block")
    })
    $("#submit_info").click(function () {
        if($("#name").val()==""){
            alert("你还没有输入要修改的姓名")
            return
        }
        if($("#sign").val()==""){
            alert("你不修改你的签名了吗")
            return
        }

        $("#form-info").submit();
    })
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

})