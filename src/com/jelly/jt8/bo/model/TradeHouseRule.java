package com.jelly.jt8.bo.model;

import java.math.BigDecimal;

/**
 * Created by user on 2015/8/26.
 */
public class TradeHouseRule {
    private String houseId;
    private String exchangeId;
    private String houseName;
    private Integer scale;
    private BigDecimal initialMargin;
    private BigDecimal maintainMargin;
    private BigDecimal openCommission;
    private BigDecimal closeCommission;
    private BigDecimal overnightCharge;
    private BigDecimal tax;
    private BigDecimal fee1;
    private BigDecimal fee2;
    private BigDecimal fee3;
    private BigDecimal fee4;
    private BigDecimal fee5;
    private String openTime1;
    private String closeTime1;
    private String resetTime1;
    private String openTime2;
    private String closeTime2;
    private String resetTime2;
    private String rolloverTime;
    private String settlementTime;
    private String newOrderTime;
    private String lastOrderTime;
    private BigDecimal uplimit;
    private BigDecimal downlimit;
    private BigDecimal marketUplimit;
    private BigDecimal marketDownlimit;
    private BigDecimal marketUpdownlimitCharge;
    private BigDecimal specialStockRule;
    private BigDecimal specialStockCharge;
    private Long tvolDeltaRule;
    private Integer tvolDeltaMinute;
    private BigDecimal tvolDeltaCharge;
    private String newOrderCloseTimeLimit;
    private Integer oiDay;
    private BigDecimal overWithdrawalCreditPercentage;
    private BigDecimal overWithdrawalCreditPercentageCharge;
    private BigDecimal overWithdrawalCreditLimit;
    private BigDecimal overWithdrawalCreditLimitCharge;
    private BigDecimal liquidationRate;
    private BigDecimal marginCallRate;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public BigDecimal getInitialMargin() {
        return initialMargin;
    }

    public void setInitialMargin(BigDecimal initialMargin) {
        this.initialMargin = initialMargin;
    }

    public BigDecimal getMaintainMargin() {
        return maintainMargin;
    }

    public void setMaintainMargin(BigDecimal maintainMargin) {
        this.maintainMargin = maintainMargin;
    }

    public BigDecimal getOpenCommission() {
        return openCommission;
    }

    public void setOpenCommission(BigDecimal openCommission) {
        this.openCommission = openCommission;
    }

    public BigDecimal getCloseCommission() {
        return closeCommission;
    }

    public void setCloseCommission(BigDecimal closeCommission) {
        this.closeCommission = closeCommission;
    }

    public BigDecimal getOvernightCharge() {
        return overnightCharge;
    }

    public void setOvernightCharge(BigDecimal overnightCharge) {
        this.overnightCharge = overnightCharge;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getFee1() {
        return fee1;
    }

    public void setFee1(BigDecimal fee1) {
        this.fee1 = fee1;
    }

    public BigDecimal getFee2() {
        return fee2;
    }

    public void setFee2(BigDecimal fee2) {
        this.fee2 = fee2;
    }

    public BigDecimal getFee3() {
        return fee3;
    }

    public void setFee3(BigDecimal fee3) {
        this.fee3 = fee3;
    }

    public BigDecimal getFee4() {
        return fee4;
    }

    public void setFee4(BigDecimal fee4) {
        this.fee4 = fee4;
    }

    public BigDecimal getFee5() {
        return fee5;
    }

    public void setFee5(BigDecimal fee5) {
        this.fee5 = fee5;
    }

    public String getOpenTime1() {
        return openTime1;
    }

    public void setOpenTime1(String openTime1) {
        this.openTime1 = openTime1;
    }

    public String getCloseTime1() {
        return closeTime1;
    }

    public void setCloseTime1(String closeTime1) {
        this.closeTime1 = closeTime1;
    }

    public String getResetTime1() {
        return resetTime1;
    }

    public void setResetTime1(String resetTime1) {
        this.resetTime1 = resetTime1;
    }

    public String getOpenTime2() {
        return openTime2;
    }

    public void setOpenTime2(String openTime2) {
        this.openTime2 = openTime2;
    }

    public String getCloseTime2() {
        return closeTime2;
    }

    public void setCloseTime2(String closeTime2) {
        this.closeTime2 = closeTime2;
    }

    public String getResetTime2() {
        return resetTime2;
    }

    public void setResetTime2(String resetTime2) {
        this.resetTime2 = resetTime2;
    }

    public String getRolloverTime() {
        return rolloverTime;
    }

    public void setRolloverTime(String rolloverTime) {
        this.rolloverTime = rolloverTime;
    }

    public String getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(String settlementTime) {
        this.settlementTime = settlementTime;
    }

    public String getNewOrderTime() {
        return newOrderTime;
    }

    public void setNewOrderTime(String newOrderTime) {
        this.newOrderTime = newOrderTime;
    }

    public String getLastOrderTime() {
        return lastOrderTime;
    }

    public void setLastOrderTime(String lastOrderTime) {
        this.lastOrderTime = lastOrderTime;
    }

    public BigDecimal getUplimit() {
        return uplimit;
    }

    public void setUplimit(BigDecimal uplimit) {
        this.uplimit = uplimit;
    }

    public BigDecimal getDownlimit() {
        return downlimit;
    }

    public void setDownlimit(BigDecimal downlimit) {
        this.downlimit = downlimit;
    }

    public BigDecimal getMarketUplimit() {
        return marketUplimit;
    }

    public void setMarketUplimit(BigDecimal marketUplimit) {
        this.marketUplimit = marketUplimit;
    }

    public BigDecimal getMarketDownlimit() {
        return marketDownlimit;
    }

    public void setMarketDownlimit(BigDecimal marketDownlimit) {
        this.marketDownlimit = marketDownlimit;
    }

    public BigDecimal getMarketUpdownlimitCharge() {
        return marketUpdownlimitCharge;
    }

    public void setMarketUpdownlimitCharge(BigDecimal marketUpdownlimitCharge) {
        this.marketUpdownlimitCharge = marketUpdownlimitCharge;
    }

    public BigDecimal getSpecialStockRule() {
        return specialStockRule;
    }

    public void setSpecialStockRule(BigDecimal specialStockRule) {
        this.specialStockRule = specialStockRule;
    }

    public BigDecimal getSpecialStockCharge() {
        return specialStockCharge;
    }

    public void setSpecialStockCharge(BigDecimal specialStockCharge) {
        this.specialStockCharge = specialStockCharge;
    }

    public Long getTvolDeltaRule() {
        return tvolDeltaRule;
    }

    public void setTvolDeltaRule(Long tvolDeltaRule) {
        this.tvolDeltaRule = tvolDeltaRule;
    }

    public Integer getTvolDeltaMinute() {
        return tvolDeltaMinute;
    }

    public void setTvolDeltaMinute(Integer tvolDeltaMinute) {
        this.tvolDeltaMinute = tvolDeltaMinute;
    }

    public BigDecimal getTvolDeltaCharge() {
        return tvolDeltaCharge;
    }

    public void setTvolDeltaCharge(BigDecimal tvolDeltaCharge) {
        this.tvolDeltaCharge = tvolDeltaCharge;
    }

    public String getNewOrderCloseTimeLimit() {
        return newOrderCloseTimeLimit;
    }

    public void setNewOrderCloseTimeLimit(String newOrderCloseTimeLimit) {
        this.newOrderCloseTimeLimit = newOrderCloseTimeLimit;
    }

    public Integer getOiDay() {
        return oiDay;
    }

    public void setOiDay(Integer oiDay) {
        this.oiDay = oiDay;
    }

    public BigDecimal getOverWithdrawalCreditPercentage() {
        return overWithdrawalCreditPercentage;
    }

    public void setOverWithdrawalCreditPercentage(BigDecimal overWithdrawalCreditPercentage) {
        this.overWithdrawalCreditPercentage = overWithdrawalCreditPercentage;
    }

    public BigDecimal getOverWithdrawalCreditPercentageCharge() {
        return overWithdrawalCreditPercentageCharge;
    }

    public void setOverWithdrawalCreditPercentageCharge(BigDecimal overWithdrawalCreditPercentageCharge) {
        this.overWithdrawalCreditPercentageCharge = overWithdrawalCreditPercentageCharge;
    }

    public BigDecimal getOverWithdrawalCreditLimit() {
        return overWithdrawalCreditLimit;
    }

    public void setOverWithdrawalCreditLimit(BigDecimal overWithdrawalCreditLimit) {
        this.overWithdrawalCreditLimit = overWithdrawalCreditLimit;
    }

    public BigDecimal getOverWithdrawalCreditLimitCharge() {
        return overWithdrawalCreditLimitCharge;
    }

    public void setOverWithdrawalCreditLimitCharge(BigDecimal overWithdrawalCreditLimitCharge) {
        this.overWithdrawalCreditLimitCharge = overWithdrawalCreditLimitCharge;
    }

    public BigDecimal getLiquidationRate() {
        return liquidationRate;
    }

    public void setLiquidationRate(BigDecimal liquidationRate) {
        this.liquidationRate = liquidationRate;
    }

    public BigDecimal getMarginCallRate() {
        return marginCallRate;
    }

    public void setMarginCallRate(BigDecimal marginCallRate) {
        this.marginCallRate = marginCallRate;
    }
}
