package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.TradeAccount;
import com.jelly.jt8.bo.model.TradeBankbook;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/9/17.
 */
public interface TradeBankbookDao {
    List<TradeBankbook> select(String accountId, String beginDate, String endDate) throws Exception;
    TradeBankbook selectLast(String accountId) throws Exception;
    List<TradeBankbook> selectLast(List<TradeAccount> tradeAccountList) throws Exception;
    void insert(Connection conn, TradeBankbook object) throws Exception;
}
