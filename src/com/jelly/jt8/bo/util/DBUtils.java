package com.jelly.jt8.bo.util;

import com.jelly.jt8.bo.model.BaseModel;
import com.jelly.jt8.common.Utils;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.beans.BeanInfo;
import java.beans.IndexedPropertyDescriptor;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.*;

/**
 * Created by user on 2015/8/30.
 */
public class DBUtils {
    public static String ROW_VERSION = "rv";
    public static String CREATE_TIME = "create_time";
    public static String UPDATE_TIME = "update_time";
    public static Map<String, IndexedPropertyDescriptor> stmtMap = new HashMap<String, IndexedPropertyDescriptor>();

    static {
        try {
            BeanInfo stmtInfo = Introspector.getBeanInfo(PreparedStatement.class);
            PropertyDescriptor[] stmtProps = stmtInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : stmtProps) {
                if (pd instanceof IndexedPropertyDescriptor) {
                    IndexedPropertyDescriptor ipd = (IndexedPropertyDescriptor) pd;
                    if (ipd.getIndexedWriteMethod() != null) {
//                        System.out.println(ipd.getName() + ":" + ipd.getIndexedWriteMethod().getName() + ":" + ipd.getIndexedPropertyType().getName() + ":" + ipd.getIndexedPropertyType().getTypeName());
                        if (ipd.getName().equals("null")) continue;
                        stmtMap.put(ipd.getIndexedPropertyType().getName(), ipd);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadTable(Class tableClass, Map<String, PropertyDescriptor> columnKeyMap, Map<String, Column> columnMap, Map<String, Column> idColumnMap) throws Exception {

        Map<String, PropertyDescriptor> readMap = new HashMap<String, PropertyDescriptor>();
        Map<String, PropertyDescriptor> writeMap = new HashMap<String, PropertyDescriptor>();
        BeanInfo info = Introspector.getBeanInfo(tableClass);
        PropertyDescriptor[] props = info.getPropertyDescriptors(); //Gets all the properties for the class.
        for (PropertyDescriptor pd : props) {
//                    System.out.println(pd.getName()+":"+pd.getPropertyType().getName());
            if (pd.getReadMethod() != null) {
//                        System.out.println(pd.getReadMethod().getName());
                readMap.put(pd.getReadMethod().getName(), pd);
            }
            if (pd.getWriteMethod() != null) {
//                        System.out.println(pd.getWriteMethod().getName());
                writeMap.put(pd.getWriteMethod().getName(), pd);
            }
        }

        for (Method method : tableClass.getDeclaredMethods()) {
            Transient t = method.getAnnotation(javax.persistence.Transient.class);
            if (t != null) {
                continue;
            }
            Column column = method.getAnnotation(javax.persistence.Column.class);
            if (column != null && readMap.containsKey(method.getName())) {
                PropertyDescriptor pd = readMap.get(method.getName());
//                        System.out.println(method.getName() + ":" + column.name() + "=" + pd.getReadMethod().invoke(object));
                columnKeyMap.put(column.name(), pd);
                if (columnMap != null) {
                    columnMap.put(column.name(), column);
                }

            }
            if (idColumnMap != null) {
                Id id = method.getAnnotation(javax.persistence.Id.class);
                if (id != null) {
                    idColumnMap.put(column.name(), column);
                }
            }
        }
    }

    public static void selectToObject(Map<String, PropertyDescriptor> columnKeyMap, Map<String, Column> idColumnMap, ResultSet rs, List list, Class outputClass, List<Join> joins) throws Exception {
        if (rs != null) {
            ResultSetMetaData rsmd = rs.getMetaData();
            Object bean = null;
            String columnName = null;
            Object columnValue = null;
            PropertyDescriptor pd = null;
            String idValue = null;
            Map<String, Join> joinMap = new HashMap<String, Join>();
            if (joins != null) {
                for (Join join : joins) {
                    joinMap.put(join.getProperty(), join);
                }
            }
            Set<String> joinsKeys = joinMap.keySet();

            Column column = null;
            while (rs.next()) {
                bean = outputClass.newInstance();
                if (joins != null) {
                    for (Join join : joins) {
                        pd = columnKeyMap.get(join.getProperty());
                        pd.getWriteMethod().invoke(bean, join.getJoinClass().newInstance());

                    }
                }
                idValue = null;
                for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
                    columnName = rsmd.getColumnName(_iterator + 1);
                    columnValue = rs.getObject(_iterator + 1);
                    if (columnValue != null) {
                        pd = columnKeyMap.get(columnName);
                        if (pd == null) {
                            for (String joinKey : joinsKeys) {
                                if (columnName.indexOf(joinKey) == 0) {
                                    pd = joinMap.get(joinKey).getColumnKeyMap().get(columnName.replaceFirst(joinKey + "_", ""));
                                    break;
                                }
                            }
                        }
                        test(bean,columnName,columnValue,pd,idColumnMap);
                    }
                }
                list.add(bean);
            }
        }
    }

    private static void test(Object bean, String columnName , Object columnValue, PropertyDescriptor pd, Map<String, Column> idColumnMap) throws Exception {
        if (pd != null) {
            BeanUtils.setProperty(bean, pd.getName(), columnValue);
        }
        Column column = idColumnMap.get(columnName);
        if (column != null && bean instanceof BaseModel) {
//                            idValue += columnValue;
            BeanUtils.setProperty(bean, "id", columnValue);
        }
    }

    public static void insertStatement(PreparedStatement stmt, Map<String, PropertyDescriptor> columnKeyMap, Map<String, Column> columnMap, Map<String, Column> idColumnMap, Object object) throws Exception {
        Object value = null;
        String propertyTypeName = null;
        Method indexedWriteMethod = null;
        PropertyDescriptor pd = null;
        IndexedPropertyDescriptor ipd = null;
        Column column = null;
        int index = 1;
        Set<String> keys = columnKeyMap.keySet();
        for (String key : keys) {
            if (key.equalsIgnoreCase(ROW_VERSION)) continue;
            column = columnMap.get(key);
            if (column.insertable()) {
                pd = columnKeyMap.get(key);
                value = pd.getReadMethod().invoke(object);
                propertyTypeName = pd.getPropertyType().getName();
                if (propertyTypeName.equals("java.lang.Integer") || propertyTypeName.equals("java.lang.Long")) {
                    propertyTypeName = "int";
                }
                ipd = stmtMap.get(propertyTypeName);
                indexedWriteMethod = ipd.getIndexedWriteMethod();
                if (key.equalsIgnoreCase(UPDATE_TIME) || key.equalsIgnoreCase(CREATE_TIME)) {
                    value = Utils.updateTime();
                }
                System.out.println("set.." + key + "(" + index + ")=" + value + "[" + propertyTypeName + "]");
                if (value == null) {
                    stmt.setNull(index, Types.NULL);
                } else {
                    indexedWriteMethod.invoke(stmt, index, value);
                }
                index++;
            }
        }
    }

    public static void updateStatement(PreparedStatement stmt, Map<String, PropertyDescriptor> columnKeyMap, Map<String, Column> columnMap, Map<String, Column> idColumnMap, Object object) throws Exception {
        Object value = null;
        String propertyTypeName = null;
        Method indexedWriteMethod = null;
        PropertyDescriptor pd = null;
        IndexedPropertyDescriptor ipd = null;
        Column column = null;
        int index = 1;
        Set<String> keys = columnKeyMap.keySet();
        Set<String> updateKeys = new LinkedHashSet<String>();
        for (String key : keys) {
            if (key.equalsIgnoreCase(ROW_VERSION)) continue;
            if (key.equalsIgnoreCase(CREATE_TIME)) continue;
            column = columnMap.get(key);
            if (!idColumnMap.containsKey(key) && column.updatable()) {
                updateKeys.add(key);
            }
        }
        for (String key : keys) {
            if (idColumnMap.containsKey(key) || key.equalsIgnoreCase(ROW_VERSION)) {
                updateKeys.add(key);
            }
        }
        for (String key : updateKeys) {
            pd = columnKeyMap.get(key);
            value = pd.getReadMethod().invoke(object);
            propertyTypeName = pd.getPropertyType().getName();
            if (propertyTypeName.equals("java.lang.Integer")) {
                propertyTypeName = "int";
            } else if (propertyTypeName.equals("java.lang.Long")) {
                propertyTypeName = "long";
            }
            System.out.println("set.." + key + "(" + index + ")=" + value + "[" + propertyTypeName + "]");
            ipd = stmtMap.get(propertyTypeName);
            indexedWriteMethod = ipd.getIndexedWriteMethod();
            if (key.equalsIgnoreCase(UPDATE_TIME)) {
                value = Utils.updateTime();
            }

            if (value == null) {
                stmt.setNull(index, Types.NULL);
            } else {
                indexedWriteMethod.invoke(stmt, index, value);
            }
            index++;
        }
    }

    public static void deleteStatement(PreparedStatement stmt, Map<String, PropertyDescriptor> columnKeyMap, Map<String, Column> columnMap, Map<String, Column> idColumnMap, Object object) throws Exception {
        Object value = null;
        String propertyTypeName = null;
        Method indexedWriteMethod = null;
        PropertyDescriptor pd = null;
        IndexedPropertyDescriptor ipd = null;
        Column column = null;
        int index = 1;
        Set<String> keys = columnKeyMap.keySet();
        Set<String> updateKeys = new LinkedHashSet<String>();
        for (String key : keys) {
            if (idColumnMap.containsKey(key)) {
                updateKeys.add(key);
            }
        }
        for (String key : updateKeys) {
            pd = columnKeyMap.get(key);
            value = pd.getReadMethod().invoke(object);
            propertyTypeName = pd.getPropertyType().getName();
            if (propertyTypeName.equals("java.lang.Integer")) {
                propertyTypeName = "int";
            } else if (propertyTypeName.equals("java.lang.Long")) {
                propertyTypeName = "long";
            }
            System.out.println("set.." + key + "(" + index + ")=" + value + "[" + propertyTypeName + "]");
            ipd = stmtMap.get(propertyTypeName);
            indexedWriteMethod = ipd.getIndexedWriteMethod();

            if (value == null) {
                stmt.setNull(index, Types.NULL);
            } else {
                indexedWriteMethod.invoke(stmt, index, value);
            }
            index++;
        }
    }
}
