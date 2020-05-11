$(function() {
        var text=""
    var type="video"
$("#search").click(function () {
    text=$("#search_txt").val();
    console.log(type+text)
    if(text.length<1){
        return;
    }else {
        if(type=="video"){
            searchvideo(text)
        }
        if(type=="user"){
            searchuser(text)
        }
        if(type=="post"){
            searchpost(text)
        }


    }
})

    $("#action").click(function () {
        console.error(type+" "+text)
        if(text.length>=1){
            console.log(type+"click")
            $.ajax({
                    type: "post",
                    url: $("#PageContext").val() + "/verify/block/"+type+"?id=" + text,
                    data: {},
                    processData: false,
                    contentType: false,
                    success: function (data) {

                    }
                }
            )
        }
    })
$("#ver_post").click(function () {
    choose(this)
})
$("#ver_video").click(function () {
    choose(this)
})
$("#ver_user").click(function () {
    choose(this)
})
    function choose(_this) {
        $(".tab").css("color","black")
        var txt='#'+_this.id.substring(4,_this.length)
        $(".verify").css("display","none");
        $(txt).css("display","block");
        $(_this).css("color","blue")
        type=txt.substring(1,txt.length);
    }
    function searchvideo(vid) {
        $("#vid").html("");
        $("#vname").html("");
        $("#vstate").html("封禁")
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/search//verify/video?vid="+vid,
            data: {},
            processData: false,
            contentType: false,
            success: function (data) {
                   $("#vid").html(data.vid);
                   $("#vname").html(data.vname);

                    $("#ovface").attr("src",$("#PageContext").val()+data.vface)
                    $("#ovid").attr("href","/outside_war_exploded/video/play?ovid="+data.vid)
                    $("#ovname").attr("href","/outside_war_exploded/video/play?ovid="+data.vid)
                    $("#ovname").html(data.vname)
                   block('#vstate',data.vstate)
            }
        });
    }
    function searchuser(uid) {
        $("#uid").html("");
        $("#uname").html("");
        $("#ustate").html("封禁")
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/search//verify/user?uid="+uid,
            data: {},
            processData: false,
            contentType: false,
            success: function (data) {
                $("#uid").html(data.uid);
                $("#uname").html(data.name);
                $("#uhead").attr("src",$("#PageContext").val() +data.touxiang)
                block('#ustate',data.state)
            }
        });
    }
    function searchpost(pid) {
        $("#puid").html("");
        $("#pid").html("");
        $("#pcontent").html("");
        $("#pstate").html("封禁")
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/search/verify/post?pid="+pid,
            data: {},
            processData: false,
            contentType: false,
            success: function (data) {
                $("#puid").html(data.uid);
                $("#pid").html(data.pid);
                $("#pcontent").html(data.pcontext);
                block('#pstate',data.state)
            }
        });
    }
    function block(from,state) {
            if(state==null){
                return;
            }
        if(state==1){
            $(from).html("正常")
            $("#action").html("封禁")
        }else {
            $(from).html("封禁")
            $("#action").html("解封")
        }
    }
}
)