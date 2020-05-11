package com.service.app.email;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: htmlText
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/3/14  12:02
 **/
public class htmlText {
    public static String html(String code) {

        String html = "<center><h1 style='color:purple;'>OUTSIDE</h1>" +
                "<HR style=\"FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)\" width=\"80%\" color=#987cb9 SIZE=1><br><br>"+
                "我们注意到您正在访问【OUTSIDE】！请输入下方验证码"+code+
                "以进行验证<br>"+
                "<h3 style='color:red;'>" + code + "</h3></center>";
        return html;
    }
}
