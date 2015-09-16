package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.TradeAccountDao;
import com.jelly.jt8.bo.model.TradeAccount;
import com.jelly.jt8.bo.service.TradeAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by user on 2015/9/15.
 */
@Service("tradeAccountService")
public class TradeAccountServiceImpl implements TradeAccountService {
    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Autowired
    @Qualifier("TradeAccountDao")
    private TradeAccountDao tradeAccountDao;

    @Override
    public List<TradeAccount> select(String loginId) throws Exception {
        return tradeAccountDao.select(loginId);
    }

    @Override
    public void insert(TradeAccount object) throws Exception {

    }

    @Override
    public void update(TradeAccount object) throws Exception {

    }

    @Override
    public void delete(TradeAccount object) throws Exception {

    }
}
