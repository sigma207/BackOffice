package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.SymbolTradableDaily;
import com.jelly.jt8.bo.model.SystemMainSymbol;

import java.util.List;

/**
 * Created by user on 2015/8/17.
 */
public interface SymbolTradableDailyService {
    List<SymbolTradableDaily> selectSymbolTradableDailyTemp() throws Exception;
    List<SymbolTradableDaily> selectSymbolTradableDailyTemp(SystemMainSymbol mainSymbol) throws Exception;
    void insertSymbolTradableDailyTemp(SystemMainSymbol mainSymbol, List<SymbolTradableDaily> list) throws Exception;
    List<SymbolTradableDaily> selectSymbolTradableDaily(SystemMainSymbol mainSymbol) throws Exception;
}
