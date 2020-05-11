package com.controller;

import com.po.post;
import com.po.user;
import com.po.video;
import com.service.Interface.PostImageService;
import com.service.Interface.PostService;
import com.service.Interface.UserService;
import com.service.Interface.VideoService;
import com.service.common.Common;
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
 * @ClassName: SpaceController
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/19  17:06
 **/
@Controller
@RequestMapping(value = "/space")
public class SpaceController {
    @Autowired
    private UserService userService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private PostService postService;
    @Autowired
    private PostImageService postImageService;

    private static final Log logger = LogFactory.getLog(SpaceController.class);

    @RequestMapping(value = "/preson")
    public String preson_space(String uid, HttpServletRequest request, Model model) {
        int useruid= (int) request.getSession().getAttribute("uid");
        int accessid=Integer.parseInt(uid);
        user acc=userService.query(accessid);
        if(acc.getState()==0){
            acc=null;
        }
        String name=acc.getName();
        String head= Common.Outside+acc.getTouxiang();
        user u=userService.query(useruid);
        model.addAttribute("sign",acc.getQianming());
        if(useruid==accessid){
            model.addAttribute("own","on");
        }else {
            model.addAttribute("own","off");
        }
        model.addAttribute("uid",useruid);
        model.addAttribute("accessid",accessid);
        model.addAttribute("name",name);
        model.addAttribute("headimg",head);
        logger.info("preson_space");
        return "main/personal_space";
    }
    @RequestMapping(value = "/video")
    @ResponseBody
    public List<video> video_list(String uid, String vname, HttpServletRequest request, HttpServletResponse response, Model model){
        System.out.println(uid);
        int intuid=Integer.parseInt(uid);
//        List<video> videolist=videoService.getVidList(intuid);
        System.out.println(vname+"------------------------------------------------------------------------");
        List<video> videolist=videoService.getVtextList(intuid,vname);
        model.addAttribute("videolist",videolist);
        return videolist;
    }
    @RequestMapping(value = "/post")
    @ResponseBody
    public List<post> post_list(String uid, HttpServletRequest request, HttpServletResponse response, Model model){
        System.out.println(uid);
        int intuid=Integer.parseInt(uid);
        List<post> videolist=postService.getAll(intuid);
//        model.addAttribute("videolist",videolist);
        return videolist;
    }
    @RequestMapping(value = "/gethead")
    @ResponseBody
    public user gethead(String uid, HttpServletRequest request, HttpServletResponse response, Model model){
        System.out.println(uid);
        int intuid=Integer.parseInt(uid);
        user u=new user();
        u.setName(userService.query_name(intuid));
        u.setTouxiang(userService.getheadimg(intuid));
//        model.addAttribute("videolist",videolist);
        return u;
    }
    @RequestMapping(value = "/post/img")
    @ResponseBody
    public List<String> getpostimg(String pid){
        int intpid=Integer.parseInt(pid);
        List<String> pip=postImageService.selectPostImg(intpid);
        return pip;
    }
    @RequestMapping(value = "/post/video")
    @ResponseBody
    public video getpostvideo(String pid){
        System.out.println("pid:"+pid);
        int intpid=Integer.parseInt(pid);
        int vid=postService.getvid(intpid);
        System.out.println(vid);
        video v=null;
        if(vid!=0){
            System.out.println(vid);
            v=videoService.videoplay(vid);
        }

        return v;
    }
}
