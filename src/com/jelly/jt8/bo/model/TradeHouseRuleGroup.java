package com.jelly.jt8.bo.model;

import javax.persistence.*;

/**
 * Created by user on 2015/9/2.
 */
@Entity
@Table(name = "trade_house_rule_group", schema = "dbo", catalog = "jt8")
@IdClass(TradeHouseRuleGroupPK.class)
public class TradeHouseRuleGroup {
    private String houseId;
    private String category;
    private String exchangeId;
    private int groupId;

    @Id
    @Column(name = "house_id")
    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    @Id
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Id
    @Column(name = "exchange_id")
    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    @Id
    @Column(name = "group_id")
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeHouseRuleGroup that = (TradeHouseRuleGroup) o;

        if (groupId != that.groupId) return false;
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
        result = 31 * result + groupId;
        return result;
    }
}
