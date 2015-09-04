package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.TradeIbGroupDao;
import com.jelly.jt8.bo.model.TradeIbGroup;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/9/4.
 */
@Repository("TradeIbGroupDao")
public class TradeIbGroupDaoImpl extends BaseDao implements TradeIbGroupDao {
    private final static String WHERE_USER_ID = " WHERE user_id = ? ";
    public TradeIbGroupDaoImpl() {
        super(TradeIbGroup.class);
    }

    @Override
    public List<TradeIbGroup> select(int userId) throws Exception {
        List<TradeIbGroup> list =  new LinkedList<TradeIbGroup>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_USER_ID);
            stmt.setInt(1, userId);
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
    public void update(Connection conn, int userId, String groupIds) throws Exception {
        CallableStatement cs = null;
        try {
            cs = conn.prepareCall("{call trade_ib_group_update(?,?,?,?)}");

            cs.setInt(1, userId);
            cs.setString(2,  groupIds);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.registerOutParameter(4, Types.VARCHAR);

            cs.execute();
            parseError(cs.getInt(3), cs.getString(4));
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insert(int userId, List<TradeIbGroup> list) throws Exception {

    }

    @Override
    public void update(int userId, List<TradeIbGroup> list) throws Exception {

    }

    @Override
    public void delete(int userId) throws Exception {

    }
}
