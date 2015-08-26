package com.jelly.jt8.bo.model;

import java.math.BigDecimal;

/**
 * Created by user on 2015/8/26.
 */
public class TradeAccountGroup {
    private String groupId;
    private Integer scale;
    private BigDecimal initialMargin;
    private BigDecimal maintainMargin;
    private BigDecimal openCommission;
    private BigDecimal closeCommission;
    private BigDecimal buyCommission;
    private BigDecimal sellCommission;
    private BigDecimal tax;
    private BigDecimal fee1;
    private BigDecimal fee2;
    private BigDecimal fee3;
    private BigDecimal fee4;
    private BigDecimal fee5;
    private BigDecimal uplimit;
    private BigDecimal downlimit;
    private BigDecimal marketUplimit;
    private BigDecimal marketDownlimit;
    private BigDecimal marketUpdownlimitCharge;
    private int specialStockRule;
    private BigDecimal specialStockCharge;
    private Long tvolDeltaRule;
    private Integer tvolDeltaMinute;
    private BigDecimal tvolDeltaCharge;
    private String newOrderCloseTimeLimit;
    private Integer oiDay;
    private BigDecimal oiCharge;
    private BigDecimal oiLots;
    private BigDecimal overWithdrawalCreditPercentage;
    private BigDecimal overWithdrawalCreditPercentageCharge;
    private BigDecimal overWithdrawalCreditLimit;
    private BigDecimal overWithdrawalCreditLimitCharge;
    private BigDecimal liquidationRate;
    private BigDecimal marginCallRate;
    private int marginRate;
    private BigDecimal maxLots;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    public BigDecimal getBuyCommission() {
        return buyCommission;
    }

    public void setBuyCommission(BigDecimal buyCommission) {
        this.buyCommission = buyCommission;
    }

    public BigDecimal getSellCommission() {
        return sellCommission;
    }

    public void setSellCommission(BigDecimal sellCommission) {
        this.sellCommission = sellCommission;
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

    public int getSpecialStockRule() {
        return specialStockRule;
    }

    public void setSpecialStockRule(int specialStockRule) {
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

    public BigDecimal getOiCharge() {
        return oiCharge;
    }

    public void setOiCharge(BigDecimal oiCharge) {
        this.oiCharge = oiCharge;
    }

    public BigDecimal getOiLots() {
        return oiLots;
    }

    public void setOiLots(BigDecimal oiLots) {
        this.oiLots = oiLots;
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

    public int getMarginRate() {
        return marginRate;
    }

    public void setMarginRate(int marginRate) {
        this.marginRate = marginRate;
    }

    public BigDecimal getMaxLots() {
        return maxLots;
    }

    public void setMaxLots(BigDecimal maxLots) {
        this.maxLots = maxLots;
    }

}
