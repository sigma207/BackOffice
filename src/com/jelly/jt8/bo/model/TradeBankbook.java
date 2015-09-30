package com.jelly.jt8.bo.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by user on 2015/9/17.
 */
@Entity
@Table(name = "trade_bankbook", schema = "dbo", catalog = "jt8")
public class TradeBankbook extends BaseModel{
    public final static String BANKBOOK_TYPE_D = "D";
    public final static String BANKBOOK_TYPE_W = "W";
    public final static String SOURCE_TYPE_I = "I";
    private long bankbookNo;
    private String accountId;
    private String tradeDate;
    private String bankbookType;//D-入金 W-出金 等等
    private BigDecimal amount;
    private BigDecimal balance;
    private String sourceNo;
    private String sourceType;
    private String memo;
    private String updateTime;
    private String modifiedBy;

    @Id
    @Column(name = "bankbook_no")
    public long getBankbookNo() {
        return bankbookNo;
    }

    public void setBankbookNo(long bankbookNo) {
        this.bankbookNo = bankbookNo;
    }

    @Basic
    @Column(name = "account_id")
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "trade_date")
    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    @Basic
    @Column(name = "bankbook_type")
    public String getBankbookType() {
        return bankbookType;
    }

    public void setBankbookType(String bankbookType) {
        this.bankbookType = bankbookType;
    }

    @Basic
    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "balance")
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "source_no")
    public String getSourceNo() {
        return sourceNo;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    @Basic
    @Column(name = "source_type")
    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
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
    @Column(name = "update_time")
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "modified_by")
    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeBankbook that = (TradeBankbook) o;

        if (bankbookNo != that.bankbookNo) return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (tradeDate != null ? !tradeDate.equals(that.tradeDate) : that.tradeDate != null) return false;
        if (bankbookType != null ? !bankbookType.equals(that.bankbookType) : that.bankbookType != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        if (sourceNo != null ? !sourceNo.equals(that.sourceNo) : that.sourceNo != null) return false;
        if (sourceType != null ? !sourceType.equals(that.sourceType) : that.sourceType != null) return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (modifiedBy != null ? !modifiedBy.equals(that.modifiedBy) : that.modifiedBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (bankbookNo ^ (bankbookNo >>> 32));
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (tradeDate != null ? tradeDate.hashCode() : 0);
        result = 31 * result + (bankbookType != null ? bankbookType.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (sourceNo != null ? sourceNo.hashCode() : 0);
        result = 31 * result + (sourceType != null ? sourceType.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (modifiedBy != null ? modifiedBy.hashCode() : 0);
        return result;
    }
}
