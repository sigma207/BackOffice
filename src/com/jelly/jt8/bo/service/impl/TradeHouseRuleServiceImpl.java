package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.TradeHouseRuleDao;
import com.jelly.jt8.bo.dao.TradeHouseRuleGroupDao;
import com.jelly.jt8.bo.model.TradeHouseRule;
import com.jelly.jt8.bo.model.TradeHouseRuleGroup;
import com.jelly.jt8.bo.service.TradeHouseRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2015/8/26.
 */
@Service("tradeHouseRuleService")
public class TradeHouseRuleServiceImpl implements TradeHouseRuleService {
    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Autowired
    @Qualifier("TradeHouseRuleDao")
    private TradeHouseRuleDao tradeHouseRuleDao;

    @Autowired
    @Qualifier("TradeHouseRuleGroupDao")
    private TradeHouseRuleGroupDao tradeHouseRuleGroupDao;

    @Override
    public List<TradeHouseRule> select() throws Exception {
        return tradeHouseRuleDao.select();
    }

    @Override
    public List<TradeHouseRule> select(String houseId) throws Exception {
        List<TradeHouseRule> list = tradeHouseRuleDao.select(houseId);
        List<TradeHouseRuleGroup> groupList = tradeHouseRuleGroupDao.select();
        Map<String,List<TradeHouseRuleGroup>> groupMap = new HashMap<String, List<TradeHouseRuleGroup>>();
        String key = null;
        List<TradeHouseRuleGroup> mapList = null;
        for(TradeHouseRuleGroup tradeHouseRuleGroup:groupList){
            key = tradeHouseRuleGroup.getHouseId()+tradeHouseRuleGroup.getCategory()+tradeHouseRuleGroup.getExchangeId();
            mapList = groupMap.get(key);
            if(mapList==null){
                mapList = new ArrayList<TradeHouseRuleGroup>();
                groupMap.put(key,mapList);
            }
            mapList.add(tradeHouseRuleGroup);
        }
        for(TradeHouseRule tradeHouseRule:list){
            key = tradeHouseRule.getHouseId()+tradeHouseRule.getCategory()+tradeHouseRule.getExchangeId();
            mapList = groupMap.get(key);
            tradeHouseRule.setTradeHouseRuleGroupList(mapList);
        }
        return list;
    }

    @Override
    public void insert(TradeHouseRule object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            tradeHouseRuleDao.insert(conn, object);
            for(TradeHouseRuleGroup tradeHouseRuleGroup:object.getTradeHouseRuleGroupList()){
                tradeHouseRuleGroupDao.insert(conn,tradeHouseRuleGroup);
            }
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
    public void delete(TradeHouseRule object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            tradeHouseRuleGroupDao.delete(conn, object);
            tradeHouseRuleDao.delete(conn, object);
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
    public void update(TradeHouseRule object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            tradeHouseRuleDao.update(conn, object);
            tradeHouseRuleGroupDao.delete(conn, object);
            for(TradeHouseRuleGroup tradeHouseRuleGroup:object.getTradeHouseRuleGroupList()){
                tradeHouseRuleGroupDao.insert(conn,tradeHouseRuleGroup);
            }
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
