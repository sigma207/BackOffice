package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.BoIbAccount;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/9/9.
 */
public interface BoIbAccountDao {
    BoIbAccount select(int userId) throws Exception;
    List<BoIbAccount> select() throws Exception;
    List<BoIbAccount> selectChildren(int userId) throws Exception;
    void insert(Connection conn,BoIbAccount object) throws Exception;
    void delete(Connection conn,BoIbAccount object) throws Exception;
    void update(Connection conn,BoIbAccount object) throws Exception;
}
