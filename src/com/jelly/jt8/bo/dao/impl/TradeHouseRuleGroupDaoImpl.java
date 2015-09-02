package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.TradeHouseRuleGroupDao;
import com.jelly.jt8.bo.model.BoRolePermission;
import com.jelly.jt8.bo.model.TradeGroup;
import com.jelly.jt8.bo.model.TradeHouseRule;
import com.jelly.jt8.bo.model.TradeHouseRuleGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/9/2.
 */
@Repository("TradeHouseRuleGroupDao")
public class TradeHouseRuleGroupDaoImpl extends BaseDao implements TradeHouseRuleGroupDao {
    private final static String WHERE_HOUSE_RULE = " WHERE house_id = ? AND category = ? AND exchange_id = ? ";
    private final static String WHERE_GROUP_ID = " WHERE group_id = ? ";
    public TradeHouseRuleGroupDaoImpl() {
        super(TradeHouseRuleGroup.class);
    }

    @Override
    public List<TradeHouseRuleGroup> select() throws Exception {
        List<TradeHouseRuleGroup> list =  new LinkedList<TradeHouseRuleGroup>();
        selectByObject(jt8Ds.getConnection(),list);
        return list;
    }

    @Override
    public List<TradeHouseRuleGroup> select(TradeHouseRule object) throws Exception {
        List<TradeHouseRuleGroup> list =  new LinkedList<TradeHouseRuleGroup>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_HOUSE_RULE);
            stmt.setString(1, object.getHouseId());
            stmt.setString(2, object.getCategory());
            stmt.setString(3, object.getExchangeId());
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
    public List<TradeHouseRuleGroup> select(TradeGroup object) throws Exception {
        List<TradeHouseRuleGroup> list =  new LinkedList<TradeHouseRuleGroup>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_GROUP_ID);
            stmt.setInt(1, object.getGroupId());
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
    public void insert(Connection conn, TradeHouseRuleGroup object) throws Exception {
        insertByObject(conn,object);
    }

    @Override
    public void delete(Connection conn, TradeHouseRule object) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(deleteTable()+WHERE_HOUSE_RULE);

            stmt.setString(1, object.getHouseId());
            stmt.setString(2, object.getCategory());
            stmt.setString(3, object.getExchangeId());
            stmt.executeUpdate();
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
}
