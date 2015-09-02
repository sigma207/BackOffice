package com.jelly.jt8.bo.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by user on 2015/9/2.
 */
public class SystemCategoryPK implements Serializable {
    private String category;
    private String exchangeId;

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

        SystemCategoryPK that = (SystemCategoryPK) o;

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
