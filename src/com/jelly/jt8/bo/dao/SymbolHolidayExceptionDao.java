package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.SymbolHolidayException;
import com.jelly.jt8.bo.model.SystemMainSymbol;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
public interface SymbolHolidayExceptionDao {
    List<SymbolHolidayException> select(SystemMainSymbol mainSymbol) throws Exception;
    void insert(Connection conn, List<SymbolHolidayException> list) throws Exception;
    void update(Connection conn, SymbolHolidayException symbolHolidayException) throws Exception;
    void delete(Connection conn, SymbolHolidayException symbolHolidayException) throws Exception;
}
