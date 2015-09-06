package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.TradeGroupDao;
import com.jelly.jt8.bo.dao.TradeHouseRuleGroupDao;
import com.jelly.jt8.bo.model.TradeGroup;
import com.jelly.jt8.bo.model.TradeHouseRuleGroup;
import com.jelly.jt8.bo.service.TradeGroupService;
import com.jelly.jt8.bo.util.ErrorMsg;
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
@Service("tradeGroupService")
public class TradeGroupServiceImpl implements TradeGroupService {

    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Autowired
    @Qualifier("TradeGroupDao")
    private TradeGroupDao tradeGroupDao;

    @Autowired
    @Qualifier("TradeHouseRuleGroupDao")
    private TradeHouseRuleGroupDao tradeHouseRuleGroupDao;

    @Override
    public List<TradeGroup> select() throws Exception {
        return tradeGroupDao.select();
    }

    @Override
    public List<TradeGroup> select(int ownerId) throws Exception {
        return tradeGroupDao.select(ownerId);
    }

    @Override
    public List<TradeGroup> select(String category,int ownerId) throws Exception {
        return tradeGroupDao.select(category,ownerId);
    }

    @Override
    public void insert(TradeGroup object) throws Exception {
        Connection conn = null;

        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            tradeGroupDao.insert(conn, object);
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
    public void update(TradeGroup object) throws Exception {
        Connection conn = null;

        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            tradeGroupDao.update(conn, object);
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
    public void delete(TradeGroup object) throws Exception {
        Connection conn = null;

        try {
            List<TradeHouseRuleGroup> list = tradeHouseRuleGroupDao.select(object);
            if(list.size()>0){
                throw new Exception(ErrorMsg.TRADE_GROUP_HAS_USED);
            }
            conn = jt8Ds.getConnection();

            conn.setAutoCommit(false);
            tradeGroupDao.delete(conn, object);
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
