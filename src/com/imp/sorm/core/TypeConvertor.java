package com.imp.sorm.core;

/**
 * 类型转换类
 *
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/9/24 20:59
 */
public interface TypeConvertor {

    /**
     * 数据库类型转换为java类型
     * @param columnType 数据库字段的数据类型
     * @return java的数据类型
     */
    public String dataBaseType2JavaType(String columnType);

    /**
     * java类型转为数据库类型
     * @param javaType java类型
     * @return 数据库类型
     */
    public String javaType2DataBaseType(String javaType);
}
