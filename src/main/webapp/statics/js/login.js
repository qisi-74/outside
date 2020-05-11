$(function() {
    $("#sp_zh").html("");
    $("#sp_pwd").html("");
    $("#log_btn").click(function () {
        if(check()){
            var zh=$("#zh").val();
            console.log(zh)
            var type=zh.substring(0,3)
            console.log(type)
            if(type=="ver"){

                $("#form1").attr("action",$("#PageContext").val()+"/verify/check")
                form1.submit();
            }else {
                form1.submit();
            }

        }
    })
    function check() {
        $("#sp_zh").html("");
        $("#sp_pwd").html("");
        var user_name=$("#zh").val();
        var user_pwd=$("#pwd").val();
        if((user_name=="")||(user_name==null)){
            $("#sp_zh").html("请输入注册时用的邮箱或者手机号呀");
            $("#zh").focus();
            return false;
        }
        else if((user_pwd=="")||(user_pwd==null)){
            $("#sp_pwd").html("您还没有输入密码");
            $("#pwd").focus();
            return false;}
        else {
            return true;
        }
    }
})