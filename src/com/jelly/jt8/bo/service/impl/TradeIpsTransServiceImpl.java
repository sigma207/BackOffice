package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.TradeBankbookDao;
import com.jelly.jt8.bo.dao.TradeIpsTransDao;
import com.jelly.jt8.bo.model.TradeBankbook;
import com.jelly.jt8.bo.model.TradeIpsTrans;
import com.jelly.jt8.bo.service.TradeIpsTransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 2015/9/24.
 */
@Service("tradeIpsTransService")
public class TradeIpsTransServiceImpl implements TradeIpsTransService {
    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Autowired
    @Qualifier("TradeIpsTransDao")
    private TradeIpsTransDao tradeIpsTransDao;

    @Autowired
    @Qualifier("TradeBankbookDao")
    private TradeBankbookDao tradeBankbookDao;

    @Override
    public List<TradeIpsTrans> select(String accountId, String boStatus, String ipsStatus, String beginDate, String endDate) throws Exception {
        return tradeIpsTransDao.select(accountId, boStatus, ipsStatus, beginDate, endDate);
    }

    @Override
    public void pass(TradeIpsTrans object) throws Exception {

        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            object.setBoStatus(TradeIpsTrans.BO_STATUS_P);
            tradeIpsTransDao.pass(conn, object);
            TradeBankbook tradeBankbook = new TradeBankbook();
            tradeBankbook.setAccountId(object.getAccountId());
            tradeBankbook.setBankbookType(object.getRequestType());
            tradeBankbook.setAmount(object.getRequestAmount());
            tradeBankbook.setSourceType(TradeBankbook.SOURCE_TYPE_I);
            tradeBankbook.setSourceNo(String.valueOf(object.getBillNo()));
            tradeBankbookDao.insert(conn,tradeBankbook);
            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }

    @Override
    public void reject(TradeIpsTrans object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            object.setBoStatus(TradeIpsTrans.BO_STATUS_R);
            tradeIpsTransDao.reject(conn, object);
            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }
}
