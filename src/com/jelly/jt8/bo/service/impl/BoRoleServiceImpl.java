package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.BoRoleDao;
import com.jelly.jt8.bo.dao.BoRolePermissionDao;
import com.jelly.jt8.bo.model.BoRole;
import com.jelly.jt8.bo.model.BoRolePermission;
import com.jelly.jt8.bo.service.BoRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
@Service("boRoleService")
public class BoRoleServiceImpl implements BoRoleService {
    @Autowired
    @Qualifier("BoRoleDao")
    private BoRoleDao boRoleDao;

    @Autowired
    @Qualifier("BoRolePermissionDao")
    private BoRolePermissionDao boRolePermissionDao;

    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Override
    public List<BoRole> select() throws Exception {
        return boRoleDao.select();
    }

    @Override
    public void insert(BoRole object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            boRoleDao.insert(conn, object);
            conn.commit();
        }catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                }catch (SQLException se){
                    se.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(BoRole object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            boRolePermissionDao.delete(conn, object.getRoleId());
            boRoleDao.delete(conn, object);
            conn.commit();
        }catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                }catch (SQLException se){
                    se.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(BoRole object) throws Exception {
        Connection conn = null;

        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            boRoleDao.update(conn, object);
            conn.commit();
        }catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                }catch (SQLException se){
                    se.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<BoRolePermission> selectRolePermission(int roleId) throws Exception {
        return boRolePermissionDao.select(roleId);
    }

    @Override
    public void allocatePermission(BoRole object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            boRoleDao.update(conn, object);
            boRolePermissionDao.delete(conn, object.getRoleId());
            for(BoRolePermission rolePermission:object.getBoRolePermissionList()){
                boRolePermissionDao.insert(conn, rolePermission);
            }
            conn.commit();
        }catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                }catch (SQLException se){
                    se.printStackTrace();
                }
            }
        }
    }
}
