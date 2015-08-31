package com.jelly.jt8.bo.model;

import javax.persistence.*;

/**
 * Created by user on 2015/8/31.
 */
@Entity
@Table(name = "bo_permission_name", schema = "dbo", catalog = "jt8")
@IdClass(BoPermissionNamePK.class)
public class BoPermissionName {
    private int permissionId;
    private String language;
    private String name;

    @Id
    @Column(name = "permission_id")
    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    @Id
    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoPermissionName that = (BoPermissionName) o;

        if (permissionId != that.permissionId) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = permissionId;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
