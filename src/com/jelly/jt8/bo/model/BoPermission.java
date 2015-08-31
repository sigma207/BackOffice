package com.jelly.jt8.bo.model;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2015/8/31.
 */
@Entity
@Table(name = "bo_permission", schema = "dbo", catalog = "jt8")
public class BoPermission extends BaseModel{
    private int permissionId;
    private String permissionCode;
    private Integer parentPermissionId;
    private int sequence;
    private Map<String,String> permissionNameMap;
    private List<BoPermission> children;


    @Id
    @Column(name = "permission_id", insertable = false)
    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    @Basic
    @Column(name = "permission_code")
    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    @Basic
    @Column(name = "parent_permission_id")
    public Integer getParentPermissionId() {
        return parentPermissionId;
    }

    public void setParentPermissionId(Integer parentPermissionId) {
        this.parentPermissionId = parentPermissionId;
    }

    @Basic
    @Column(name = "sequence")
    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Transient
    public Map<String, String> getPermissionNameMap() {
        return permissionNameMap;
    }

    public void setPermissionNameMap(Map<String, String> permissionNameMap) {
        this.permissionNameMap = permissionNameMap;
    }

    @Transient
    public List<BoPermission> getChildren() {
        return children;
    }

    public void setChildren(List<BoPermission> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoPermission that = (BoPermission) o;

        if (permissionId != that.permissionId) return false;
        if (sequence != that.sequence) return false;
        if (permissionCode != null ? !permissionCode.equals(that.permissionCode) : that.permissionCode != null)
            return false;
        if (parentPermissionId != null ? !parentPermissionId.equals(that.parentPermissionId) : that.parentPermissionId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = permissionId;
        result = 31 * result + (permissionCode != null ? permissionCode.hashCode() : 0);
        result = 31 * result + (parentPermissionId != null ? parentPermissionId.hashCode() : 0);
        result = 31 * result + sequence;
        return result;
    }
}
