package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.*;
import com.jelly.jt8.bo.model.*;
import com.jelly.jt8.bo.service.TradeLoginInAccountService;
import com.jelly.jt8.bo.util.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2015/9/14.
 */
@Service("tradeLoginInAccountService")
public class TradeLoginInAccountServiceImpl implements TradeLoginInAccountService {
    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Autowired
    @Qualifier("TradeLoginAccountDao")
    private TradeLoginAccountDao tradeLoginAccountDao;

    @Autowired
    @Qualifier("TradeAccountDao")
    private TradeAccountDao tradeAccountDao;

    @Autowired
    @Qualifier("TradeBankbookDao")
    private TradeBankbookDao tradeBankbookDao;

    @Autowired
    @Qualifier("SystemTradeRuleDao")
    private SystemTradeRuleDao systemTradeRuleDao;

    @Override
    public List<TradeLoginAccount> select() throws Exception {
        return tradeLoginAccountDao.select();
    }

    /**
     * 每筆TradeLoginAccount包含底下的List<TradeAccount>
     * TradeAccount又包含最後一筆的TradeBankbook(顯示帳戶餘額)
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<TradeLoginAccount> select(int userId) throws Exception {
        List<TradeLoginAccount> list = tradeLoginAccountDao.select(userId);
        List<TradeAccount> tradeAccountList = tradeAccountDao.select(list);
        List<TradeBankbook> tradeBankbookList= tradeBankbookDao.selectLast(tradeAccountList);
        Map<String,TradeBankbook> tradeBankbookMap = new HashMap<String, TradeBankbook>();
        Map<String,List<TradeAccount>> tradeAccountMap = new HashMap<String, List<TradeAccount>>();
        List<TradeAccount> temp = null;
        for(TradeBankbook tradeBankbook:tradeBankbookList){
            tradeBankbookMap.put(tradeBankbook.getAccountId(),tradeBankbook);
        }
        for(TradeAccount tradeAccount:tradeAccountList){
            tradeAccount.setLastTradeBankbook(tradeBankbookMap.get(tradeAccount.getAccountId()));
            temp = tradeAccountMap.get(tradeAccount.getLoginId());
            if(temp==null){
                temp = new ArrayList<TradeAccount>();
                tradeAccountMap.put(tradeAccount.getLoginId(),temp);
            }
            temp.add(tradeAccount);
        }
        for(TradeLoginAccount tradeLoginAccount:list){
            tradeLoginAccount.setTradeAccountList(tradeAccountMap.get(tradeLoginAccount.getLoginId()));
        }

        return list;
    }

    private int getMaxAccountId() throws Exception{
        String maxAccountId = tradeAccountDao.selectMaxAccountId();
        if(maxAccountId==null){
            return 0;
        }else{
            return Integer.parseInt(maxAccountId);
        }
    }

    @Override
    public void insert(TradeLoginAccount object) throws Exception {
        Connection conn = null;
        try {
            List<SystemTradeRule> systemTradeRuleList = systemTradeRuleDao.select();
            int maxAccountId = getMaxAccountId();
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            object.setPassword(Password.createPassword(object.getPassword()));
            object.setIsActive(0);
            tradeLoginAccountDao.insert(conn, object);
            TradeAccount tradeAccount = null;
            for(SystemTradeRule systemTradeRule:systemTradeRuleList){
                maxAccountId++;
                tradeAccount = new TradeAccount();
                tradeAccount.setAccountId(String.valueOf(maxAccountId));
                tradeAccount.setLoginId(object.getLoginId());
                tradeAccount.setTradeStatus(0);
                tradeAccount.setCategory(systemTradeRule.getCategory());
                tradeAccount.setExchangeId(systemTradeRule.getExchangeId());
                tradeAccount.setGroupId(systemTradeRule.getGroupId());
                tradeAccountDao.insert(conn,tradeAccount);
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
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(TradeLoginAccount object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            object.setPassword(Password.createPassword(object.getPassword()));
            tradeLoginAccountDao.update(conn, object);

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
    public void delete(TradeLoginAccount object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            tradeAccountDao.delete(conn, object.getLoginId());
            tradeLoginAccountDao.delete(conn, object);
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
    public void updateTradeAccounts(TradeLoginAccount object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            tradeLoginAccountDao.update(conn, object);
            for(TradeAccount tradeAccount:object.getTradeAccountList()){
                tradeAccount.setTradeStatus(object.getIsActive());
                tradeAccountDao.update(conn,tradeAccount);
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
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }

    @Override
    public void updateIsActive(TradeLoginAccount object) throws Exception {
        Connection conn = null;

        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            tradeLoginAccountDao.updateIsActive(conn, object);
            tradeAccountDao.updateTradeStatus(conn, object.getLoginId(),object.getIsActive());
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
