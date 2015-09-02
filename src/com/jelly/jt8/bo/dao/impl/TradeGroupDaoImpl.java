package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.TradeGroupDao;
import com.jelly.jt8.bo.model.TradeGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/8/26.
 */
@Repository("TradeGroupDao")
public class TradeGroupDaoImpl extends BaseDao implements TradeGroupDao {
    private final static String WHERE_CATEGORY = " WHERE category = ? ";
    public TradeGroupDaoImpl() {
        super(TradeGroup.class);
    }

    @Override
    public List<TradeGroup> select() throws Exception {
        List<TradeGroup> list =  new LinkedList<TradeGroup>();
        selectByObject(jt8Ds.getConnection(),list);
        return list;
    }

    @Override
    public List<TradeGroup> select(String category) throws Exception {
        List<TradeGroup> list =  new LinkedList<TradeGroup>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_CATEGORY);
            stmt.setString(1, category);
            rs = stmt.executeQuery();
            selectToObject(rs,list);
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
        return list;
    }

    @Override
    public void insert(Connection conn, TradeGroup object) throws Exception {
        int lastKey = insertByObject(conn, object);
        object.setGroupId(lastKey);
    }

    @Override
    public void update(Connection conn, TradeGroup object) throws Exception {
        updateByObject(conn, object);
    }

    @Override
    public void delete(Connection conn, TradeGroup object) throws Exception {
        deleteByObject(conn, object);
    }
}
