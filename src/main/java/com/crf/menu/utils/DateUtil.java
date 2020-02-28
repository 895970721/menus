package com.crf.menu.utils;

public class DateUtil {
    public static String getDateDiff(Long youngTimeStamp,Long nowTimeStamp){
        Long timestamp = nowTimeStamp - youngTimeStamp;
        Long Cyear = (timestamp/1000/3600/24/365);
        Long Cmonth = (timestamp/1000/3600/24/30);
        Long Cday = (timestamp/1000/3600/24);
        Long Chour = (timestamp/1000/3600);
        Long Cminute = (timestamp/1000/60);
        Long Csecond = (timestamp/1000);
        String result = "";
        if(Cyear >= 1){
            result = (Cyear) + "年前";
        }else if(Cmonth >= 1){
            result = (Cmonth) + "个月前";
        }else if(Cday >= 1){
            result = (Cday) + "天前";
        }else if(Chour >= 1){
            result = (Chour) + "小时前";
        }else if(Cminute >= 1){
            result = (Cminute) + "分钟前";
        }else if(Csecond >= 1){
            result = (Csecond) + "秒前";
        }
        return result;
    }
}
