$(function() {
    $("#box").init(function () {
        console.log("box init")
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/notify/all",
            data: {},
            processData: false,
            contentType: false,
            success: function (data) {
               $.each(data.reverse(),function (i,item) {
                   var user= getheadimg(item.sender)
                   var index = user.lastIndexOf(":");
                   var head=$("#PageContext").val()+user.substr(0,index)
                   var name= user.substr(index+1);
                   var action=convert(item.action,item.targetType)
                   var time=timestampToTime(item.createdAt)
                   var content=getcontent(item.targetType,item.targetid);
                   var pid=getpid(item.targetType,item.targetid)
                   console.log(pid)
                   creatediv(item.id,name,head,item.sender,action,item.content,time,content,pid)
               })
            }
        });
    })
    function getpid(type,id) {
        var pid=0;
        if(type=="post"){
            pid=id;
        }
        if(type=="comment"){
            $.ajax({
                type: "post",
                url: $("#PageContext").val() + "/notify/comment?id="+id,
                data: {},
                async:false,
                processData: false,
                contentType: false,
                success: function (data) {
                    id=data.pid;
                    console.log("comment"+data.pid)
                }
            });
        }
        if(type=="reply"){
            $.ajax({
                type: "post",
                url: $("#PageContext").val() + "/notify/reply?id="+id,
                data: {},
                async:false,
                processData: false,
                contentType: false,
                success: function (data) {
                    id=getpid("comment",data.comment_id);
                }
            });
        }
        return id;
    }
    function getcontent(type,id) {
        var content=""
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/notify/"+type+"?id="+id,
            data: {},
            async:false,
            processData: false,
            contentType: false,
            success: function (data) {

                if(type=="post"){
                    content=""+data.toString()+"";
                }else {
                    content=data.content;
                }


            }
        });
        console.log(type+content)
        return content;
    }
    function timestampToTime(timestamp) {
        var date = new Date(parseInt(timestamp));//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        var D = date.getDate() + ' ';
        var h = (date.getHours() >= 10 ? date.getHours() : '0' + date.getHours()) + ':';
        var m = (date.getMinutes() >= 10 ? date.getMinutes() : '0' + date.getMinutes()) + ':';
        var s = (date.getSeconds() >= 10 ? date.getSeconds() : '0' + date.getSeconds());
        return Y + M + D + h + m + s;
    }
    function convert(action,type) {
        var str=""
        var straction=""
        var strtype=""
        if(action=="comment"){
            straction="评论"
        }
        if(action=="reply"){
            straction="回复"
        }
        if(action=="follow"){
            straction="关注"
        }
        if(type=="post"){
            strtype="投稿"
        }
        if(type=="reply"||type=="comment"){
            strtype="评论"
        }
        str=straction+"了我的"+strtype;
        return str;
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
    function creatediv(id,name,head,uid,action,content,time,yuancontent,pid) {
        var div="<div class='notify-card' id='notify-card"+id+"'>" +
            "<div class='left-box'>" +
            "<img class='user-head-img' src='"+head+"' alt='"+name+"'>" +
            "</div>" +
            "<div class='center-box'>" +
            "<div class='line'>" +
            "<a href='"+$("#PageContext").val()+"/space/preson?uid="+uid+"'>" +
            "<span class='notify-card-name'>" +
            name +
            "</span>" +
            "</a>" +
            "<span>" +
            action +
            "</span>" +
            "</div>" +
            "<a href='"+$("#PageContext").val()+"/t/post/watching?pid="+pid+"'>" +
            "<div class='line'>" +
            "<span>" +
            content +
            "</span>" +
            "</div>" +
            "<div class='line'>" +
            time +
            "</div>" +
            "</a>" +
            "</div>" +
            "<div class='right-box'>" +
            "<span title='"+yuancontent+"' alt='"+yuancontent+"'>" +
            yuancontent +
            "</span>" +
            "</div>" +
            "</div>"

        $("#notify-list").append(div);
    }

    function formatDateTime(inputTime) {
        let date = new Date(inputTime);
        let y = date.getFullYear();
        let m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        let d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        let h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        let minute = date.getMinutes();
        let second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return `${y}-${m}-${d}`     //  ${h}:${minute}+':'+second;
    }
    $("#search").click(function () {
        var text = $("#search_txt").val();
        if (text.length<2) {
            alert("最少输入两位词语")
        } else {
            $(".search-form").submit();
        }
    })
})