package com.jelly.jt8.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by user on 2015/7/29.
 */
public class Utils {
    private static SimpleDateFormat updateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat updateDateFormat = new SimpleDateFormat("yyyyMMdd");
    public static String updateTime(){
        return  updateTimeFormat.format(Calendar.getInstance().getTime());
    }

    public static String updateDate(){
        return  updateDateFormat.format(Calendar.getInstance().getTime());
    }
}
