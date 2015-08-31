package com.jelly.jt8.bo.model;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by user on 2015/8/31.
 */
@Entity
@Table(name = "symbol_holiday_exception", schema = "dbo", catalog = "jt8")
public class SymbolHolidayException extends BaseModel{
    private int holidayId;
    private String exchangeId;
    private String mainSymbolId;
    private String calendar;
    private String updateTime;
    private String memo;
    private byte[] rv;

    @Id
    @Column(name = "holiday_id", insertable = false)
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
    @Column(name = "calendar")
    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
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
    @Column(name = "rv")
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

        SymbolHolidayException that = (SymbolHolidayException) o;

        if (holidayId != that.holidayId) return false;
        if (exchangeId != null ? !exchangeId.equals(that.exchangeId) : that.exchangeId != null) return false;
        if (mainSymbolId != null ? !mainSymbolId.equals(that.mainSymbolId) : that.mainSymbolId != null) return false;
        if (calendar != null ? !calendar.equals(that.calendar) : that.calendar != null) return false;
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
        result = 31 * result + (calendar != null ? calendar.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (rv != null ? Arrays.hashCode(rv) : 0);
        return result;
    }
}
