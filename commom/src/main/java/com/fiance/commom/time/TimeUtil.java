package com.fiance.commom.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static String stampToDate(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }
}
