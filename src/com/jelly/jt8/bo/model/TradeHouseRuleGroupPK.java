package com.jelly.jt8.bo.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by user on 2015/9/2.
 */
public class TradeHouseRuleGroupPK implements Serializable {
    private String houseId;
    private String category;
    private String exchangeId;
    private int groupId;

    @Column(name = "house_id")
    @Id
    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    @Column(name = "category")
    @Id
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "exchange_id")
    @Id
    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeHouseRuleGroupPK that = (TradeHouseRuleGroupPK) o;

        if (houseId != null ? !houseId.equals(that.houseId) : that.houseId != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (exchangeId != null ? !exchangeId.equals(that.exchangeId) : that.exchangeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = houseId != null ? houseId.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (exchangeId != null ? exchangeId.hashCode() : 0);
        return result;
    }

    @Column(name = "group_id")
    @Basic
    @Id
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
