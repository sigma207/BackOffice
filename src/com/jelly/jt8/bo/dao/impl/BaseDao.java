package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.util.RsMapper;

import javax.persistence.Column;
import javax.persistence.Table;
import java.beans.BeanInfo;
import java.beans.IndexedPropertyDescriptor;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

/**
 * Created by user on 2015/8/12.
 */

public class BaseDao {
    Class tableClass;
    final int batchSize = 1000;
    public void execute(Connection conn, String sql, List list, Class c) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            RsMapper.map(rs, list, c);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void parseError(int code, String message) throws Exception {
        if (code != 0 || !message.equals("")) {
            throw new Exception(message);
        }
    }

    protected PreparedStatement insertStatement(Connection conn, Object object) throws Exception {
        BeanInfo stmtInfo = Introspector.getBeanInfo(PreparedStatement.class);
        PropertyDescriptor[] stmtProps = stmtInfo.getPropertyDescriptors();

        Class tableClass = object.getClass();
        Table table = (Table)tableClass.getAnnotation(javax.persistence.Table.class);
        String tableName = table.name();

        Map<String,PropertyDescriptor> readMap = new HashMap<String,PropertyDescriptor>();
        Map<String,PropertyDescriptor> writeMap = new HashMap<String,PropertyDescriptor>();
        Map<String,IndexedPropertyDescriptor> stmtMap = new HashMap<String,IndexedPropertyDescriptor>();
        Map<String,PropertyDescriptor> columnMap = new LinkedHashMap<>();
        BeanInfo info = Introspector.getBeanInfo(tableClass);
        PropertyDescriptor[] props = info.getPropertyDescriptors(); //Gets all the properties for the class.
        for (PropertyDescriptor pd : props){
            System.out.println(pd.getName()+":"+pd.getPropertyType().getName());
            if(pd.getReadMethod()!=null){
                System.out.println(pd.getReadMethod().getName());
                readMap.put(pd.getReadMethod().getName(), pd);
            }
            if(pd.getWriteMethod()!=null){
                System.out.println(pd.getWriteMethod().getName());
                writeMap.put(pd.getWriteMethod().getName(), pd);
            }
        }
        System.out.println("...............");

        for (PropertyDescriptor pd : stmtProps){
            if(pd instanceof IndexedPropertyDescriptor){
                IndexedPropertyDescriptor ipd = (IndexedPropertyDescriptor)pd;
                if(ipd.getIndexedWriteMethod()!=null){
                    System.out.println(ipd.getName() + ":" + ipd.getIndexedWriteMethod().getName() + ":" + ipd.getIndexedPropertyType().getName() + ":" + ipd.getIndexedPropertyType().getTypeName());
                    if(ipd.getName().equals("null"))continue;
                    stmtMap.put(ipd.getIndexedPropertyType().getName(),ipd);
                }
            }
        }

        System.out.println("...............");
        StringBuffer columnNames = new StringBuffer();
        StringBuffer values = new StringBuffer();
        for(Method method:tableClass.getDeclaredMethods()){
            Column column = method.getAnnotation(javax.persistence.Column.class);
            if(column!=null && column.insertable() && readMap.containsKey(method.getName())){
                PropertyDescriptor pd = readMap.get(method.getName());
                System.out.println(method.getName() + ":" + column.name() + "=" + pd.getReadMethod().invoke(object));
                columnMap.put(column.name(),pd);
            }
        }

        Set<String> keys = columnMap.keySet();
        for(String key:keys){
            columnNames.append(key+",");
            values.append("?,");
        }
        columnNames.deleteCharAt(columnNames.length() - 1);
        values.deleteCharAt(values.length() - 1);
        String sql = "INSERT INTO "+tableName+ "("+columnNames.toString()+") VALUES ("+values.toString()+");";
        System.out.println(sql);

        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        Object value = null;
        String propertyTypeName = null;
        Method indexedWriteMethod = null;
        int index = 1;
        for(String key:keys){
            PropertyDescriptor pd = columnMap.get(key);
            value = pd.getReadMethod().invoke(object);
            propertyTypeName = pd.getPropertyType().getName();
            if(propertyTypeName.equals("java.lang.Integer")||propertyTypeName.equals("java.lang.Long")){
                propertyTypeName = "int";
            }
            IndexedPropertyDescriptor ipd = stmtMap.get(propertyTypeName);
            indexedWriteMethod = ipd.getIndexedWriteMethod();
            System.out.println("set.."+key+"("+index+")="+value+"["+propertyTypeName+"]");
            if(value==null){
                stmt.setNull(index, Types.NULL);
            }else{
                indexedWriteMethod.invoke(stmt,index,value);
            }

            index++;
        }

        return stmt;
    }
}
