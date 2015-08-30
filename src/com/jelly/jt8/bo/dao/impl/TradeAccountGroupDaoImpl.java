package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.TradeAccountGroupDao;
import com.jelly.jt8.bo.model.TradeAccountGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/8/26.
 */
@Repository("TradeAccountGroupDao")
public class TradeAccountGroupDaoImpl extends BaseDao implements TradeAccountGroupDao {
    public TradeAccountGroupDaoImpl() {
        super(TradeAccountGroup.class);
    }

    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Override
    public List<TradeAccountGroup> select() throws Exception {
        List<TradeAccountGroup> list =  new LinkedList<TradeAccountGroup>();
        selectByObject(jt8Ds.getConnection(),list);
        return list;
    }

    @Override
    public void insert(Connection conn, TradeAccountGroup tradeAccountGroup) throws Exception {
        int lastKey = insertByObject(conn,tradeAccountGroup);
        tradeAccountGroup.setGroupId(lastKey);
    }

    @Override
    public void update(Connection conn, TradeAccountGroup tradeAccountGroup) throws Exception {
        updateByObject(conn, tradeAccountGroup);
    }

    @Override
    public void delete(Connection conn, TradeAccountGroup tradeAccountGroup) throws Exception {
        deleteByObject(conn, tradeAccountGroup);
    }
}
