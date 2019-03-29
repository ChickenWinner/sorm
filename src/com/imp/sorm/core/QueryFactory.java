package com.imp.sorm.core;

/**
 * 工厂类，用来生成Query对象
 *
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/9/24 20:59
 */
public class QueryFactory {

    private static Query prototypeObj;  //原型对象

    static {


        try {
            // 配置文件指定的类
            Class c = Class.forName(DBManager.getConf().getQueryClass());  // 加载指定的query类
            prototypeObj = (Query) c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //加载po包下面所有的类，便于重用，提高效率！
        TableContext.loadPOTables();
    }

    private QueryFactory() {  //私有构造器
    }

    public static Query createQuery() {
        try {
            return (Query) prototypeObj.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
