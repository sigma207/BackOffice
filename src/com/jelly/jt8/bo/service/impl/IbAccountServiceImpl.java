package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.*;
import com.jelly.jt8.bo.model.*;
import com.jelly.jt8.bo.service.IbAccountService;
import com.jelly.jt8.bo.util.ErrorMsg;
import com.jelly.jt8.bo.util.Password;
import com.jelly.jt8.bo.util.SqlTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015/9/10.
 */
@Service("ibAccountService")
public class IbAccountServiceImpl implements IbAccountService {
    private final static String IB_ROOT_LOGIN_ID = "ibRoot";
    private final static String E_SALES_ROLE_CODE = "eSales";
    private final static String IB_ROLE_CODE = "ib";
    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Autowired
    @Qualifier("BoUserDao")
    private BoUserDao boUserDao;

    @Autowired
    @Qualifier("BoIbAccountDao")
    private BoIbAccountDao boIbAccountDao;

    @Autowired
    @Qualifier("BoRoleDao")
    private BoRoleDao boRoleDao;

    @Autowired
    @Qualifier("BoUserRoleDao")
    private BoUserRoleDao boUserRoleDao;

    @Autowired
    @Qualifier("TradeLoginAccountDao")
    private TradeLoginAccountDao tradeLoginAccountDao;

    @Override
    public BoUser selectIb(int userId) throws Exception {
        //登入的userId 可能不是代理
        BoIbAccount boIbAccount = boIbAccountDao.select(userId);
        BoUser ibUser = null;
        if(boIbAccount==null){
            ibUser = boUserDao.select(IB_ROOT_LOGIN_ID);
            boIbAccount = boIbAccountDao.select(ibUser.getUserId());
        } else {
            ibUser = boUserDao.select(userId);
        }
        ibUser.setBoIbAccount(boIbAccount);
        return ibUser;
    }

    @Override
    public List<BoUser> selectIbChildren(int userId) throws Exception {
        List<BoUser> children = boUserDao.selectIbAccountChildren(userId);//BoUser已經設定好boIbAccount
        return children;
    }

    @Override
    public void insert(BoUser object) throws Exception {
        BoIbAccount boIbAccount = object.getBoIbAccount();
        Connection conn = null;
        try {
            List<BoRole> insertBoRoleList = new ArrayList<BoRole>();
            if(boIbAccount.getLevelNo()==1){//一級代理給予電銷角色
                insertBoRoleList.add(boRoleDao.select(E_SALES_ROLE_CODE));
            }
            insertBoRoleList.add(boRoleDao.select(IB_ROLE_CODE));//給予代理角色

            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            object.setPassword(Password.createPassword(object.getPassword()));
            boUserDao.insert(conn, object);

            for(BoRole boRole:insertBoRoleList){//新增bo_user的角色
                BoUserRole boUserRole = new BoUserRole();
                boUserRole.setUserId(object.getUserId());
                boUserRole.setRoleId(boRole.getRoleId());
                boUserRoleDao.insert(conn,boUserRole);
            }
            //新增bo_ib_account
            boIbAccount.setIbUserId(object.getUserId());
            boIbAccountDao.insert(conn,boIbAccount);

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
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }

    }

    @Override
    public void delete(BoUser object) throws Exception {
        BoIbAccount boIbAccount = object.getBoIbAccount();
        Connection conn = null;
        try {
            List<BoIbAccount> children = boIbAccountDao.selectChildren(boIbAccount.getIbUserId());
            if(children.size()>0){
                throw new Exception(ErrorMsg.TRADE_IB_HAS_CHILDREN);
            }
            List<TradeLoginAccount> tradeLoginAccountList = tradeLoginAccountDao.select(boIbAccount.getIbUserId());
            if(tradeLoginAccountList.size()>0){
                throw new Exception(ErrorMsg.TRADE_IB_HAS_LOGIN_ACCOUNT);
            }

            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            boIbAccountDao.delete(conn, boIbAccount);//刪除代理關係
            boUserRoleDao.delete(conn, object.getUserId());//刪除角色
            boUserDao.delete(conn, object);//刪除用戶
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
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(BoUser object) throws Exception {
        BoIbAccount boIbAccount = object.getBoIbAccount();
        Connection conn = null;
        try {

            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            boIbAccountDao.update(conn, boIbAccount);
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
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }
}
