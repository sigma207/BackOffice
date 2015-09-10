package com.jelly.jt8.bo.util;

/**
 * Created by user on 2015/9/10.
 */
public class Join {
    public final static String INNER = "INNER";
    private String property;
    private String joinType;
    private String alias;
    private String columnA;
    private String columnB;

    public Join() {
    }

    public Join(String property, String joinType, String alias, String columnA, String columnB) {
        this.property = property;
        this.joinType = joinType;
        this.alias = alias;
        this.columnA = columnA;
        this.columnB = columnB;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getColumnA() {
        return columnA;
    }

    public void setColumnA(String columnA) {
        this.columnA = columnA;
    }

    public String getColumnB() {
        return columnB;
    }

    public void setColumnB(String columnB) {
        this.columnB = columnB;
    }
}
