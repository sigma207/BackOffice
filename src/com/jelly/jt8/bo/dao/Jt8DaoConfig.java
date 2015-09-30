package com.jelly.jt8.bo.dao;

/**
 * Created by user on 2015/9/24.
 */
public class Jt8DaoConfig {
    /**
     * 為了讓system_trade_rule和trade_account的資料順序一致
     */
    public final static String CATEGORY_EXCHANGE_ID_ORDER_BY = " ORDER BY category,exchange_id ";
}
