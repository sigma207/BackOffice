package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.BoRoleDao;
import com.jelly.jt8.bo.dao.BoUserDao;
import com.jelly.jt8.bo.dao.BoUserRoleDao;
import com.jelly.jt8.bo.dao.TradeIbGroupDao;
import com.jelly.jt8.bo.model.BoRole;
import com.jelly.jt8.bo.model.BoUser;
import com.jelly.jt8.bo.model.BoUserRole;
import com.jelly.jt8.bo.service.IbService;
import com.jelly.jt8.bo.util.ErrorMsg;
import com.jelly.jt8.bo.util.Password;
import com.jelly.jt8.bo.util.SqlTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 2015/9/7.
 */
@Service("ibService")
public class IbServiceImpl implements IbService {
    private String eSalesRoleCode = "eSales";
    @Autowired
    @Qualifier("BoUserDao")
    private BoUserDao boUserDao;

    @Autowired
    @Qualifier("BoRoleDao")
    private BoRoleDao boRoleDao;

    @Autowired
    @Qualifier("BoUserRoleDao")
    private BoUserRoleDao boUserRoleDao;

    @Autowired
    @Qualifier("TradeIbGroupDao")
    private TradeIbGroupDao tradeIbGroupDao;

    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Override
    public List<BoUser> select(int parentUserId) throws Exception {
        return boUserDao.selectChildren(parentUserId);
    }

    @Override
    public void insert(BoUser object) throws Exception {
        Connection conn = null;
        try {
            BoRole eSalesRole  = boRoleDao.select(eSalesRoleCode);
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            object.setPassword(Password.createPassword(object.getPassword()));
            boUserDao.insert(conn, object);
            BoUserRole boUserRole = new BoUserRole();
            boUserRole.setUserId(object.getUserId());
            boUserRole.setRoleId(eSalesRole.getRoleId());
            boUserRoleDao.insert(conn,boUserRole);

            StringBuffer groupIds = new StringBuffer();
            if (object.getTradeGroupIdList() != null && object.getTradeGroupIdList().size() > 0) {
                for (Integer tradeGroupId : object.getTradeGroupIdList()) {
                    SqlTool.append(groupIds, String.valueOf(tradeGroupId));
                }
            }
            tradeIbGroupDao.update(conn, object.getUserId(), groupIds.toString());
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
        Connection conn = null;
        try {
            List<BoUser> children = boUserDao.selectChildren(object.getUserId());
            if(children.size()>0){
                throw new Exception(ErrorMsg.TRADE_IB_HAS_CHILDREN);
            }
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);

            tradeIbGroupDao.update(conn, object.getUserId(), "");
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

    @Override
    public void update(BoUser object) throws Exception {
        Connection conn = null;
        try {
            //找出下層所有ib所使用的group,將gorupId存到groupIdSet裡
            Set<Integer> groupIdSet = new HashSet<Integer>();
            List<Integer> childrenGroupIdList = tradeIbGroupDao.selectChildrenGroupId(object.getUserId());
            for(Integer groupId:childrenGroupIdList){
                if (object.getTradeGroupIdList() != null && object.getTradeGroupIdList().size() > 0) {
                    if(!object.getTradeGroupIdList().contains(groupId)){
                        throw new Exception(ErrorMsg.TRADE_IB_CHILDREN_GROUP_HAS_USED);
                    }
                }
            }
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);

            StringBuffer groupIds = new StringBuffer();
            if (object.getTradeGroupIdList() != null && object.getTradeGroupIdList().size() > 0) {
                for (Integer tradeGroupId : object.getTradeGroupIdList()) {
                    SqlTool.append(groupIds, String.valueOf(tradeGroupId));
                }
            }
            tradeIbGroupDao.update(conn, object.getUserId(), groupIds.toString());
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
