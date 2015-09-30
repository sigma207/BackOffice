package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.Jt8DaoConfig;
import com.jelly.jt8.bo.dao.SystemTradeRuleDao;
import com.jelly.jt8.bo.model.SystemTradeRule;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/9/9.
 */
@Repository("SystemTradeRuleDao")
public class SystemTradeRuleDaoImpl extends BaseDao implements SystemTradeRuleDao {
    private final static String WHERE_GROUP_ID = " WHERE group_id = ? ";
    public SystemTradeRuleDaoImpl() {
        super(SystemTradeRule.class);
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
    public List<SystemTradeRule> select() throws Exception {
        List<SystemTradeRule> list = new LinkedList<SystemTradeRule>();
        selectByObject(jt8Ds.getConnection(), list, selectSQL()+ Jt8DaoConfig.CATEGORY_EXCHANGE_ID_ORDER_BY);
        return list;
    }

    @Override
    public void insert(Connection conn, SystemTradeRule object) throws Exception {
        insertByObject(conn, object);
    }

    @Override
    public void update(Connection conn, SystemTradeRule object) throws Exception {
        updateByObject(conn, object);
    }

    @Override
    public void delete(Connection conn, SystemTradeRule object) throws Exception {
        deleteByObject(conn, object);
    }
}
