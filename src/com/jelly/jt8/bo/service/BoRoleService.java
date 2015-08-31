package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.BoRole;
import com.jelly.jt8.bo.model.BoRolePermission;

import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
public interface BoRoleService {
    List<BoRole> select() throws Exception;
    void insert(BoRole object) throws Exception;
    void delete(BoRole object) throws Exception;
    void update(BoRole object) throws Exception;
    List<BoRolePermission> selectRolePermission(int roleId) throws Exception;
    void allocatePermission(BoRole object) throws Exception;
}
