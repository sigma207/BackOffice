package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.Holiday;
import com.jelly.jt8.bo.model.HolidayException;
import com.jelly.jt8.bo.model.MainSymbol;
import com.jelly.jt8.bo.model.SymbolHoliday;

import java.util.List;

/**
 * Created by user on 2015/8/12.
 */
public interface HolidayExceptionService {
    public List<SymbolHoliday> selectHoliday(MainSymbol mainSymbol) throws Exception;
//    public List<Holiday> selectHoliday(MainSymbol mainSymbol) throws Exception;
    public void insertHoliday(List<SymbolHoliday> holidayList) throws Exception;
    public void updateHoliday(SymbolHoliday holiday) throws Exception;
    public void deleteHoliday(SymbolHoliday holiday) throws Exception;
    public List<HolidayException> selectHolidayException(MainSymbol mainSymbol) throws Exception;
    public void insertHolidayException(List<HolidayException> holidayExceptionList) throws Exception;
    public void updateHolidayException(HolidayException holidayException) throws Exception;
    public void deleteHolidayException(HolidayException holidayException) throws Exception;
}
