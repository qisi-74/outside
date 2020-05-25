$(function() {
        $("#name").keyup(
            function () {
                $.post({
                    url: $("#PageContext").val() + "/check/ajax/name",
                    data: {"name": $("#name").val()},
                    success: function (data) {
                        if (data.toString() == "ok") {
                            $("#name").css("border", "2px solid green");
                            $("#spErr1").html("");
                            $("#button").attr("disabled", false);
                        } else {
                            $("#name").css("border", "2px solid red");
                            $("#spErr1").css("color", "red");
                            $("#spErr1").html(data);
                            $("#button").removeAttr("disabled");
                        }
                    }
                })
            }
        );
        var InterValObj; //timer变量，控制时间
        var count = 60; //间隔函数，1秒执行
        var curCount;//当前剩余秒数
        var sms = "";
        $("#btn_yzm").click(function () {
            if(check_phone()){
                curCount = count;
                $("#btn_yzm").attr("disabled", "true");
                $("#btn_yzm").val(curCount + "秒后可重新发送");
                InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次请求后台发送验证码 TODO
                $.post($("#PageContext").val()+"/check/ajax/yzm",{"phone" : $("#phone").val()},function(data){
                    sms=data.toString();
                    alert(sms)
                });
            }

})
        $("#pwd").focusout(function () {
            $("#pwd").attr("type","text");
        });
        $("#pwd").focus(function () {
            $("#pwd").attr("type","password");
            $("#pwd").attr("oncopy","return false");
            $("#pwd").attr("onpaste","return false");

        });

        $("#yzm").keyup(function() {
            $("#spErr4").html("");
            var code = $("#yzm").val();
            if(code.length==6){
                if (code!=sms) {
                    $("#button").attr("disabled", false);
                    $("#spErr4").css("color","red");
                    $("#spErr4").html("&nbsp;&nbsp;验证码有误");
                    $("#yzm").css("border", "0px solid green");
                    $("#yzm").focus();
                }else {
                    $("#yzm").css("border", "2px solid green");
                    $("#button").removeAttr("disabled");
                }
            }


        });

        //timer处理函数
        function SetRemainTime() {
            if (curCount == 0) {
                window.clearInterval(InterValObj);//停止计时器
                $("#btn_yzm").removeAttr("disabled");//启用按钮
                $("#btn_yzm").val("重新发送验证码");
            } else {
                curCount--;
                $("#btn_yzm").val(curCount + "秒后可重新发送");
            }
        }

        function check_phone() {
            var phone = $("#phone").val();
            $("#spErr3").html("");
            if ((phone == "") || (phone == null)) {
                $("#spErr3").css("color", "red");
                $("#spErr3").html("&nbsp;&nbsp;请输入手机号");
                $("#phone").focus();
                return false;
            } else if (!(phone.length == 11 && /^[1][3,4,5,7,8][0-9]{9}$/.test(phone))) {
                $("#spErr3").css("color", "red");
                $("#spErr3").html("&nbsp;&nbsp;您的手机号是非法的");
                $("#phone").focus();
                return false;
            } else {
                return true;
            }

        }

        $("#button").click(function () {
            var user_name = $("#name").val();
            var user_pwd = $("#pwd").val();

            $("#spErr1").html("");
            $("#spErr2").html("");

            if ((user_name == "") || (user_name == null)) {
                $("#spErr1").css("color", "red");
                $("#spErr1").html("&nbsp;&nbsp;请输入昵称");
                $("#name").focus();
                return;
            }
            if ((user_pwd == "") || (user_pwd == null)) {
                $("#spErr2").css("color", "red");
                $("#spErr2").html("&nbsp;&nbsp;请输入密码");
                $("#pwd").focus();
                return;
            }
            if(user_pwd.length<8||user_pwd.length>16){
                $("#spErr2").css("color", "red");
                $("#spErr2").html("&nbsp;&nbsp;密码长度应为8到16位");
                $("#pwd").focus();
                return;
            }

            if(!check_phone()){
                return;
            }
            if($("#yzm").val()!=sms){
                $("#yzm").focus();
                return;
            }
            form.submit();
            window.open("${pageContext.request.contextPath }/check/registercheck");
        });
    }
)


