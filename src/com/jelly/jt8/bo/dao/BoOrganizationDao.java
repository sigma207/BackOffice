package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.BoOrganization;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
public interface BoOrganizationDao {
    List<BoOrganization> select() throws Exception;
    BoOrganization select(int id) throws Exception;
    List<BoOrganization> selectWithChildren(int id) throws Exception;
    void insert(Connection conn,BoOrganization object) throws Exception;
    void update(Connection conn,BoOrganization object) throws Exception;
    void delete(Connection conn,BoOrganization object) throws Exception;
}
