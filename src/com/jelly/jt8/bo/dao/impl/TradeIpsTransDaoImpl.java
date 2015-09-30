package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.TradeIpsTransDao;
import com.jelly.jt8.bo.model.TradeIpsTrans;
import com.jelly.jt8.bo.util.Where;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/9/24.
 */
@Repository("TradeIpsTransDao")
public class TradeIpsTransDaoImpl extends BaseDao implements TradeIpsTransDao {
    private final static String BO_STATUS = " bo_status = ? ";
    private final static String IPS_STATUS = " ips_status = ? ";
    private final static String ACCOUNT_ID = " account_id = ? ";
    private final static String TRADE_DATE_BEING = " trade_date >= ? ";
    private final static String TRADE_DATE_END = " trade_date <= ? ";
    private final static String UPDATE_BO_STATUS = "UPDATE trade_ips_trans SET bo_status = ? WHERE bill_no = ? ";
    private final static String UPDATE_BO_STATUS_AND_MEMO = "UPDATE trade_ips_trans SET bo_status = ?, memo = ? WHERE bill_no = ? ";
    public TradeIpsTransDaoImpl() {
        super(TradeIpsTrans.class);
    }

    @Override
    public List<TradeIpsTrans> select(String accountId, String boStatus, String ipsStatus, String beginDate, String endDate) throws Exception {
        List<TradeIpsTrans> list = new LinkedList<TradeIpsTrans>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            Where where = new Where();
            addConditionParameter(where, BO_STATUS, boStatus);
            addConditionParameter(where, IPS_STATUS, ipsStatus);
            addConditionParameter(where, ACCOUNT_ID, accountId);
            addConditionParameter(where, TRADE_DATE_BEING, beginDate);
            addConditionParameter(where, TRADE_DATE_END, endDate);
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + where.getSql().toString());
            addConditionValue(stmt,where);
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
        return list;
    }

    @Override
    public void pass(Connection conn, TradeIpsTrans object) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(UPDATE_BO_STATUS);
            stmt.setString(1, object.getBoStatus());
            stmt.setLong(2, object.getBillNo());
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

    @Override
    public void reject(Connection conn, TradeIpsTrans object) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(UPDATE_BO_STATUS_AND_MEMO);
            stmt.setString(1, object.getBoStatus());
            stmt.setString(2, object.getMemo());
            stmt.setLong(3, object.getBillNo());
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
