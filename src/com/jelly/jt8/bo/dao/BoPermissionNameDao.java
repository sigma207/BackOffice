package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.BoPermission;
import com.jelly.jt8.bo.model.BoPermissionName;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
public interface BoPermissionNameDao {
    List<BoPermissionName> select() throws Exception;
    void insert(Connection conn, BoPermissionName object) throws Exception;
    void update(Connection conn, BoPermissionName object) throws Exception;
    void delete(Connection conn, int permission_id) throws Exception;
}
