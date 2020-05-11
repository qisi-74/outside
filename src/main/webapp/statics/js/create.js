$(function() {
    var textclick=true;

    $(".post-content").click(function () {
        console.log("1111")
        var _this=this.id;
        var id="#"+_this
        if(textclick)
        $(id).removeClass("content-enough")
        else
        $(id).addClass("content-enough")
        textclick=(!textclick)
    })


})