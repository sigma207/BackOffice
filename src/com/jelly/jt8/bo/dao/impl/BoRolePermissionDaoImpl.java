package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.BoRolePermissionDao;
import com.jelly.jt8.bo.model.BoRolePermission;
import com.jelly.jt8.bo.model.BoUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
@Repository("BoRolePermissionDao")
public class BoRolePermissionDaoImpl extends BaseDao implements BoRolePermissionDao {
    private final static String WHERE_ROLE_ID = " WHERE role_id = ? ";
    private final static String GROUP_BY_PERMISSION_ID = " GROUP BY permission_id ";

    public BoRolePermissionDaoImpl() {
        super(BoRolePermission.class);
    }

    @Override
    public List<BoRolePermission> select(int roleId) throws Exception {
        List<BoRolePermission> list = new LinkedList<BoRolePermission>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_ROLE_ID);
            stmt.setInt(1, roleId);
            rs = stmt.executeQuery();
            selectToObject(rs, list);
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
    public List<BoRolePermission> select(List<BoUserRole> boUserRoleList) throws Exception {
        List<BoRolePermission> list = new LinkedList<BoRolePermission>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            String where = "";
            where = whereInSQL(where, "role_id", boUserRoleList.size());
            List<String> groupByList = new ArrayList<String>();
            groupByList.add("permission_id");
            stmt = conn.prepareStatement(selectSQL(groupByList) + where + groupBySQL(groupByList));
            int index = 1;
            for (BoUserRole boUserRole : boUserRoleList) {
                stmt.setInt(index++, boUserRole.getRoleId());
            }

            rs = stmt.executeQuery();
            selectToObject(rs, list);
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
    public void insert(Connection conn, BoRolePermission object) throws Exception {
        insertByObject(conn, object);
    }

    @Override
    public void delete(Connection conn, int roleId) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(deleteTable() + WHERE_ROLE_ID);

            stmt.setInt(1, roleId);
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
