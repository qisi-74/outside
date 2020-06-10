package com.service.app.media.FFmpeg;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: ConverVideo
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/15  23:23
 **/
import com.service.common.Common;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ConvertVideo {
    private  String inputPath = "";
    private  String SaveName= "";
    private  String type= "";
    private  String outputPath = "";
    private String uname = "outside ";

    private static String ffmpegPath = "";
    public ConvertVideo(String uname,String oldPath){
        this.uname=this.uname+uname;
        inputPath=oldPath;
        Calendar c = Calendar.getInstance();
        SaveName = String.valueOf(c.getTimeInMillis())+ Math.round(Math.random() * 100000);
        type = inputPath.substring(inputPath.lastIndexOf(".") + 1, inputPath.length()).toLowerCase();
        getPath();
        getSaveName();
    }
    public String getSaveName()
    {return SaveName;}
//    public static void main(String args[]) throws IOException {
//        ConvertVideo convertVideo=new ConvertVideo("E:\\idea\\graduation\\outside\\web\\statics\\video\\temp\\123.mp4");
//
//        if (!convertVideo.checkfile(inputPath)) {
//            System.out.println(inputPath + " is not file");
//            return;
//        }
////        if(convertVideo.Cut())
////            System.out.println("cutpic");
////
//        if (convertVideo.Water_Mark())
//            System.out.println("watermark success");
//
//
//        if (convertVideo.process())
//            System.out.println("To mp4 is ok");
//    }

    public  void getPath(){
        // 先获取当前项目路径，在获得源文件、目标文件、转换器的路径
        File diretory = new File("");
        try {
            String currPath = diretory.getAbsolutePath();
//            //视频的地址
//            inputPath = "E:\\test\\video1.flv";
//            //视频转完格式存放地址
//            outputPath = "E:\\outside\\video\\";
            //转换视频的插件
            ffmpegPath = Common.ffmpegPath;
            System.out.println(currPath);
            System.out.println("输入视频"+inputPath);
            System.out.println();
        }
        catch (Exception e) {
            System.out.println("getPath出错");
        }
    }
    public boolean Water_Mark()  {
        String tarPath=Common.VIDEOPath+SaveName+"."+type;
//        String formPath=inputPath;
        boolean ByImg=false;
        boolean ByFont=true;
//        if(checkContentType()==4){
//            System.out.println("视频是mp4类型，没有转码，所以直接对temp里的文件加水印");
//        }else {
//            System.out.println("视频不是mp4类型，已经转码，所以要对video里的文件加水印");
//            formPath=tarPath;
//        }
        if(ByImg){
            processFfmpegWatermarkByImg(inputPath,tarPath,Common.WATERMARK);
        }
        if(ByFont){
            processFfmpegWatermarkByFont(inputPath,tarPath,uname);
        }
        return true;
    }
    public  boolean process() {
        int type = checkContentType();
        System.out.println("扩展名是"+type);
            boolean status = false;
            System.out.println("直接转成mp4格式");
            status = processMp4(inputPath);// 直接转成mp4格式
            return status;
    }
    public boolean Cut(){
        int type = checkContentType();
        boolean status = false;
        System.out.println("截取封面");
        status = CutPic(inputPath);// 直接转成mp4格式
        return status;
    }

    private  int checkContentType() {
        String type = inputPath.substring(inputPath.lastIndexOf(".") + 1, inputPath.length())
                .toLowerCase();
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
            return 4;
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

    private  boolean checkfile(String path) {
        File file = new File(path);
        if (!file.isFile()) {
            return false;
        }
        return true;
    }
    private boolean processFfmpegWatermarkByImg(String srcPath,String tarVideoPath,String waterMarkPath) {
        System.out.println("watermark");
        if (!new File(waterMarkPath).exists()){
            System.out.println("找不到水印："+waterMarkPath+"");
            return  false;
        }
        //创建一个List集合来保存给视频加水印的命令
        List<String> lwatermark = new ArrayList<String>();
        lwatermark.add(Common.ffmpegPath);
        lwatermark.add("-i");
        lwatermark.add(srcPath);
        lwatermark.add("-i");
        lwatermark.add(waterMarkPath);
        lwatermark.add("-filter_complex");
        lwatermark.add("\"overlay=x=10:y=10\"");
        lwatermark.add(tarVideoPath);
        inputPath=tarVideoPath;
        boolean mark = true;
        try {
            Process videoProcess = new ProcessBuilder(lwatermark).redirectErrorStream(true).start();

            new PrintStream(videoProcess.getErrorStream()).start();

            new PrintStream(videoProcess.getInputStream()).start();

            videoProcess.waitFor();

            return mark;
        } catch (Exception e) {
            mark = false;
            System.out.println(e);
            e.printStackTrace();
        }
        return mark;
    }
    private boolean processFfmpegWatermarkByFont(String srcPath,String tarVideoPath,String waterMarkPath) {
        System.out.println("watermark_Font");

        // 创建一个List集合来保存给视频加水印的命令
        List<String> fwatermark = new ArrayList<String>();
        fwatermark.add(Common.ffmpegPath);
        fwatermark.add("-i");
        fwatermark.add(srcPath);
        fwatermark.add("-y");
        fwatermark.add("-vf");
        fwatermark.add("drawtext=fontfile=STXINGKA.TTF:text='"+waterMarkPath+"':x=10:y=10:fontsize=25:fontcolor=orange");
        fwatermark.add(tarVideoPath);
        inputPath=tarVideoPath;
        boolean mark = true;
        try {
            Process videoProcess = new ProcessBuilder(fwatermark).redirectErrorStream(true).start();

            new PrintStream(videoProcess.getErrorStream()).start();

            new PrintStream(videoProcess.getInputStream()).start();

            videoProcess.waitFor();

            return mark;
        } catch (Exception e) {
            mark = false;
            System.out.println(e);
            e.printStackTrace();
        }
        return mark;
    }
    // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等), 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
    private  String processAVI(int type) {
        List<String> commend = new ArrayList<String>();
        commend.add(ffmpegPath + "mencoder");
        commend.add(inputPath);
        commend.add("-oac");
        commend.add("lavc");
        commend.add("-lavcopts");
        commend.add("acodec=mp3:abitrate=64");
        commend.add("-ovc");
        commend.add("xvid");
        commend.add("-xvidencopts");
        commend.add("bitrate=600");
        commend.add("-of");
        commend.add("mp4");
        commend.add("-o");
        commend.add(outputPath + "a.AVI");
        try {
            ProcessBuilder builder = new ProcessBuilder();
            Process process = builder.command(commend).redirectErrorStream(true).start();
            new PrintStream(process.getInputStream());
            new PrintStream(process.getErrorStream());
            process.waitFor();
            return outputPath + "a.AVI";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
    private  boolean processFlv(String oldfilepath) {

        if (!checkfile(inputPath)) {
            System.out.println(oldfilepath + " is not file");
            return false;
        }
        List<String> command = new ArrayList<String>();
        command.add(ffmpegPath + "ffmpeg");
        command.add("-i");
        command.add(oldfilepath);
        command.add("-ab");
        command.add("56");
        command.add("-ar");
        command.add("22050");
        command.add("-qscale");
        command.add("8");
        command.add("-r");
        command.add("15");
        command.add("-s");
        command.add("600x500");
        command.add(outputPath + "a.flv");
        try {

            // 方案1
//            Process videoProcess = Runtime.getRuntime().exec(ffmpegPath + "ffmpeg -i " + oldfilepath
//                    + " -ab 56 -ar 22050 -qscale 8 -r 15 -s 600x500 "
//                    + outputPath + "a.flv");

            // 方案2
            Process videoProcess = new ProcessBuilder(command).redirectErrorStream(true).start();

            new PrintStream(videoProcess.getErrorStream()).start();

            new PrintStream(videoProcess.getInputStream()).start();

            videoProcess.waitFor();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    private  boolean processMp4(String oldfilepath) {
        System.out.println(inputPath);
        if (!checkfile(inputPath)) {
            System.out.println(oldfilepath + " is not file");
            return false;
        }
        String savevideo=Common.VIDEOPath+SaveName+".mp4";
        System.out.println(savevideo);
        List<String> command = new ArrayList<String>();
        command.add(Common.ffmpegPath);
        command.add("-i");
        command.add(oldfilepath);
        command.add("-y");
        command.add("-c:v");
        command.add("libx264");
        command.add("-mbd");
        command.add("0");
        command.add("-c:a");
        command.add("aac");
        command.add("-strict");
        command.add("-2");
        command.add("-pix_fmt");
        command.add("yuv420p");
        command.add("-movflags");
        command.add("faststart");
        command.add(savevideo);
        //-vf scale=-2:360 指定为360p

        try {

            // 方案1
            //        Process videoProcess = Runtime.getRuntime().exec(ffmpegPath + "ffmpeg -i " + oldfilepath
            //                + " -ab 56 -ar 22050 -qscale 8 -r 15 -s 600x500 "
            //                + outputPath + "a.flv");

            // 方案2
            Process videoProcess = new ProcessBuilder(command).redirectErrorStream(true).start();

            new PrintStream(videoProcess.getErrorStream()).start();

            new PrintStream(videoProcess.getInputStream()).start();

            videoProcess.waitFor();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    private  boolean CutPic(String oldfilepath) {

        if (!checkfile(inputPath)) {
            System.out.println(oldfilepath + " is not file");
            return false;
        }
        String savepic=Common.PICPath+SaveName+".jpg";
        // 创建一个List集合来保存从视频中截取图片的命令
        List<String> cutpic = new ArrayList<String>();
        cutpic.add(Common.ffmpegPath);
        cutpic.add("-i");
        cutpic.add(oldfilepath);
        cutpic.add("-y");
        cutpic.add("-f");
        cutpic.add("image2");
        cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
        cutpic.add("15"); // 添加起始时间为第15秒
        cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
        cutpic.add("0.004"); // 添加持续时间为1毫秒
        cutpic.add(savepic); // 添加截取的图片的保存路径

        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(cutpic);
            builder.redirectErrorStream(true);
            Process videoProcess=builder.start();
            // 方案1
            //        Process videoProcess = Runtime.getRuntime().exec(ffmpegPath + "ffmpeg -i " + oldfilepath
            //                + " -ab 56 -ar 22050 -qscale 8 -r 15 -s 600x500 "
            //                + outputPath + "a.flv");

            // 方案2
//            Process videoProcess = new ProcessBuilder(savepic).redirectErrorStream(true).start();

            new PrintStream(videoProcess.getErrorStream()).start();

            new PrintStream(videoProcess.getInputStream()).start();

            videoProcess.waitFor();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

class PrintStream extends Thread
{
    java.io.InputStream __is = null;
    public PrintStream(java.io.InputStream is)
    {
        __is = is;
    }

    public void run()
    {
        try
        {
            while(this != null)
            {
                int _ch = __is.read();
                if(_ch != -1)
                    System.out.print((char)_ch);
                else break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}