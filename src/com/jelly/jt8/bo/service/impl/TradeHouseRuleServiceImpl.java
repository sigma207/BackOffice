package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.*;
import com.jelly.jt8.bo.model.*;
import com.jelly.jt8.bo.service.TradeHouseRuleService;
import com.jelly.jt8.bo.util.SqlTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

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
    @Qualifier("TradeIbGroupDao")
    private TradeIbGroupDao tradeIbGroupDao;

    @Autowired
    @Qualifier("TradeGroupDao")
    private TradeGroupDao tradeGroupDao;

    @Autowired
    @Qualifier("BoUserDao")
    private BoUserDao boUserDao;

    @Override
    public List<TradeHouseRule> select() throws Exception {
        return tradeHouseRuleDao.select();
    }

    @Override
    public List<TradeHouseRule> select(String houseId) throws Exception {
        List<TradeHouseRule> list = tradeHouseRuleDao.select(houseId);
//        List<TradeHouseRuleGroup> groupList = tradeHouseRuleGroupDao.select();
//        Map<String,List<TradeHouseRuleGroup>> groupMap = new HashMap<String, List<TradeHouseRuleGroup>>();
//        String key = null;
//        List<TradeHouseRuleGroup> mapList = null;
//        for(TradeHouseRuleGroup tradeHouseRuleGroup:groupList){
//            key = tradeHouseRuleGroup.getHouseId()+tradeHouseRuleGroup.getCategory()+tradeHouseRuleGroup.getExchangeId();
//            mapList = groupMap.get(key);
//            if(mapList==null){
//                mapList = new ArrayList<TradeHouseRuleGroup>();
//                groupMap.put(key,mapList);
//            }
//            mapList.add(tradeHouseRuleGroup);
//        }
//        for(TradeHouseRule tradeHouseRule:list){
//            key = tradeHouseRule.getHouseId()+tradeHouseRule.getCategory()+tradeHouseRule.getExchangeId();
//            mapList = groupMap.get(key);
//            tradeHouseRule.setTradeHouseRuleGroupList(mapList);
//        }
        return list;
    }

    @Override
    public List<TradeHouseRule> select4HouseRule(int userId) throws Exception {
        BoUser loginUser = boUserDao.select(userId);
        List<TradeHouseRule> tradeHouseRuleList = select(loginUser.getLoginId());
        List<TradeIbGroup> tradeIbGroupList = tradeIbGroupDao.select(userId);//拿到此user的所有group了,要依houseRule的category去分,但不知TradeIbGroup的category是什麼,只有group_id
        List<TradeGroup> tradeGroupList = tradeGroupDao.select();//先拿出所有tradeGroup

        Map<String, TradeHouseRule> tradeHouseRuleMap = new HashMap<String, TradeHouseRule>();
        Map<Integer, TradeGroup> tradeGroupMap = new HashMap<Integer, TradeGroup>();
        String key = null;
        TradeHouseRule rule = null;
        TradeGroup group = null;
        List<TradeIbGroup> ibGroupList = null;
        for (TradeHouseRule tradeHouseRule : tradeHouseRuleList) {
            key = tradeHouseRule.getCategory() + tradeHouseRule.getExchangeId();
            tradeHouseRuleMap.put(key, tradeHouseRule);
        }

        for (TradeGroup tradeGroup : tradeGroupList) {
            tradeGroupMap.put(tradeGroup.getGroupId(), tradeGroup);
        }

        for (TradeIbGroup tradeIbGroup : tradeIbGroupList) {
            group = tradeGroupMap.get(tradeIbGroup.getGroupId());
            key = group.getCategory() + group.getExchangeId();
            rule = tradeHouseRuleMap.get(key);
            ibGroupList = rule.getTradeIbGroupList();
            if (ibGroupList == null) {
                ibGroupList = new ArrayList<TradeIbGroup>();
                rule.setTradeIbGroupList(ibGroupList);
            }
            ibGroupList.add(tradeIbGroup);
        }

        return tradeHouseRuleList;
    }

    @Override
    public List<TradeHouseRule> select(int userId) throws Exception {
        BoUser loginUser = boUserDao.select(userId);
        List<TradeHouseRule> tradeHouseRuleList = select(loginUser.getLoginId());
//        List<TradeGroup> tradeGroupList = tradeGroupDao.select();
//        Map<Integer,TradeGroup> tradeGroupMap = new HashMap<Integer, TradeGroup>();
//        for(TradeGroup tradeGroup:tradeGroupList){
//            tradeGroupMap.put(tradeGroup.getGroupId(),tradeGroup);
//        }
//        List<TradeGroup> temp = null;
//        for(TradeHouseRule tradeHouseRule:tradeHouseRuleList){
//            temp = new ArrayList<TradeGroup>();
//            if(tradeHouseRule.getTradeHouseRuleGroupList()!=null){
//                for(TradeHouseRuleGroup tradeHouseRuleGroup:tradeHouseRule.getTradeHouseRuleGroupList()){
//                    temp.add(tradeGroupMap.get(tradeHouseRuleGroup.getGroupId()));
//                }
//            }
//            tradeHouseRule.setTradeGroupList(temp);
//        }
//        if(tradeHouseRuleList.size()==0){
//
//        }
        return tradeHouseRuleList;
    }

    @Override
    public void insert(TradeHouseRule object, int userId) throws Exception {
        Connection conn = null;
        try {

            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            tradeHouseRuleDao.insert(conn, object);
            StringBuffer groupIds = new StringBuffer();
            if (object.getTradeIbGroupList() != null && object.getTradeIbGroupList().size() > 0) {
//                SqlTool.appendStart(groupIds);
                for (TradeIbGroup tradeIbGroup : object.getTradeIbGroupList()) {
                    SqlTool.append(groupIds, String.valueOf(tradeIbGroup.getGroupId()));
                }
//                SqlTool.appendEnd(groupIds);
            }
            tradeIbGroupDao.update(conn, userId, groupIds.toString());
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
    public void delete(TradeHouseRule object, int userId) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            tradeIbGroupDao.update(conn, userId, "");
            tradeHouseRuleDao.delete(conn, object);
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
    public void update(TradeHouseRule object, int userId) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            tradeHouseRuleDao.update(conn, object);
            StringBuffer groupIds = new StringBuffer();
            if (object.getTradeIbGroupList() != null && object.getTradeIbGroupList().size() > 0) {
//                SqlTool.appendStart(groupIds);
                for (TradeIbGroup tradeIbGroup : object.getTradeIbGroupList()) {
                    SqlTool.append(groupIds, String.valueOf(tradeIbGroup.getGroupId()));
                }
//                SqlTool.appendEnd(groupIds);
            }
            tradeIbGroupDao.update(conn, userId, groupIds.toString());
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
