package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.OrganizationDao;
import com.jelly.jt8.bo.dao.TradeAccountGroupDao;
import com.jelly.jt8.bo.dao.TradeHouseRuleDao;
import com.jelly.jt8.bo.model.TradeAccountGroup;
import com.jelly.jt8.bo.service.TradeAccountGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 2015/8/26.
 */
@Service("tradeAccountGroupService")
public class TradeAccountGroupServiceImpl implements TradeAccountGroupService {

    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Autowired
    @Qualifier("TradeAccountGroupDao")
    private TradeAccountGroupDao tradeAccountGroupDao;

    @Override
    public List<TradeAccountGroup> select() throws Exception {
        return tradeAccountGroupDao.select();
    }

    @Override
    public void insert(TradeAccountGroup tradeAccountGroup) throws Exception {
        Connection conn = null;

        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            tradeAccountGroupDao.insert(conn, tradeAccountGroup);
            conn.commit();
        }catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                }catch (SQLException se){
                    se.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(TradeAccountGroup tradeAccountGroup) throws Exception {
        Connection conn = null;

        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            tradeAccountGroupDao.update(conn, tradeAccountGroup);
            conn.commit();
        }catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                }catch (SQLException se){
                    se.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(TradeAccountGroup tradeAccountGroup) throws Exception {
        Connection conn = null;

        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            tradeAccountGroupDao.delete(conn, tradeAccountGroup);
            conn.commit();
        }catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                }catch (SQLException se){
                    se.printStackTrace();
                }
            }
        }
    }
}
