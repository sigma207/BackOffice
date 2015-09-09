package com.jelly.jt8.bo.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by user on 2015/9/9.
 */
@Entity
@javax.persistence.Table(name = "system_trade_rule", schema = "dbo", catalog = "jt8")
public class SystemTradeRule extends BaseModel{
    private String exchangeId;

    @Id
    @javax.persistence.Column(name = "exchange_id")
    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    private String category;

    @Id
    @javax.persistence.Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String ruleName;

    @Basic
    @javax.persistence.Column(name = "rule_name")
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    private Integer scale;

    @Basic
    @javax.persistence.Column(name = "scale")
    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
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

    private BigDecimal maintainMargin;

    @Basic
    @javax.persistence.Column(name = "maintain_margin")
    public BigDecimal getMaintainMargin() {
        return maintainMargin;
    }

    public void setMaintainMargin(BigDecimal maintainMargin) {
        this.maintainMargin = maintainMargin;
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

    private BigDecimal overnightCharge;

    @Basic
    @javax.persistence.Column(name = "overnight_charge")
    public BigDecimal getOvernightCharge() {
        return overnightCharge;
    }

    public void setOvernightCharge(BigDecimal overnightCharge) {
        this.overnightCharge = overnightCharge;
    }

    private BigDecimal tax;

    @Basic
    @javax.persistence.Column(name = "tax")
    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    private BigDecimal fee1;

    @Basic
    @javax.persistence.Column(name = "fee1")
    public BigDecimal getFee1() {
        return fee1;
    }

    public void setFee1(BigDecimal fee1) {
        this.fee1 = fee1;
    }

    private BigDecimal fee2;

    @Basic
    @javax.persistence.Column(name = "fee2")
    public BigDecimal getFee2() {
        return fee2;
    }

    public void setFee2(BigDecimal fee2) {
        this.fee2 = fee2;
    }

    private BigDecimal fee3;

    @Basic
    @javax.persistence.Column(name = "fee3")
    public BigDecimal getFee3() {
        return fee3;
    }

    public void setFee3(BigDecimal fee3) {
        this.fee3 = fee3;
    }

    private BigDecimal fee4;

    @Basic
    @javax.persistence.Column(name = "fee4")
    public BigDecimal getFee4() {
        return fee4;
    }

    public void setFee4(BigDecimal fee4) {
        this.fee4 = fee4;
    }

    private BigDecimal fee5;

    @Basic
    @javax.persistence.Column(name = "fee5")
    public BigDecimal getFee5() {
        return fee5;
    }

    public void setFee5(BigDecimal fee5) {
        this.fee5 = fee5;
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

    private String closeTime1;

    @Basic
    @javax.persistence.Column(name = "close_time1")
    public String getCloseTime1() {
        return closeTime1;
    }

    public void setCloseTime1(String closeTime1) {
        this.closeTime1 = closeTime1;
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

    private String openTime2;

    @Basic
    @javax.persistence.Column(name = "open_time2")
    public String getOpenTime2() {
        return openTime2;
    }

    public void setOpenTime2(String openTime2) {
        this.openTime2 = openTime2;
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

    private String resetTime2;

    @Basic
    @javax.persistence.Column(name = "reset_time2")
    public String getResetTime2() {
        return resetTime2;
    }

    public void setResetTime2(String resetTime2) {
        this.resetTime2 = resetTime2;
    }

    private String rolloverTime;

    @Basic
    @javax.persistence.Column(name = "rollover_time")
    public String getRolloverTime() {
        return rolloverTime;
    }

    public void setRolloverTime(String rolloverTime) {
        this.rolloverTime = rolloverTime;
    }

    private String settlementTime;

    @Basic
    @javax.persistence.Column(name = "settlement_time")
    public String getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(String settlementTime) {
        this.settlementTime = settlementTime;
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

    private BigDecimal uplimit;

    @Basic
    @javax.persistence.Column(name = "uplimit")
    public BigDecimal getUplimit() {
        return uplimit;
    }

    public void setUplimit(BigDecimal uplimit) {
        this.uplimit = uplimit;
    }

    private BigDecimal downlimit;

    @Basic
    @javax.persistence.Column(name = "downlimit")
    public BigDecimal getDownlimit() {
        return downlimit;
    }

    public void setDownlimit(BigDecimal downlimit) {
        this.downlimit = downlimit;
    }

    private BigDecimal marketUplimit;

    @Basic
    @javax.persistence.Column(name = "market_uplimit")
    public BigDecimal getMarketUplimit() {
        return marketUplimit;
    }

    public void setMarketUplimit(BigDecimal marketUplimit) {
        this.marketUplimit = marketUplimit;
    }

    private BigDecimal marketDownlimit;

    @Basic
    @javax.persistence.Column(name = "market_downlimit")
    public BigDecimal getMarketDownlimit() {
        return marketDownlimit;
    }

    public void setMarketDownlimit(BigDecimal marketDownlimit) {
        this.marketDownlimit = marketDownlimit;
    }

    private BigDecimal marketUpdownlimitCharge;

    @Basic
    @javax.persistence.Column(name = "market_updownlimit_charge")
    public BigDecimal getMarketUpdownlimitCharge() {
        return marketUpdownlimitCharge;
    }

    public void setMarketUpdownlimitCharge(BigDecimal marketUpdownlimitCharge) {
        this.marketUpdownlimitCharge = marketUpdownlimitCharge;
    }

    private BigDecimal specialStockRule;

    @Basic
    @javax.persistence.Column(name = "special_stock_rule")
    public BigDecimal getSpecialStockRule() {
        return specialStockRule;
    }

    public void setSpecialStockRule(BigDecimal specialStockRule) {
        this.specialStockRule = specialStockRule;
    }

    private BigDecimal specialStockCharge;

    @Basic
    @javax.persistence.Column(name = "special_stock_charge")
    public BigDecimal getSpecialStockCharge() {
        return specialStockCharge;
    }

    public void setSpecialStockCharge(BigDecimal specialStockCharge) {
        this.specialStockCharge = specialStockCharge;
    }

    private Long tvolDeltaRule;

    @Basic
    @javax.persistence.Column(name = "tvol_delta_rule")
    public Long getTvolDeltaRule() {
        return tvolDeltaRule;
    }

    public void setTvolDeltaRule(Long tvolDeltaRule) {
        this.tvolDeltaRule = tvolDeltaRule;
    }

    private Integer tvolDeltaMinute;

    @Basic
    @javax.persistence.Column(name = "tvol_delta_minute")
    public Integer getTvolDeltaMinute() {
        return tvolDeltaMinute;
    }

    public void setTvolDeltaMinute(Integer tvolDeltaMinute) {
        this.tvolDeltaMinute = tvolDeltaMinute;
    }

    private BigDecimal tvolDeltaCharge;

    @Basic
    @javax.persistence.Column(name = "tvol_delta_charge")
    public BigDecimal getTvolDeltaCharge() {
        return tvolDeltaCharge;
    }

    public void setTvolDeltaCharge(BigDecimal tvolDeltaCharge) {
        this.tvolDeltaCharge = tvolDeltaCharge;
    }

    private String newOrderCloseTimeLimit;

    @Basic
    @javax.persistence.Column(name = "new_order_close_time_limit")
    public String getNewOrderCloseTimeLimit() {
        return newOrderCloseTimeLimit;
    }

    public void setNewOrderCloseTimeLimit(String newOrderCloseTimeLimit) {
        this.newOrderCloseTimeLimit = newOrderCloseTimeLimit;
    }

    private Integer oiDay;

    @Basic
    @javax.persistence.Column(name = "oi_day")
    public Integer getOiDay() {
        return oiDay;
    }

    public void setOiDay(Integer oiDay) {
        this.oiDay = oiDay;
    }

    private BigDecimal overWithdrawalCreditPercentage;

    @Basic
    @javax.persistence.Column(name = "over_withdrawal_credit_percentage")
    public BigDecimal getOverWithdrawalCreditPercentage() {
        return overWithdrawalCreditPercentage;
    }

    public void setOverWithdrawalCreditPercentage(BigDecimal overWithdrawalCreditPercentage) {
        this.overWithdrawalCreditPercentage = overWithdrawalCreditPercentage;
    }

    private BigDecimal overWithdrawalCreditPercentageCharge;

    @Basic
    @javax.persistence.Column(name = "over_withdrawal_credit_percentage_charge")
    public BigDecimal getOverWithdrawalCreditPercentageCharge() {
        return overWithdrawalCreditPercentageCharge;
    }

    public void setOverWithdrawalCreditPercentageCharge(BigDecimal overWithdrawalCreditPercentageCharge) {
        this.overWithdrawalCreditPercentageCharge = overWithdrawalCreditPercentageCharge;
    }

    private BigDecimal overWithdrawalCreditLimit;

    @Basic
    @javax.persistence.Column(name = "over_withdrawal_credit_limit")
    public BigDecimal getOverWithdrawalCreditLimit() {
        return overWithdrawalCreditLimit;
    }

    public void setOverWithdrawalCreditLimit(BigDecimal overWithdrawalCreditLimit) {
        this.overWithdrawalCreditLimit = overWithdrawalCreditLimit;
    }

    private BigDecimal overWithdrawalCreditLimitCharge;

    @Basic
    @javax.persistence.Column(name = "over_withdrawal_credit_limit_charge")
    public BigDecimal getOverWithdrawalCreditLimitCharge() {
        return overWithdrawalCreditLimitCharge;
    }

    public void setOverWithdrawalCreditLimitCharge(BigDecimal overWithdrawalCreditLimitCharge) {
        this.overWithdrawalCreditLimitCharge = overWithdrawalCreditLimitCharge;
    }

    private BigDecimal liquidationRate;

    @Basic
    @javax.persistence.Column(name = "liquidation_rate")
    public BigDecimal getLiquidationRate() {
        return liquidationRate;
    }

    public void setLiquidationRate(BigDecimal liquidationRate) {
        this.liquidationRate = liquidationRate;
    }

    private BigDecimal marginCallRate;

    @Basic
    @javax.persistence.Column(name = "margin_call_rate")
    public BigDecimal getMarginCallRate() {
        return marginCallRate;
    }

    public void setMarginCallRate(BigDecimal marginCallRate) {
        this.marginCallRate = marginCallRate;
    }

    private List<TradeGroup> tradeGroupList;

    @Transient
    public List<TradeGroup> getTradeGroupList() {
        return tradeGroupList;
    }

    public void setTradeGroupList(List<TradeGroup> tradeGroupList) {
        this.tradeGroupList = tradeGroupList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemTradeRule that = (SystemTradeRule) o;

        if (exchangeId != null ? !exchangeId.equals(that.exchangeId) : that.exchangeId != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (ruleName != null ? !ruleName.equals(that.ruleName) : that.ruleName != null) return false;
        if (scale != null ? !scale.equals(that.scale) : that.scale != null) return false;
        if (initialMargin != null ? !initialMargin.equals(that.initialMargin) : that.initialMargin != null)
            return false;
        if (maintainMargin != null ? !maintainMargin.equals(that.maintainMargin) : that.maintainMargin != null)
            return false;
        if (openCommission != null ? !openCommission.equals(that.openCommission) : that.openCommission != null)
            return false;
        if (closeCommission != null ? !closeCommission.equals(that.closeCommission) : that.closeCommission != null)
            return false;
        if (overnightCharge != null ? !overnightCharge.equals(that.overnightCharge) : that.overnightCharge != null)
            return false;
        if (tax != null ? !tax.equals(that.tax) : that.tax != null) return false;
        if (fee1 != null ? !fee1.equals(that.fee1) : that.fee1 != null) return false;
        if (fee2 != null ? !fee2.equals(that.fee2) : that.fee2 != null) return false;
        if (fee3 != null ? !fee3.equals(that.fee3) : that.fee3 != null) return false;
        if (fee4 != null ? !fee4.equals(that.fee4) : that.fee4 != null) return false;
        if (fee5 != null ? !fee5.equals(that.fee5) : that.fee5 != null) return false;
        if (openTime1 != null ? !openTime1.equals(that.openTime1) : that.openTime1 != null) return false;
        if (closeTime1 != null ? !closeTime1.equals(that.closeTime1) : that.closeTime1 != null) return false;
        if (resetTime1 != null ? !resetTime1.equals(that.resetTime1) : that.resetTime1 != null) return false;
        if (openTime2 != null ? !openTime2.equals(that.openTime2) : that.openTime2 != null) return false;
        if (closeTime2 != null ? !closeTime2.equals(that.closeTime2) : that.closeTime2 != null) return false;
        if (resetTime2 != null ? !resetTime2.equals(that.resetTime2) : that.resetTime2 != null) return false;
        if (rolloverTime != null ? !rolloverTime.equals(that.rolloverTime) : that.rolloverTime != null) return false;
        if (settlementTime != null ? !settlementTime.equals(that.settlementTime) : that.settlementTime != null)
            return false;
        if (newOrderTime != null ? !newOrderTime.equals(that.newOrderTime) : that.newOrderTime != null) return false;
        if (lastOrderTime != null ? !lastOrderTime.equals(that.lastOrderTime) : that.lastOrderTime != null)
            return false;
        if (uplimit != null ? !uplimit.equals(that.uplimit) : that.uplimit != null) return false;
        if (downlimit != null ? !downlimit.equals(that.downlimit) : that.downlimit != null) return false;
        if (marketUplimit != null ? !marketUplimit.equals(that.marketUplimit) : that.marketUplimit != null)
            return false;
        if (marketDownlimit != null ? !marketDownlimit.equals(that.marketDownlimit) : that.marketDownlimit != null)
            return false;
        if (marketUpdownlimitCharge != null ? !marketUpdownlimitCharge.equals(that.marketUpdownlimitCharge) : that.marketUpdownlimitCharge != null)
            return false;
        if (specialStockRule != null ? !specialStockRule.equals(that.specialStockRule) : that.specialStockRule != null)
            return false;
        if (specialStockCharge != null ? !specialStockCharge.equals(that.specialStockCharge) : that.specialStockCharge != null)
            return false;
        if (tvolDeltaRule != null ? !tvolDeltaRule.equals(that.tvolDeltaRule) : that.tvolDeltaRule != null)
            return false;
        if (tvolDeltaMinute != null ? !tvolDeltaMinute.equals(that.tvolDeltaMinute) : that.tvolDeltaMinute != null)
            return false;
        if (tvolDeltaCharge != null ? !tvolDeltaCharge.equals(that.tvolDeltaCharge) : that.tvolDeltaCharge != null)
            return false;
        if (newOrderCloseTimeLimit != null ? !newOrderCloseTimeLimit.equals(that.newOrderCloseTimeLimit) : that.newOrderCloseTimeLimit != null)
            return false;
        if (oiDay != null ? !oiDay.equals(that.oiDay) : that.oiDay != null) return false;
        if (overWithdrawalCreditPercentage != null ? !overWithdrawalCreditPercentage.equals(that.overWithdrawalCreditPercentage) : that.overWithdrawalCreditPercentage != null)
            return false;
        if (overWithdrawalCreditPercentageCharge != null ? !overWithdrawalCreditPercentageCharge.equals(that.overWithdrawalCreditPercentageCharge) : that.overWithdrawalCreditPercentageCharge != null)
            return false;
        if (overWithdrawalCreditLimit != null ? !overWithdrawalCreditLimit.equals(that.overWithdrawalCreditLimit) : that.overWithdrawalCreditLimit != null)
            return false;
        if (overWithdrawalCreditLimitCharge != null ? !overWithdrawalCreditLimitCharge.equals(that.overWithdrawalCreditLimitCharge) : that.overWithdrawalCreditLimitCharge != null)
            return false;
        if (liquidationRate != null ? !liquidationRate.equals(that.liquidationRate) : that.liquidationRate != null)
            return false;
        if (marginCallRate != null ? !marginCallRate.equals(that.marginCallRate) : that.marginCallRate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = exchangeId != null ? exchangeId.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (ruleName != null ? ruleName.hashCode() : 0);
        result = 31 * result + (scale != null ? scale.hashCode() : 0);
        result = 31 * result + (initialMargin != null ? initialMargin.hashCode() : 0);
        result = 31 * result + (maintainMargin != null ? maintainMargin.hashCode() : 0);
        result = 31 * result + (openCommission != null ? openCommission.hashCode() : 0);
        result = 31 * result + (closeCommission != null ? closeCommission.hashCode() : 0);
        result = 31 * result + (overnightCharge != null ? overnightCharge.hashCode() : 0);
        result = 31 * result + (tax != null ? tax.hashCode() : 0);
        result = 31 * result + (fee1 != null ? fee1.hashCode() : 0);
        result = 31 * result + (fee2 != null ? fee2.hashCode() : 0);
        result = 31 * result + (fee3 != null ? fee3.hashCode() : 0);
        result = 31 * result + (fee4 != null ? fee4.hashCode() : 0);
        result = 31 * result + (fee5 != null ? fee5.hashCode() : 0);
        result = 31 * result + (openTime1 != null ? openTime1.hashCode() : 0);
        result = 31 * result + (closeTime1 != null ? closeTime1.hashCode() : 0);
        result = 31 * result + (resetTime1 != null ? resetTime1.hashCode() : 0);
        result = 31 * result + (openTime2 != null ? openTime2.hashCode() : 0);
        result = 31 * result + (closeTime2 != null ? closeTime2.hashCode() : 0);
        result = 31 * result + (resetTime2 != null ? resetTime2.hashCode() : 0);
        result = 31 * result + (rolloverTime != null ? rolloverTime.hashCode() : 0);
        result = 31 * result + (settlementTime != null ? settlementTime.hashCode() : 0);
        result = 31 * result + (newOrderTime != null ? newOrderTime.hashCode() : 0);
        result = 31 * result + (lastOrderTime != null ? lastOrderTime.hashCode() : 0);
        result = 31 * result + (uplimit != null ? uplimit.hashCode() : 0);
        result = 31 * result + (downlimit != null ? downlimit.hashCode() : 0);
        result = 31 * result + (marketUplimit != null ? marketUplimit.hashCode() : 0);
        result = 31 * result + (marketDownlimit != null ? marketDownlimit.hashCode() : 0);
        result = 31 * result + (marketUpdownlimitCharge != null ? marketUpdownlimitCharge.hashCode() : 0);
        result = 31 * result + (specialStockRule != null ? specialStockRule.hashCode() : 0);
        result = 31 * result + (specialStockCharge != null ? specialStockCharge.hashCode() : 0);
        result = 31 * result + (tvolDeltaRule != null ? tvolDeltaRule.hashCode() : 0);
        result = 31 * result + (tvolDeltaMinute != null ? tvolDeltaMinute.hashCode() : 0);
        result = 31 * result + (tvolDeltaCharge != null ? tvolDeltaCharge.hashCode() : 0);
        result = 31 * result + (newOrderCloseTimeLimit != null ? newOrderCloseTimeLimit.hashCode() : 0);
        result = 31 * result + (oiDay != null ? oiDay.hashCode() : 0);
        result = 31 * result + (overWithdrawalCreditPercentage != null ? overWithdrawalCreditPercentage.hashCode() : 0);
        result = 31 * result + (overWithdrawalCreditPercentageCharge != null ? overWithdrawalCreditPercentageCharge.hashCode() : 0);
        result = 31 * result + (overWithdrawalCreditLimit != null ? overWithdrawalCreditLimit.hashCode() : 0);
        result = 31 * result + (overWithdrawalCreditLimitCharge != null ? overWithdrawalCreditLimitCharge.hashCode() : 0);
        result = 31 * result + (liquidationRate != null ? liquidationRate.hashCode() : 0);
        result = 31 * result + (marginCallRate != null ? marginCallRate.hashCode() : 0);
        return result;
    }
}
