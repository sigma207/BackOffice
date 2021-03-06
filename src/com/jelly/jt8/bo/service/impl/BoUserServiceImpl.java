package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.BoRolePermissionDao;
import com.jelly.jt8.bo.dao.BoUserDao;
import com.jelly.jt8.bo.dao.BoUserRoleDao;
import com.jelly.jt8.bo.model.BoRolePermission;
import com.jelly.jt8.bo.model.BoUser;
import com.jelly.jt8.bo.model.BoUserRole;
import com.jelly.jt8.bo.service.BoUserService;
import com.jelly.jt8.bo.util.ErrorMsg;
import com.jelly.jt8.bo.util.Password;
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
@Service("boUserService")
public class BoUserServiceImpl implements BoUserService {

    @Autowired
    @Qualifier("BoUserDao")
    private BoUserDao boUserDao;

    @Autowired
    @Qualifier("BoUserRoleDao")
    private BoUserRoleDao boUserRoleDao;

    @Autowired
    @Qualifier("BoRolePermissionDao")
    private BoRolePermissionDao boRolePermissionDao;

    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    /**
     * 一般正常登入後台檢查用
     * 登入帳號正確後則取出該帳號的可用權限(該帳號所有角色加總)
     * @param login_id
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public BoUser login(String login_id, String password) throws Exception {
        BoUser checkUser =  boUserDao.select(login_id);
        BoUser loginUser = null;
        if(checkUser==null){
            throw new Exception(ErrorMsg.LOGIN_ACCOUNT);
        }else {
            if(Password.authenticatePassword(checkUser.getPassword(), password)){
                loginUser = checkUser;
                loginUser.setPassword(null);
                List<BoUserRole> boUserRoleList = boUserRoleDao.select(loginUser.getUserId());
                List<BoRolePermission> boRolePermissionList = boRolePermissionDao.select(boUserRoleList);
                loginUser.setBoRolePermissionList(boRolePermissionList);
            }else {
                throw new Exception(ErrorMsg.LOGIN_PASSWORD);
            }
        }
        return loginUser;
    }

    /**
     * 開發人員測試用的快速login
     * 功能同login
     * @param login_id
     * @return
     * @throws Exception
     */
    @Override
    public BoUser fastLogin(String login_id) throws Exception {
        BoUser checkUser =  boUserDao.select(login_id);
        BoUser loginUser = null;
        if(checkUser==null){
            throw new Exception(ErrorMsg.LOGIN_ACCOUNT);
        }else {
            loginUser = checkUser;
            loginUser.setPassword(null);
            List<BoUserRole> boUserRoleList = boUserRoleDao.select(loginUser.getUserId());
            if(boUserRoleList.size()>0){
                List<BoRolePermission> boRolePermissionList = boRolePermissionDao.select(boUserRoleList);
                loginUser.setBoRolePermissionList(boRolePermissionList);
            }else{
                //沒有角色.就沒有任何權限.
            }
        }
        return loginUser;
    }

    @Override
    public List<BoUser> select() throws Exception {
        return boUserDao.select();
    }

    @Override
    public List<BoUserRole> selectUserRole(int user_id) throws Exception {
        return boUserRoleDao.select(user_id);
    }

    @Override
    public List<BoUser> selectChildren(int parentUserId) throws Exception {
        return boUserDao.selectChildren(parentUserId);
    }

    @Override
    public void insert(BoUser object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            object.setPassword(Password.createPassword(object.getPassword()));
            boUserDao.insert(conn, object);
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
    public void update(BoUser object) throws Exception {

    }

    @Override
    public void delete(BoUser object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            boUserRoleDao.delete(conn, object.getUserId());
            boUserDao.delete(conn, object);
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

    /**
     * 分配BoUser的角色(先delete all再insert)
     * @param object
     * @throws Exception
     */
    @Override
    public void allocateUserRole(BoUser object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            boUserDao.update(conn, object);
            boUserRoleDao.delete(conn, object.getUserId());
            for(BoUserRole boUserRole: object.getBoUserRoleList()){
                boUserRoleDao.insert(conn,boUserRole);
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
