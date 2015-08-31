package com.jelly.jt8.bo.model;

import java.util.List;

/**
 * Created by user on 2015/8/14.
 */
public class Exchange {
    private String exchangeId;
    private List<SystemMainSymbol> systemMainSymbolList;

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public List<SystemMainSymbol> getSystemMainSymbolList() {
        return systemMainSymbolList;
    }

    public void setSystemMainSymbolList(List<SystemMainSymbol> systemMainSymbolList) {
        this.systemMainSymbolList = systemMainSymbolList;
    }
}
