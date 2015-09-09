package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.SystemTradeRule;

import java.util.List;

/**
 * Created by user on 2015/9/9.
 */
public interface SystemTradeRuleService {
    List<SystemTradeRule> select() throws Exception;

    void insert(SystemTradeRule object) throws Exception;

    void update(SystemTradeRule object) throws Exception;

    void delete(SystemTradeRule object) throws Exception;

}
