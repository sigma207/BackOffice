package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.TradeHouseRule;

import java.util.List;

/**
 * Created by user on 2015/8/26.
 */
public interface TradeHouseRuleDao {
    List<TradeHouseRule> select() throws Exception;

}
