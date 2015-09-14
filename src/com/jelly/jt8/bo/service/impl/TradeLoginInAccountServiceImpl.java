package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.TradeLoginAccountDao;
import com.jelly.jt8.bo.model.TradeLoginAccount;
import com.jelly.jt8.bo.service.TradeLoginInAccountService;
import com.jelly.jt8.bo.util.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015/9/14.
 */
@Service("tradeLoginInAccountService")
public class TradeLoginInAccountServiceImpl implements TradeLoginInAccountService {
    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Autowired
    @Qualifier("TradeLoginAccountDao")
    private TradeLoginAccountDao tradeLoginAccountDao;

    @Override
    public List<TradeLoginAccount> select() throws Exception {
        return tradeLoginAccountDao.select();
    }

    @Override
    public List<TradeLoginAccount> select(int userId) throws Exception {
        return tradeLoginAccountDao.select(userId);
    }

    @Override
    public void insert(TradeLoginAccount object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            object.setPassword(Password.createPassword(object.getPassword()));
            tradeLoginAccountDao.insert(conn, object);

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
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(TradeLoginAccount object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            object.setPassword(Password.createPassword(object.getPassword()));
            tradeLoginAccountDao.update(conn, object);

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
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(TradeLoginAccount object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            object.setPassword(Password.createPassword(object.getPassword()));
            tradeLoginAccountDao.delete(conn, object);

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
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }
}
