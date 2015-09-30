package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.TradeBankbookDao;
import com.jelly.jt8.bo.model.TradeAccount;
import com.jelly.jt8.bo.model.TradeBankbook;
import com.jelly.jt8.bo.model.TradeLoginAccount;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/9/17.
 */
@Repository("TradeBankbookDao")
public class TradeBankbookDaoImpl extends BaseDao implements TradeBankbookDao {
    private final static String SP_TRADE_BANKBOOK_CALL = "{call trade_bankbook_cal(?,?,?,?,?,?,?,?,?,?)}";
    private final static String WHERE_ACCOUNT_ID = " WHERE account_id = ? ";
    private final static String AND_TRADE_DATE_RANGE = " AND trade_date >= ? AND trade_date <= ? ";
    private final static String SELECT_TOP_1 = " SELECT TOP 1 * FROM trade_bankbook ";
    private final static String ORDER_BY_BANKBOOK_NO_DESC = " ORDER BY bankbook_no DESC ";
    private final static String LAST_QUERY_IN_ACCOUNT_ID =
            "SELECT *\n" +
            "FROM trade_bankbook \n" +
            "WHERE bankbook_no in (\n" +
            "\tSELECT MAX(bankbook_no) bankbook_no\n" +
            "\tFROM trade_bankbook\n" +
            "\t where? \n" +
            "\tGROUP BY account_id\n" +
            ") ";

    public TradeBankbookDaoImpl() {
        super(TradeBankbook.class);
    }

    @Override
    public List<TradeBankbook> select(String accountId, String beginDate, String endDate) throws Exception {
        List<TradeBankbook> list = new LinkedList<TradeBankbook>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_ACCOUNT_ID + AND_TRADE_DATE_RANGE);
            stmt.setString(1, accountId);
            stmt.setString(2, beginDate);
            stmt.setString(3, endDate);
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
    public TradeBankbook selectLast(String accountId) throws Exception {
        List<TradeBankbook> list = new LinkedList<TradeBankbook>();
        TradeBankbook tradeBankbook = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(SELECT_TOP_1 + WHERE_ACCOUNT_ID + ORDER_BY_BANKBOOK_NO_DESC);
            stmt.setString(1, accountId);
            rs = stmt.executeQuery();
            selectToObject(rs,list);
            if(list.size()>0){
                tradeBankbook = list.get(0);
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
        return tradeBankbook;
    }

    @Override
    public List<TradeBankbook> selectLast(List<TradeAccount> tradeAccountList) throws Exception {
        List<TradeBankbook> list = new LinkedList<TradeBankbook>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            String where = "";
            where = whereInSQL(where, "account_id", tradeAccountList.size());
            stmt = conn.prepareStatement(LAST_QUERY_IN_ACCOUNT_ID.replace("where?",where));
            int index = 1;
            for (TradeAccount tradeAccount : tradeAccountList) {
                stmt.setString(index++, tradeAccount.getAccountId());
            }

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
    public void insert(Connection conn, TradeBankbook object) throws Exception {
        CallableStatement cs = null;
        try {
            cs = conn.prepareCall(SP_TRADE_BANKBOOK_CALL);

            cs.setString(1, object.getAccountId());
            cs.setString(2, object.getBankbookType());
            cs.setBigDecimal(3, object.getAmount());
            cs.setString(4, object.getMemo());
            cs.setString(5, object.getSourceType());
            cs.setString(6, object.getSourceNo());
            cs.setString(7, object.getModifiedBy());

            cs.registerOutParameter(8, Types.INTEGER);
            cs.registerOutParameter(9, Types.VARCHAR);
            cs.registerOutParameter(10, Types.VARCHAR);

            cs.execute();
            parseError(cs.getInt(8), cs.getString(9));
            object.setBankbookNo(Long.parseLong(cs.getString(10)));
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
