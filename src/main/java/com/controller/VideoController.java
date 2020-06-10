package com.controller;

import com.po.video;
import com.service.Interface.PostService;
import com.service.Interface.UserService;
import com.service.Interface.VideoService;
import com.service.common.Common;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
@RequestMapping(value = "/video")
public class VideoController {
    private static final Logger logger= Logger.getLogger(VideoController.class);

    @Autowired
    private VideoService videoService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @RequestMapping(value = "/play")
    public String video_playing(String ovid, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        logger.info(ovid);
        int vid =Integer.parseInt(ovid);
        video v=videoService.videoplay(vid);
        logger.info("状态"+v.getVstate());
        if(v.getVstate()==0){

            v=null;
        }
        String projectpath=request.getContextPath();
        logger.info("ov");
        int uid=v.getUid();
        String name=userService.query_name(uid);
        String head= Common.Outside+userService.getheadimg(uid);
        String title=v.getVname();
        String ov=v.getVaddress();
        String cut_pic=v.getVcut_pic();
        String birth=v.getVbirth();
        int pid= postService.getpid(vid);
        model.addAttribute("name",name);
        model.addAttribute("uid",uid);
        model.addAttribute("headimg",head);
        model.addAttribute("ov",projectpath+ov);
        model.addAttribute("cp",projectpath+cut_pic);
        model.addAttribute("title",title);
        model.addAttribute("birth",birth);
        model.addAttribute("pid",pid);
        return "main/video_playing";
}
//以下为以流的形式播放
//@RequestMapping(value = "/playing")
//    public String playing(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
//    logger.info("video_playing");
//    String File = "E:\\test" + "\\" + "video.mp4";
//    response.reset();
//    response.setContentType("video/mp4;charset=UTF-8");
//    InputStream in = null;
//    ServletOutputStream out = null;
//    try {
//        out = response.getOutputStream();
//
//        in = new FileInputStream(new File(File));
//        if(in !=null){
//            byte[] b = new byte[1024];
//            int i = 0;
//            while((i = in.read(b)) > 0){
//                out.write(b, 0, i);
//            }
//            out.flush();
//            in.close();
//        }
//    } catch (Exception e) {
//
//        e.printStackTrace();
//
//    }finally{
//        if(in != null) {
//            try { in.close(); } catch (IOException e) { }
//            in = null;
//        }
//        if(out != null) {
//            try { out.close(); } catch (IOException e) { }
//            out = null;
//        }
//    }
//        return "main/video_playing";
//}
//    @RequestMapping(value = "/picture")
//    public String picture(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
//        logger.info("video_cut_pic");
//        String File = "E:\\outside\\picture\\huawei-image-1.jpg";
//        response.reset();
//        response.setContentType("image/jpg;charset=UTF-8");
//
//        InputStream in = null;
//        ServletOutputStream out = null;
//        try {
//            out = response.getOutputStream();
//
//            in = new FileInputStream(new File(File));
//            if(in !=null){
//                byte[] b = new byte[1024];
//                int i = 0;
//                while((i = in.read(b)) > 0){
//                    out.write(b, 0, i);
//                }
//                out.flush();
//                in.close();
//            }
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }finally{
//            if(in != null) {
//                try { in.close(); } catch (IOException e) { }
//                in = null;
//            }
//            if(out != null) {
//                try { out.close(); } catch (IOException e) { }
//                out = null;
//            }
//        }
//        return "main/video_playing";
//    }
//    @ResponseBody
//    @RequestMapping("/getVideoSrc")
//    public OutputStream getVideoSrc(HttpServletRequest httpServletRequest,
//                                    HttpServletResponse httpServletResponse){
//        logger.info("XHR:getvideosrc-----------------------------");
//        //1.创建文件对象
//        File f = new File("E:\\test\\video.mp4");
//        //2.获取文件名称
//        String fileName = f.getName();
//        //3.导出文件
//        String agent = httpServletRequest.getHeader("User-Agent").toUpperCase();
//        InputStream fis = null;
//        OutputStream os = null;
//        try {
//            httpServletResponse.reset();
//            //4.获取输入流
//            fis = new BufferedInputStream(new FileInputStream(f.getPath()));
//            byte[] buffer;
//            buffer = new byte[fis.available()];
//            fis.read(buffer);
//            //5.由于火狐和其他浏览器显示名称的方式不相同，需要进行不同的编码处理
//            if(agent.indexOf("FIREFOX") != -1){//火狐浏览器
//                httpServletResponse.addHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("GB2312"),"ISO-8859-1"));
//            }else{//其他浏览器
//                httpServletResponse.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
//            }
//            //6.设置response编码
//            httpServletResponse.setCharacterEncoding("UTF-8");
//            httpServletResponse.addHeader("Content-Length", "" + f.length());
//            //设置输出文件类型
//            httpServletResponse.setContentType("video/mp4");
//            //7.获取response输出流
//            os = httpServletResponse.getOutputStream();
//            os.flush();
//            //8.输出文件
//            os.write(buffer);
//        }catch(Exception e){
//            logger.info("try  "+e.getMessage());
//        } finally{
//            //关闭流
//            try {
//                if(fis != null){ fis.close(); }
//
//                if(os != null){ os.flush(); }
//
//                if(os != null){os.close(); }
//
//            } catch (IOException e) {
//                logger.info("finally  "+e.getMessage());
//            }
//        }
//
//        return os;
//    }
}
