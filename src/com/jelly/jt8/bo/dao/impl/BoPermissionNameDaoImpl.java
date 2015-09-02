package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.BoPermissionNameDao;
import com.jelly.jt8.bo.model.BoPermission;
import com.jelly.jt8.bo.model.BoPermissionName;
import com.jelly.jt8.bo.util.ErrorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
@Repository("BoPermissionNameDao")
public class BoPermissionNameDaoImpl extends BaseDao implements BoPermissionNameDao {
    private final static String WHERE_PERMISSION_ID = " WHERE permission_id = ? ";
    public BoPermissionNameDaoImpl() {
        super(BoPermissionName.class);
    }

    @Override
    public List<BoPermissionName> select() throws Exception {
        List<BoPermissionName> list =  new LinkedList<BoPermissionName>();
        selectByObject(jt8Ds.getConnection(),list);
        return list;
    }

    @Override
    public void insert(Connection conn, BoPermissionName object) throws Exception {
        insertByObject(conn,object);
    }

    @Override
    public void update(Connection conn, BoPermissionName object) throws Exception {
        updateByObject(conn, object);
    }

    @Override
    public void delete(Connection conn, int permission_id) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(deleteTable()+WHERE_PERMISSION_ID);

            stmt.setInt(1, permission_id);
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
