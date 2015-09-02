package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.SystemMainSymbolDao;
import com.jelly.jt8.bo.model.SystemMainSymbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
@Repository("SystemMainSymbolDao")
public class SystemMainSymbolDaoImpl extends BaseDao implements SystemMainSymbolDao {
    public SystemMainSymbolDaoImpl() {
        super(SystemMainSymbol.class);
    }

    @Override
    public List<SystemMainSymbol> select() throws Exception {
        List<SystemMainSymbol> list =  new LinkedList<SystemMainSymbol>();
        selectByObject(jt8Ds.getConnection(),list);
        return list;
    }
}
