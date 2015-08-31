package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.TradeHouseRuleDao;
import com.jelly.jt8.bo.model.TradeHouseRule;
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
 * Created by user on 2015/8/26.
 */
@Repository("TradeHouseRuleDao")
public class TradeHouseRuleDaoImpl extends BaseDao implements TradeHouseRuleDao {
    public TradeHouseRuleDaoImpl() {
        super(TradeHouseRule.class);
    }

    private final static String WHERE_HOUSE_ID = " WHERE house_id = ? ";

    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Override
    public List<TradeHouseRule> select() throws Exception {
        List<TradeHouseRule> list =  new LinkedList<TradeHouseRule>();
        selectByObject(jt8Ds.getConnection(),list);
        return list;
    }

    @Override
    public List<TradeHouseRule> select(String houseId) throws Exception {
        List<TradeHouseRule> list = new LinkedList<TradeHouseRule>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_HOUSE_ID);
            if(houseId!=null)
            stmt.setString(1, houseId);
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
}
