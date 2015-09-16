package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.BoUserDao;
import com.jelly.jt8.bo.model.BaseModel;
import com.jelly.jt8.bo.model.BoIbAccount;
import com.jelly.jt8.bo.model.BoUser;
import com.jelly.jt8.bo.model.SymbolHoliday;
import com.jelly.jt8.bo.util.Join;
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
@Repository("BoUserDao")
public class BoUserDaoImpl extends BaseDao implements BoUserDao {
    private final static String WHERE_LOGIN_ID = " WHERE login_id = ? ";
    private final static String WHERE_USER_ID = " WHERE user_id = ? ";
    private final static String WHERE_PARENT_USER_ID = " WHERE parent_user_id = ? ";
    private final static String WHERE_PARENT_IB_USER_ID = " WHERE parent_ib_user_id = ? ";
    public BoUserDaoImpl() {
        super(BoUser.class);
    }

    @Override
    public BoUser select(String loginId) throws Exception {
        List<BoUser> list = new LinkedList<BoUser>();
        BoUser boUser = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_LOGIN_ID);
            stmt.setString(1,loginId);
            rs = stmt.executeQuery();
            selectToObject(rs,list);
            if(list.size()>0){
                boUser = list.get(0);
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
        return boUser;
    }

    @Override
    public BoUser select(int userId) throws Exception {
        List<BoUser> list = new LinkedList<BoUser>();
        BoUser boUser = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_USER_ID);
            stmt.setInt(1,userId);
            rs = stmt.executeQuery();
            selectToObject(rs,list);
            if(list.size()>0){
                boUser = list.get(0);
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
        return boUser;
    }

    @Override
    public List<BoUser> select() throws Exception {
        List<BoUser> list =  new LinkedList<BoUser>();
        selectByObject(jt8Ds.getConnection(),list);
        return list;
    }

    @Override
    public List<BoUser> selectChildren(int parentUserId) throws Exception {
        List<BoUser> list = new LinkedList<BoUser>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_PARENT_USER_ID);
            stmt.setInt(1, parentUserId);
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
    public List<BoUser> selectIbChildren(int parentIbUserId) throws Exception {
        List<BoUser> list = new LinkedList<BoUser>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_PARENT_IB_USER_ID);
            stmt.setInt(1, parentIbUserId);
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
    public List<BoUser> selectIbAccountChildren(int userId) throws Exception {
        List<BoUser> list = new LinkedList<BoUser>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            List<Join> joinList = new ArrayList<Join>();
            joinList.add(new Join(tableClass,"boIbAccount",Join.INNER,"bia","user_id","ib_user_id"));
            stmt = conn.prepareStatement(selectSQL("bu",joinList) + WHERE_PARENT_IB_USER_ID);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            selectToObject(rs, list,joinList);
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
    public void insert(Connection conn,BoUser object) throws Exception {
        object.setPermission(1);
        object.setConcurrent(1);
        object.setRetry(0);
        object.setMaxRetry(5);
        if(object.getParentBoUser()!=null){
            object.setParentUserId(object.getParentBoUser().getUserId());
        }
        if(object.getBoOrganization()!=null){
            object.setOrganizationId(object.getBoOrganization().getOrganizationId());
        }
        int lastKey = insertByObject(conn,object);
        object.setUserId(lastKey);
    }

    @Override
    public void update(Connection conn,BoUser object) throws Exception {
        updateByObject(conn, object);
    }

    @Override
    public void delete(Connection conn,BoUser object) throws Exception {
        deleteByObject(conn, object);
    }
}
