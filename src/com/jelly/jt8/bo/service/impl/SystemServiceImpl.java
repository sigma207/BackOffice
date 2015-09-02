package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.SystemCategoryDao;
import com.jelly.jt8.bo.dao.SystemMainSymbolDao;
import com.jelly.jt8.bo.model.Exchange;
import com.jelly.jt8.bo.model.SystemCategory;
import com.jelly.jt8.bo.model.SystemMainSymbol;
import com.jelly.jt8.bo.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by user on 2015/9/2.
 */
@Service("systemService")
public class SystemServiceImpl implements SystemService {
    @Autowired
    @Qualifier("SystemCategoryDao")
    private SystemCategoryDao systemCategoryDao;

    @Autowired
    @Qualifier("SystemMainSymbolDao")
    private SystemMainSymbolDao systemMainSymbolDao;

    @Override
    public List<Exchange> selectExchange() throws Exception {
        List<SystemMainSymbol> mainSymbolList = systemMainSymbolDao.select();
        List<Exchange> exchangeList = new ArrayList<Exchange>();
        Map<String,Exchange> map = new HashMap<String,Exchange>();
        Exchange exchange = null;
        String exchangeId = null;
        for (SystemMainSymbol mainSymbol : mainSymbolList) {
            exchangeId = mainSymbol.getExchangeId();
            exchange = map.get(exchangeId);
            if(!map.containsKey(exchangeId)){
                exchange = new Exchange();
                exchange.setExchangeId(exchangeId);
                exchange.setSystemMainSymbolList(new ArrayList<SystemMainSymbol>());
                map.put(exchangeId, exchange);
                exchangeList.add(exchange);
            }
            exchange.getSystemMainSymbolList().add(mainSymbol);
        }
        return exchangeList;
    }

    @Override
    public List<SystemCategory> selectSystemCategory() throws Exception {
        List<SystemCategory> list = systemCategoryDao.select();
        List<SystemCategory> systemCategoryList = new ArrayList<SystemCategory>();
        Set<String> keys = new HashSet<String>();
        for(SystemCategory systemCategory:list){
            if(!keys.contains(systemCategory.getCategory())){
                systemCategoryList.add(systemCategory);
                keys.add(systemCategory.getCategory());
            }
        }
        return systemCategoryList;
    }
}
