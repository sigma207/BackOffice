package com.jelly.jt8.bo.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by user on 2015/9/15.
 */
@Entity
@Table(name = "trade_account", schema = "dbo", catalog = "jt8")
public class TradeAccount {
    private String accountId;
    private String houseId;
    private String loginId;
    private int tradeStatus;
    private String accountType;
    private String roleId;
    private String accountLevel;
    private int marginRate;
    private BigDecimal marginCallRate;
    private BigDecimal liquidationRate;
    private BigDecimal openCommission;
    private BigDecimal closeCommission;
    private BigDecimal maxLots;
    private Integer groupId;
    private String createTime;
    private String updateTime;
    private String category;
    private String exchangeId;

    @Id
    @Column(name = "account_id")
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "house_id")
    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    @Basic
    @Column(name = "login_id")
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Basic
    @Column(name = "trade_status")
    public int getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(int tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    @Basic
    @Column(name = "account_type")
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Basic
    @Column(name = "role_id")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "account_level")
    public String getAccountLevel() {
        return accountLevel;
    }

    public void setAccountLevel(String accountLevel) {
        this.accountLevel = accountLevel;
    }

    @Basic
    @Column(name = "margin_rate")
    public int getMarginRate() {
        return marginRate;
    }

    public void setMarginRate(int marginRate) {
        this.marginRate = marginRate;
    }

    @Basic
    @Column(name = "margin_call_rate")
    public BigDecimal getMarginCallRate() {
        return marginCallRate;
    }

    public void setMarginCallRate(BigDecimal marginCallRate) {
        this.marginCallRate = marginCallRate;
    }

    @Basic
    @Column(name = "liquidation_rate")
    public BigDecimal getLiquidationRate() {
        return liquidationRate;
    }

    public void setLiquidationRate(BigDecimal liquidationRate) {
        this.liquidationRate = liquidationRate;
    }

    @Basic
    @Column(name = "open_commission")
    public BigDecimal getOpenCommission() {
        return openCommission;
    }

    public void setOpenCommission(BigDecimal openCommission) {
        this.openCommission = openCommission;
    }

    @Basic
    @Column(name = "close_commission")
    public BigDecimal getCloseCommission() {
        return closeCommission;
    }

    public void setCloseCommission(BigDecimal closeCommission) {
        this.closeCommission = closeCommission;
    }

    @Basic
    @Column(name = "max_lots")
    public BigDecimal getMaxLots() {
        return maxLots;
    }

    public void setMaxLots(BigDecimal maxLots) {
        this.maxLots = maxLots;
    }

    @Basic
    @Column(name = "group_id")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "create_time")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeAccount that = (TradeAccount) o;

        if (tradeStatus != that.tradeStatus) return false;
        if (marginRate != that.marginRate) return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (houseId != null ? !houseId.equals(that.houseId) : that.houseId != null) return false;
        if (loginId != null ? !loginId.equals(that.loginId) : that.loginId != null) return false;
        if (accountType != null ? !accountType.equals(that.accountType) : that.accountType != null) return false;
        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;
        if (accountLevel != null ? !accountLevel.equals(that.accountLevel) : that.accountLevel != null) return false;
        if (marginCallRate != null ? !marginCallRate.equals(that.marginCallRate) : that.marginCallRate != null)
            return false;
        if (liquidationRate != null ? !liquidationRate.equals(that.liquidationRate) : that.liquidationRate != null)
            return false;
        if (openCommission != null ? !openCommission.equals(that.openCommission) : that.openCommission != null)
            return false;
        if (closeCommission != null ? !closeCommission.equals(that.closeCommission) : that.closeCommission != null)
            return false;
        if (maxLots != null ? !maxLots.equals(that.maxLots) : that.maxLots != null) return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountId != null ? accountId.hashCode() : 0;
        result = 31 * result + (houseId != null ? houseId.hashCode() : 0);
        result = 31 * result + (loginId != null ? loginId.hashCode() : 0);
        result = 31 * result + tradeStatus;
        result = 31 * result + (accountType != null ? accountType.hashCode() : 0);
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        result = 31 * result + (accountLevel != null ? accountLevel.hashCode() : 0);
        result = 31 * result + marginRate;
        result = 31 * result + (marginCallRate != null ? marginCallRate.hashCode() : 0);
        result = 31 * result + (liquidationRate != null ? liquidationRate.hashCode() : 0);
        result = 31 * result + (openCommission != null ? openCommission.hashCode() : 0);
        result = 31 * result + (closeCommission != null ? closeCommission.hashCode() : 0);
        result = 31 * result + (maxLots != null ? maxLots.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "exchange_id")
    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }
}
