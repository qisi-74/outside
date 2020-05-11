package com.service.app.upload;

import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 *
 * @ProjectName: graduation
 * @ClassName: sizeFormat
 * @Description: java类作用描述
 * @CreateUser: Mr.Hao
 * @CreateDate: 2020/3/22  12:23
 **/
public class TimeFormat {
    public static String formatSeconds(long seconds){
        String standardTime;
        seconds=seconds/1000;
        if (seconds <= 0){
            standardTime = "00:00";
        } else if (seconds < 60) {
            standardTime = String.format(Locale.getDefault(), "00:%02d", seconds % 60);
        } else if (seconds < 3600) {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
        } else {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d:%02d", seconds / 3600, seconds % 3600 / 60, seconds % 60);
        }
        return standardTime;
    }
}
