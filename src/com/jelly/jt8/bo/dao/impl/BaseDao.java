package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.sql.DataSource;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.*;
import java.util.*;

/**
 * Created by user on 2015/8/12.
 */

public class BaseDao {
    /**
     * 繼承的class,在construct時指定,用來找出tableName
     */
    protected Class tableClass;
    protected String tableName;
    protected Map<String, PropertyDescriptor> columnKeyMap = new LinkedHashMap<String, PropertyDescriptor>();
    protected Map<String, Column> columnMap = new HashMap<String, Column>();
    protected Map<String, Column> idColumnMap = new HashMap<String, Column>();
    protected Map<String, PropertyDescriptor> transientMap = new HashMap<String, PropertyDescriptor>();

    public BaseDao() {
    }

    public BaseDao(Class c) {
        tableClass = c;
        if (tableClass != null) {
            try {
//                System.out.println("BaseDao "+tableClass.getName());
                Table table = (Table) tableClass.getAnnotation(javax.persistence.Table.class);
                tableName = table.name();
                DBUtils.loadTable(tableClass, columnKeyMap, columnMap, idColumnMap, transientMap);

//                System.out.println("BaseDao");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Autowired
    @Qualifier("jt8Ds")
    protected DataSource jt8Ds;

    final int batchSize = 1000;

    /**
     * 舊的寫法,自動依Class將resultSet的內容填入到Object
     * @param conn
     * @param sql
     * @param list
     * @param c
     * @throws Exception
     */
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



    /**
     * 回傳該查詢語法有沒有資料
     * @param conn
     * @param condition
     * @return
     * @throws Exception
     */
    protected boolean hasData(Connection conn, String condition) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean hasData = false;
        try {
            stmt = conn.prepareStatement(selectTop1SQL() + condition);
            rs = stmt.executeQuery();
            hasData = rs.next();
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
        return hasData;
    }

    protected String selectTop1SQL() throws Exception{
        return "SELECT TOP 1 * FROM " + tableName + " ";
    }

    /**
     * select * from tableName的rs->List<Model>
     * @param conn
     * @param list
     * @throws Exception
     */
    protected void selectByObject(Connection conn, List list) throws Exception {
        selectByObject(conn, list, selectSQL());
    }

    /**
     * select * from tableName的rs->List<Model>
     * 通常是用來加查詢條件的sql
     * sql = select * from tableName where xxx = ? and yyy = ?
     * @param conn
     * @param list
     * @param sql
     * @throws Exception
     */
    protected void selectByObject(Connection conn, List list, String sql) throws Exception {
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

    /**
     * 批次新增List<Model>
     * @param conn
     * @param list
     * @throws Exception
     */
    protected void insertByObject(Connection conn, List list) throws Exception {
        PreparedStatement stmt = null;
        try {
            int count = 0;
            stmt = conn.prepareStatement(insertSQL(), Statement.RETURN_GENERATED_KEYS);
            for (Object object : list) {
                insertStatement(stmt, object);
                stmt.addBatch();
                if (++count % batchSize == 0) {
                    stmt.executeBatch();
                }
            }
            stmt.executeBatch();
        } catch (Exception e) {
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

    /**
     * 新增Model並回傳自動增長的識別種子
     * @param conn
     * @param object
     * @return
     * @throws Exception
     */
    protected int insertByObject(Connection conn, Object object) throws Exception {
        PreparedStatement stmt = null;
        int lastKey = -1;
        try {
            stmt = conn.prepareStatement(insertSQL(), Statement.RETURN_GENERATED_KEYS);
            insertStatement(stmt, object);
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();

            if (keys.next()) {
                lastKey = keys.getInt(1);
            }
        } catch (Exception e) {
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

    /**
     * update Model資料
     * @param conn
     * @param object
     * @throws Exception
     */
    protected void updateByObject(Connection conn, Object object) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(updateSQL());
            updateStatement(stmt, object);

            if (stmt.executeUpdate() == 0) {
                //修改權限的bo_permission_name時明明有修改成功但筆數還是0
                //檢查rv]可能要換別的方式,下面這行先註解掉
//                throw new Exception(ErrorMsg.DIRTY_DATA);
            }
        } catch (Exception e) {
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

    /**
     * 刪除Model資料
     * @param conn
     * @param object
     * @throws Exception
     */
    protected void deleteByObject(Connection conn, Object object) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(deleteSQL());
            deleteStatement(stmt, object);

            if (stmt.executeUpdate() == 0) {
                throw new Exception(ErrorMsg.DIRTY_DATA);
            }
        } catch (Exception e) {
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

    /**
     * 將call statement的錯誤訊息改成用Exception
     * @param code
     * @param message
     * @throws Exception
     */
    public void parseError(int code, String message) throws Exception {
        if (code != 0 || !message.equals("")) {
            throw new Exception(message);
        }
    }

    /**
     * 產生insert的sql語法(略過rv欄位)
     * @return
     * @throws Exception
     */
    protected String insertSQL() throws Exception {
        StringBuffer columnNames = new StringBuffer();
        StringBuffer values = new StringBuffer();
        Column column = null;

        Set<String> keys = columnKeyMap.keySet();
        for (String key : keys) {
            column = columnMap.get(key);
            if (key.equalsIgnoreCase(DBUtils.ROW_VERSION)) continue;
            if (column.insertable()) {
                columnNames.append(key + ",");
                values.append("?,");
            }
        }
        columnNames.deleteCharAt(columnNames.length() - 1);
        values.deleteCharAt(values.length() - 1);
        return "INSERT INTO " + tableName + "(" + columnNames.toString() + ") VALUES (" + values.toString() + ");";
    }

    /**
     * 產生update的sql語法(set xx = ? 會略過rv和create_time欄位)
     * where 條件會是Id和rv組合而成
     * @return
     * @throws Exception
     */
    protected String updateSQL() throws Exception {
        StringBuffer updateValues = new StringBuffer();
        StringBuffer whereValues = new StringBuffer();
        Column column = null;

        Set<String> keys = columnKeyMap.keySet();
        for (String key : keys) {
            if (key.equalsIgnoreCase(DBUtils.ROW_VERSION)) continue;
            if (key.equalsIgnoreCase(DBUtils.CREATE_TIME)) continue;
            column = columnMap.get(key);
            if (!idColumnMap.containsKey(key) && column.updatable()) {
                updateValues.append(key + " = ?,");
            }
        }
        for (String key : keys) {
            if (idColumnMap.containsKey(key) || key.equalsIgnoreCase(DBUtils.ROW_VERSION)) {
                if (whereValues.length() == 0) {
                    whereValues.append(key + " = ?");
                } else {
                    whereValues.append(" AND " + key + " = ?");
                }
            }
        }
        updateValues.deleteCharAt(updateValues.length() - 1);
        return "UPDATE " + tableName + " SET " + updateValues.toString() + " WHERE " + whereValues.toString();
    }

    /**
     * 產生delete的sql語法
     * where 條件會是Id和rv組合而成
     * @return
     * @throws Exception
     */
    protected String deleteSQL() throws Exception {
        StringBuffer whereValues = new StringBuffer();
        Set<String> keys = columnKeyMap.keySet();
        for (String key : keys) {
            if (idColumnMap.containsKey(key)) {
                if (whereValues.length() == 0) {
                    whereValues.append(key + " = ?");
                } else {
                    whereValues.append(" AND " + key + " = ?");
                }
            }
        }
        return "DELETE " + tableName + " WHERE " + whereValues.toString();
    }

    protected String deleteTable() throws Exception {
        return "DELETE " + tableName;
    }

    /**
     * 新增where條件到Where物件(for動態查詢條件)
     * @param where
     * @param parameter
     * @param value
     * @throws Exception
     */
    protected void addConditionParameter(Where where, String parameter, String value) throws Exception{
        if(value.length()>0){
            where.getValues().add(value);
            if(where.getValues().size()==1){
                where.getSql().append(" WHERE ");
            } else{
                where.getSql().append(" AND ");
            }
            where.getSql().append(parameter);
        }
    }

    /**
     * 依Where物件set value到PrepareStatement
     * @param stmt
     * @param where
     * @throws Exception
     */
    protected void addConditionValue(PreparedStatement stmt,Where where) throws Exception{
        int index = 1;
        for(String value:where.getValues()){
            if(value.length()>0){
                stmt.setString(index++, value);
            }
        }
    }

    protected String selectSQL() throws Exception {
        return selectSQL(null);
    }

    /**
     * 產生select的sql(for join)
     * 會依Join的條件自動產生join語法和select join table的欄位
     * @param fromAlias
     * @param joins
     * @return
     * @throws Exception
     */
    protected String selectSQL(String fromAlias, List<Join> joins) throws Exception {
        BeanInfo info = Introspector.getBeanInfo(tableClass);
        PropertyDescriptor[] props = info.getPropertyDescriptors(); //Gets all the properties for the class.
        Map<String, Class> classMap = new HashMap<String, Class>();
        for (PropertyDescriptor pd : props) {
            classMap.put(pd.getName(), pd.getPropertyType());
        }

        StringBuffer columnNames = new StringBuffer();
        StringBuffer fromJoin = new StringBuffer();
        Set<String> keys = columnKeyMap.keySet();
        for (String key : keys) {
            columnNames.append(fromAlias + "." + key + ",");
        }

        fromJoin.append(" FROM " + tableName + " " + fromAlias);

        for (Join join : joins) {
            Table table = (Table) join.getJoinClass().getAnnotation(javax.persistence.Table.class);
            String joinTableName = table.name();
            Map<String, PropertyDescriptor> joinColumnKeyMap = join.getColumnKeyMap();
            keys = joinColumnKeyMap.keySet();
            for (String key : keys) {
//                columnNames.append(join.getAlias() + "." + key + ",");//bu.ib_user_id
                columnNames.append(join.getAlias() + "." + key + " AS "+join.getProperty()+"_"+key+",");//bu.ib_user_id AS boIbAccount_ib_user_id
            }
            fromJoin.append(" " + join.getJoinType() + " JOIN " + joinTableName + " " + join.getAlias() + " ON " + fromAlias + "." + join.getColumnA() + " = " + join.getAlias() + "." + join.getColumnB());
        }
        if (columnNames.length() > 0) columnNames.deleteCharAt(columnNames.length() - 1);
        String sql = "SELECT " + columnNames.toString() + fromJoin.toString();
        System.out.println(sql);
        return sql;
    }

    /**
     * 產生select的sql
     * 當sql有group by時使用此method
     * 將group by的條件傳入
     * @param groupByList
     * @return
     * @throws Exception
     */
    protected String selectSQL(List<String> groupByList) throws Exception {
        StringBuffer columnNames = new StringBuffer();
        Set<String> keys = columnKeyMap.keySet();
        if (groupByList != null && groupByList.size() > 0) {
            for (String key : keys) {
                if (groupByList.contains(key)) {
                    columnNames.append(key + ",");
                }
            }
        } else {
            for (String key : keys) {
                columnNames.append(key + ",");
            }
        }

        columnNames.deleteCharAt(columnNames.length() - 1);
        return "SELECT " + columnNames.toString() + " FROM " + tableName + " ";
    }

    /**
     * 產生GROUP BY的語法(依groupByList)
     * @param groupByList
     * @return
     * @throws Exception
     */
    protected String groupBySQL(List<String> groupByList) throws Exception {
        StringBuffer sb = new StringBuffer();
        for (String key : groupByList) {
            sb.append(key + ",");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        return " GROUP BY " + sb.toString();
    }

    /**
     * 產生 where xxx in (?,?,?)的語法
     * @param sql
     * @param condition
     * @param size
     * @return
     * @throws Exception
     */
    protected String whereInSQL(String sql, String condition, int size) throws Exception {
        if(size==0) return sql;//0筆就不產生
        sql += (sql.length() == 0) ? " WHERE " : " AND ";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb.append("?,");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        return sql + condition + " in (" + sb.toString() + ")";
    }

    protected void selectToObject(ResultSet rs, List list, List<Join> joins) throws Exception {
        DBUtils.selectToObject(columnKeyMap, idColumnMap,transientMap, rs, list, tableClass, joins);
    }

    protected void selectToObject(ResultSet rs, List list) throws Exception {
        DBUtils.selectToObject(columnKeyMap, idColumnMap, transientMap, rs, list, tableClass, null);
    }

    protected void insertStatement(PreparedStatement stmt, Object object) throws Exception {
        DBUtils.insertStatement(stmt, columnKeyMap, columnMap, idColumnMap, object);
    }

    protected void updateStatement(PreparedStatement stmt, Object object) throws Exception {
        DBUtils.updateStatement(stmt, columnKeyMap, columnMap, idColumnMap, object);
    }

    protected void deleteStatement(PreparedStatement stmt, Object object) throws Exception {
        DBUtils.deleteStatement(stmt, columnKeyMap, columnMap, idColumnMap, object);
    }

}
