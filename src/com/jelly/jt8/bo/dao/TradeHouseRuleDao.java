package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.TradeHouseRule;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/8/26.
 */
public interface TradeHouseRuleDao {
    List<TradeHouseRule> select() throws Exception;
    List<TradeHouseRule> select(String houseId) throws Exception;
    void insert(Connection conn,TradeHouseRule object) throws Exception;
    void update(Connection conn,TradeHouseRule object) throws Exception;
    void delete(Connection conn,TradeHouseRule object) throws Exception;
}
