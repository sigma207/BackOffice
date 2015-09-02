package com.jelly.jt8.bo.model;

import javax.persistence.*;

/**
 * Created by user on 2015/9/2.
 */
@Entity
@Table(name = "system_category", schema = "dbo", catalog = "jt8")
@IdClass(SystemCategoryPK.class)
public class SystemCategory {
    private String category;
    private String exchangeId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemCategory that = (SystemCategory) o;

        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (exchangeId != null ? !exchangeId.equals(that.exchangeId) : that.exchangeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (exchangeId != null ? exchangeId.hashCode() : 0);
        return result;
    }
}
