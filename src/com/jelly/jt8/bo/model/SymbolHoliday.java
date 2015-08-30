package com.jelly.jt8.bo.model;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by user on 2015/8/30.
 */
@Entity
@Table(name = "symbol_holiday", schema = "dbo", catalog = "jt8")
public class SymbolHoliday extends BaseModel{
    private int holidayId;
    private String exchangeId;
    private String mainSymbolId;
    private String beginDate;
    private String endDate;
    private String updateTime;
    private String memo;
    private byte[] rv;

    @Id
    @Column(name = "holiday_id" , insertable = false)
    public int getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(int holidayId) {
        this.holidayId = holidayId;
    }

    @Basic
    @Column(name = "exchange_id")
    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    @Basic
    @Column(name = "main_symbol_id")
    public String getMainSymbolId() {
        return mainSymbolId;
    }

    public void setMainSymbolId(String mainSymbolId) {
        this.mainSymbolId = mainSymbolId;
    }

    @Basic
    @Column(name = "begin_date")
    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    @Basic
    @Column(name = "end_date")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "update_time")
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "memo")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Basic
    @Column(name = "rv",insertable = false, updatable = false)
    public byte[] getRv() {
        return rv;
    }

    public void setRv(byte[] rv) {
        this.rv = rv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SymbolHoliday that = (SymbolHoliday) o;

        if (holidayId != that.holidayId) return false;
        if (exchangeId != null ? !exchangeId.equals(that.exchangeId) : that.exchangeId != null) return false;
        if (mainSymbolId != null ? !mainSymbolId.equals(that.mainSymbolId) : that.mainSymbolId != null) return false;
        if (beginDate != null ? !beginDate.equals(that.beginDate) : that.beginDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;
        if (!Arrays.equals(rv, that.rv)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = holidayId;
        result = 31 * result + (exchangeId != null ? exchangeId.hashCode() : 0);
        result = 31 * result + (mainSymbolId != null ? mainSymbolId.hashCode() : 0);
        result = 31 * result + (beginDate != null ? beginDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (rv != null ? Arrays.hashCode(rv) : 0);
        return result;
    }
}
