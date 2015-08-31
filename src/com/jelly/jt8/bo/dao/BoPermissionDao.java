package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.BoPermission;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
public interface BoPermissionDao {
    List<BoPermission> select() throws Exception;
    void insert(Connection conn, BoPermission object) throws Exception;
    void update(Connection conn, BoPermission object) throws Exception;
    void delete(Connection conn, BoPermission object) throws Exception;
}
