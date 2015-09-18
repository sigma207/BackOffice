package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.TradeBankbookDao;
import com.jelly.jt8.bo.model.TradeBankbook;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 2015/9/17.
 */
@Repository("TradeBankbookDao")
public class TradeBankbookDaoImpl extends BaseDao implements TradeBankbookDao {
    public TradeBankbookDaoImpl() {
        super(TradeBankbook.class);
    }

    @Override
    public List<TradeBankbook> select(String accountId) throws Exception {
        return null;
    }

    @Override
    public void insert(TradeBankbook object) throws Exception {

    }
}
