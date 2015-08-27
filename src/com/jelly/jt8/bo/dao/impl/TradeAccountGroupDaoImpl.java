package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.TradeAccountGroupDao;
import com.jelly.jt8.bo.model.TradeAccountGroup;
import com.jelly.jt8.bo.model.TradeHouseRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/8/26.
 */
@Repository("TradeAccountGroupDao")
public class TradeAccountGroupDaoImpl extends BaseDao implements TradeAccountGroupDao {
    private final static String QUERY = "SELECT [group_id]\n" +
            "      ,[scale]\n" +
            "      ,[initial_margin]\n" +
            "      ,[maintain_margin]\n" +
            "      ,[open_commission]\n" +
            "      ,[close_commission]\n" +
            "      ,[buy_commission]\n" +
            "      ,[sell_commission]\n" +
            "      ,[tax]\n" +
            "      ,[fee1]\n" +
            "      ,[fee2]\n" +
            "      ,[fee3]\n" +
            "      ,[fee4]\n" +
            "      ,[fee5]\n" +
            "      ,[uplimit]\n" +
            "      ,[downlimit]\n" +
            "      ,[market_uplimit]\n" +
            "      ,[market_downlimit]\n" +
            "      ,[market_updownlimit_charge]\n" +
            "      ,[special_stock_rule]\n" +
            "      ,[special_stock_charge]\n" +
            "      ,[tvol_delta_rule]\n" +
            "      ,[tvol_delta_minute]\n" +
            "      ,[tvol_delta_charge]\n" +
            "      ,[new_order_close_time_limit]\n" +
            "      ,[oi_day]\n" +
            "      ,[oi_charge]\n" +
            "      ,[oi_lots]\n" +
            "      ,[over_withdrawal_credit_percentage]\n" +
            "      ,[over_withdrawal_credit_percentage_charge]\n" +
            "      ,[over_withdrawal_credit_limit]\n" +
            "      ,[over_withdrawal_credit_limit_charge]\n" +
            "      ,[liquidation_rate]\n" +
            "      ,[margin_call_rate]\n" +
            "      ,[margin_rate]\n" +
            "      ,[max_lots] FROM trade_account_group ";

    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Override
    public List<TradeAccountGroup> select() throws Exception {
        List<TradeAccountGroup> list =  new LinkedList<TradeAccountGroup>();
        execute(jt8Ds.getConnection(),QUERY,list,TradeAccountGroup.class);
        return list;
    }
}
