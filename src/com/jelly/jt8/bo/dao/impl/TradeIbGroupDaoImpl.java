package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.TradeIbGroupDao;
import com.jelly.jt8.bo.model.TradeIbGroup;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/9/4.
 */
@Repository("TradeIbGroupDao")
public class TradeIbGroupDaoImpl extends BaseDao implements TradeIbGroupDao {
    private final static String WHERE_USER_ID = " WHERE user_id = ? ";
    private final static String QUERY_WITH_PARENT_USER_ID = "with my_user as (\n" +
            "\tSELECT *\n" +
            "\tFROM bo_user\n" +
            "\tWHERE parent_user_id = ?\n" +
            "\tUNION ALL\n" +
            "\tSELECT bu.*\n" +
            "\tFROM bo_user bu\n" +
            "\tINNER JOIN my_user mu ON mu.user_id = bu.parent_user_id\n" +
            ")\n" +
            "SELECT tig.group_id\n" +
            "FROM my_user mu\n" +
            "INNER JOIN trade_ib_group tig on mu.user_id = tig.user_id\n" +
            "GROUP BY tig.group_id\n";
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
    public List<Integer> selectChildrenGroupId(int parentUserId) throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(QUERY_WITH_PARENT_USER_ID);
            stmt.setInt(1, parentUserId);
            rs = stmt.executeQuery();
            while (rs.next()){
                list.add(rs.getInt("group_id"));
            }
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
