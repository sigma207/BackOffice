package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.TradeAccountDao;
import com.jelly.jt8.bo.model.TradeAccount;
import com.jelly.jt8.bo.util.ErrorMsg;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/9/15.
 */
@Repository("TradeAccountDao")
public class TradeAccountDaoImpl extends BaseDao implements TradeAccountDao {
    private final static String MAX_ACCOUNT_ID_QUERY = " SELECT MAX(account_id) account_id FROM trade_account ";
    private final static String UPDATE_TRADE_STATUS = "UPDATE trade_account SET trade_status = ? ";
    private final static String DELETE = " DELETE trade_account ";
    private final static String WHERE_LOGIN_ID = " WHERE login_id = ? ";
    private final static String WHERE_ACCOUNT_ID = " WHERE account_id = ? ";
    private final static String WHERE_GROUP_ID = " WHERE group_id = ? ";
    public TradeAccountDaoImpl() {
        super(TradeAccount.class);
    }

    @Override
    public boolean hasData(int groupId) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean hasData = false;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectTop1SQL() + WHERE_GROUP_ID);
            stmt.setInt(1,groupId);
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

    @Override
    public String selectMaxAccountId() throws Exception {
        List<TradeAccount> list = new LinkedList<TradeAccount>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(MAX_ACCOUNT_ID_QUERY);
            rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getString("account_id");
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
        return null;
    }

    @Override
    public List<TradeAccount> select(String loginId) throws Exception {
        List<TradeAccount> list = new LinkedList<TradeAccount>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_LOGIN_ID);
            stmt.setString(1, loginId);
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
    public void insert(Connection conn, TradeAccount object) throws Exception {
        insertByObject(conn, object);
    }

    @Override
    public void update(Connection conn, TradeAccount object) throws Exception {
        updateByObject(conn,object);
    }

    @Override
    public void delete(Connection conn, TradeAccount object) throws Exception {
        deleteByObject(conn,object);
    }

    @Override
    public void delete(Connection conn, String loginId) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(DELETE+WHERE_LOGIN_ID);
            stmt.setString(1,loginId);
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

    @Override
    public void updateTradeStatus(Connection conn, String loginId, int tradeStatus) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(UPDATE_TRADE_STATUS+WHERE_LOGIN_ID);
            stmt.setInt(1, tradeStatus);
            stmt.setString(2, loginId);
            stmt.executeUpdate();
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
}
