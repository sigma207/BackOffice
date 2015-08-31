package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.BoUserRole;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
public interface BoUserRoleDao {
    List<BoUserRole> select(int user_id)throws Exception;
    void insert(Connection conn, BoUserRole object) throws Exception;
    void delete(Connection conn, int user_id) throws Exception;
}
