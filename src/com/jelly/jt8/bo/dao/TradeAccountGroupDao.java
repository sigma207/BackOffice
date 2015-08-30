package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.TradeAccountGroup;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/8/26.
 */
public interface TradeAccountGroupDao {
    List<TradeAccountGroup> select() throws Exception;
    void insert(Connection conn, TradeAccountGroup tradeAccountGroup) throws Exception;
    void update(Connection conn, TradeAccountGroup tradeAccountGroup) throws Exception;
    void delete(Connection conn, TradeAccountGroup tradeAccountGroup) throws Exception;
}
