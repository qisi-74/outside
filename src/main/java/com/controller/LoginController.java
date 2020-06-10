package com.controller;



import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/index")
public class LoginController  {
//    private static final Log logger = LogFactory.getLog(LoginController.class);
    private static final Logger logger= Logger.getLogger(LoginController.class);
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request){
        logger.info(request.getSession().getServletContext().getRealPath("/") );
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
