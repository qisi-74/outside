package com.service.app.upload;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: sizeFormat
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/3/22  12:23
 **/
public class SizeFormat {
    public String Format(long size){
        float fsize = size;
        String fileSizeString;
        if (fsize < 1024) {
            fileSizeString = String.format("%.2f", fsize) + "B"; //2f表示保留两位小数
        } else if (fsize < 1048576) {
            fileSizeString = String.format("%.2f", fsize/1024) + "KB";
        } else if (fsize < 1073741824) {
            fileSizeString = String.format("%.2f", fsize/1024/1024) + "MB";
        } else if (fsize < 1024 * 1024 * 1024) {
            fileSizeString = String.format("%.2f", fsize/1024/1024/1024) + "GB";
        } else {
            fileSizeString = "0B";
        }
        return fileSizeString;
    }
}
