package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.BoPermissionDao;
import com.jelly.jt8.bo.model.BoPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
@Repository("BoPermissionDao")
public class BoPermissionDaoImpl extends BaseDao implements BoPermissionDao {
    private final static String ORDER_BY = " ORDER BY sequence+parent_permission_id,sequence";
    public BoPermissionDaoImpl() {
        super(BoPermission.class);
    }

    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;
    @Override
    public List<BoPermission> select() throws Exception {
        List<BoPermission> list =  new LinkedList<BoPermission>();
        selectByObject(jt8Ds.getConnection(),list,selectSQL()+ORDER_BY);
        return list;
    }

    @Override
    public void insert(Connection conn, BoPermission object) throws Exception {
        int lastKey = insertByObject(conn,object);
        object.setPermissionId(lastKey);
    }

    @Override
    public void update(Connection conn, BoPermission object) throws Exception {
        updateByObject(conn, object);
    }

    @Override
    public void delete(Connection conn, BoPermission object) throws Exception {
        deleteByObject(conn, object);
    }
}
