package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.TradeGroupDao;
import com.jelly.jt8.bo.model.SymbolTradableDaily;
import com.jelly.jt8.bo.model.TradeGroup;
import com.jelly.jt8.common.Utils;
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
    private final static String WHERE_CATEGORY_AND_OWNER_ID = " WHERE category = ? AND owner_id = ? ";
    private final static String WHERE_CATEGORY = " WHERE category = ? ";
    private final static String WHERE_OWNER_ID = " WHERE owner_id = ? ";
    private final static String UPDATE_IS_ACTIVE = "UPDATE trade_group SET is_active = ? WHERE group_id = ? ";
    private final static String UPDATE_IS_ACTIVE_OFF = "UPDATE trade_group SET is_active = ? ";
    private final static String WHERE_CATEGORY_AND_EXCHANGE_ID = " WHERE category = ? AND exchange_id = ? ";
    private final static String ORDER_BY_CATEGORY = " ORDER BY category ";
    public TradeGroupDaoImpl() {
        super(TradeGroup.class);
    }

    @Override
    public List<TradeGroup> select() throws Exception {
        List<TradeGroup> list =  new LinkedList<TradeGroup>();
        selectByObject(jt8Ds.getConnection(), list, selectSQL()+ORDER_BY_CATEGORY);
        return list;
    }

    @Override
    public List<TradeGroup> select(int ownerId) throws Exception {
        List<TradeGroup> list =  new LinkedList<TradeGroup>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_OWNER_ID);
            stmt.setInt(1, ownerId);
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
    public List<TradeGroup> select(String category,int ownerId) throws Exception {
        List<TradeGroup> list =  new LinkedList<TradeGroup>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_CATEGORY_AND_OWNER_ID);
            stmt.setString(1, category);
            stmt.setInt(2, ownerId);
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

    @Override
    public void updateIsActive(Connection conn, TradeGroup object) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(UPDATE_IS_ACTIVE);
            stmt.setInt(1, object.getIsActive());
            stmt.setInt(2, object.getGroupId());
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
    public void updateIsActive(Connection conn, List<TradeGroup> list) throws Exception {
        PreparedStatement stmt = null;
        try {
            int count = 0;
            stmt = conn.prepareStatement(UPDATE_IS_ACTIVE);
            for (TradeGroup model : list) {
                stmt.setInt(1, model.getIsActive());
                stmt.setInt(2, model.getGroupId());
                stmt.addBatch();
                if(++count % batchSize == 0) {
                    stmt.executeBatch();
                }
            }
            stmt.executeBatch();
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
    public void updateIsActiveOff(Connection conn, String category, String exchangeId) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(UPDATE_IS_ACTIVE_OFF+WHERE_CATEGORY_AND_EXCHANGE_ID);

            stmt.setString(1, category);
            stmt.setString(2, exchangeId);
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
