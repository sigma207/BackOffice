package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.util.DBUtils;
import com.jelly.jt8.bo.util.ErrorMsg;
import com.jelly.jt8.bo.util.Join;
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

    protected PreparedStatement getStatement(Connection conn, String sql) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
        } catch (Exception e) {
            throw e;
        } finally {
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

    protected void query(PreparedStatement stmt, List list) throws Exception {
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

    protected void selectByObject(Connection conn, List list) throws Exception {
        selectByObject(conn, list, selectSQL());
    }

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

    protected void updateByObject(Connection conn, Object object) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(updateSQL());
            updateStatement(stmt, object);

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

    public void parseError(int code, String message) throws Exception {
        if (code != 0 || !message.equals("")) {
            throw new Exception(message);
        }
    }

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

    protected String selectSQL() throws Exception {
        return selectSQL(null);
    }

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

    protected String groupBySQL(List<String> groupByList) throws Exception {
        StringBuffer sb = new StringBuffer();
        for (String key : groupByList) {
            sb.append(key + ",");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        return " GROUP BY " + sb.toString();
    }

    protected String whereInSQL(String sql, String condition, int size) throws Exception {
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
