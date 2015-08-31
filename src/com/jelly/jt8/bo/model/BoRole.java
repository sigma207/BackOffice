package com.jelly.jt8.bo.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
@Entity
@Table(name = "bo_role", schema = "dbo", catalog = "jt8")
public class BoRole extends BaseModel{
    private int roleId;
    private Integer parentRoleId;
    private String roleCode;
    private String roleName;
    private String updateTime;
    private byte[] rv;
    private List<BoRolePermission> boRolePermissionList;

    @Id
    @Column(name = "role_id", insertable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "parent_role_id")
    public Integer getParentRoleId() {
        return parentRoleId;
    }

    public void setParentRoleId(Integer parentRoleId) {
        this.parentRoleId = parentRoleId;
    }

    @Basic
    @Column(name = "role_code")
    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
    @Column(name = "rv")
    public byte[] getRv() {
        return rv;
    }

    public void setRv(byte[] rv) {
        this.rv = rv;
    }

    @Transient
    public List<BoRolePermission> getBoRolePermissionList() {
        return boRolePermissionList;
    }

    public void setBoRolePermissionList(List<BoRolePermission> boRolePermissionList) {
        this.boRolePermissionList = boRolePermissionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoRole boRole = (BoRole) o;

        if (roleId != boRole.roleId) return false;
        if (parentRoleId != null ? !parentRoleId.equals(boRole.parentRoleId) : boRole.parentRoleId != null)
            return false;
        if (roleCode != null ? !roleCode.equals(boRole.roleCode) : boRole.roleCode != null) return false;
        if (roleName != null ? !roleName.equals(boRole.roleName) : boRole.roleName != null) return false;
        if (updateTime != null ? !updateTime.equals(boRole.updateTime) : boRole.updateTime != null) return false;
        if (!Arrays.equals(rv, boRole.rv)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + (parentRoleId != null ? parentRoleId.hashCode() : 0);
        result = 31 * result + (roleCode != null ? roleCode.hashCode() : 0);
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (rv != null ? Arrays.hashCode(rv) : 0);
        return result;
    }
}
