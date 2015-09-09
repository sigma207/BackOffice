package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.SystemTradeRuleDao;
import com.jelly.jt8.bo.model.SystemTradeRule;
import com.jelly.jt8.bo.model.TradeHouseRule;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/9/9.
 */
@Repository("SystemTradeRuleDao")
public class SystemTradeRuleDaoImpl extends BaseDao implements SystemTradeRuleDao {
    public SystemTradeRuleDaoImpl() {
        super(SystemTradeRule.class);
    }

    @Override
    public List<SystemTradeRule> select() throws Exception {
        List<SystemTradeRule> list = new LinkedList<SystemTradeRule>();
        selectByObject(jt8Ds.getConnection(), list);
        return list;
    }

    @Override
    public void insert(Connection conn, SystemTradeRule object) throws Exception {
        insertByObject(conn, object);
    }

    @Override
    public void update(Connection conn, SystemTradeRule object) throws Exception {
        updateByObject(conn, object);
    }

    @Override
    public void delete(Connection conn, SystemTradeRule object) throws Exception {
        deleteByObject(conn, object);
    }
}
