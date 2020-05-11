$(function() {

    var rotate = document.getElementById('rotate');
    var photo = document.getElementById('photo');
    var buttons = document.getElementById('buttons').getElementsByTagName('span');
    var prev = document.getElementById('prev');
    var next = document.getElementById('next');
    var index = 1;
    var len = 5;
    var changed = false;
    var interval = 5000;
    var timer;
    var videolist=0;
    var ext_video=0;;
$(".recommend-box").init(function () {
    var str="";
    var acid="101";
    $.ajax({
        type: "post",
        url: $("#PageContext").val() + "/space/video?uid="+acid+"&vname="+str,
        data: {},
        processData: false,
        contentType: false,
        success: function (data) {
            if (data == "error") {
                alert("文件提交失败!");
            } else {
                $.each(data.reverse(), function (i, item) {
                    setvideolist(item.vid,item.vface,item.vname)
                })

            }
        }
    });
})
    $(".ttab").init(function () {
        $.ajax({
            type: "post",
            url: $("#PageContext").val() + "/notify/NotRead",
            data: {},
            processData: false,
            contentType: false,
            success: function (data) {

                if(data.toString()=="0"){
                    console.log(data)
                    console.log(data.toString())
                    $(".remain").css("display","none")
                }
                $(".remain").html(data)
            }
        });
    })
    $(".ext-video").init(function () {
            var str="";
            var acid="101";
            $.ajax({
                type: "post",
                url: $("#PageContext").val() + "/space/video?uid="+acid+"&vname="+str,
                data: {},
                processData: false,
                contentType: false,
                success: function (data) {
                    if (data == "error") {
                        alert("文件提交失败!");
                    } else {
                        $.each(data.reverse(), function (i, item) {
                            extvideo(item.vid,item.vface,item.vname)
                        })

                    }
                }
            });

    })
    function extvideo(ovid,face,vname) {

        if(ext_video<6){
            ext_video++;
            var div="<div class='video-card' id='ext_video"+ext_video+"'>" +
                "<a href='/outside_war_exploded/video/play?ovid="+ovid+"' target='_blank'>" +
                "<img src='/outside_war_exploded/"+face+"' title='"+vname+"' alt='"+vname+"' class='video-card-face'>" +
                "</a>" +
                "<div class='video-info'>" +
                "<a href='/outside_war_exploded/video/play?ovid="+ovid+"' target='_blank' " +
                "title='"+vname+"' alt='"+vname+"'>" +
                vname  +
                "</a>"
            "</div>"
            $(".ext-video").append(div);
            console.log(ext_video)
        }
    }
    function setvideolist(ovid,face,vname) {
    if(videolist<4){
        videolist++;
        var div="<div class='video-card' id='video"+videolist+"'>" +
            "<a href='/outside_war_exploded/video/play?ovid="+ovid+"' target='_blank'>" +
            "<img src='/outside_war_exploded/"+face+"' title='"+vname+"' alt='"+vname+"' class='video-card-face'>" +
            "</a>" +
            "<div class='video-info'>" +
            "<a href='/outside_war_exploded/video/play?ovid="+ovid+"' target='_blank' " +
            "title='"+vname+"' alt='"+vname+"'>" +
            vname  +
            "</a>"
        "</div>"
        $(".recommend-box").append(div);
    }
    }
    //改变图片
    function change (offset) {
        if (offset == 0) {
            return;
        }
        changed = true;
        var time = 300;
        var inteval = 10;
        var speed = offset/(time/inteval);
        var left = parseInt(photo.style.left) + offset;

        var go = function (){
            if ( (speed > 0 && parseInt(photo.style.left) < left) || (speed < 0 && parseInt(photo.style.left) > left)) {
                photo.style.left = parseInt(photo.style.left) + speed + 'px';
                setTimeout(go, inteval);
            }
            else {
                photo.style.left = left + 'px';
                if(left>-200){
                    photo.style.left = -550 * len + 'px';
                }
                if(left<(-550 * len)) {
                    photo.style.left = '-550px';
                }
                changed = false;
            }
        }
        go();
    }
    //点亮圆点
    function showButton() {
        for (var i = 0; i < buttons.length ; i++) {
            if( buttons[i].className == 'on'){
                buttons[i].className = '';
                break;
            }
        }
        buttons[index - 1].className = 'on';
    }
    //自动播放
    function play() {
        timer = setTimeout(function () {
            next.onclick();
            play();
        }, interval);
    }
    //计时器停止
    function stop() {
        clearTimeout(timer);
    }
    //单击下一张按钮
    next.onclick = function () {
        if (changed) {
            return;
        }
        if (index == 5) {
            index = 1;
        }else {
            index += 1;
        }
        change(-550);
        showButton();
    }
    //单击上一张按钮
    prev.onclick = function () {
        if (changed) {
            return;
        }
        if (index == 1) {
            index = 5;
        }else {
            index -= 1;
        }
        change(550);
        showButton();
    }
    //单击圆点切换图片
    for (var i = 0; i < buttons.length; i++) {
        buttons[i].onclick = function () {
            if (changed) {
                return;
            }
            if(this.className == 'on') {
                return;
            }
            var myIndex = parseInt(this.getAttribute('index'));
            var offset = -550 * (myIndex - index);

            change(offset);
            index = myIndex;
            showButton();
        }
    }

    play();
    $("#search").click(function () {
        var text = $("#search_txt").val();
        if (text.length<2) {
            alert("最少输入两位词语")
        } else {
            $(".search-form").submit();
        }
    })
})