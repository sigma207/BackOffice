package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.TradeLoginAccount;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/9/14.
 */
public interface TradeLoginAccountDao {
    List<TradeLoginAccount> select() throws Exception;

    List<TradeLoginAccount> select(int userId) throws Exception;

    TradeLoginAccount select(String loginId) throws Exception;

    void insert(Connection conn, TradeLoginAccount object) throws Exception;

    void update(Connection conn, TradeLoginAccount object) throws Exception;

    void delete(Connection conn, TradeLoginAccount object) throws Exception;
}
