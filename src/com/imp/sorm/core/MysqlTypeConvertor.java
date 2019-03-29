package com.imp.sorm.core;

/**
 * mysql类型转为java类型
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/9/25 20:50
 */
public class MysqlTypeConvertor implements TypeConvertor {

    @Override
    public String dataBaseType2JavaType(String columnType) {

        //varchar-->String
        if ("varchar".equalsIgnoreCase(columnType) || "char".equalsIgnoreCase(columnType)) {
            return "String";
        } else {
            if ("int".equalsIgnoreCase(columnType)
                    || "tinyint".equalsIgnoreCase(columnType)
                    || "smallint".equalsIgnoreCase(columnType)
                    || "integer".equalsIgnoreCase(columnType)
                    ) {
                return "Integer";
            } else {
                if ("bigint".equalsIgnoreCase(columnType)) {
                    return "Long";
                } else {
                    if ("double".equalsIgnoreCase(columnType) || "float".equalsIgnoreCase(columnType)) {
                        return "Double";
                    } else {
                        if ("clob".equalsIgnoreCase(columnType)) {
                            return "java.sql.CLob";
                        } else {
                            if ("blob".equalsIgnoreCase(columnType)) {
                                return "java.sql.BLob";
                            } else {
                                if ("date".equalsIgnoreCase(columnType)) {
                                    return "java.sql.Date";
                                } else {
                                    if ("time".equalsIgnoreCase(columnType)) {
                                        return "java.sql.Time";
                                    } else {
                                        if ("timestamp".equalsIgnoreCase(columnType)) {
                                            return "java.sql.Timestamp";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }


    @Override
    public String javaType2DataBaseType(String javaType) {
        return null;
    }
}
