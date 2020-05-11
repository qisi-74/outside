package com.service.app.media;

import com.service.common.Common;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: MediaUtil
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/10  11:38
 **/
public class MediaUtil {
    private static String PATH= "E:\\test\\video.mp4";
    private static String SaveName= "";
    private static String type= "";

    public MediaUtil(String oldPath){
        PATH=oldPath;
        System.out.println(PATH);
        Calendar c = Calendar.getInstance();
        SaveName = String.valueOf(c.getTimeInMillis())+ Math.round(Math.random() * 100000);
        type = PATH.substring(PATH.lastIndexOf(".") + 1, PATH.length()).toLowerCase();
        System.out.println(SaveName);
//        PATH=string;
        if (!checkfile(PATH)) {   //判断路径是不是一个文件
            System.out.println(PATH + " is not file");
            return;
        }
    }
    public boolean Convert_video(){
        boolean conver_video=false;
        boolean cut_pic=false;
        boolean water_mark=true;
        if(conver_video){
            process();
        }
        if(cut_pic){

        }
        if(water_mark){
            Water_Mark();
        }
        return true;
    }

    private boolean Water_Mark(){
        String tarPath=Common.VIDEOPath+SaveName+"."+type;
        boolean ByImg=true;
        boolean ByFont=false;
        if(ByImg){
            processFfmpegWatermarkByImg(PATH,tarPath,Common.WATERMARK);
        }
        return true;
    }
    private  boolean checkfile(String path) {
        File file = new File(path);
        if (file.isFile()) {
            return true;
        }
        return false;
    }

    private  boolean process() {
        // 判断视频的类型
        int type = checkContentType();
        boolean status = false;
        //如果是ffmpeg可以转换的类型直接转码，否则先用mencoder转码成AVI
        if (type == 0) {
//                status=processMP4(PATH);// 直接将文件转为mp4文件
//                if(!status){
            status = processFLV(PATH);// 直接将文件转为flv文件
//                }
        } else{
            System.out.println("无法转码");
            status=false;
        }
        return status;
    }

    private  int checkContentType() {

        // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
        if (type.equals("avi")) {
            return 0;
        } else if (type.equals("mpg")) {
            return 0;
        } else if (type.equals("wmv")) {
            return 0;
        } else if (type.equals("3gp")) {
            return 0;
        } else if (type.equals("mov")) {
            return 0;
        } else if (type.equals("mp4")) {
            return 0;
        } else if (type.equals("asf")) {
            return 0;
        } else if (type.equals("asx")) {
            return 0;
        } else if (type.equals("flv")) {
            return 0;
        }
        // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
        // 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
        else if (type.equals("wmv9")) {
            return 1;
        } else if (type.equals("rm")) {
            return 1;
        } else if (type.equals("rmvb")) {
            return 1;
        }
        return 9;
    }


    // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
    private boolean processFLV(String oldfilepath) {
        if (!checkfile(oldfilepath)) {
            System.out.println(oldfilepath + " is not file");
            return false;
        }

        // 文件命名

        String savevideo=Common.VIDEOPath+SaveName+".mp4";
        String savepic=Common.PICPath+SaveName+".jpg";
        System.out.println(savevideo);
        System.out.println(savepic);
            // 创建一个List集合来保存转换视频文件为flv格式的命令
            List<String> convert = new ArrayList<String>();
            convert.add(Common.ffmpegPath); // 添加转换工具路径
            convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
            convert.add(oldfilepath); // 添加要转换格式的视频文件的路径
            convert.add("-qscale");     //指定转换的质量
            convert.add("6");
            convert.add("-ab");        //设置音频码率
            convert.add("64");
            convert.add("-ac");        //设置声道数
            convert.add("2");
            convert.add("-ar");        //设置声音的采样频率
            convert.add("22050");
            convert.add("-r");        //设置帧频
            convert.add("24");
            convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
            convert.add("E:\\outside\\video\\video5.flv");

            // 创建一个List集合来保存从视频中截取图片的命令
            List<String> cutpic = new ArrayList<String>();
            cutpic.add(Common.ffmpegPath);
            cutpic.add("-i");
            cutpic.add(oldfilepath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
            cutpic.add("-y");
            cutpic.add("-f");
            cutpic.add("image2");
            cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
            cutpic.add("17"); // 添加起始时间为第17秒
            cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
            cutpic.add("0.001"); // 添加持续时间为1毫秒
            cutpic.add(savepic); // 添加截取的图片的保存路径

            boolean mark = true;
            ProcessBuilder builder = new ProcessBuilder();
            try {
                builder.command(convert);
                builder.redirectErrorStream(true);
                builder.start();

//                builder.command(cutpic);
//                builder.redirectErrorStream(true);
//                // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
//                //因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
//                builder.start();
//                processFfmpegWatermarkByImg(oldfilepath,savevideo,Common.WATERMARK);
            } catch (Exception e) {
                mark = false;
                System.out.println(e);
                e.printStackTrace();
            }
            return mark;
        }

    private boolean processFfmpegWatermarkByImg(String srcPath,String tarVideoPath,String waterMarkPath) {
        System.out.println("watermark");
            if (!new File(waterMarkPath).exists()){
                System.out.println("找不到水印："+waterMarkPath+"");
                return  false;
            }
        // 创建一个List集合来保存从视频中截取图片的命令
        List<String> lwatermark = new ArrayList<String>();
        lwatermark.add(Common.ffmpegPath);
        lwatermark.add("-i");
        lwatermark.add(srcPath);
        lwatermark.add("-i");
        lwatermark.add(waterMarkPath);
        lwatermark.add("-filter_complex");
        lwatermark.add("\"overlay=x=10:y=10\"");
        lwatermark.add(tarVideoPath);

        boolean mark = true;
        ProcessBuilder builder = new ProcessBuilder();
        try {
            System.out.println("try to build");
            builder.command(lwatermark);
            builder.redirectErrorStream(true);
            builder.start();
        } catch (Exception e) {
            mark = false;
            System.out.println(e);
            e.printStackTrace();
        }
        return mark;
    }
//        List<String> commend = new ArrayList<String>();
//        String comend="-ab  64  -acodec mp3  -ac 2  -ar  22050  -b 230 -r 24 -y";
//        commend.add(Common.ffmpegPath);
//        commend.add("-i");
//        commend.add(oldfilepath);
//        commend.add("-acodec");
//        commend.add("copy");
//        commend.add("-vcodec");
//        commend.add("copy");
//        commend.add("-f");
//        commend.add("E:\\outside\\video\\video5.flv");
//


}

