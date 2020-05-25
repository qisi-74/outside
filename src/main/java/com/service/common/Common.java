package com.service.common;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: common
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/9  17:57
 **/
public final class Common {
    //工程的真实路径
    public static final String REAL_PATH= "E:\\idea\\Maven\\outside\\src\\main\\webapp\\";
    //头像路径
    public static final String HEAD_PATH=REAL_PATH+ "statics\\userheadimg\\";
    //db头像路径
    public static final String DBHEAD_PATH= "/statics/userheadimg/";
    //上传路径
    public static final String UPLOAD_PATH=REAL_PATH+ "statics\\video\\temp\\";
    //加水印之后的视频位置
    public static final String VIDEOPath=REAL_PATH+"statics\\video\\video\\";
    //数据库中的视频位置
    public static final String DB_VIDEOPath="/statics/video/video/";
    //视频的封面位置
    public static final String PICPath=REAL_PATH+"statics\\video\\picture\\";
    //数据库中视频的截图位置
    public static final String DB_PICPath="/statics/video/picture/";
    //上传动态中的图片的位置
    public static final String PostImgPath=REAL_PATH+"statics\\post\\image\\";
    //水印字体的位置
    public static final String FontPath=REAL_PATH+"statics\\font\\STXINGKA.TTF";
    //face
    public static final String FACEPath=REAL_PATH+"statics\\video\\face\\";
    //数据库中的face
    public static final String DB_FACEPath="/statics/video/face/";
    //水印位置
    public static final String WATERMARK=REAL_PATH+"statics\\video\\watermark.png";
    //ffmpeg命令
    public static final String ffmpegPath = "ffmpeg  ";
    //outside_war_exploded
    public static final String Outside = "/outside_war_exploded";
}
