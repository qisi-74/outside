package com.controller;

import com.po.comment;
import com.po.notify;
import com.po.reply;
import com.service.Interface.CommentService;
import com.service.Interface.NotifyService;
import com.service.Interface.PostService;
import com.service.Interface.ReplyService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: NotifyController
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/28  15:56
 **/
@Controller
@RequestMapping(value = "/notify")
public class NotifyController {
    private static final Logger logger= Logger.getLogger(NotifyController.class);
    @Autowired
    private NotifyService notifyService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ReplyService replyService;
    @RequestMapping(value = "/all")
    @ResponseBody
    public List<notify> getAll(HttpServletRequest request){
        int intuid=Integer.parseInt(request.getSession().getAttribute("uid").toString());
        List<notify> n=notifyService.pullRemind(intuid);
        notifyService.read(intuid);
        return n;
    }
    @RequestMapping(value = "/NotRead")
    @ResponseBody
    public int NotRead(HttpServletRequest request){
        int intuid=Integer.parseInt(request.getSession().getAttribute("uid").toString());
        int n=notifyService.pullNotReadReming(intuid);
        return n;
    }
    @RequestMapping(value = "/post")
    @ResponseBody
    public String getpostcontent(String id){
        logger.info("/post/id");
        int intid=Integer.parseInt(id);
        String content=postService.getpost(intid).getPcontext();
        return content;
    }
    @RequestMapping(value = "/comment")
    @ResponseBody
    public comment getCommentcontent(String id){
        logger.info("/comment/id");
        int intid=Integer.parseInt(id);
        comment content=commentService.getcomment(intid);
        return content;
    }
    @RequestMapping(value = "/reply")
    @ResponseBody
    public reply getreplycontent(String id){
        logger.info("/reply/id");
        int intid=Integer.parseInt(id);
        reply content=replyService.getreply(intid);
        logger.info(content);
        return content;
    }
    @RequestMapping(value = "/message")
    public String view(HttpServletRequest request){

        return "main/notify";
    }


}
