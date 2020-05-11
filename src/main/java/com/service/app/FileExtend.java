package com.service.app;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: FileExtend
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/4/13  21:49
 **/
public class FileExtend {
    private String imgreg="(jpeg|jpg|png)$";
    private String videoreg="(mp4|flv|mov|ts|avi)$";

    public String mate(String extend){
        if(extend.matches(imgreg)){
            return "image";
        }
        if(extend.matches(videoreg)){
            return "video";
        }
        return "fail";
    }
}
