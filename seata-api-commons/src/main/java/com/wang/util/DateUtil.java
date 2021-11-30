package com.wang.util;

import java.text.SimpleDateFormat;

/**
 * @author username
 * @date 2021/11/27 17:34
 * @description:TODO
 * @since 8
 */
public class DateUtil {

    public static String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        return simpleDateFormat.format(System.currentTimeMillis());
    }
}
