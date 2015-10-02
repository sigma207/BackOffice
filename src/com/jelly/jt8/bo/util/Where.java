package com.jelly.jt8.bo.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015/9/25.
 */

/**
 * 用來產生where的sql語法(condition數量為動態時)
 */
public class Where {
    private StringBuffer sql;
    List<String> values;

    public Where() {
        sql = new StringBuffer();
        values = new ArrayList<String>();
    }

    public StringBuffer getSql() {
        return sql;
    }

    public void setSql(StringBuffer sql) {
        this.sql = sql;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
