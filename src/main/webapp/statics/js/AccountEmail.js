$(function() {
    var InterValObj; //timer变量，控制时间
    var count = 60; //间隔函数，1秒执行
    var curCount;//当前剩余秒数
    var sms = "";
    curCount = count;

    $("#yzm_btn").click(function () {
        if(check_email()){
            $("#yzm_btn").attr("disabled", "true");
            $("#yzm_btn").val(curCount + "秒后可重新发送");
            InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次请求后台发送验证码 TODO
            $.post($("#PageContext").val()+"/account/ajax/yzm/email",{"email" : $("#email").val()},function(data){
                            sms=data.toString();
                        });
        }

    })


        $("#email_yzm").keyup(function() {
            $("#spErr2").html("");
            var code = $("#email_yzm").val();
            if(code.length==6){
                if (code!=sms) {
                    $("#button").attr("disabled", false);
                    $("#spErr2").css("color","red");
                    $("#spErr2").html("&nbsp;&nbsp;验证码有误");
                    $("#email_yzm").focus();
                }else {
                    $("#button").removeAttr("disabled");
                }
            }


        })

timer处理函数
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
            var email = $("#email").val();
            $("#spErr1").html("");
            if ((email == "") || (email == null)) {
                $("#spErr1").css("color", "red");
                $("#spErr1").html("&nbsp;&nbsp;请输入邮箱");
                $("#email").focus();
                return false;
            } else if (!(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email))) {
                $("#spErr3").css("color", "red");
                $("#spErr3").html("&nbsp;&nbsp;您的邮箱格式不正确");
                $("#phone").focus();
                return false;
            } else {
                return true;
            }

        }


    })



