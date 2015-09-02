package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.SymbolHolidayDao;
import com.jelly.jt8.bo.model.SymbolHoliday;
import com.jelly.jt8.bo.model.SystemMainSymbol;
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
 * Created by user on 2015/8/30.
 */
@Repository("SymbolHolidayDao")
public class SymbolHolidayDaoImpl extends BaseDao implements SymbolHolidayDao {
    public SymbolHolidayDaoImpl() {
        super(SymbolHoliday.class);
    }

    private final static String WHERE_MAIN_SYMBOL = " WHERE exchange_id = ? AND main_symbol_id = ? ";

    @Override
    public List<SymbolHoliday> select(SystemMainSymbol mainSymbol) throws Exception {
        List<SymbolHoliday> list = new LinkedList<SymbolHoliday>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_MAIN_SYMBOL);
            stmt.setString(1, mainSymbol.getExchangeId());
            stmt.setString(2, mainSymbol.getMainSymbolId());
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
    public void insert(Connection conn, SymbolHoliday symbolHoliday) throws Exception {
        int lastKey = insertByObject(conn, symbolHoliday);
        symbolHoliday.setHolidayId(lastKey);
    }

    @Override
    public void insert(Connection conn, List<SymbolHoliday> list) throws Exception {
        insertByObject(conn, list);
    }

    @Override
    public void update(Connection conn, SymbolHoliday symbolHoliday) throws Exception {
        updateByObject(conn, symbolHoliday);
    }

    @Override
    public void delete(Connection conn, SymbolHoliday symbolHoliday) throws Exception {
        deleteByObject(conn, symbolHoliday);
    }
}
