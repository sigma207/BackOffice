package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.TradeGroup;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/8/26.
 */
public interface TradeGroupDao {
    List<TradeGroup> select() throws Exception;
    List<TradeGroup> select(int ownerId) throws Exception;
    List<TradeGroup> select(String category) throws Exception;
    List<TradeGroup> select(String category,int ownerId) throws Exception;
    void insert(Connection conn, TradeGroup object) throws Exception;
    void update(Connection conn, TradeGroup object) throws Exception;
    void updateIsActive(Connection conn, TradeGroup object) throws Exception;
    void delete(Connection conn, TradeGroup object) throws Exception;
    void updateIsActive(Connection conn, List<TradeGroup> list) throws Exception;
    void updateIsActiveOff(Connection conn, String category, String exchangeId) throws Exception;
}
