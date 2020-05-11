$(function(){

  var pbText = $('.form-1 .progress-bar_text');
  function changeText(el, text, color) {
    el.text(text).css('color', color);
  };

//全部都是灰色的
  function primary(){
    $('.form-1 .progress-bar_item').each(function() {
      $(this).removeClass('active')
    });
    $('.form-1 .active').css('background-color', 'transparent');
    changeText(pbText, '密码为空');
    $("#button").attr("disabled", false);
  }

  //密码强度为弱的时候
  function weak(){
    $('.form-1 .progress-bar_item-1').addClass('active');
    $('.form-1 .progress-bar_item-2').removeClass('active');
    $('.form-1 .progress-bar_item-3').removeClass('active');
    $('.form-1 .active').css('background-color', '#FF4B47');
    changeText(pbText, '密码强度：简单','#FF4B47');
  }
  //密码强度为中等的时候
  function middle(){
    $('.form-1 .progress-bar_item-2').addClass('active');
    $('.form-1 .progress-bar_item-3').removeClass('active');
    $('.form-1 .active').css('background-color', '#F9AE35');
    changeText(pbText, '密码强度：一般','#F9AE35');
  }

  //密码强度为强的时候
  function strong(){
    $('.form-1 .progress-bar_item').each(function() {
      $(this).addClass('active');
    });
    $('.form-1 .active').css('background-color', '#2daf7d');
    changeText(pbText, '密码强度：强','#2daf7d');
  }
  function checkpwd(obj){
    var txt = $.trim(obj.val());//输入框内容 trim处理两端空格
    var len = txt.length;
    var num = /\d/.test(txt);//匹配数字
    var small = /[a-z]/.test(txt);//匹配小写字母
    var big = /[A-Z]/.test(txt);//匹配大写字母
    var corps = /\W/.test(txt);//特殊符号
    var val = num + small+big+corps; //四个组合


    if(len<1){
      primary();
    }else if(len<6){
      $("#button").attr("disabled", true);
      weak();
    }else if(len>6 && len<=8){
      $("#button").attr("disabled", false);
      if(val==1){
        weak();
      }else if(val==2){
        middle();
      }
    }else if(len>8){
      if(val==1){
        weak();
      }else if(val==2){
        middle();
      }else if(val==3){
        strong();
      }
      return false;
    }

  }

  $('.input-2').keyup(function(){

    var txt=$(this).val(); //获取密码框内容
    var len=txt.length; //获取内容长度

    checkpwd($(this));

  })
});