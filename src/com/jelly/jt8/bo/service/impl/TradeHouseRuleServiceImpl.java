package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.TradeHouseRuleDao;
import com.jelly.jt8.bo.model.TradeHouseRule;
import com.jelly.jt8.bo.service.TradeHouseRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by user on 2015/8/26.
 */
@Service("tradeHouseRuleService")
public class TradeHouseRuleServiceImpl implements TradeHouseRuleService {
    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Autowired
    @Qualifier("TradeHouseRuleDao")
    private TradeHouseRuleDao tradeHouseRuleDao;

    @Override
    public List<TradeHouseRule> select() throws Exception {
        return tradeHouseRuleDao.select();
    }

    @Override
    public List<TradeHouseRule> select(String houseId) throws Exception {
        return tradeHouseRuleDao.select(houseId);
    }
}
