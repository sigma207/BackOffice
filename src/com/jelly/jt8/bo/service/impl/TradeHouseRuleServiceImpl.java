package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.*;
import com.jelly.jt8.bo.model.*;
import com.jelly.jt8.bo.service.TradeHouseRuleService;
import com.jelly.jt8.bo.util.ErrorMsg;
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
 * 此程式是代理可以選擇下層代理可用group的版本,現在暫時已用不到
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

    /**
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<TradeHouseRule> select4HouseRule(int userId) throws Exception {
        BoUser loginUser = boUserDao.select(userId);
        List<TradeHouseRule> tradeHouseRuleList = tradeHouseRuleDao.select(loginUser.getLoginId());
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
            ibGroupList = new ArrayList<TradeIbGroup>();
            ibGroupList.addAll(tradeIbGroupList);
            tradeHouseRule.setUserOtherIbGroupList(ibGroupList);
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

        for (TradeHouseRule tradeHouseRule : tradeHouseRuleList) {
            if(tradeHouseRule.getTradeIbGroupList()!=null){
                for(TradeIbGroup tradeIbGroup:tradeHouseRule.getTradeIbGroupList()){
                    tradeHouseRule.getUserOtherIbGroupList().remove(tradeIbGroup);
                }
            }
        }

        return tradeHouseRuleList;
    }

    @Override
    public List<TradeHouseRule> select4IbInsert(int userId) throws Exception {
        BoUser loginUser = boUserDao.select(userId);
        List<TradeHouseRule> tradeHouseRuleList = null;
        if(loginUser.getHouseId()!=null){
            tradeHouseRuleList = tradeHouseRuleDao.select(loginUser.getHouseId());
        } else{
            tradeHouseRuleList = tradeHouseRuleDao.select(loginUser.getLoginId());
        }
        if(tradeHouseRuleList.size()==0){
            throw new Exception(ErrorMsg.TRADE_HOUSE_RULE_NOT_FOUND);
        }
        List<TradeIbGroup> tradeIbGroupList = tradeIbGroupDao.select(userId);//拿到此user的所有group了,要依houseRule的category去分,但不知TradeIbGroup的category是什麼,只有group_id
        List<TradeGroup> tradeGroupList = tradeGroupDao.select();//先拿出所有tradeGroup

        Map<String, TradeHouseRule> tradeHouseRuleMap = getTradeHouseRuleMap(tradeHouseRuleList);
        Map<Integer, TradeGroup> tradeGroupMap = getTradeGroupMap(tradeGroupList);
        String key = null;

        TradeHouseRule rule = null;
        TradeGroup group = null;
        List<TradeIbGroup> ibGroupList = null;
        List<TradeGroup> ruleTradeGroupList = null;
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
            ruleTradeGroupList = rule.getTradeGroupList();
            if(ruleTradeGroupList==null){
                ruleTradeGroupList = new ArrayList<TradeGroup>();
                rule.setTradeGroupList(ruleTradeGroupList);
            }
            ruleTradeGroupList.add(group);
        }
        return tradeHouseRuleList;
    }

    @Override
    public List<TradeHouseRule> select4IbUpdate(int userId,int parentIbUserId) throws Exception {
        BoUser loginUser = boUserDao.select(userId);
        List<TradeHouseRule> tradeHouseRuleList = null;
        if(loginUser.getHouseId()!=null){
            tradeHouseRuleList = tradeHouseRuleDao.select(loginUser.getHouseId());
        } else{
            tradeHouseRuleList = tradeHouseRuleDao.select(loginUser.getLoginId());
        }
        if(tradeHouseRuleList.size()==0){
            throw new Exception(ErrorMsg.TRADE_HOUSE_RULE_NOT_FOUND);
        }
        List<TradeIbGroup> parentIbTradeIbGroupList = tradeIbGroupDao.select(parentIbUserId);
        List<TradeIbGroup> tradeIbGroupList = tradeIbGroupDao.select(userId);//拿到此user的所有group了,要依houseRule的category去分,但不知TradeIbGroup的category是什麼,只有group_id
        List<TradeGroup> tradeGroupList = tradeGroupDao.select();//先拿出所有tradeGroup

        Map<String, TradeHouseRule> tradeHouseRuleMap = getTradeHouseRuleMap(tradeHouseRuleList);
        Map<Integer, TradeGroup> tradeGroupMap = getTradeGroupMap(tradeGroupList);
        String key = null;

        TradeHouseRule rule = null;
        TradeGroup group = null;
        List<TradeIbGroup> ibGroupList = null;
        List<TradeGroup> ruleTradeGroupList = null;
        //UI要顯示的可選group列表是來自parentIbGroup
        for (TradeIbGroup tradeIbGroup : parentIbTradeIbGroupList) {
            group = tradeGroupMap.get(tradeIbGroup.getGroupId());
            key = group.getCategory() + group.getExchangeId();
            rule = tradeHouseRuleMap.get(key);
            ruleTradeGroupList = rule.getTradeGroupList();
            if(ruleTradeGroupList==null){
                ruleTradeGroupList = new ArrayList<TradeGroup>();
                rule.setTradeGroupList(ruleTradeGroupList);
            }
            ruleTradeGroupList.add(group);
        }
        //此ib已選的group
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

    private Map<String, TradeHouseRule> getTradeHouseRuleMap(List<TradeHouseRule> tradeHouseRuleList)throws Exception{
        Map<String, TradeHouseRule> tradeHouseRuleMap = new HashMap<String, TradeHouseRule>();
        String key = null;

        for (TradeHouseRule tradeHouseRule : tradeHouseRuleList) {
            key = tradeHouseRule.getCategory() + tradeHouseRule.getExchangeId();
            tradeHouseRuleMap.put(key, tradeHouseRule);
        }
        return tradeHouseRuleMap;
    }

    private Map<Integer, TradeGroup> getTradeGroupMap(List<TradeGroup> tradeGroupList) throws Exception{
        Map<Integer, TradeGroup> tradeGroupMap = new HashMap<Integer, TradeGroup>();
        for (TradeGroup tradeGroup : tradeGroupList) {
            tradeGroupMap.put(tradeGroup.getGroupId(), tradeGroup);
        }
        return tradeGroupMap;
    }

    @Override
    public void insert(TradeHouseRule object, int userId) throws Exception {
        Connection conn = null;
        try {

            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            tradeHouseRuleDao.insert(conn, object);
            StringBuffer groupIds = new StringBuffer();
            if(object.getUserOtherIbGroupList()!=null &&object.getUserOtherIbGroupList().size()>0){
                for (TradeIbGroup tradeIbGroup : object.getUserOtherIbGroupList()) {
                    SqlTool.append(groupIds, String.valueOf(tradeIbGroup.getGroupId()));
                }
            }
            if (object.getTradeIbGroupList() != null && object.getTradeIbGroupList().size() > 0) {
                for (TradeIbGroup tradeIbGroup : object.getTradeIbGroupList()) {
                    SqlTool.append(groupIds, String.valueOf(tradeIbGroup.getGroupId()));
                }
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
            StringBuffer groupIds = new StringBuffer();
            if(object.getUserOtherIbGroupList()!=null &&object.getUserOtherIbGroupList().size()>0){
                for (TradeIbGroup tradeIbGroup : object.getUserOtherIbGroupList()) {
                    SqlTool.append(groupIds, String.valueOf(tradeIbGroup.getGroupId()));
                }
            }
            tradeIbGroupDao.update(conn, userId, groupIds.toString());
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
            if(object.getUserOtherIbGroupList()!=null &&object.getUserOtherIbGroupList().size()>0){
                for (TradeIbGroup tradeIbGroup : object.getUserOtherIbGroupList()) {
                    SqlTool.append(groupIds, String.valueOf(tradeIbGroup.getGroupId()));
                }
            }
            if (object.getTradeIbGroupList() != null && object.getTradeIbGroupList().size() > 0) {
                for (TradeIbGroup tradeIbGroup : object.getTradeIbGroupList()) {
                    SqlTool.append(groupIds, String.valueOf(tradeIbGroup.getGroupId()));
                }
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
