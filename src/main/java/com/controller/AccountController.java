package com.controller;

import com.service.Interface.UserService;
import com.service.app.Converbase64;
import com.service.app.email.EmailCode;
import com.service.app.email.EmailUtil_netease;
import com.service.app.email.EmailUtil_qq;
import com.service.app.email.htmlText;
import com.service.common.Common;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
    private static final Logger logger= Logger.getLogger(AccountController.class);
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/setting")
    public String Setting(){
        return "main/account/account_setting";
}
    @RequestMapping(value = "/info")
    public String account(Model model){
        model.addAttribute("type","account");
    return "main/account/account";
}
    @RequestMapping(value = "/face")
    public String face(HttpServletRequest request, Model model){
        int i=Integer.parseInt(request.getSession().getAttribute("uid").toString());
        String head=userService.getheadimg(i);
        model.addAttribute("head",head);
        model.addAttribute("type","face");
    return "main/account/account";
    }


    @RequestMapping(value = "/update/head")
    public String face(String head, HttpServletRequest request, Model model){
        int i=Integer.parseInt(request.getSession().getAttribute("uid").toString());
        String name= UUID.randomUUID()+".jpg";
        Converbase64.generateImage(head, Common.HEAD_PATH+name);
        String dburl=Common.DBHEAD_PATH+name;
        userService.update_head(i,dburl);
        request.getSession().setAttribute("head_img",dburl);
    return "main/account/account";
}

    @RequestMapping(value = "/updatesetting")
    public String updateSetting(HttpServletRequest request, Model model){
        int i=Integer.parseInt(request.getParameter("uid"));
        String name=request.getParameter("name");
        short sex=(request.getParameter("sex")==null||request.getParameter("sex")=="")?2:Short.parseShort(request.getParameter("sex"));
        String qianming=request.getParameter("qianming");
        if(name!=null){
            if(userService.uniq_name(name)!=null){
                model.addAttribute("error","该昵称已被注册");
                model.addAttribute("type","account");
                return "main/account/account";
            }
        }

            userService.update_setting(i,name,sex,qianming);
        return "main/main";
    }
    @RequestMapping(value = "/update/pwd")
    public String updatepwd(HttpServletRequest request, Model model){
        logger.info("更新邮箱");
        int i=Integer.parseInt(request.getSession().getAttribute("uid").toString());
        String oldpwd=request.getParameter("oldpwd");
        String newpwd=request.getParameter("newpwd");
        if(!userService.checkpwd(i).equals(oldpwd)){
            model.addAttribute("error","原密码输入错误");
            model.addAttribute("type","security");
            return "main/account/account";
        }else {
            userService.updatepwd(i,newpwd);
            return "redirect:/outside/destory";
        }
    }
    @RequestMapping(value = "/update/email")
    public String updateemail(HttpServletRequest request){
        logger.info("updateemail");
        int i=Integer.parseInt(request.getSession().getAttribute("uid").toString());
        logger.info(i);

        String toemail=request.getParameter("toemail");
        logger.info(toemail);
        userService.updatemail(i,toemail);
       return "redirect:/account/security/email";
    }
    @RequestMapping(value = "/newpwd")
    public String updatepwd(@RequestParam("email") String email, @RequestParam("pwd") String pwd, Model model){
        logger.info(email+pwd);
        userService.findpwd(email,pwd);
        return "findpwd";
    }


    @RequestMapping(value = "/findpwd")
    public  String findpwd(String pwd){
        return "findpwd";
    }
    @RequestMapping(value = "/update/sign")
    public String updateSetting(String sign, HttpServletRequest request){
        int i=Integer.parseInt(request.getSession().getAttribute("uid").toString());
            userService.update_sign(i,sign);
        return "main/account/account_setting";
    }
    @RequestMapping(value = "/security/pwd")
    public String security_pwd(Model model){
        model.addAttribute("type","pwd");
        return "main/account/account";
    }

    @RequestMapping(value = "/security/yuan")
    public String security_setting(Model model){
        model.addAttribute("type","security");
        return "main/account/account_security";
    }
    @RequestMapping(value = "/security/phone")
    public String security_phone(HttpServletRequest request, Model model){
        int uid= (int) request.getSession().getAttribute("uid");
        String phone=userService.setting(uid,"phone");
        model.addAttribute("phone",phone.substring(0,3)+"****"+phone.substring(7, phone.length()));
        return "main/account/account_security_phone";
    }
    @RequestMapping(value = "/security/email")
    public String security_email(HttpServletRequest request, Model model){
        int uid= (int) request.getSession().getAttribute("uid");
        String email="";
        email=userService.setting(uid,"email")==null?"":userService.setting(uid,"email");
        if(!email.equals("")){
        model.addAttribute("email",email);
        }
        model.addAttribute("type","email");
        return "main/account/account";
    }
    @RequestMapping(value = "/ajax/yzm/email")
    @ResponseBody
    public String ajax_yzm_email(String email){
        boolean send_success=false;
        String code= EmailCode.getRandom();
        String Content= htmlText.html(code);
        logger.info(Content);
        EmailUtil_netease emailUtil=new EmailUtil_netease();
        try {
            send_success=emailUtil.sendMail(email,Content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!send_success){
            logger.info("send email by qq");
            try {
                EmailUtil_qq email_qq=new EmailUtil_qq();
                email_qq.sendMail(email,Content);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        logger.info("验证码为："+code);
        return code;
    }
    @RequestMapping(value = "/security/password")
    public String security_password(){
        return "main/account/account_security_password";
    }
    @RequestMapping(value = "/security/identification")
    public String security_identification(){
        return "main/account/account_security_identification";
    }

}
