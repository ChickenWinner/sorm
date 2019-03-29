package com.imp.sorm.utils;

/**
 * 处理字符串工具类
 *
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/9/24 21:12
 */
public class StringUtils {


    public static String firstChar2UpperCase(String name) {
        return name.toUpperCase().substring(0,1) + name.substring(1);
    }
}
