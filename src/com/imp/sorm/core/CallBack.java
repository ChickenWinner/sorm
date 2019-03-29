package com.imp.sorm.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/9/28 10:42
 */
public interface CallBack {
    public Object doExecute(Connection conn, PreparedStatement ps, ResultSet rs);
}
