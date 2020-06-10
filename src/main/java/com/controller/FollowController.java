package com.controller;

import com.po.user;
import com.service.Interface.FollowService;
import com.service.Interface.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: FollowController
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/25  10:39
 **/
@Controller
@RequestMapping(value = "/follow")
public class FollowController {
    private static final Logger logger= Logger.getLogger(FollowController.class);
    @Autowired
    private FollowService followService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/query")
    @ResponseBody
    public String query(String accessid, HttpServletRequest request, Model model){
        int intacid=Integer.parseInt(accessid);
        int intuid=Integer.parseInt(request.getSession().getAttribute("uid").toString());
        String relation=followService.queryRelation(intacid,intuid);
        return relation;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(String accessid, HttpServletRequest request){
        int intacid=Integer.parseInt(accessid);
        int intuid=Integer.parseInt(request.getSession().getAttribute("uid").toString());
        followService.follow(intacid,intuid);
        String relation=followService.queryRelation(intacid,intuid);
        return relation;
    }
    @RequestMapping(value = "/fan/follower")
    @ResponseBody
    public List<Integer>fan_follow(String accessid, HttpServletRequest request){
        int acid=Integer.parseInt(accessid);
        List<Integer> f=followService.queryFollowing(acid);
        return f;
    }
    @RequestMapping(value = "/fan/follow")
    @ResponseBody
    public List<Integer>fan_follower(String accessid, HttpServletRequest request){
        int acid=Integer.parseInt(accessid);
        List<Integer> f=followService.queryFollower(acid);
        return f;
    }
    @RequestMapping(value = "/fan/search")
    @ResponseBody
    public user fan_search(String uid){
        int intuid=Integer.parseInt(uid);
        user u=userService.query(intuid);
        if(u.getState()==0){
            u=null;
        }
        return u;
    }


}
