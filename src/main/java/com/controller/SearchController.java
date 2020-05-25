package com.controller;

import com.po.post;
import com.po.user;
import com.po.video;
import com.service.Interface.PostService;
import com.service.Interface.UserService;
import com.service.Interface.VideoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: SearchController
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/23  12:11
 **/
@Controller
@RequestMapping(value = "/search")
public class SearchController {
    private static final Log logger = LogFactory.getLog(SearchController.class);
    @Autowired
    private VideoService videoService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @RequestMapping(value = "/video")
    public String search(HttpServletRequest req, HttpServletResponse response, Model model){
        String search_text=req.getParameter("search_text");
        logger.info(search_text);
        model.addAttribute("search_text",search_text);
        model.addAttribute("type","video");
        return "main/search";
    }
    @RequestMapping(value = "/user")
    public String search_user(HttpServletRequest req, HttpServletResponse response, Model model){
        String search_text=req.getParameter("search_text");
        logger.info(search_text);
        model.addAttribute("search_text",search_text);
        model.addAttribute("type","user");
        return "main/search";
    }
    @RequestMapping(value = "/result/video")
    @ResponseBody
    public List<video> search(String key, HttpServletRequest req, HttpServletResponse response, Model model){
        logger.info(key);
        List<video> v= videoService.SearchList(key);
        return v;
    }
    @RequestMapping(value = "/verify/video")
    @ResponseBody
    public video verify_video(String vid, HttpServletRequest req, HttpServletResponse response, Model model){
        logger.info(vid);
        int intvid=Integer.parseInt(vid);
        video v= videoService.videoplay(intvid);
        return v;
    }
    @RequestMapping(value = "/verify/user")
    @ResponseBody
    public user verify_user(String uid, HttpServletRequest req, HttpServletResponse response, Model model){
        logger.info(uid);
        int intuid=Integer.parseInt(uid);
        user u= userService.query(intuid);
        return u;
    }
    @RequestMapping(value = "/verify/post")
    @ResponseBody
    public post verify_post(String pid, HttpServletRequest req, HttpServletResponse response, Model model){
        logger.info(pid);
        int intpid=Integer.parseInt(pid);
        post p= postService.getpost(intpid);
        return p;
    }
    @RequestMapping(value = "/result/user")
    @ResponseBody
    public List<user> search_user(String key, HttpServletRequest req, HttpServletResponse response, Model model){
        logger.info(key);
        List<user> user=userService.searchuser(key);
        return user;
    }


}
