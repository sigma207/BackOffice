package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.BoUser;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
public interface BoUserDao{
    BoUser login(String loginId) throws Exception;
    List<BoUser> select() throws Exception;
    void insert(Connection conn, BoUser object) throws Exception;
    void update(Connection conn, BoUser object) throws Exception;
    void delete(Connection conn, BoUser object) throws Exception;
}
