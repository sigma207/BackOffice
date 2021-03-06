package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.TradeHouseRule;

import java.util.List;

/**
 * Created by user on 2015/8/26.
 */
public interface TradeHouseRuleService {
    List<TradeHouseRule> select() throws Exception;
    List<TradeHouseRule> select4HouseRule(int userId) throws Exception;
    List<TradeHouseRule> select4IbInsert(int userId) throws Exception;
    List<TradeHouseRule> select4IbUpdate(int userId,int parentIbUserId) throws Exception;
    void insert(TradeHouseRule object,int userId) throws Exception;
    void delete(TradeHouseRule object,int userId) throws Exception;
    void update(TradeHouseRule object,int userId) throws Exception;
}
