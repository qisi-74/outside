package com.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/index")
public class LoginController  {
    private static final Log logger = LogFactory.getLog(LoginController.class);
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request){
        if(request.getSession().getAttribute("uid")!=null){
            return "redirect:/outside/main";
        }
        logger.info("login");
        return "login";
}
    @RequestMapping(value = "/register")
    public String register(){
        logger.info("register");
        return "register";
    }
}
