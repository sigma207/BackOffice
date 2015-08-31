package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.BoOrganization;

import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
public interface BoOrganizationService {
    List<BoOrganization> select() throws Exception;

    BoOrganization select(int id) throws Exception;

    List<BoOrganization> selectWithChildren(int id) throws Exception;

    void insert(BoOrganization object) throws Exception;

    void delete(BoOrganization object) throws Exception;

    void update(BoOrganization object) throws Exception;
}
