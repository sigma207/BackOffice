package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.TradeAccount;

import java.util.List;

/**
 * Created by user on 2015/9/15.
 */
public interface TradeAccountService {
    List<TradeAccount> select(String loginId) throws Exception;
    void insert(TradeAccount object) throws Exception;
    void update(TradeAccount object) throws Exception;
    void delete(TradeAccount object) throws Exception;
}
