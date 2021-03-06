package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.SymbolHoliday;
import com.jelly.jt8.bo.model.SystemMainSymbol;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/8/30.
 */
public interface SymbolHolidayDao {
    List<SymbolHoliday> select(SystemMainSymbol mainSymbol) throws Exception;
    void insert(Connection conn, SymbolHoliday symbolHoliday) throws Exception;
    void insert(Connection conn, List<SymbolHoliday> list) throws Exception;
    void update(Connection conn, SymbolHoliday symbolHoliday) throws Exception;
    void delete(Connection conn, SymbolHoliday symbolHoliday) throws Exception;
}
