package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.BoUserDao;
import com.jelly.jt8.bo.dao.BoUserRoleDao;
import com.jelly.jt8.bo.model.BoUserRole;
import com.jelly.jt8.bo.model.SymbolHoliday;
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
@Repository("BoUserRoleDao")
public class BoUserRoleDaoImpl extends BaseDao implements BoUserRoleDao {
    private final static String WHERE_USER_ID = " WHERE user_id = ? ";
    public BoUserRoleDaoImpl() {
        super(BoUserRole.class);
    }

    @Override
    public List<BoUserRole> select(int user_id) throws Exception {
        List<BoUserRole> list =  new LinkedList<BoUserRole>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_USER_ID);
            stmt.setInt(1, user_id);
            rs = stmt.executeQuery();
            selectToObject(rs,list);
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
        return list;
    }

    @Override
    public void insert(Connection conn, BoUserRole object) throws Exception {
        insertByObject(conn,object);
    }

    @Override
    public void delete(Connection conn, int user_id) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(deleteTable()+WHERE_USER_ID);

            stmt.setInt(1, user_id);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
