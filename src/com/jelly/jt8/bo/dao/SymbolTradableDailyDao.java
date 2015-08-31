package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.SymbolTradableDaily;
import com.jelly.jt8.bo.model.SystemMainSymbol;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/8/17.
 */
public interface SymbolTradableDailyDao {
    List<SymbolTradableDaily> selectTemp() throws Exception;
    List<SymbolTradableDaily> selectTemp(SystemMainSymbol mainSymbol) throws Exception;
    void insertTemp(Connection conn, List<SymbolTradableDaily> list) throws Exception;
    void deleteTemp(Connection conn, SystemMainSymbol mainSymbol) throws Exception;
    List<SymbolTradableDaily> select(SystemMainSymbol mainSymbol) throws Exception;
}
