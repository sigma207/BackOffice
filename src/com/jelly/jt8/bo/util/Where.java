package com.jelly.jt8.bo.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015/9/25.
 */
public class Where {
    private int index;
    private StringBuffer sql;
    List<String> values;

    public Where() {
        index = 1;
        sql = new StringBuffer();
        values = new ArrayList<String>();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
