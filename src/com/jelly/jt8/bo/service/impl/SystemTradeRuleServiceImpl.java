package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.SystemTradeRuleDao;
import com.jelly.jt8.bo.dao.TradeGroupDao;
import com.jelly.jt8.bo.model.SystemTradeRule;
import com.jelly.jt8.bo.model.TradeIbGroup;
import com.jelly.jt8.bo.service.SystemTradeRuleService;
import com.jelly.jt8.bo.util.SqlTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 2015/9/9.
 */
@Service("systemTradeRuleService")
public class SystemTradeRuleServiceImpl implements SystemTradeRuleService {
    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Autowired
    @Qualifier("SystemTradeRuleDao")
    private SystemTradeRuleDao systemTradeRuleDao;

    @Autowired
    @Qualifier("TradeGroupDao")
    private TradeGroupDao tradeGroupDao;

    @Override
    public List<SystemTradeRule> select() throws Exception {
        return systemTradeRuleDao.select();
    }

    @Override
    public void insert(SystemTradeRule object) throws Exception {
        Connection conn = null;
        try {

            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            systemTradeRuleDao.insert(conn, object);
            tradeGroupDao.updateIsActive(conn, object.getTradeGroupList());
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
    public void update(SystemTradeRule object) throws Exception {
        Connection conn = null;
        try {

            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            systemTradeRuleDao.update(conn, object);
            tradeGroupDao.updateIsActive(conn, object.getTradeGroupList());
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
    public void delete(SystemTradeRule object) throws Exception {
        Connection conn = null;
        try {

            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);

            tradeGroupDao.updateIsActiveOff(conn, object.getCategory(), object.getExchangeId());
            systemTradeRuleDao.delete(conn, object);
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
