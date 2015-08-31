package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.SystemMainSymbol;

import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
public interface SystemMainSymbolDao {
    List<SystemMainSymbol> select() throws Exception;
}
