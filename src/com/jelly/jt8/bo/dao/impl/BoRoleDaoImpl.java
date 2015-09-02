package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.BoRoleDao;
import com.jelly.jt8.bo.model.BoRole;
import com.jelly.jt8.bo.model.BoUser;
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
@Repository("BoRoleDao")
public class BoRoleDaoImpl extends BaseDao implements BoRoleDao {
    public BoRoleDaoImpl() {
        super(BoRole.class);
    }

    @Override
    public List<BoRole> select() throws Exception {
        List<BoRole> list = new LinkedList<BoRole>();
        selectByObject(jt8Ds.getConnection(), list);
        return list;
    }

    @Override
    public void insert(Connection conn, BoRole object) throws Exception {
        int lastKey = insertByObject(conn, object);
        object.setRoleId(lastKey);
    }

    @Override
    public void update(Connection conn, BoRole object) throws Exception {
        updateByObject(conn, object);
    }

    @Override
    public void delete(Connection conn, BoRole object) throws Exception {
        deleteByObject(conn, object);
    }
}
