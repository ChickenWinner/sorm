package com.imp.sorm.utils;

import com.imp.sorm.bean.ColumnInfo;
import com.imp.sorm.bean.JavaFieldGetSet;
import com.imp.sorm.bean.TableInfo;
import com.imp.sorm.core.DBManager;
import com.imp.sorm.core.MysqlTypeConvertor;
import com.imp.sorm.core.TableContext;
import com.imp.sorm.core.TypeConvertor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 生成java文件常用的操作
 *
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/9/24 21:13
 */
public class JavaFileUtils {


    /**
     * 生成属性、get方法、set方法
     *
     * @param column    字段
     * @param convertor 类型转换器
     * @return 一个java类信息
     */
    public static JavaFieldGetSet createFieldGetSetSRC(ColumnInfo column,
                                                       TypeConvertor convertor) {
        JavaFieldGetSet jfgs = new JavaFieldGetSet();

        String javaFieldType = convertor.dataBaseType2JavaType(column.getDataType());

        jfgs.setFieldInfo("\tprivate " + javaFieldType + " " + column.getName() + ";\n");

        //public String getUsername(){return username;}
        //生成get方法的源代码
        StringBuilder getSrc = new StringBuilder();
        getSrc.append("\tpublic " + javaFieldType + " get" + StringUtils.
                firstChar2UpperCase(column.getName()) + "(){\n");
        getSrc.append("\t\treturn " + column.getName() + ";\n");
        getSrc.append("\t}\n");
        jfgs.setGetInfo(getSrc.toString());

        //public void setUsername(String username){this.username=username;}
        //生成set方法的源代码
        StringBuilder setSrc = new StringBuilder();
        setSrc.append("\tpublic void set" + StringUtils.firstChar2UpperCase(column.getName()) + "(");
        setSrc.append(javaFieldType + " " + column.getName() + "){\n");
        setSrc.append("\t\tthis." + column.getName() + "=" + column.getName() + ";\n");
        setSrc.append("\t}\n");
        jfgs.setSetInfo(setSrc.toString());
        return jfgs;
    }


    /**
     * 根据表信息生成java类的源代码
     *
     * @param tableInfo 表信息
     * @param convertor 数据类型转化器
     * @return java类的源代码
     */
    public static String createJavaSrc
    (TableInfo tableInfo, TypeConvertor convertor) {

        // 得到所有的列
        Map<String, ColumnInfo> columns = tableInfo.getColumns();
        List<JavaFieldGetSet> javaFields = new ArrayList<JavaFieldGetSet>();

        for (ColumnInfo c : columns.values()) {
            javaFields.add(createFieldGetSetSRC(c, convertor));
        }

        StringBuilder src = new StringBuilder();

        //生成package语句
        src.append("package " + DBManager.getConf().getPoPackage() + ";\n\n");
        //生成import语句
        src.append("import java.sql.*;\n");
        src.append("import java.util.*;\n\n");
        //生成类声明语句
        src.append("public class " + StringUtils.firstChar2UpperCase(tableInfo.getTableName()) + " {\n\n");

        //生成属性列表
        for (JavaFieldGetSet f : javaFields) {
            src.append(f.getFieldInfo());
        }
        src.append("\n\n");
        //生成get方法列表
        for (JavaFieldGetSet f : javaFields) {
            src.append(f.getGetInfo());
        }
        //生成set方法列表
        for (JavaFieldGetSet f : javaFields) {
            src.append(f.getSetInfo());
        }

        //生成类结束
        src.append("}\n");
        return src.toString();
    }


    /**
     * 建立java文件
     * @param tableInfo 表信息
     * @param convertor 类型转换器
     */
    public static void createJavaPOFile(TableInfo tableInfo,TypeConvertor convertor){
        String src = createJavaSrc(tableInfo,convertor);

        String srcPath = DBManager.getConf().getSrcPath()+"\\";
        String packagePath = DBManager.getConf().getPoPackage().replaceAll("\\.", "/");

        File f = new File(srcPath+packagePath);

        if(!f.exists())
        {  //如果指定目录不存在，则帮助用户建立
            f.mkdirs();
        }

        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(f.getAbsoluteFile()+"/"+StringUtils.
                    firstChar2UpperCase(tableInfo.getTableName())+".java"));
            bw.write(src);
            System.out.println("建立表"+tableInfo.getTableName()+
                    "对应的java类："+StringUtils.firstChar2UpperCase(tableInfo.getTableName())+".java");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(bw!=null){
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        Map<String, TableInfo> tables = TableContext.tables;
        TableInfo ti = tables.get("emp");
        //System.out.println(createJavaSrc(ti, new MysqlTypeConvertor()));
        createJavaPOFile(ti, new MysqlTypeConvertor());
    }




}
