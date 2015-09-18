package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.TradeBankbook;

import java.util.List;

/**
 * Created by user on 2015/9/17.
 */
public interface TradeBankbookDao {
    List<TradeBankbook> select(String accountId) throws Exception;
    void insert(TradeBankbook object) throws Exception;
}
