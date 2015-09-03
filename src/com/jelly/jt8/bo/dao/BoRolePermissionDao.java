package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.BoRolePermission;
import com.jelly.jt8.bo.model.BoUserRole;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
public interface BoRolePermissionDao {
    List<BoRolePermission> select(int roleId) throws Exception;
    List<BoRolePermission> select(List<BoUserRole> boUserRoleList) throws Exception;
    void insert(Connection conn, BoRolePermission object) throws Exception;
    void delete(Connection conn, int roleId) throws Exception;
}
