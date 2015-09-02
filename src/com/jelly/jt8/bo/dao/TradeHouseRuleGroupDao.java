package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.TradeGroup;
import com.jelly.jt8.bo.model.TradeHouseRule;
import com.jelly.jt8.bo.model.TradeHouseRuleGroup;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/9/2.
 */
public interface TradeHouseRuleGroupDao {
    List<TradeHouseRuleGroup> select() throws Exception;
    List<TradeHouseRuleGroup> select(TradeHouseRule object) throws Exception;
    List<TradeHouseRuleGroup> select(TradeGroup object) throws Exception;
    void insert(Connection conn, TradeHouseRuleGroup object) throws Exception;
    void delete(Connection conn, TradeHouseRule object) throws Exception;
}
