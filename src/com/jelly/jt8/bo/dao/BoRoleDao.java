package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.BoRole;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
public interface BoRoleDao {
    List<BoRole> select() throws Exception;

    BoRole select(String roleCode) throws Exception;

    void insert(Connection conn, BoRole object) throws Exception;

    void update(Connection conn, BoRole object) throws Exception;

    void delete(Connection conn, BoRole object) throws Exception;
}
