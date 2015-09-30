package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.TradeBankbookDao;
import com.jelly.jt8.bo.model.TradeBankbook;
import com.jelly.jt8.bo.service.TradeBankbookService;
import com.jelly.jt8.common.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 2015/9/22.
 */
@Service("tradeBankbookService")
public class TradeBankbookServiceImpl implements TradeBankbookService {
    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Autowired
    @Qualifier("TradeBankbookDao")
    private TradeBankbookDao tradeBankbookDao;

    @Override
    public List<TradeBankbook> select(String accountId, String beginDate, String endDate) throws Exception {
        return tradeBankbookDao.select(accountId,beginDate,endDate);
    }

    @Override
    public TradeBankbook selectLast(String accountId) throws Exception {
        return tradeBankbookDao.selectLast(accountId);
    }

    @Override
    public void insert(TradeBankbook object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
//            conn.setAutoCommit(false);
            object.setTradeDate(Utils.updateDate());
            if (object.getAmount().compareTo(BigDecimal.ZERO) > 0){
                object.setBankbookType(TradeBankbook.BANKBOOK_TYPE_D);
            }else{
                object.setBankbookType(TradeBankbook.BANKBOOK_TYPE_W);
            }
            tradeBankbookDao.insert(conn,object);

//            conn.commit();
        }catch (Exception e) {
//            if (conn != null) {
//                conn.rollback();
//            }
            throw e;
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }

}
