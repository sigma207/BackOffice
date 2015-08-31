package com.jelly.jt8.bo.model;

import javax.persistence.*;

/**
 * Created by user on 2015/8/31.
 */
@Entity
@Table(name = "bo_user_role", schema = "dbo", catalog = "jt8")
@IdClass(BoUserRolePK.class)
public class BoUserRole {
    private int userId;
    private int roleId;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoUserRole that = (BoUserRole) o;

        if (userId != that.userId) return false;
        if (roleId != that.roleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + roleId;
        return result;
    }
}
