package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.util.DBUtils;
import com.jelly.jt8.bo.util.ErrorMsg;
import com.jelly.jt8.bo.util.RsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.sql.DataSource;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

/**
 * Created by user on 2015/8/12.
 */

public class BaseDao {
    protected Class tableClass;
    protected String tableName;
    protected Map<String,PropertyDescriptor> columnKeyMap = new LinkedHashMap<String,PropertyDescriptor>();
    protected Map<String,Column> columnMap = new HashMap<String,Column>();
    protected Map<String,Column> idColumnMap = new HashMap<String,Column>();

    public BaseDao() {
    }

    public BaseDao(Class c) {
        tableClass = c;
        if(tableClass!=null){
            try {
//                System.out.println("BaseDao "+tableClass.getName());
                Table table = (Table)tableClass.getAnnotation(javax.persistence.Table.class);
                tableName = table.name();

                Map<String,PropertyDescriptor> readMap = new HashMap<String,PropertyDescriptor>();
                Map<String,PropertyDescriptor> writeMap = new HashMap<String,PropertyDescriptor>();
                BeanInfo info = Introspector.getBeanInfo(tableClass);
                PropertyDescriptor[] props = info.getPropertyDescriptors(); //Gets all the properties for the class.
                for (PropertyDescriptor pd : props){
//                    System.out.println(pd.getName()+":"+pd.getPropertyType().getName());
                    if(pd.getReadMethod()!=null){
//                        System.out.println(pd.getReadMethod().getName());
                        readMap.put(pd.getReadMethod().getName(), pd);
                    }
                    if(pd.getWriteMethod()!=null){
//                        System.out.println(pd.getWriteMethod().getName());
                        writeMap.put(pd.getWriteMethod().getName(), pd);
                    }
                }

                for(Method method:tableClass.getDeclaredMethods()){
                    Transient t = method.getAnnotation(javax.persistence.Transient.class);
                    if(t!=null){
                        continue;
                    }
                    Column column = method.getAnnotation(javax.persistence.Column.class);
                    if(column!=null && readMap.containsKey(method.getName())){
                        PropertyDescriptor pd = readMap.get(method.getName());
//                        System.out.println(method.getName() + ":" + column.name() + "=" + pd.getReadMethod().invoke(object));
                        columnKeyMap.put(column.name(), pd);
                        columnMap.put(column.name(),column);
                    }
                    Id id =  method.getAnnotation(javax.persistence.Id.class);
                    if(id!=null){
//                        System.out.println("id="+column.name());
                        idColumnMap.put(column.name(),column);
                    }
                }
//                System.out.println("BaseDao");
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Autowired
    @Qualifier("jt8Ds")
    protected DataSource jt8Ds;

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

    protected PreparedStatement getStatement(Connection conn,String sql) throws Exception{
        PreparedStatement stmt = null;
        try{
            stmt = conn.prepareStatement(sql);
        }catch (Exception e){
            throw e;
        }finally {
            try {
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
        return stmt;
    }

    protected void query(PreparedStatement stmt, List list) throws Exception{
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = stmt.getConnection();
            rs = stmt.executeQuery();
            selectToObject(rs, list);
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

    protected void selectByObject(Connection conn, List list) throws Exception{
        selectByObject(conn,list,selectSQL());
    }

    protected void selectByObject(Connection conn, List list, String sql) throws Exception{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            selectToObject(rs, list);
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

    protected void insertByObject(Connection conn, List list) throws Exception {
        PreparedStatement stmt = null;
        try {
            int count = 0;
            stmt = conn.prepareStatement(insertSQL(), Statement.RETURN_GENERATED_KEYS);
            for (Object object : list) {
                insertStatement(stmt, object);
                stmt.addBatch();
                if(++count % batchSize == 0) {
                    stmt.executeBatch();
                }
            }
            stmt.executeBatch();
        } catch (Exception e){
            throw e;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected int insertByObject(Connection conn, Object object) throws Exception {
        PreparedStatement stmt = null;
        int lastKey = -1;
        try {
            stmt = conn.prepareStatement(insertSQL(),Statement.RETURN_GENERATED_KEYS);
            insertStatement(stmt, object);
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();

            if (keys.next()) {
                lastKey = keys.getInt(1);
            }
        } catch (Exception e){
            throw e;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lastKey;
    }

    protected void updateByObject(Connection conn, Object object) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(updateSQL());
            updateStatement(stmt, object);

            if(stmt.executeUpdate()==0){
                throw new Exception(ErrorMsg.DIRTY_DATA);
            }
        } catch (Exception e){
            throw e;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void deleteByObject(Connection conn, Object object) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(deleteSQL());
            deleteStatement(stmt, object);

            if(stmt.executeUpdate()==0){
                throw new Exception(ErrorMsg.DIRTY_DATA);
            }
        } catch (Exception e){
            throw e;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
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

    protected String insertSQL() throws Exception{
        StringBuffer columnNames = new StringBuffer();
        StringBuffer values = new StringBuffer();
        Column column = null;

        Set<String> keys = columnKeyMap.keySet();
        for(String key:keys){
            column = columnMap.get(key);
            if(key.equalsIgnoreCase(DBUtils.ROW_VERSION))continue;
            if(column.insertable()){
                columnNames.append(key+",");
                values.append("?,");
            }
        }
        columnNames.deleteCharAt(columnNames.length() - 1);
        values.deleteCharAt(values.length() - 1);
        return "INSERT INTO "+tableName+ "("+columnNames.toString()+") VALUES ("+values.toString()+");";
    }

    protected String updateSQL() throws Exception{
        StringBuffer updateValues = new StringBuffer();
        StringBuffer whereValues = new StringBuffer();
        Column column = null;

        Set<String> keys = columnKeyMap.keySet();
        for(String key:keys){
            if(key.equalsIgnoreCase(DBUtils.ROW_VERSION))continue;
            if(key.equalsIgnoreCase(DBUtils.CREATE_TIME))continue;
            column = columnMap.get(key);
            if(!idColumnMap.containsKey(key) && column.updatable()){
                updateValues.append(key + " = ?,");
            }
        }
        for(String key:keys){
            if(idColumnMap.containsKey(key) || key.equalsIgnoreCase(DBUtils.ROW_VERSION)){
                if(whereValues.length()==0){
                    whereValues.append(key + " = ?");
                }else{
                    whereValues.append(" AND "+key + " = ?");
                }
            }
        }
        updateValues.deleteCharAt(updateValues.length() - 1);
        return "UPDATE "+tableName+" SET "+updateValues.toString()+" WHERE "+whereValues.toString();
    }

    protected String deleteSQL() throws Exception{
        StringBuffer whereValues = new StringBuffer();
        Set<String> keys = columnKeyMap.keySet();
        for(String key:keys){
            if(idColumnMap.containsKey(key)){
                if(whereValues.length()==0){
                    whereValues.append(key + " = ?");
                }else{
                    whereValues.append(" AND "+key + " = ?");
                }
            }
        }
        return "DELETE "+tableName+" WHERE "+whereValues.toString();
    }

    protected String deleteTable() throws Exception{
        return "DELETE "+tableName;
    }

    protected String selectSQL() throws Exception{
        StringBuffer columnNames = new StringBuffer();
        Set<String> keys = columnKeyMap.keySet();
        for(String key:keys){
            columnNames.append(key+",");
        }

        columnNames.deleteCharAt(columnNames.length() - 1);
        return "SELECT "+columnNames.toString()+" FROM "+tableName +" ";
    }

    protected void selectToObject(ResultSet rs,List list) throws Exception{
        DBUtils.selectToObject(columnKeyMap, columnMap, idColumnMap, rs, list, tableClass);
    }

    protected void insertStatement(PreparedStatement stmt, Object object) throws Exception{
        DBUtils.insertStatement(stmt, columnKeyMap, columnMap, idColumnMap, object);
    }

    protected void updateStatement(PreparedStatement stmt, Object object) throws Exception{
        DBUtils.updateStatement(stmt, columnKeyMap, columnMap, idColumnMap, object);
    }

    protected void deleteStatement(PreparedStatement stmt, Object object) throws Exception{
        DBUtils.deleteStatement(stmt, columnKeyMap, columnMap, idColumnMap, object);
    }

}
