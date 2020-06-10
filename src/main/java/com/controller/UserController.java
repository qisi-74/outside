package com.controller;


import com.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/check")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @RequestMapping(value = "/logincheck")
    public String loginheck(HttpServletRequest request, HttpServletResponse resp, HttpSession session, Model model){
        System.out.println(userService);
        String zh=request.getParameter("zhanghao");
        String ps=request.getParameter("password");
        System.out.println(zh+ps);
        if(userService.login(zh,ps)){
            session =request.getSession();
            int uid=userService.uniq(zh);
            if(userService.query(uid).getState()==1){
                String headimg=userService.getheadimg(uid);
                session.setAttribute("head_img",headimg);
                session.setAttribute("uid",uid);
                return "redirect:/outside/main";
            }else {
                model.addAttribute("error","那啥，，你号被封了~");
                return  "login";
            }

        }else {
            model.addAttribute("error","用户名或密码错误!!!");
            return  "login";
        }
//        return "redirect:/outside/main";
    }
    @RequestMapping(value = "/registercheck")
    public String registercheck(HttpServletRequest request, Model model){
        String name=request.getParameter("name");
        String phone=request.getParameter("phone");
        String password=request.getParameter("password");
        if(userService.register(name,password,phone)){
            return "redirect:/index/login";
        }else {
            model.addAttribute("error","该手机号已被注册！！");
            model.addAttribute("name",name);
            model.addAttribute("password",password);
            return  "register";
        }
    }
    @RequestMapping(value = "/ajax/name")
    @ResponseBody
    public String ajax_name(String name){
        if(userService.uniq_name(name)==null){
            return "ok";
        }else {
            System.out.println(userService.uniq_name(name));
            return  "该昵称已被占用";
        }
    }
    @RequestMapping(value = "/ajax/yzm")
    @ResponseBody
    public String ajax_yzm(String phone){
        String code=(int)((Math.random()*9+1)*100000)+"";
        System.out.println("手机号为："+phone);
        System.out.println("验证码为："+code);
//        短信api调用
        return code;
    }
}

