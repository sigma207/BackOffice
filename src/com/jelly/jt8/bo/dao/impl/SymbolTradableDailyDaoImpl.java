package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.SymbolTradableDailyDao;
import com.jelly.jt8.bo.model.SymbolTradableDaily;
import com.jelly.jt8.bo.model.SystemMainSymbol;
import com.jelly.jt8.bo.util.RsMapper;
import com.jelly.jt8.common.Utils;
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
 * Created by user on 2015/8/17.
 */
@Repository("SymbolTradableDailyDao")
public class SymbolTradableDailyDaoImpl extends BaseDao implements SymbolTradableDailyDao {
    private final static String QUERY = "SELECT exchange_id, symbol_id, stock_name, last, total_volume, updown_per, tradable FROM symbol_tradable_daily ";
    private final static String QUERY_TEMP = "SELECT exchange_id, symbol_id, stock_name, last, total_volume, updown_per, tradable FROM symbol_tradable_daily_temp ";
    private final static String WHERE_MAIN_SYMBOL = "WHERE exchange_id = ? ";
    private final static String INSERT = "INSERT INTO symbol_stock_daily_temp (exchange_id, symbol_id, update_time) VALUES (?, ?, ?);";
    private final static String DELETE = "DELETE symbol_stock_daily_temp WHERE exchange_id = ? ";

    @Override
    public List<SymbolTradableDaily> selectTemp() throws Exception {
        List<SymbolTradableDaily> list = new LinkedList<SymbolTradableDaily>();
        execute(jt8Ds.getConnection(),QUERY_TEMP,list,SymbolTradableDaily.class);
        return list;
    }

    @Override
    public List<SymbolTradableDaily> selectTemp(SystemMainSymbol mainSymbol) throws Exception {
        List<SymbolTradableDaily> list =  new LinkedList<SymbolTradableDaily>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(QUERY_TEMP + WHERE_MAIN_SYMBOL);
            stmt.setString(1,mainSymbol.getExchangeId());

            rs = stmt.executeQuery();
            RsMapper.map(rs, list, SymbolTradableDaily.class);
        } catch (Exception e){
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
    public void insertTemp(Connection conn, List<SymbolTradableDaily> list) throws Exception {
        PreparedStatement stmt = null;
        try {
            int count = 0;
            stmt = conn.prepareStatement(INSERT);
            for (SymbolTradableDaily model : list) {
                stmt.setString(1, model.getExchange_id());
                stmt.setString(2, model.getSymbol_id());
                stmt.setString(3, Utils.updateTime());
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
    public void deleteTemp(Connection conn, SystemMainSymbol mainSymbol) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(DELETE);

            stmt.setString(1, mainSymbol.getExchangeId());
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
    public List<SymbolTradableDaily> select(SystemMainSymbol mainSymbol) throws Exception {
        List<SymbolTradableDaily> list =  new LinkedList<SymbolTradableDaily>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(QUERY + WHERE_MAIN_SYMBOL);
            stmt.setString(1,mainSymbol.getExchangeId());

            rs = stmt.executeQuery();
            RsMapper.map(rs, list, SymbolTradableDaily.class);
        } catch (Exception e){
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
