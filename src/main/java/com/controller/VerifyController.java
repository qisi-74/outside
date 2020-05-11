package com.controller;

import com.service.Interface.PostService;
import com.service.Interface.UserService;
import com.service.Interface.VerifyService;
import com.service.Interface.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: VerifyController
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/26  16:31
 **/
@Controller
@RequestMapping(value = "/verify")
public class VerifyController {
    @Autowired
    private VerifyService verifyService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @RequestMapping(value = "/main")
    public String main(){

        return "verify/verify";
    }
    @RequestMapping(value = "/check")
    public String verifyheck(HttpServletRequest request, HttpServletResponse resp, HttpSession session, Model model){
        String zh=request.getParameter("zhanghao");
        String ps=request.getParameter("password");
        String verid=zh.substring(3,zh.length());
        if(verifyService.check(verid,ps)){
            System.out.println(zh+ps);
            session=request.getSession();
            session.setAttribute("ver_id",verid);
            return "redirect:/verify/main";
        }else {
            return "login";
        }

//        if(userService.login(zh,ps)){
//            session =request.getSession();
//            int uid=userService.uniq(zh);
//            String headimg=userService.getheadimg(uid);
//            session.setAttribute("head_img",headimg);
//            session.setAttribute("uid",uid);
//            return "redirect:/outside/main";
//        }else {
//            model.addAttribute("error","用户名或密码错误!!!");
//            return  "login";
//        }
//        return "redirect:/outside/main";
    }
    @RequestMapping(value = "/block/video")
    @ResponseBody
    public String blockvideo(String id){
        int intid=Integer.parseInt(id);
        videoService.block(intid);
        return "";
    }
    @RequestMapping(value = "/block/post")
    @ResponseBody
    public String blockpost(String id){
        int intid=Integer.parseInt(id);
        postService.block(intid);
        return "";
    }
    @RequestMapping(value = "/block/user")
    @ResponseBody
    public String blockuser(String id){
        int intid=Integer.parseInt(id);
        userService.block(intid);
        return "";
    }
}
