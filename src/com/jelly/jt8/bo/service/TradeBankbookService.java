package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.TradeBankbook;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by user on 2015/9/22.
 */
public interface TradeBankbookService {
    List<TradeBankbook> select(String accountId, String beginDate, String endDate) throws Exception;
    TradeBankbook selectLast(String accountId) throws Exception;
    void insert(TradeBankbook object) throws Exception;
}
