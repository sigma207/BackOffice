package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.TradeHouseRule;

import java.util.List;

/**
 * Created by user on 2015/8/26.
 */
public interface TradeHouseRuleService {
    List<TradeHouseRule> select() throws Exception;
    List<TradeHouseRule> select(String houseId) throws Exception;
    void insert(TradeHouseRule object) throws Exception;
    void delete(TradeHouseRule object) throws Exception;
    void update(TradeHouseRule object) throws Exception;
}
