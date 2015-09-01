package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.TradeGroupDao;
import com.jelly.jt8.bo.model.TradeGroup;
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
@Repository("TradeGroupDao")
public class TradeGroupDaoImpl extends BaseDao implements TradeGroupDao {
    public TradeGroupDaoImpl() {
        super(TradeGroup.class);
    }

    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Override
    public List<TradeGroup> select() throws Exception {
        List<TradeGroup> list =  new LinkedList<TradeGroup>();
        selectByObject(jt8Ds.getConnection(),list);
        return list;
    }

    @Override
    public void insert(Connection conn, TradeGroup object) throws Exception {
        int lastKey = insertByObject(conn, object);
        object.setGroupId(lastKey);
    }

    @Override
    public void update(Connection conn, TradeGroup object) throws Exception {
        updateByObject(conn, object);
    }

    @Override
    public void delete(Connection conn, TradeGroup object) throws Exception {
        deleteByObject(conn, object);
    }
}
