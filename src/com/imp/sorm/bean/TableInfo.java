package com.imp.sorm.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表信息
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/9/24 21:23
 */
public class TableInfo {

    private String tableName; // 表名

    private Map<String, ColumnInfo> columns; // 所有字段

    private ColumnInfo onlyPriKey; // 唯一主键

    private List<ColumnInfo> priKeys; // 联合主键

    public TableInfo() {};

    public TableInfo(String tableName,  List<ColumnInfo> priKeys,Map<String, ColumnInfo> columns) {
        this.tableName = tableName;
        this.columns = columns;
        this.priKeys = priKeys;
    }

    public TableInfo(String tableName, Map<String, ColumnInfo> columns, ColumnInfo onlyPriKey, List<ColumnInfo> priKeys) {
        this.tableName = tableName;
        this.columns = columns;
        this.onlyPriKey = onlyPriKey;
        this.priKeys = priKeys;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, ColumnInfo> columns) {
        this.columns = columns;
    }

    public ColumnInfo getOnlyPriKey() {
        return onlyPriKey;
    }

    public void setOnlyPriKey(ColumnInfo onlyPriKey) {
        this.onlyPriKey = onlyPriKey;
    }

    public List<ColumnInfo> getPriKeys() {
        return priKeys;
    }

    public void setPriKeys(List<ColumnInfo> priKeys) {
        this.priKeys = priKeys;
    }
}
