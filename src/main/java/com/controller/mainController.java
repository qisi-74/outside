package com.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.service.Interface.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/outside")
public class mainController {
    private static final Logger logger= Logger.getLogger(mainController.class);
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/main")
    public String main(HttpSession session, Model model){
        int uid=Integer.parseInt(session.getAttribute("uid").toString());
        model.addAttribute("uid",session.getAttribute("uid"));
        model.addAttribute("user_head",session.getAttribute("head_img"));
        return "main/main";
}
@RequestMapping(value = "/search")
    public String search(HttpServletRequest req, HttpServletResponse response, Model model){
        return "main/search";
}
@RequestMapping(value = "/destory")
public String destroy(HttpServletRequest req, HttpSession session)
{
    session=req.getSession();
    session.removeAttribute("uid");
    session.removeAttribute("head_img");
    return "redirect:/index/login";
}

}
