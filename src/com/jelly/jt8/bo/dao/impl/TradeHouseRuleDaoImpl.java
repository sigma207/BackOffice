package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.TradeHouseRuleDao;
import com.jelly.jt8.bo.model.Organization;
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
@Repository("TradeHouseRuleDao")
public class TradeHouseRuleDaoImpl extends BaseDao implements TradeHouseRuleDao {
        public TradeHouseRuleDaoImpl() {
                super(TradeHouseRule.class);
        }

        private final static String QUERY = "SELECT [house_id]\n" +
            "      ,[exchange_id]\n" +
            "      ,[house_name]\n" +
            "      ,[scale]\n" +
            "      ,[initial_margin]\n" +
            "      ,[maintain_margin]\n" +
            "      ,[open_commission]\n" +
            "      ,[close_commission]\n" +
            "      ,[overnight_charge]\n" +
            "      ,[tax]\n" +
            "      ,[fee1]\n" +
            "      ,[fee2]\n" +
            "      ,[fee3]\n" +
            "      ,[fee4]\n" +
            "      ,[fee5]\n" +
            "      ,[open_time1]\n" +
            "      ,[close_time1]\n" +
            "      ,[reset_time1]\n" +
            "      ,[open_time2]\n" +
            "      ,[close_time2]\n" +
            "      ,[reset_time2]\n" +
            "      ,[rollover_time]\n" +
            "      ,[settlement_time]\n" +
            "      ,[new_order_time]\n" +
            "      ,[last_order_time]\n" +
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
            "      ,[over_withdrawal_credit_percentage]\n" +
            "      ,[over_withdrawal_credit_percentage_charge]\n" +
            "      ,[over_withdrawal_credit_limit]\n" +
            "      ,[over_withdrawal_credit_limit_charge]\n" +
            "      ,[liquidation_rate]\n" +
            "      ,[margin_call_rate] FROM trade_house_rule ";

    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Override
    public List<TradeHouseRule> select() throws Exception {
        List<TradeHouseRule> list =  new LinkedList<TradeHouseRule>();
        execute(jt8Ds.getConnection(),QUERY,list,TradeHouseRule.class);
        return list;
    }
}
