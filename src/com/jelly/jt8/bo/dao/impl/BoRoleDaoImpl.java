package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.BoRoleDao;
import com.jelly.jt8.bo.model.BoRole;
import com.jelly.jt8.bo.model.BoRolePermission;
import com.jelly.jt8.bo.model.BoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
@Repository("BoRoleDao")
public class BoRoleDaoImpl extends BaseDao implements BoRoleDao {
    private final static String WHERE_ROLE_CODE = " WHERE role_code = ? ";
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
    public BoRole select(String roleCode) throws Exception {
        List<BoRole> list = new LinkedList<BoRole>();
        BoRole boRole = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_ROLE_CODE);
            stmt.setString(1, roleCode);
            rs = stmt.executeQuery();
            selectToObject(rs,list);
            if(list.size()>0){
                boRole = list.get(0);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return boRole;
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
