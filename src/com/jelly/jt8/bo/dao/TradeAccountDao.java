package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.TradeAccount;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/9/15.
 */
public interface TradeAccountDao {
    boolean hasData(int groupId) throws Exception;
    List<TradeAccount> select(String loginId) throws Exception;
    String selectMaxAccountId() throws Exception;
    void insert(Connection conn, TradeAccount object) throws Exception;
    void update(Connection conn, TradeAccount object) throws Exception;
    void delete(Connection conn, TradeAccount object) throws Exception;
    void delete(Connection conn, String loginId) throws Exception;
    void updateTradeStatus(Connection conn, String loginId, int tradeStatus) throws Exception;
}
