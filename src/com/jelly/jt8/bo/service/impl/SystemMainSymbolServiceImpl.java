package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.SystemMainSymbolDao;
import com.jelly.jt8.bo.model.Exchange;
import com.jelly.jt8.bo.model.SystemMainSymbol;
import com.jelly.jt8.bo.service.SystemMainSymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2015/8/31.
 */
@Service("systemMainSymbolService")
public class SystemMainSymbolServiceImpl implements SystemMainSymbolService {
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
}
