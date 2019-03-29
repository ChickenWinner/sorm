package com.imp.sorm.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 *
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/9/24 21:13
 */
public class ReflectUtils {

    /**
     * 调用obj对象对应属性fieldName的get方法
     *
     * @param fieldName
     * @param obj
     * @return
     */
    public static Object invokeGet(String fieldName, Object obj) {
        try {
            Class c = obj.getClass();
            Method m = c.getDeclaredMethod
                    ("get" + StringUtils.firstChar2UpperCase(fieldName), null);
            return m.invoke(obj, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void invokeSet(Object obj,
                                 String columnName, Object columnValue) {
        try {
            Method m = obj.getClass().
                    getDeclaredMethod("set" + StringUtils.firstChar2UpperCase(columnName),
                            columnValue.getClass());
            m.invoke(obj, columnValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
