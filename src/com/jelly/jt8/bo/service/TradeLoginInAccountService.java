package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.TradeLoginAccount;

import java.util.List;

/**
 * Created by user on 2015/9/14.
 */
public interface TradeLoginInAccountService {
    List<TradeLoginAccount> select() throws Exception;
    List<TradeLoginAccount> select(int userId) throws Exception;
    void insert(TradeLoginAccount object) throws Exception;
    void update(TradeLoginAccount object) throws Exception;
    void delete(TradeLoginAccount object) throws Exception;
}
