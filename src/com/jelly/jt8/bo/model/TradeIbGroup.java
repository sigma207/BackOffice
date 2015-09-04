package com.jelly.jt8.bo.model;

import javax.persistence.*;

/**
 * Created by user on 2015/9/4.
 */
@Entity
@Table(name = "trade_ib_group", schema = "dbo", catalog = "jt8")
@IdClass(TradeIbGroupPK.class)
public class TradeIbGroup {
    private int userId;
    private int groupId;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "group_id")
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeIbGroup that = (TradeIbGroup) o;

        if (userId != that.userId) return false;
        if (groupId != that.groupId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + groupId;
        return result;
    }
}
