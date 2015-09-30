package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.TradeIpsTrans;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/9/24.
 */
public interface TradeIpsTransDao {
    List<TradeIpsTrans> select(String accountId, String boStatus, String ipsStatus, String beginDate, String endDate) throws Exception;
    void pass(Connection conn, TradeIpsTrans object) throws Exception;
    void reject(Connection conn, TradeIpsTrans object) throws Exception;
}
