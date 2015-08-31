package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.*;
import com.jelly.jt8.bo.model.*;
import com.jelly.jt8.bo.service.HolidayExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by user on 2015/8/12.
 */
@Service("holidayExceptionService")
public class HolidayExceptionServiceImpl implements HolidayExceptionService {
    @Autowired
    @Qualifier("SymbolHolidayDao")
    private SymbolHolidayDao holidayDao;

    @Autowired
    @Qualifier("SymbolHolidayExceptionDao")
    private SymbolHolidayExceptionDao holidayExceptionDao;

    @Autowired
    @Qualifier("TransDateDao")
    private TransDateDao transDateDao;

    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Override
    public List<SymbolHoliday> selectHoliday(SystemMainSymbol mainSymbol) throws Exception {
        return holidayDao.select(mainSymbol);
    }

    @Override
    public void insertHoliday(List<SymbolHoliday> holidayList) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            Map<String,SymbolHoliday> map = new HashMap<String,SymbolHoliday>();
            holidayDao.insert(conn, holidayList);
            for (SymbolHoliday holiday : holidayList) {
                if(!map.containsKey(holiday.getExchangeId()+ holiday.getMainSymbolId())){
                    map.put(holiday.getExchangeId()+ holiday.getMainSymbolId(),holiday);
                }
            }
            Set<String> keys =map.keySet();
            for (String key : keys) {
                transDateDao.generate(conn,  map.get(key).getExchangeId(),  map.get(key).getMainSymbolId());
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

    @Override
    public void updateHoliday(SymbolHoliday holiday) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            holidayDao.update(conn, holiday);
            transDateDao.generate(conn, holiday.getExchangeId(), holiday.getMainSymbolId());
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
    public void deleteHoliday(SymbolHoliday holiday) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            holidayDao.delete(conn, holiday);
            transDateDao.generate(conn, holiday.getExchangeId(), holiday.getMainSymbolId());
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
    public List<SymbolHolidayException> selectHolidayException(SystemMainSymbol mainSymbol) throws Exception {
        return holidayExceptionDao.select(mainSymbol);
    }

    @Override
    public void insertHolidayException(List<SymbolHolidayException> holidayExceptionList) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            Map<String,SymbolHolidayException> map = new HashMap<String,SymbolHolidayException>();
            holidayExceptionDao.insert(conn, holidayExceptionList);
            for (SymbolHolidayException holidayException : holidayExceptionList) {
                if(!map.containsKey(holidayException.getExchangeId()+ holidayException.getMainSymbolId())){
                    map.put(holidayException.getExchangeId()+ holidayException.getMainSymbolId(),holidayException);
                }
            }
            Set<String> keys =map.keySet();
            for (String key : keys) {
                transDateDao.generate(conn,  map.get(key).getExchangeId(),  map.get(key).getMainSymbolId());
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

    @Override
    public void updateHolidayException(SymbolHolidayException holidayException) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            holidayExceptionDao.update(conn, holidayException);
            transDateDao.generate(conn, holidayException.getExchangeId(), holidayException.getMainSymbolId());
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
    public void deleteHolidayException(SymbolHolidayException holidayException) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            holidayExceptionDao.delete(conn, holidayException);
            transDateDao.generate(conn, holidayException.getExchangeId(), holidayException.getMainSymbolId());
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
