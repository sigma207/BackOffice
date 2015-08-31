package com.jelly.jt8.bo.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
@Entity
@javax.persistence.Table(name = "bo_user", schema = "dbo", catalog = "jt8")
public class BoUser extends BaseModel{
    private int userId;

    @Id
    @javax.persistence.Column(name = "user_id", insertable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private String loginId;

    @Basic
    @javax.persistence.Column(name = "login_id")
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    private String password;

    @Basic
    @javax.persistence.Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String createTime;

    @Basic
    @javax.persistence.Column(name = "create_time")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    private int permission;

    @Basic
    @javax.persistence.Column(name = "permission")
    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    private int concurrent;

    @Basic
    @javax.persistence.Column(name = "concurrent")
    public int getConcurrent() {
        return concurrent;
    }

    public void setConcurrent(int concurrent) {
        this.concurrent = concurrent;
    }

    private int retry;

    @Basic
    @javax.persistence.Column(name = "retry")
    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    private int maxRetry;

    @Basic
    @javax.persistence.Column(name = "max_retry")
    public int getMaxRetry() {
        return maxRetry;
    }

    public void setMaxRetry(int maxRetry) {
        this.maxRetry = maxRetry;
    }

    private String activeDate;

    @Basic
    @javax.persistence.Column(name = "active_date")
    public String getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(String activeDate) {
        this.activeDate = activeDate;
    }

    private Integer duration;

    @Basic
    @javax.persistence.Column(name = "duration")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    private String expireDate;

    @Basic
    @javax.persistence.Column(name = "expire_date")
    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
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

    private Integer isActive;

    @Basic
    @javax.persistence.Column(name = "is_active")
    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    private String loginTime;

    @Basic
    @javax.persistence.Column(name = "login_time")
    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    private String lastLoginTime;

    @Basic
    @javax.persistence.Column(name = "last_login_time")
    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    private String orgId;

    @Basic
    @javax.persistence.Column(name = "org_id")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    private Integer organizationId;

    @Basic
    @javax.persistence.Column(name = "organization_id")
    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    private Integer parentUserId;

    @Basic
    @javax.persistence.Column(name = "parent_user_id")
    public Integer getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(Integer parentUserId) {
        this.parentUserId = parentUserId;
    }

    private List<BoUserRole> boUserRoleList;

    @Transient
    public List<BoUserRole> getBoUserRoleList() {
        return boUserRoleList;
    }

    public void setBoUserRoleList(List<BoUserRole> boUserRoleList) {
        this.boUserRoleList = boUserRoleList;
    }

    private BoUser parentBoUser;

    @Transient
    public BoUser getParentBoUser() {
        return parentBoUser;
    }

    public void setParentBoUser(BoUser parentBoUser) {
        this.parentBoUser = parentBoUser;
    }

    private BoOrganization boOrganization;

    @Transient
    public BoOrganization getBoOrganization() {
        return boOrganization;
    }

    public void setBoOrganization(BoOrganization boOrganization) {
        this.boOrganization = boOrganization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoUser boUser = (BoUser) o;

        if (userId != boUser.userId) return false;
        if (permission != boUser.permission) return false;
        if (concurrent != boUser.concurrent) return false;
        if (retry != boUser.retry) return false;
        if (maxRetry != boUser.maxRetry) return false;
        if (loginId != null ? !loginId.equals(boUser.loginId) : boUser.loginId != null) return false;
        if (password != null ? !password.equals(boUser.password) : boUser.password != null) return false;
        if (createTime != null ? !createTime.equals(boUser.createTime) : boUser.createTime != null) return false;
        if (activeDate != null ? !activeDate.equals(boUser.activeDate) : boUser.activeDate != null) return false;
        if (duration != null ? !duration.equals(boUser.duration) : boUser.duration != null) return false;
        if (expireDate != null ? !expireDate.equals(boUser.expireDate) : boUser.expireDate != null) return false;
        if (updateTime != null ? !updateTime.equals(boUser.updateTime) : boUser.updateTime != null) return false;
        if (isActive != null ? !isActive.equals(boUser.isActive) : boUser.isActive != null) return false;
        if (loginTime != null ? !loginTime.equals(boUser.loginTime) : boUser.loginTime != null) return false;
        if (lastLoginTime != null ? !lastLoginTime.equals(boUser.lastLoginTime) : boUser.lastLoginTime != null)
            return false;
        if (orgId != null ? !orgId.equals(boUser.orgId) : boUser.orgId != null) return false;
        if (organizationId != null ? !organizationId.equals(boUser.organizationId) : boUser.organizationId != null)
            return false;
        if (parentUserId != null ? !parentUserId.equals(boUser.parentUserId) : boUser.parentUserId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (loginId != null ? loginId.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + permission;
        result = 31 * result + concurrent;
        result = 31 * result + retry;
        result = 31 * result + maxRetry;
        result = 31 * result + (activeDate != null ? activeDate.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (expireDate != null ? expireDate.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + (loginTime != null ? loginTime.hashCode() : 0);
        result = 31 * result + (lastLoginTime != null ? lastLoginTime.hashCode() : 0);
        result = 31 * result + (orgId != null ? orgId.hashCode() : 0);
        result = 31 * result + (organizationId != null ? organizationId.hashCode() : 0);
        result = 31 * result + (parentUserId != null ? parentUserId.hashCode() : 0);
        return result;
    }
}
