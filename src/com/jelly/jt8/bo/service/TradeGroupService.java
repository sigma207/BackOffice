package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.TradeGroup;

import java.util.List;

/**
 * Created by user on 2015/8/26.
 */
public interface TradeGroupService {
    List<TradeGroup> select() throws Exception;
    List<TradeGroup> select(String category) throws Exception;
    void insert(TradeGroup object) throws Exception;
    void update(TradeGroup object) throws Exception;
    void delete(TradeGroup object) throws Exception;
}
