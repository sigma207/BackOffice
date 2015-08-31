package com.jelly.jt8.bo.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by user on 2015/8/31.
 */
@Entity
@javax.persistence.Table(name = "system_main_symbol", schema = "dbo", catalog = "jt8")
public class SystemMainSymbol {
    private String exchangeId;

    @Id
    @javax.persistence.Column(name = "exchange_id")
    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    private String mainSymbolId;

    @Id
    @javax.persistence.Column(name = "main_symbol_id")
    public String getMainSymbolId() {
        return mainSymbolId;
    }

    public void setMainSymbolId(String mainSymbolId) {
        this.mainSymbolId = mainSymbolId;
    }

    private String currency;

    @Basic
    @javax.persistence.Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    private BigDecimal contractSize;

    @Basic
    @javax.persistence.Column(name = "contract_size")
    public BigDecimal getContractSize() {
        return contractSize;
    }

    public void setContractSize(BigDecimal contractSize) {
        this.contractSize = contractSize;
    }

    private BigDecimal tickUnit;

    @Basic
    @javax.persistence.Column(name = "tick_unit")
    public BigDecimal getTickUnit() {
        return tickUnit;
    }

    public void setTickUnit(BigDecimal tickUnit) {
        this.tickUnit = tickUnit;
    }

    private BigDecimal tickValue;

    @Basic
    @javax.persistence.Column(name = "tick_value")
    public BigDecimal getTickValue() {
        return tickValue;
    }

    public void setTickValue(BigDecimal tickValue) {
        this.tickValue = tickValue;
    }

    private Integer tradeStatus;

    @Basic
    @javax.persistence.Column(name = "trade_status")
    public Integer getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(Integer tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    private Integer oiDays;

    @Basic
    @javax.persistence.Column(name = "oi_days")
    public Integer getOiDays() {
        return oiDays;
    }

    public void setOiDays(Integer oiDays) {
        this.oiDays = oiDays;
    }

    private Integer oiLots;

    @Basic
    @javax.persistence.Column(name = "oi_lots")
    public Integer getOiLots() {
        return oiLots;
    }

    public void setOiLots(Integer oiLots) {
        this.oiLots = oiLots;
    }

    private Integer lotsPerMinute;

    @Basic
    @javax.persistence.Column(name = "lots_per_minute")
    public Integer getLotsPerMinute() {
        return lotsPerMinute;
    }

    public void setLotsPerMinute(Integer lotsPerMinute) {
        this.lotsPerMinute = lotsPerMinute;
    }

    private Integer lotsPerTrade;

    @Basic
    @javax.persistence.Column(name = "lots_per_trade")
    public Integer getLotsPerTrade() {
        return lotsPerTrade;
    }

    public void setLotsPerTrade(Integer lotsPerTrade) {
        this.lotsPerTrade = lotsPerTrade;
    }

    private Integer creditLots;

    @Basic
    @javax.persistence.Column(name = "credit_lots")
    public Integer getCreditLots() {
        return creditLots;
    }

    public void setCreditLots(Integer creditLots) {
        this.creditLots = creditLots;
    }

    private BigDecimal creditLine;

    @Basic
    @javax.persistence.Column(name = "credit_line")
    public BigDecimal getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(BigDecimal creditLine) {
        this.creditLine = creditLine;
    }

    private BigDecimal initialMargin;

    @Basic
    @javax.persistence.Column(name = "initial_margin")
    public BigDecimal getInitialMargin() {
        return initialMargin;
    }

    public void setInitialMargin(BigDecimal initialMargin) {
        this.initialMargin = initialMargin;
    }

    private BigDecimal mntMargin;

    @Basic
    @javax.persistence.Column(name = "mnt_margin")
    public BigDecimal getMntMargin() {
        return mntMargin;
    }

    public void setMntMargin(BigDecimal mntMargin) {
        this.mntMargin = mntMargin;
    }

    private BigDecimal dailyMargin;

    @Basic
    @javax.persistence.Column(name = "daily_margin")
    public BigDecimal getDailyMargin() {
        return dailyMargin;
    }

    public void setDailyMargin(BigDecimal dailyMargin) {
        this.dailyMargin = dailyMargin;
    }

    private BigDecimal overnightMargin;

    @Basic
    @javax.persistence.Column(name = "overnight_margin")
    public BigDecimal getOvernightMargin() {
        return overnightMargin;
    }

    public void setOvernightMargin(BigDecimal overnightMargin) {
        this.overnightMargin = overnightMargin;
    }

    private BigDecimal openCommission;

    @Basic
    @javax.persistence.Column(name = "open_commission")
    public BigDecimal getOpenCommission() {
        return openCommission;
    }

    public void setOpenCommission(BigDecimal openCommission) {
        this.openCommission = openCommission;
    }

    private BigDecimal closeCommission;

    @Basic
    @javax.persistence.Column(name = "close_commission")
    public BigDecimal getCloseCommission() {
        return closeCommission;
    }

    public void setCloseCommission(BigDecimal closeCommission) {
        this.closeCommission = closeCommission;
    }

    private Integer openUpdown;

    @Basic
    @javax.persistence.Column(name = "open_updown")
    public Integer getOpenUpdown() {
        return openUpdown;
    }

    public void setOpenUpdown(Integer openUpdown) {
        this.openUpdown = openUpdown;
    }

    private BigDecimal fee;

    @Basic
    @javax.persistence.Column(name = "fee")
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    private BigDecimal interest;

    @Basic
    @javax.persistence.Column(name = "interest")
    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    private String updateTime;

    @Basic
    @javax.persistence.Column(name = "update_time")
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    private Integer decimal;

    @Basic
    @javax.persistence.Column(name = "decimal")
    public Integer getDecimal() {
        return decimal;
    }

    public void setDecimal(Integer decimal) {
        this.decimal = decimal;
    }

    private Integer step;

    @Basic
    @javax.persistence.Column(name = "step")
    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    private Integer crossDay;

    @Basic
    @javax.persistence.Column(name = "cross_day")
    public Integer getCrossDay() {
        return crossDay;
    }

    public void setCrossDay(Integer crossDay) {
        this.crossDay = crossDay;
    }

    private Integer timeZone;

    @Basic
    @javax.persistence.Column(name = "time_zone")
    public Integer getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(Integer timeZone) {
        this.timeZone = timeZone;
    }

    private String openTime1;

    @Basic
    @javax.persistence.Column(name = "open_time1")
    public String getOpenTime1() {
        return openTime1;
    }

    public void setOpenTime1(String openTime1) {
        this.openTime1 = openTime1;
    }

    private String openTime2;

    @Basic
    @javax.persistence.Column(name = "open_time2")
    public String getOpenTime2() {
        return openTime2;
    }

    public void setOpenTime2(String openTime2) {
        this.openTime2 = openTime2;
    }

    private String closeTime1;

    @Basic
    @javax.persistence.Column(name = "close_time1")
    public String getCloseTime1() {
        return closeTime1;
    }

    public void setCloseTime1(String closeTime1) {
        this.closeTime1 = closeTime1;
    }

    private String closeTime2;

    @Basic
    @javax.persistence.Column(name = "close_time2")
    public String getCloseTime2() {
        return closeTime2;
    }

    public void setCloseTime2(String closeTime2) {
        this.closeTime2 = closeTime2;
    }

    private String resetTime1;

    @Basic
    @javax.persistence.Column(name = "reset_time1")
    public String getResetTime1() {
        return resetTime1;
    }

    public void setResetTime1(String resetTime1) {
        this.resetTime1 = resetTime1;
    }

    private String resetTime2;

    @Basic
    @javax.persistence.Column(name = "reset_time2")
    public String getResetTime2() {
        return resetTime2;
    }

    public void setResetTime2(String resetTime2) {
        this.resetTime2 = resetTime2;
    }

    private String newOrderTime;

    @Basic
    @javax.persistence.Column(name = "new_order_time")
    public String getNewOrderTime() {
        return newOrderTime;
    }

    public void setNewOrderTime(String newOrderTime) {
        this.newOrderTime = newOrderTime;
    }

    private String lastOrderTime;

    @Basic
    @javax.persistence.Column(name = "last_order_time")
    public String getLastOrderTime() {
        return lastOrderTime;
    }

    public void setLastOrderTime(String lastOrderTime) {
        this.lastOrderTime = lastOrderTime;
    }

    private Integer secondToTrigger;

    @Basic
    @javax.persistence.Column(name = "second_to_trigger")
    public Integer getSecondToTrigger() {
        return secondToTrigger;
    }

    public void setSecondToTrigger(Integer secondToTrigger) {
        this.secondToTrigger = secondToTrigger;
    }

    private int nearMonthCount;

    @Basic
    @javax.persistence.Column(name = "near_month_count")
    public int getNearMonthCount() {
        return nearMonthCount;
    }

    public void setNearMonthCount(int nearMonthCount) {
        this.nearMonthCount = nearMonthCount;
    }

    private int farMonthCount;

    @Basic
    @javax.persistence.Column(name = "far_month_count")
    public int getFarMonthCount() {
        return farMonthCount;
    }

    public void setFarMonthCount(int farMonthCount) {
        this.farMonthCount = farMonthCount;
    }

    private int activeNearMonthCount;

    @Basic
    @javax.persistence.Column(name = "active_near_month_count")
    public int getActiveNearMonthCount() {
        return activeNearMonthCount;
    }

    public void setActiveNearMonthCount(int activeNearMonthCount) {
        this.activeNearMonthCount = activeNearMonthCount;
    }

    private int activeFarMonthCount;

    @Basic
    @javax.persistence.Column(name = "active_far_month_count")
    public int getActiveFarMonthCount() {
        return activeFarMonthCount;
    }

    public void setActiveFarMonthCount(int activeFarMonthCount) {
        this.activeFarMonthCount = activeFarMonthCount;
    }

    private int tradableNearMonthCount;

    @Basic
    @javax.persistence.Column(name = "tradable_near_month_count")
    public int getTradableNearMonthCount() {
        return tradableNearMonthCount;
    }

    public void setTradableNearMonthCount(int tradableNearMonthCount) {
        this.tradableNearMonthCount = tradableNearMonthCount;
    }

    private int tradableFarMonthCount;

    @Basic
    @javax.persistence.Column(name = "tradable_far_month_count")
    public int getTradableFarMonthCount() {
        return tradableFarMonthCount;
    }

    public void setTradableFarMonthCount(int tradableFarMonthCount) {
        this.tradableFarMonthCount = tradableFarMonthCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemMainSymbol that = (SystemMainSymbol) o;

        if (nearMonthCount != that.nearMonthCount) return false;
        if (farMonthCount != that.farMonthCount) return false;
        if (activeNearMonthCount != that.activeNearMonthCount) return false;
        if (activeFarMonthCount != that.activeFarMonthCount) return false;
        if (tradableNearMonthCount != that.tradableNearMonthCount) return false;
        if (tradableFarMonthCount != that.tradableFarMonthCount) return false;
        if (exchangeId != null ? !exchangeId.equals(that.exchangeId) : that.exchangeId != null) return false;
        if (mainSymbolId != null ? !mainSymbolId.equals(that.mainSymbolId) : that.mainSymbolId != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (contractSize != null ? !contractSize.equals(that.contractSize) : that.contractSize != null) return false;
        if (tickUnit != null ? !tickUnit.equals(that.tickUnit) : that.tickUnit != null) return false;
        if (tickValue != null ? !tickValue.equals(that.tickValue) : that.tickValue != null) return false;
        if (tradeStatus != null ? !tradeStatus.equals(that.tradeStatus) : that.tradeStatus != null) return false;
        if (oiDays != null ? !oiDays.equals(that.oiDays) : that.oiDays != null) return false;
        if (oiLots != null ? !oiLots.equals(that.oiLots) : that.oiLots != null) return false;
        if (lotsPerMinute != null ? !lotsPerMinute.equals(that.lotsPerMinute) : that.lotsPerMinute != null)
            return false;
        if (lotsPerTrade != null ? !lotsPerTrade.equals(that.lotsPerTrade) : that.lotsPerTrade != null) return false;
        if (creditLots != null ? !creditLots.equals(that.creditLots) : that.creditLots != null) return false;
        if (creditLine != null ? !creditLine.equals(that.creditLine) : that.creditLine != null) return false;
        if (initialMargin != null ? !initialMargin.equals(that.initialMargin) : that.initialMargin != null)
            return false;
        if (mntMargin != null ? !mntMargin.equals(that.mntMargin) : that.mntMargin != null) return false;
        if (dailyMargin != null ? !dailyMargin.equals(that.dailyMargin) : that.dailyMargin != null) return false;
        if (overnightMargin != null ? !overnightMargin.equals(that.overnightMargin) : that.overnightMargin != null)
            return false;
        if (openCommission != null ? !openCommission.equals(that.openCommission) : that.openCommission != null)
            return false;
        if (closeCommission != null ? !closeCommission.equals(that.closeCommission) : that.closeCommission != null)
            return false;
        if (openUpdown != null ? !openUpdown.equals(that.openUpdown) : that.openUpdown != null) return false;
        if (fee != null ? !fee.equals(that.fee) : that.fee != null) return false;
        if (interest != null ? !interest.equals(that.interest) : that.interest != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (decimal != null ? !decimal.equals(that.decimal) : that.decimal != null) return false;
        if (step != null ? !step.equals(that.step) : that.step != null) return false;
        if (crossDay != null ? !crossDay.equals(that.crossDay) : that.crossDay != null) return false;
        if (timeZone != null ? !timeZone.equals(that.timeZone) : that.timeZone != null) return false;
        if (openTime1 != null ? !openTime1.equals(that.openTime1) : that.openTime1 != null) return false;
        if (openTime2 != null ? !openTime2.equals(that.openTime2) : that.openTime2 != null) return false;
        if (closeTime1 != null ? !closeTime1.equals(that.closeTime1) : that.closeTime1 != null) return false;
        if (closeTime2 != null ? !closeTime2.equals(that.closeTime2) : that.closeTime2 != null) return false;
        if (resetTime1 != null ? !resetTime1.equals(that.resetTime1) : that.resetTime1 != null) return false;
        if (resetTime2 != null ? !resetTime2.equals(that.resetTime2) : that.resetTime2 != null) return false;
        if (newOrderTime != null ? !newOrderTime.equals(that.newOrderTime) : that.newOrderTime != null) return false;
        if (lastOrderTime != null ? !lastOrderTime.equals(that.lastOrderTime) : that.lastOrderTime != null)
            return false;
        if (secondToTrigger != null ? !secondToTrigger.equals(that.secondToTrigger) : that.secondToTrigger != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = exchangeId != null ? exchangeId.hashCode() : 0;
        result = 31 * result + (mainSymbolId != null ? mainSymbolId.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (contractSize != null ? contractSize.hashCode() : 0);
        result = 31 * result + (tickUnit != null ? tickUnit.hashCode() : 0);
        result = 31 * result + (tickValue != null ? tickValue.hashCode() : 0);
        result = 31 * result + (tradeStatus != null ? tradeStatus.hashCode() : 0);
        result = 31 * result + (oiDays != null ? oiDays.hashCode() : 0);
        result = 31 * result + (oiLots != null ? oiLots.hashCode() : 0);
        result = 31 * result + (lotsPerMinute != null ? lotsPerMinute.hashCode() : 0);
        result = 31 * result + (lotsPerTrade != null ? lotsPerTrade.hashCode() : 0);
        result = 31 * result + (creditLots != null ? creditLots.hashCode() : 0);
        result = 31 * result + (creditLine != null ? creditLine.hashCode() : 0);
        result = 31 * result + (initialMargin != null ? initialMargin.hashCode() : 0);
        result = 31 * result + (mntMargin != null ? mntMargin.hashCode() : 0);
        result = 31 * result + (dailyMargin != null ? dailyMargin.hashCode() : 0);
        result = 31 * result + (overnightMargin != null ? overnightMargin.hashCode() : 0);
        result = 31 * result + (openCommission != null ? openCommission.hashCode() : 0);
        result = 31 * result + (closeCommission != null ? closeCommission.hashCode() : 0);
        result = 31 * result + (openUpdown != null ? openUpdown.hashCode() : 0);
        result = 31 * result + (fee != null ? fee.hashCode() : 0);
        result = 31 * result + (interest != null ? interest.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (decimal != null ? decimal.hashCode() : 0);
        result = 31 * result + (step != null ? step.hashCode() : 0);
        result = 31 * result + (crossDay != null ? crossDay.hashCode() : 0);
        result = 31 * result + (timeZone != null ? timeZone.hashCode() : 0);
        result = 31 * result + (openTime1 != null ? openTime1.hashCode() : 0);
        result = 31 * result + (openTime2 != null ? openTime2.hashCode() : 0);
        result = 31 * result + (closeTime1 != null ? closeTime1.hashCode() : 0);
        result = 31 * result + (closeTime2 != null ? closeTime2.hashCode() : 0);
        result = 31 * result + (resetTime1 != null ? resetTime1.hashCode() : 0);
        result = 31 * result + (resetTime2 != null ? resetTime2.hashCode() : 0);
        result = 31 * result + (newOrderTime != null ? newOrderTime.hashCode() : 0);
        result = 31 * result + (lastOrderTime != null ? lastOrderTime.hashCode() : 0);
        result = 31 * result + (secondToTrigger != null ? secondToTrigger.hashCode() : 0);
        result = 31 * result + nearMonthCount;
        result = 31 * result + farMonthCount;
        result = 31 * result + activeNearMonthCount;
        result = 31 * result + activeFarMonthCount;
        result = 31 * result + tradableNearMonthCount;
        result = 31 * result + tradableFarMonthCount;
        return result;
    }
}
