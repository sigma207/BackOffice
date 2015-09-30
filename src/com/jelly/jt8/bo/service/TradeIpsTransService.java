package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.TradeIpsTrans;

import java.util.List;

/**
 * Created by user on 2015/9/24.
 */
public interface TradeIpsTransService {
    List<TradeIpsTrans> select(String accountId, String boStatus, String ipsStatus, String beginDate, String endDate) throws Exception;

    void pass(TradeIpsTrans object) throws Exception;

    void reject(TradeIpsTrans object) throws Exception;
}
