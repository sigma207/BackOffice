package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.TradeAccountGroup;

import java.util.List;

/**
 * Created by user on 2015/8/26.
 */
public interface TradeAccountGroupService {
    List<TradeAccountGroup> select() throws Exception;
}
