package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.BoIbAccountDao;
import com.jelly.jt8.bo.model.BoIbAccount;
import com.jelly.jt8.bo.model.BoUser;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/9/9.
 */
@Repository("BoIbAccountDao")
public class BoIbAccountDaoImpl extends BaseDao implements BoIbAccountDao {
    private final static String WHERE_IB_USER_ID = " WHERE ib_user_id = ? ";
    private final static String WHERE_PARENT_IB_USER_ID = " WHERE parent_ib_user_id = ? ";
    public BoIbAccountDaoImpl() {
        super(BoIbAccount.class);
    }

    @Override
    public BoIbAccount select(int userId) throws Exception {
        List<BoIbAccount> list = new LinkedList<BoIbAccount>();
        BoIbAccount boIbAccount = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_IB_USER_ID);
            stmt.setInt(1,userId);
            rs = stmt.executeQuery();
            selectToObject(rs,list);
            if(list.size()>0){
                boIbAccount = list.get(0);
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
        return boIbAccount;
    }

    @Override
    public List<BoIbAccount> select() throws Exception {
        return null;
    }

    @Override
    public List<BoIbAccount> selectChildren(int userId) throws Exception {
        List<BoIbAccount> list = new LinkedList<BoIbAccount>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_PARENT_IB_USER_ID);
            stmt.setInt(1, userId);
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
    public void insert(Connection conn,BoIbAccount object) throws Exception {
        insertByObject(conn, object);
    }

    @Override
    public void delete(Connection conn,BoIbAccount object) throws Exception {
        deleteByObject(conn,object);
    }

    @Override
    public void update(Connection conn, BoIbAccount object) throws Exception {
        updateByObject(conn,object);
    }
}
