package com.wang.util;

import java.util.UUID;

/**
 * @author username
 * @date 2021/11/27 17:36
 * @description:TODO
 * @since 8
 */
public class UUIDUtil {
    public static String getUUID(){
        String string = UUID.randomUUID().toString();
        return string.replace("-","");
    }
}
