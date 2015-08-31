package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.BoRolePermission;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
public interface BoRolePermissionDao {
    List<BoRolePermission> select(int roleId) throws Exception;
    void insert(Connection conn, BoRolePermission object) throws Exception;
    void delete(Connection conn, int roleId) throws Exception;
}
