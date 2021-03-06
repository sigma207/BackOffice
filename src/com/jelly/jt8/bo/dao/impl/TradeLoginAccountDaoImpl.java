package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.TradeLoginAccountDao;
import com.jelly.jt8.bo.model.TradeLoginAccount;
import com.jelly.jt8.bo.util.Join;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/9/14.
 */
@Repository("TradeLoginAccountDao")
public class TradeLoginAccountDaoImpl extends BaseDao implements TradeLoginAccountDao {
    private final static String WHERE_USER_ID = " WHERE user_id = ? ";
    private final static String WHERE_LOGIN_ID = " WHERE login_id = ? ";
    private final static String UPDATE_IS_ACTIVE = "UPDATE trade_login_account SET is_active = ? WHERE login_id = ? ";
    public TradeLoginAccountDaoImpl() {
        super(TradeLoginAccount.class);
    }

    @Override
    public List<TradeLoginAccount> select() throws Exception {
        List<TradeLoginAccount> list = new LinkedList<TradeLoginAccount>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            List<Join> joinList = new ArrayList<Join>();
            joinList.add(new Join(tableClass,"boUser",Join.INNER,"bu","user_id","user_id"));
            stmt = conn.prepareStatement(selectSQL("tia",joinList));
            rs = stmt.executeQuery();
            selectToObject(rs, list,joinList);
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
    public List<TradeLoginAccount> select(int userId) throws Exception {
        List<TradeLoginAccount> list = new LinkedList<TradeLoginAccount>();
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
    public TradeLoginAccount select(String loginId) throws Exception {
        List<TradeLoginAccount> list = new LinkedList<TradeLoginAccount>();
        TradeLoginAccount boIbAccount = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_LOGIN_ID);
            stmt.setString(1, loginId);
            rs = stmt.executeQuery();
            selectToObject(rs,list);
            if(list.size()>0){
                boIbAccount = list.get(0);
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
        return boIbAccount;
    }

    @Override
    public void insert(Connection conn, TradeLoginAccount object) throws Exception {
        insertByObject(conn, object);
    }

    @Override
    public void update(Connection conn, TradeLoginAccount object) throws Exception {
        updateByObject(conn,object);
    }

    @Override
    public void delete(Connection conn, TradeLoginAccount object) throws Exception {
        deleteByObject(conn,object);
    }

    @Override
    public void updateIsActive(Connection conn, TradeLoginAccount object) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(UPDATE_IS_ACTIVE);
            stmt.setInt(1, object.getIsActive());
            stmt.setString(2, object.getLoginId());
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
