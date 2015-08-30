package com.jelly.jt8.bo.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by user on 2015/8/27.
 */
@Entity
@javax.persistence.Table(name = "trade_account_group", schema = "dbo", catalog = "jt8")
public class TradeAccountGroup extends BaseModel{
    private int groupId;

    @Id
    @javax.persistence.Column(name = "group_id", insertable = false)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    private String groupName;

    @Basic
    @javax.persistence.Column(name = "group_name")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    private int ownerId;

    @Basic
    @javax.persistence.Column(name = "owner_id")
    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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

    private BigDecimal buyCommission;

    @Basic
    @javax.persistence.Column(name = "buy_commission")
    public BigDecimal getBuyCommission() {
        return buyCommission;
    }

    public void setBuyCommission(BigDecimal buyCommission) {
        this.buyCommission = buyCommission;
    }

    private BigDecimal sellCommission;

    @Basic
    @javax.persistence.Column(name = "sell_commission")
    public BigDecimal getSellCommission() {
        return sellCommission;
    }

    public void setSellCommission(BigDecimal sellCommission) {
        this.sellCommission = sellCommission;
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

    private int specialStockRule;

    @Basic
    @javax.persistence.Column(name = "special_stock_rule")
    public int getSpecialStockRule() {
        return specialStockRule;
    }

    public void setSpecialStockRule(int specialStockRule) {
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

    private BigDecimal oiCharge;

    @Basic
    @javax.persistence.Column(name = "oi_charge")
    public BigDecimal getOiCharge() {
        return oiCharge;
    }

    public void setOiCharge(BigDecimal oiCharge) {
        this.oiCharge = oiCharge;
    }

    private BigDecimal oiLots;

    @Basic
    @javax.persistence.Column(name = "oi_lots")
    public BigDecimal getOiLots() {
        return oiLots;
    }

    public void setOiLots(BigDecimal oiLots) {
        this.oiLots = oiLots;
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

    private int marginRate;

    @Basic
    @javax.persistence.Column(name = "margin_rate")
    public int getMarginRate() {
        return marginRate;
    }

    public void setMarginRate(int marginRate) {
        this.marginRate = marginRate;
    }

    private BigDecimal maxLots;

    @Basic
    @javax.persistence.Column(name = "max_lots")
    public BigDecimal getMaxLots() {
        return maxLots;
    }

    public void setMaxLots(BigDecimal maxLots) {
        this.maxLots = maxLots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeAccountGroup that = (TradeAccountGroup) o;

        if (groupId != that.groupId) return false;
        if (ownerId != that.ownerId) return false;
        if (specialStockRule != that.specialStockRule) return false;
        if (marginRate != that.marginRate) return false;
        if (groupName != null ? !groupName.equals(that.groupName) : that.groupName != null) return false;
        if (scale != null ? !scale.equals(that.scale) : that.scale != null) return false;
        if (initialMargin != null ? !initialMargin.equals(that.initialMargin) : that.initialMargin != null)
            return false;
        if (maintainMargin != null ? !maintainMargin.equals(that.maintainMargin) : that.maintainMargin != null)
            return false;
        if (openCommission != null ? !openCommission.equals(that.openCommission) : that.openCommission != null)
            return false;
        if (closeCommission != null ? !closeCommission.equals(that.closeCommission) : that.closeCommission != null)
            return false;
        if (buyCommission != null ? !buyCommission.equals(that.buyCommission) : that.buyCommission != null)
            return false;
        if (sellCommission != null ? !sellCommission.equals(that.sellCommission) : that.sellCommission != null)
            return false;
        if (tax != null ? !tax.equals(that.tax) : that.tax != null) return false;
        if (fee1 != null ? !fee1.equals(that.fee1) : that.fee1 != null) return false;
        if (fee2 != null ? !fee2.equals(that.fee2) : that.fee2 != null) return false;
        if (fee3 != null ? !fee3.equals(that.fee3) : that.fee3 != null) return false;
        if (fee4 != null ? !fee4.equals(that.fee4) : that.fee4 != null) return false;
        if (fee5 != null ? !fee5.equals(that.fee5) : that.fee5 != null) return false;
        if (uplimit != null ? !uplimit.equals(that.uplimit) : that.uplimit != null) return false;
        if (downlimit != null ? !downlimit.equals(that.downlimit) : that.downlimit != null) return false;
        if (marketUplimit != null ? !marketUplimit.equals(that.marketUplimit) : that.marketUplimit != null)
            return false;
        if (marketDownlimit != null ? !marketDownlimit.equals(that.marketDownlimit) : that.marketDownlimit != null)
            return false;
        if (marketUpdownlimitCharge != null ? !marketUpdownlimitCharge.equals(that.marketUpdownlimitCharge) : that.marketUpdownlimitCharge != null)
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
        if (oiCharge != null ? !oiCharge.equals(that.oiCharge) : that.oiCharge != null) return false;
        if (oiLots != null ? !oiLots.equals(that.oiLots) : that.oiLots != null) return false;
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
        if (maxLots != null ? !maxLots.equals(that.maxLots) : that.maxLots != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        result = 31 * result + ownerId;
        result = 31 * result + (scale != null ? scale.hashCode() : 0);
        result = 31 * result + (initialMargin != null ? initialMargin.hashCode() : 0);
        result = 31 * result + (maintainMargin != null ? maintainMargin.hashCode() : 0);
        result = 31 * result + (openCommission != null ? openCommission.hashCode() : 0);
        result = 31 * result + (closeCommission != null ? closeCommission.hashCode() : 0);
        result = 31 * result + (buyCommission != null ? buyCommission.hashCode() : 0);
        result = 31 * result + (sellCommission != null ? sellCommission.hashCode() : 0);
        result = 31 * result + (tax != null ? tax.hashCode() : 0);
        result = 31 * result + (fee1 != null ? fee1.hashCode() : 0);
        result = 31 * result + (fee2 != null ? fee2.hashCode() : 0);
        result = 31 * result + (fee3 != null ? fee3.hashCode() : 0);
        result = 31 * result + (fee4 != null ? fee4.hashCode() : 0);
        result = 31 * result + (fee5 != null ? fee5.hashCode() : 0);
        result = 31 * result + (uplimit != null ? uplimit.hashCode() : 0);
        result = 31 * result + (downlimit != null ? downlimit.hashCode() : 0);
        result = 31 * result + (marketUplimit != null ? marketUplimit.hashCode() : 0);
        result = 31 * result + (marketDownlimit != null ? marketDownlimit.hashCode() : 0);
        result = 31 * result + (marketUpdownlimitCharge != null ? marketUpdownlimitCharge.hashCode() : 0);
        result = 31 * result + specialStockRule;
        result = 31 * result + (specialStockCharge != null ? specialStockCharge.hashCode() : 0);
        result = 31 * result + (tvolDeltaRule != null ? tvolDeltaRule.hashCode() : 0);
        result = 31 * result + (tvolDeltaMinute != null ? tvolDeltaMinute.hashCode() : 0);
        result = 31 * result + (tvolDeltaCharge != null ? tvolDeltaCharge.hashCode() : 0);
        result = 31 * result + (newOrderCloseTimeLimit != null ? newOrderCloseTimeLimit.hashCode() : 0);
        result = 31 * result + (oiDay != null ? oiDay.hashCode() : 0);
        result = 31 * result + (oiCharge != null ? oiCharge.hashCode() : 0);
        result = 31 * result + (oiLots != null ? oiLots.hashCode() : 0);
        result = 31 * result + (overWithdrawalCreditPercentage != null ? overWithdrawalCreditPercentage.hashCode() : 0);
        result = 31 * result + (overWithdrawalCreditPercentageCharge != null ? overWithdrawalCreditPercentageCharge.hashCode() : 0);
        result = 31 * result + (overWithdrawalCreditLimit != null ? overWithdrawalCreditLimit.hashCode() : 0);
        result = 31 * result + (overWithdrawalCreditLimitCharge != null ? overWithdrawalCreditLimitCharge.hashCode() : 0);
        result = 31 * result + (liquidationRate != null ? liquidationRate.hashCode() : 0);
        result = 31 * result + (marginCallRate != null ? marginCallRate.hashCode() : 0);
        result = 31 * result + marginRate;
        result = 31 * result + (maxLots != null ? maxLots.hashCode() : 0);
        return result;
    }
}
