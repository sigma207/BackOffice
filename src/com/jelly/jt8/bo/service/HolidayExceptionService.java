package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.*;

import java.util.List;

/**
 * Created by user on 2015/8/12.
 */
public interface HolidayExceptionService {
    public List<SymbolHoliday> selectHoliday(SystemMainSymbol mainSymbol) throws Exception;
//    public List<Holiday> selectHoliday(MainSymbol mainSymbol) throws Exception;
    public void insertHoliday(List<SymbolHoliday> holidayList) throws Exception;
    public void updateHoliday(SymbolHoliday holiday) throws Exception;
    public void deleteHoliday(SymbolHoliday holiday) throws Exception;
    public List<SymbolHolidayException> selectHolidayException(SystemMainSymbol mainSymbol) throws Exception;
    public void insertHolidayException(List<SymbolHolidayException> holidayExceptionList) throws Exception;
    public void updateHolidayException(SymbolHolidayException holidayException) throws Exception;
    public void deleteHolidayException(SymbolHolidayException holidayException) throws Exception;
}
