package com.imp.sorm.core;

import com.imp.sorm.bean.Configuration;
import com.imp.sorm.pool.DBConnPool;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 根据配置信息，维持连接对象的管理
 *
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/9/24 21:11
 */
public class DBManager {

    // 配置信息
    private static Configuration conf;

    // 连接池对象
    private static DBConnPool pool ;

    // 加载配置信息
    static {
        // 读取配置信息内容
        Properties pros = new Properties();
        try {
            pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        conf = new Configuration();
        conf.setDriver(pros.getProperty("driver"));
        conf.setPoPackage(pros.getProperty("poPackage"));
        conf.setPwd(pros.getProperty("pwd"));
        conf.setSrcPath(pros.getProperty("srcPath"));
        conf.setUrl(pros.getProperty("url"));
        conf.setUser(pros.getProperty("user"));
        conf.setUsingDB(pros.getProperty("usingDB"));
        conf.setQueryClass(pros.getProperty("queryClass"));
        conf.setPoolMinSize(Integer.parseInt( pros.getProperty("poolMinSize")));
        conf.setPoolMaxSize(Integer.parseInt(pros.getProperty("poolMaxSize")));


    }


    // 获得连接
    public static Connection getConn() {
        if(pool == null) {
            pool = new DBConnPool();
        }
        return pool.getConnection();
    }

    // 获得连接
    public static Connection createConn() {
        try {
            Class.forName(conf.getDriver());
            return DriverManager.getConnection(conf.getUrl(),conf.getUser(),conf.getPwd());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 关闭方法
    public static void close(ResultSet rs, Statement ps, Connection conn) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null) {
            try {
                pool.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Configuration getConf() {
        return conf;
    }
}
