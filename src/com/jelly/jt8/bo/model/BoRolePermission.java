package com.jelly.jt8.bo.model;

import javax.persistence.*;

/**
 * Created by user on 2015/8/31.
 */
@Entity
@Table(name = "bo_role_permission", schema = "dbo", catalog = "jt8")
@IdClass(BoRolePermissionPK.class)
public class BoRolePermission {
    private int roleId;
    private int permissionId;

    @Id
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Id
    @Column(name = "permission_id")
    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoRolePermission that = (BoRolePermission) o;

        if (roleId != that.roleId) return false;
        if (permissionId != that.permissionId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + permissionId;
        return result;
    }
}
