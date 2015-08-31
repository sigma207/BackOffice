package com.jelly.jt8.bo.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
@Entity
@Table(name = "bo_organization", schema = "dbo", catalog = "jt8")
public class BoOrganization extends BaseModel{
    private int organizationId;
    private String organizationCode;
    private String organizationName;
    private Integer parentOrganizationId;
    private int sequence;
    private List<BoOrganization> children;

    @Id
    @Column(name = "organization_id", insertable = false)
    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    @Basic
    @Column(name = "organization_code")
    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    @Basic
    @Column(name = "organization_name")
    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Basic
    @Column(name = "parent_organization_id")
    public Integer getParentOrganizationId() {
        return parentOrganizationId;
    }

    public void setParentOrganizationId(Integer parentOrganizationId) {
        this.parentOrganizationId = parentOrganizationId;
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
    public List<BoOrganization> getChildren() {
        return children;
    }

    public void setChildren(List<BoOrganization> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoOrganization that = (BoOrganization) o;

        if (organizationId != that.organizationId) return false;
        if (sequence != that.sequence) return false;
        if (organizationCode != null ? !organizationCode.equals(that.organizationCode) : that.organizationCode != null)
            return false;
        if (organizationName != null ? !organizationName.equals(that.organizationName) : that.organizationName != null)
            return false;
        if (parentOrganizationId != null ? !parentOrganizationId.equals(that.parentOrganizationId) : that.parentOrganizationId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = organizationId;
        result = 31 * result + (organizationCode != null ? organizationCode.hashCode() : 0);
        result = 31 * result + (organizationName != null ? organizationName.hashCode() : 0);
        result = 31 * result + (parentOrganizationId != null ? parentOrganizationId.hashCode() : 0);
        result = 31 * result + sequence;
        return result;
    }
}
