package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.SystemTradeRule;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/9/9.
 */
public interface SystemTradeRuleDao {
    List<SystemTradeRule> select() throws Exception;
    void insert(Connection conn, SystemTradeRule object) throws Exception;
    void update(Connection conn, SystemTradeRule object) throws Exception;
    void delete(Connection conn, SystemTradeRule object) throws Exception;
}
