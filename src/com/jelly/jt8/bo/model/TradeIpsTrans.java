package com.jelly.jt8.bo.model;

import com.jelly.jt8.bo.dao.impl.BaseDao;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by user on 2015/9/24.
 */
@Entity
@Table(name = "trade_ips_trans", schema = "dbo", catalog = "jt8")
public class TradeIpsTrans extends BaseModel{
    public final static String BO_STATUS_P = "P";
    public final static String BO_STATUS_R = "R";
    public final static String BO_STATUS_A = "A";
    public final static String BO_STATUS_T = "T";
    private long billNo;
    private String accountId;
    private String tradeDate;
    private String currency;
    private BigDecimal requestAmount;
    private String requestType;
    private String boStatus;
    private String boErrorCode;
    private String ipsId;
    private String ipsName;
    private String ipsBillNo;
    private BigDecimal ipsAmount;
    private String ipsStatus;
    private String ipsErrorCode;
    private Long bankbookNo;
    private String receiveTime;
    private String updateTime;
    private String modifiedBy;
    private String memo;

    @Id
    @Column(name = "bill_no")
    public long getBillNo() {
        return billNo;
    }

    public void setBillNo(long billNo) {
        this.billNo = billNo;
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
    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "request_amount")
    public BigDecimal getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(BigDecimal requestAmount) {
        this.requestAmount = requestAmount;
    }

    @Basic
    @Column(name = "request_type")
    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    @Basic
    @Column(name = "bo_status")
    public String getBoStatus() {
        return boStatus;
    }

    public void setBoStatus(String boStatus) {
        this.boStatus = boStatus;
    }

    @Basic
    @Column(name = "bo_error_code")
    public String getBoErrorCode() {
        return boErrorCode;
    }

    public void setBoErrorCode(String boErrorCode) {
        this.boErrorCode = boErrorCode;
    }

    @Basic
    @Column(name = "ips_id")
    public String getIpsId() {
        return ipsId;
    }

    public void setIpsId(String ipsId) {
        this.ipsId = ipsId;
    }

    @Basic
    @Column(name = "ips_name")
    public String getIpsName() {
        return ipsName;
    }

    public void setIpsName(String ipsName) {
        this.ipsName = ipsName;
    }

    @Basic
    @Column(name = "ips_bill_no")
    public String getIpsBillNo() {
        return ipsBillNo;
    }

    public void setIpsBillNo(String ipsBillNo) {
        this.ipsBillNo = ipsBillNo;
    }

    @Basic
    @Column(name = "ips_amount")
    public BigDecimal getIpsAmount() {
        return ipsAmount;
    }

    public void setIpsAmount(BigDecimal ipsAmount) {
        this.ipsAmount = ipsAmount;
    }

    @Basic
    @Column(name = "ips_status")
    public String getIpsStatus() {
        return ipsStatus;
    }

    public void setIpsStatus(String ipsStatus) {
        this.ipsStatus = ipsStatus;
    }

    @Basic
    @Column(name = "ips_error_code")
    public String getIpsErrorCode() {
        return ipsErrorCode;
    }

    public void setIpsErrorCode(String ipsErrorCode) {
        this.ipsErrorCode = ipsErrorCode;
    }

    @Basic
    @Column(name = "bankbook_no")
    public Long getBankbookNo() {
        return bankbookNo;
    }

    public void setBankbookNo(Long bankbookNo) {
        this.bankbookNo = bankbookNo;
    }

    @Basic
    @Column(name = "receive_time")
    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
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

        TradeIpsTrans that = (TradeIpsTrans) o;

        if (billNo != that.billNo) return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (tradeDate != null ? !tradeDate.equals(that.tradeDate) : that.tradeDate != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (requestAmount != null ? !requestAmount.equals(that.requestAmount) : that.requestAmount != null)
            return false;
        if (requestType != null ? !requestType.equals(that.requestType) : that.requestType != null) return false;
        if (boStatus != null ? !boStatus.equals(that.boStatus) : that.boStatus != null) return false;
        if (boErrorCode != null ? !boErrorCode.equals(that.boErrorCode) : that.boErrorCode != null) return false;
        if (ipsId != null ? !ipsId.equals(that.ipsId) : that.ipsId != null) return false;
        if (ipsName != null ? !ipsName.equals(that.ipsName) : that.ipsName != null) return false;
        if (ipsBillNo != null ? !ipsBillNo.equals(that.ipsBillNo) : that.ipsBillNo != null) return false;
        if (ipsAmount != null ? !ipsAmount.equals(that.ipsAmount) : that.ipsAmount != null) return false;
        if (ipsStatus != null ? !ipsStatus.equals(that.ipsStatus) : that.ipsStatus != null) return false;
        if (ipsErrorCode != null ? !ipsErrorCode.equals(that.ipsErrorCode) : that.ipsErrorCode != null) return false;
        if (bankbookNo != null ? !bankbookNo.equals(that.bankbookNo) : that.bankbookNo != null) return false;
        if (receiveTime != null ? !receiveTime.equals(that.receiveTime) : that.receiveTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (modifiedBy != null ? !modifiedBy.equals(that.modifiedBy) : that.modifiedBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (billNo ^ (billNo >>> 32));
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (tradeDate != null ? tradeDate.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (requestAmount != null ? requestAmount.hashCode() : 0);
        result = 31 * result + (requestType != null ? requestType.hashCode() : 0);
        result = 31 * result + (boStatus != null ? boStatus.hashCode() : 0);
        result = 31 * result + (boErrorCode != null ? boErrorCode.hashCode() : 0);
        result = 31 * result + (ipsId != null ? ipsId.hashCode() : 0);
        result = 31 * result + (ipsName != null ? ipsName.hashCode() : 0);
        result = 31 * result + (ipsBillNo != null ? ipsBillNo.hashCode() : 0);
        result = 31 * result + (ipsAmount != null ? ipsAmount.hashCode() : 0);
        result = 31 * result + (ipsStatus != null ? ipsStatus.hashCode() : 0);
        result = 31 * result + (ipsErrorCode != null ? ipsErrorCode.hashCode() : 0);
        result = 31 * result + (bankbookNo != null ? bankbookNo.hashCode() : 0);
        result = 31 * result + (receiveTime != null ? receiveTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (modifiedBy != null ? modifiedBy.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "memo")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
