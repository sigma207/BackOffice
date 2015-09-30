package com.jelly.jt8.bo.util;

import javax.persistence.Column;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2015/9/10.
 */
public class Join {
    public final static String INNER = "INNER";
    public final static String LEFT = "LEFT";
    private Class originClass;
    private Class joinClass;
    private String property;
    private String joinType;
    private String alias;
    private String columnA;
    private String columnB;
    private Map<String,PropertyDescriptor> columnKeyMap;
    private Map<String, Column> idColumnMap;
    private Map<String, PropertyDescriptor> transientMap;
    public Join() {
    }

    public Join(Class originClass, String property, String joinType, String alias, String columnA, String columnB) throws Exception{
        this.originClass = originClass;
        this.property = property;
        this.joinType = joinType;
        this.alias = alias;
        this.columnA = columnA;
        this.columnB = columnB;
        columnKeyMap = new HashMap<String, PropertyDescriptor>();
        idColumnMap = new HashMap<String, Column>();
        transientMap = new HashMap<String, PropertyDescriptor>();
        BeanInfo info = Introspector.getBeanInfo(originClass);
        PropertyDescriptor[] props = info.getPropertyDescriptors();
        for (PropertyDescriptor pd : props){
            if(pd.getName().equalsIgnoreCase(property)){
                joinClass = pd.getPropertyType();
                break;
            }
        }
        if(joinClass!=null){
            DBUtils.loadTable(joinClass, columnKeyMap, null, idColumnMap,transientMap);
        }
    }

    public Class getOriginClass() {
        return originClass;
    }

    public void setOriginClass(Class originClass) {
        this.originClass = originClass;
    }

    public Class getJoinClass() {
        return joinClass;
    }

    public void setJoinClass(Class joinClass) {
        this.joinClass = joinClass;
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

    public Map<String, PropertyDescriptor> getColumnKeyMap() {
        return columnKeyMap;
    }

    public void setColumnKeyMap(Map<String, PropertyDescriptor> columnKeyMap) {
        this.columnKeyMap = columnKeyMap;
    }

    public Map<String, Column> getIdColumnMap() {
        return idColumnMap;
    }

    public void setIdColumnMap(Map<String, Column> idColumnMap) {
        this.idColumnMap = idColumnMap;
    }

    public Map<String, PropertyDescriptor> getTransientMap() {
        return transientMap;
    }

    public void setTransientMap(Map<String, PropertyDescriptor> transientMap) {
        this.transientMap = transientMap;
    }
}
