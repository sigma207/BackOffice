package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.SymbolHolidayExceptionDao;
import com.jelly.jt8.bo.model.SymbolHolidayException;
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
 * Created by user on 2015/8/31.
 */
@Repository("SymbolHolidayExceptionDao")
public class SymbolHolidayExceptionDaoImpl extends BaseDao implements SymbolHolidayExceptionDao {
    public SymbolHolidayExceptionDaoImpl() {
        super(SymbolHolidayException.class);
    }
    private final static String WHERE_MAIN_SYMBOL = " WHERE exchange_id = ? AND main_symbol_id = ? ";

    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Override
    public List<SymbolHolidayException> select(SystemMainSymbol mainSymbol) throws Exception {
        List<SymbolHolidayException> list =  new LinkedList<SymbolHolidayException>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_MAIN_SYMBOL);
            stmt.setString(1,mainSymbol.getExchangeId());
            stmt.setString(2, mainSymbol.getMainSymbolId());
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
    public void insert(Connection conn, List<SymbolHolidayException> list) throws Exception {
        insertByObject(conn, list);
    }

    @Override
    public void update(Connection conn, SymbolHolidayException symbolHolidayException) throws Exception {
        updateByObject(conn, symbolHolidayException);
    }

    @Override
    public void delete(Connection conn, SymbolHolidayException symbolHolidayException) throws Exception {
        deleteByObject(conn, symbolHolidayException);
    }
}
