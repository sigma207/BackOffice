package com.jelly.jt8.bo.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by user on 2015/9/9.
 */
@Entity
@Table(name = "bo_ib_account", schema = "dbo", catalog = "jt8")
public class BoIbAccount extends BaseModel{
    private int ibUserId;
    private int isRoot;
    private int levelNo;
    private String lineage;
    private String updateTime;
    private Long treeIndex;
    private String modifiedBy;
    private String createTime;
    private Integer parentIbUserId;
    private BigDecimal commission;

    @Id
    @Column(name = "ib_user_id")
    public int getIbUserId() {
        return ibUserId;
    }

    public void setIbUserId(int ibUserId) {
        this.ibUserId = ibUserId;
    }

    @Basic
    @Column(name = "is_root")
    public int getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(int isRoot) {
        this.isRoot = isRoot;
    }

    @Basic
    @Column(name = "level_no")
    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    @Basic
    @Column(name = "lineage")
    public String getLineage() {
        return lineage;
    }

    public void setLineage(String lineage) {
        this.lineage = lineage;
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
    @Column(name = "tree_index")
    public Long getTreeIndex() {
        return treeIndex;
    }

    public void setTreeIndex(Long treeIndex) {
        this.treeIndex = treeIndex;
    }

    @Basic
    @Column(name = "modified_by")
    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
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
    @Column(name = "parent_ib_user_id")
    public Integer getParentIbUserId() {
        return parentIbUserId;
    }

    public void setParentIbUserId(Integer parentIbUserId) {
        this.parentIbUserId = parentIbUserId;
    }

    @Basic
    @Column(name = "commission")
    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoIbAccount that = (BoIbAccount) o;

        if (ibUserId != that.ibUserId) return false;
        if (isRoot != that.isRoot) return false;
        if (levelNo != that.levelNo) return false;
        if (lineage != null ? !lineage.equals(that.lineage) : that.lineage != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (treeIndex != null ? !treeIndex.equals(that.treeIndex) : that.treeIndex != null) return false;
        if (modifiedBy != null ? !modifiedBy.equals(that.modifiedBy) : that.modifiedBy != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (parentIbUserId != null ? !parentIbUserId.equals(that.parentIbUserId) : that.parentIbUserId != null)
            return false;
        if (commission != null ? !commission.equals(that.commission) : that.commission != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ibUserId;
        result = 31 * result + isRoot;
        result = 31 * result + levelNo;
        result = 31 * result + (lineage != null ? lineage.hashCode() : 0);
        result = 31 * result + (parentIbUserId != null ? parentIbUserId.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (treeIndex != null ? treeIndex.hashCode() : 0);
        result = 31 * result + (modifiedBy != null ? modifiedBy.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (commission != null ? commission.hashCode() : 0);
        return result;
    }
}
