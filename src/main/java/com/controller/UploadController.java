package com.controller;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: UploadContrller
 * @Description: 用以接收前台upload.jsp传过来的资源并予以返回相对应的信息
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/3/17  18:49
 **/

import com.po.ProgressEntity;
import com.po.videogroup;
import com.service.Interface.*;
import com.service.app.CookieUtil;
import com.service.app.FileExtend;
import com.service.app.media.FFmpeg.ConvertVideo;
import com.service.app.upload.SizeFormat;
import com.service.app.upload.TimeFormat;
import com.service.common.Common;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {
    private static final Log logger = LogFactory.getLog(UploadController.class);

    @Autowired
    private VideoService videoService;
    @Autowired
    private UserService userService;
    @Autowired
    private VideoGroupService videoGroupService;
    @Autowired
    private PostService postService;
    @Autowired
    private NotifyService notifyService;
    //返回到upload.jsp
    @RequestMapping(value = "/frame")
    public String upload(){
        logger.info("upload_frame");
        return "main/upload/upload";
    }
    @RequestMapping(value = "/contribution")
    public String contrubute(HttpServletRequest request){
        //当这里提交之后，对视频进行转码，水印，封面，
        //所以，我需要接收的有：uid，shipin目录,封面目录,
        System.out.println("contribution");
        int uid= request.getSession().getAttribute("uid")!=null?(int) request.getSession().getAttribute("uid"):-1;
        System.out.println("视频来源："+request.getParameter("radio_kind"));
        System.out.println("分区"+request.getParameter("area"));
        System.out.println("标题"+request.getParameter("title"));
        System.out.println("简介"+request.getParameter("main"));
        System.out.println("粉丝动态"+request.getParameter("fan"));

        System.out.println(userService.query_name(Integer.parseInt(request.getSession().getAttribute("uid").toString())));

        String video_src= CookieUtil.getCookieValue(request,"video_src",true);
        String img_src=CookieUtil.getCookieValue(request,"img_src",true);

//        ConvertVideo convertVideo=new ConvertVideo(Common.UPLOAD_PATH+video_src);
//        try {
//            Runtime.getRuntime().exec("echo %cd%");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if(convertVideo.Cut())
//            logger.info("cutpic");
//
//        if (request.getParameter("watermark")!=null) {
//            if (convertVideo.Water_Mark())
//                logger.info("watermark success");
//        }
//
//        if (convertVideo.process())
//            logger.info("To mp4 is ok");


        System.out.println(video_src);
        System.out.println(img_src);
        return "redirect:/outside/main";
    }
    @RequestMapping(value = "/contribution/ajax")
    public String contrubute_ajax(String lastdance, String title, String main, String fan, String watermark, HttpServletRequest request){
        //当这里提交之后，对视频进行转码，水印，封面，
        //所以，我需要接收的有：uid，shipin目录,封面目录,
        System.out.println("contribution");
        System.out.println("lastdance"+lastdance);

        int uid= request.getSession().getAttribute("uid")!=null?(int) request.getSession().getAttribute("uid"):-1;
        System.out.println("标题"+title);
        System.out.println("简介"+main);
        System.out.println("粉丝动态"+fan);
        System.out.println("watermark"+watermark);

        String name=userService.query_name(Integer.parseInt(request.getSession().getAttribute("uid").toString()));

        String video_src=CookieUtil.getCookieValue(request,"video_src",true);
        String img_src=CookieUtil.getCookieValue(request,"img_src",true);


        ConvertVideo convertVideo=new ConvertVideo(name, Common.UPLOAD_PATH+video_src);

        if(convertVideo.Cut())
            logger.info("cutpic");

        if (request.getParameter("watermark")!=null) {
            if (convertVideo.Water_Mark())
                logger.info("watermark success");
        }

        if (convertVideo.process())
            logger.info("To mp4 is ok");
        String cutpic=Common.DB_PICPath+convertVideo.getSaveName()+".jpg";
        String address=Common.DB_VIDEOPath+convertVideo.getSaveName()+".mp4";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date utildate=new Date();
        Timestamp sqldate=new Timestamp(utildate.getTime());
        String current_date=df.format(utildate);
        videoService.upload(title,Common.DB_FACEPath+img_src,cutpic,address,main,current_date,uid,lastdance);
        int vid=videoService.getLast(uid);
        System.out.println("刚刚上传的视频是："+videoService.getLast(uid));

        postService.post_publish_V(fan,vid,uid,current_date);
        int last=postService.getLast(uid);
        notifyService.createSubScript(last,"post","comment",uid,sqldate);
        System.out.println(video_src);
        System.out.println(img_src);
        return "main/upload/upload";
    }
    @RequestMapping(value = "/area/ajax")
    @ResponseBody
    public List<videogroup> getarea(String first, HttpServletRequest request, HttpSession session, Model model){
        String last=first.substring(first.length()-1);
        int first_area=Integer.parseInt(last);
        List<videogroup> vg=videoGroupService.select_sub_area(first_area);
        return vg;
    }
    //异步接收上传的封面或者视频
    @RequestMapping(value = "/ajax")
    public String uploadajax(@RequestParam("file") CommonsMultipartFile file,
                             HttpServletRequest req, HttpServletResponse response, Model model){
        logger.info("upload_ajax");
        System.out.println(req.getSession().getServletContext().getRealPath("/"));
        if (file.getSize() != 0) {
            System.out.println(file.getName());
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getSize());
            //上传的多格式的视频文件-作为临时路径保存，转码以后删除-路径不能写//
//            String path = "E:\\idea\\graduation\\outside\\web\\statics\\temp";
            // 获取上传时候的文件名
            String filename = file.getOriginalFilename();

            // 获取文件后缀名
            String filename_extension = filename.substring(filename
                    .lastIndexOf(".") + 1);
            System.out.println("视频的后缀名:"+filename_extension);
            System.getProperty("user.dir");
            String path="";
            String type="";
            type=new FileExtend().mate(filename_extension);

            //时间戳做新的文件名，避免中文乱码-重新生成filename
            long filename1 = new Date().getTime();
            filename = Long.toString(filename1)+ UUID.randomUUID().toString()+"."+filename_extension;

            if(type.equals("video")){
                path=Common.UPLOAD_PATH;
                CookieUtil.setCookie(req,response,"video_src",filename,true);
            }else if (type.equals("image")){
                path= Common.FACEPath;
                CookieUtil.setCookie(req,response,"img_src",filename,true);
            }else {
                return "main/upload/upload";
            }
            File TempFile = new File(path);
            if (TempFile.exists()) {
                if (TempFile.isDirectory()) {
                    System.out.println("该文件夹存在。");
                } else {
                    System.out.println("同名的文件存在，不能创建文件夹。");
                }
            } else {
                System.out.println("文件夹不存在，创建该文件夹。");
                TempFile.mkdir();
            }

            //源视频地址=视频缓冲路径+重命名后的视频名
            String yuanPATH =(path+filename);

            System.out.println("源视频路径为:"+yuanPATH);

            //上传到本地磁盘/服务器
            try {
                System.out.println("写入本地磁盘/服务器;");
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

//            MediaUtil mediaUtil=new MediaUtil(yuanPATH);
//            boolean b = mediaUtil.Convert_video();
//            videoService.upload("这是一个视频名字","\\statics\\video\\face\\",
//                    "\\statics\\video\\face\\","\\statics\\video\\face\\","",Byte.valueOf("1"),Byte.valueOf("1"));
        }
        return "main/upload/upload";
    }
    //返回上传的进度
    @RequestMapping(value = "/ajax/getStatus/map")
    @ResponseBody
    public Map<String,Object> ajax_uploadStatus_map(HttpSession httpSession){
        ProgressEntity status = (ProgressEntity) httpSession.getAttribute("upload_ps");
        SizeFormat f=new SizeFormat();
        double percent=status.getPrecent();
        long length=status.getpBytesRead();
        long totalLength=status.getpContentLength();
        long time=status.getUseTime();
        long velocity=length/time;
        long totalTime=status.getpContentLength()/velocity;
        long timeLeft=totalTime-time;
//        System.out.println( percent + "%||" + f.Format(length) + "||" + f.Format(totalLength) + "||"
//                + f.Format(velocity*1000)+ "/s||" + time/1000.0 + "s||" + totalTime + "s||" + TimeFormat.formatSeconds(timeLeft));
        Map<String, Object> result = new HashMap<>();
        result.put("up_total",f.Format(totalLength));
        result.put("up_read",f.Format(length));
        result.put("up_precent",percent);
        result.put("up_speed",f.Format(velocity*1000));
        result.put("up_time", TimeFormat.formatSeconds(timeLeft));
        return result;
    }
}
