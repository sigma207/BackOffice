package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.Exchange;
import com.jelly.jt8.bo.model.SystemMainSymbol;

import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
public interface SystemMainSymbolService {
    List<Exchange> selectExchange() throws Exception;
}
