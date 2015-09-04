package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.TradeIbGroup;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/9/4.
 */
public interface TradeIbGroupDao {
    List<TradeIbGroup> select(int userId) throws Exception;
    void update(Connection conn,int userId,String groupIds) throws Exception;
    void insert(int userId,List<TradeIbGroup> list) throws Exception;
    void update(int userId,List<TradeIbGroup> list) throws Exception;
    void delete(int userId) throws Exception;

}
