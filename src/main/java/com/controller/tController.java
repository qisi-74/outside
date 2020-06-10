package com.controller;

import com.po.comment;
import com.po.post;
import com.po.reply;
import com.service.Interface.*;
import com.service.app.CookieUtil;
import com.service.common.Common;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: tController
 * @Description: 类似于t.bilibili.com中的t,但我做不到买域名
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/17  17:08
 **/
@Controller
@RequestMapping(value = "/t")
public class tController {
    private static final Logger logger= Logger.getLogger(tController.class);

    @Autowired
    private PostService postService;
    @Autowired
    private PostImageService postImageService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private FollowService followService;
    @Autowired
    private NotifyService notifyService;

    @RequestMapping(value = "/post")
    public String post(){
        logger.info("post");
        return "main/post/post";
    }
    @RequestMapping(value = "/feed")
    @ResponseBody
    public List<post> feed(HttpServletRequest request){
        int intuid=Integer.parseInt(request.getSession().getAttribute("uid").toString());
        List<Integer> f=followService.queryFollower(intuid);
        f.add(intuid);
        List<post> postList=postService.getAll(f);
        return postList;
    }

    @RequestMapping(value = "/post/publish")
    public String publish_post(@RequestParam("editor") String editor, @RequestParam("div_n") int div_n, HttpServletRequest req, HttpServletResponse response){
        logger.info("publish_post");
        int uid=  req.getSession().getAttribute("uid")!=null?(int) req.getSession().getAttribute("uid"):-1;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date utildate =new Date();
        Timestamp sqldate=new Timestamp(utildate.getTime());
        String current_date=df.format(utildate);
        List<String> pip=new ArrayList<>();
        for (int i=1;i<=div_n;i++){
            pip.add(CookieUtil.getCookieValue(req,"post_img_nine_"+i,true));
        }
        if(uid!=-1){
            postService.post_publish(editor,uid,current_date);
            int pid=postService.getLast(uid);
            notifyService.createSubScript(pid,"post","comment",uid,sqldate);
            postImageService.addPIPath(pid,pip);
        }
        return "main/post/post";
    }
    @RequestMapping(value = "/post/ajax/img")
    public String post_img(@RequestParam("file") CommonsMultipartFile file, @RequestParam("div_n") int div_n,
                           HttpServletRequest req, HttpServletResponse response, Model model) {
        logger.info("post.ajax.img");

        if (file.getSize() != 0) {
            // 获取上传时候的文件名
            String filename = file.getOriginalFilename();

            // 获取文件后缀名
            String filename_extension = filename.substring(filename
                    .lastIndexOf(".") + 1);
            String path = Common.PostImgPath;

            //时间戳做新的文件名，避免中文乱码-重新生成filename
            long filename1 = new Date().getTime();
            filename = Long.toString(filename1) + UUID.randomUUID().toString() + "." + filename_extension;
            String cookie_nine_number="post_img_nine_"+div_n;
            CookieUtil.setCookie(req,response,cookie_nine_number,filename,true);
            File TempFile = new File(path);
            if (TempFile.exists()) {
                if (TempFile.isDirectory()) {
                    logger.info("该文件夹存在。");
                } else {
                    logger.info("同名的文件存在，不能创建文件夹。");
                }
            } else {
                logger.info("文件夹不存在，创建该文件夹。");
                TempFile.mkdirs();
            }

            //源视频地址=视频缓冲路径+重命名后的视频名
            String yuanPATH = (path + filename);

            logger.info("源视频路径为:" + yuanPATH);

            //上传到本地磁盘/服务器
            try {
                logger.info("写入本地磁盘/服务器;");
                InputStream is = file.getInputStream();
                OutputStream os = new FileOutputStream(new File(path, filename));
                int len = 0;
                byte[] buffer = new byte[2048];
                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
                os.close();
                os.flush();
                is.close();
            } catch (FileNotFoundException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();
                return "error";
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "error";
            }

            return "main/post/post";
        }
        return "main/post/post";
    }
    @RequestMapping(value = "/post/watching")
    public String post_watch(String pid, HttpServletRequest req, Model model){
        int intpid=Integer.parseInt(pid);
        int uid=postService.getuid(intpid);
        String headimg=userService.getheadimg(uid);
        String name=userService.query_name(uid);
        post p=postService.getpost(intpid);
        if(p.getState()==0){
            p=null;
        }
        List<String> pimg=postImageService.selectPostImg(intpid);
        //当前用户
        int session_uid=Integer.parseInt(req.getSession().getAttribute("uid").toString());
        String session_headimg=userService.getheadimg(session_uid);
        String session_name=userService.query_name(session_uid);
        model.addAttribute("pid",intpid);
        model.addAttribute("session_name",session_name);
        model.addAttribute("name",name);
        model.addAttribute("req_uid",uid);
        model.addAttribute("session_headimg",session_headimg);
        model.addAttribute("headimg",headimg);
        model.addAttribute("post_img",pimg);
        model.addAttribute("birthtime",p.getPbirth());
        model.addAttribute("postcontext",p.getPcontext());
        return "main/post_watching";
    }
    @RequestMapping(value = "/post/img")
    @ResponseBody
    public List<String> getpostimg(String pid){
        int intpid=Integer.parseInt(pid);
        List<String> pip=postImageService.selectPostImg(intpid);
        return pip;
    }
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(String table,String id){
        int intid=Integer.parseInt(id);
        if(table.equals("comment")){
            //先删除reply表
            replyService.delete_reply(table,intid);
            commentService.delete(intid);
            //在删除comment表
        }else if(table.equals("reply")) {
            replyService.delete_reply(table,intid);

        }

        return "ok";
    }
    @RequestMapping(value = "/comment/send")
    @ResponseBody
    public String send_comment(@RequestParam("pid")String pid, @RequestParam("context")String content, @RequestParam("headimg")String headimg, @RequestParam("name")String name, HttpServletRequest req){
        int intpid=Integer.parseInt(pid);
        int session_uid=Integer.parseInt(req.getSession().getAttribute("uid").toString());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date utildate =new Date();
        Timestamp sqldate=new Timestamp(utildate.getTime());
        String current_date=df.format(utildate);
        //提交评论
        commentService.comment(intpid,content,session_uid,headimg,name,current_date);
        //获取提交的评论的id
        int last=commentService.getLast(session_uid);
        //当前用户订阅该评论的回复功能
        notifyService.createSubScript(last,"comment","reply",session_uid,sqldate);
        //写入消息队列中，获取刚写入的消息的id
        long id=notifyService.createNoity(content,Byte.valueOf("1"),intpid,"post","comment",session_uid,new Timestamp(new Date().getTime()));

        //写入用户消息队列,第一个参数应为pid 的uid
        int uidpid=postService.getuid(intpid);
        notifyService.createUserNoity(uidpid,id,new Timestamp(new Date().getTime()));
        return "ok";
    }

    @RequestMapping(value = "/reply/send")
    @ResponseBody
    public String send_reply(@RequestParam("cid")String cid, @RequestParam("rid")String rid, @RequestParam("context")String context, @RequestParam("name")String name, @RequestParam("headimg")String headimg, @RequestParam("fromname")String fromname, HttpServletRequest req){

        int intcid=Integer.parseInt(cid);
        int intrid=Integer.parseInt(rid);
        int intuid=Integer.parseInt(req.getSession().getAttribute("uid").toString());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date utildate =new Date();
        Timestamp sqldate=new Timestamp(utildate.getTime());
        String current_date=df.format(utildate);
        String newcontext=context;
        if(!name.equals("no")){
            newcontext="@"+name+"："+context;
        }
        //获取提交的回复的id
        int last=replyService.reply(intcid,intrid,name,newcontext,intuid,fromname,headimg,current_date);
        //当前用户订阅该回复的回复功能
        notifyService.createSubScript(last,"reply","reply",intuid,sqldate);
        //写入消息队列中，获取刚写入的消息的id
        long id=notifyService.createNoity(context,Byte.valueOf("1"),last,"reply","reply",intuid,new Timestamp(new Date().getTime()));
        //写入用户消息队列,第一个参数为回复的评论所属id，即from_id
        int acid=0;
        if(intrid==0){
            acid=commentService.getuid(intcid);
        }else {
            acid=replyService.getfromid(intrid);
        }
        notifyService.createUserNoity(acid,id,new Timestamp(new Date().getTime()));
        return "ok";
    }
    @RequestMapping(value = "/post/comment")
    @ResponseBody
    public List<comment> getcommentlist(String pid){
        int intpid=Integer.parseInt(pid);
        List<comment> pcl=commentService.getCommonList(intpid);
        return pcl;
    }
    @RequestMapping(value = "/post/reply")
    @ResponseBody
    public List<reply> getReplylist(String commentid){
        int intcommentid=Integer.parseInt(commentid);
        List<reply> cid=replyService.getReplyList(intcommentid);
        return cid;
    }


}