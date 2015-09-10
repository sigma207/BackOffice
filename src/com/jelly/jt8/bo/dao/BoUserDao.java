package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.BoUser;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
public interface BoUserDao{
    BoUser select(String loginId) throws Exception;
    BoUser select(int userId) throws Exception;
    List<BoUser> select() throws Exception;
    List<BoUser> selectChildren(int parentUserId) throws Exception;
    List<BoUser> selectIbChildren(int parentIbUserId) throws Exception;
    List<BoUser> selectIbAccountChildren(int userId) throws Exception;
    void insert(Connection conn, BoUser object) throws Exception;
    void update(Connection conn, BoUser object) throws Exception;
    void delete(Connection conn, BoUser object) throws Exception;
}
