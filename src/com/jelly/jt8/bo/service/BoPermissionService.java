package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.BoPermission;
import com.jelly.jt8.bo.model.PermissionMoveSetting;

import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
public interface BoPermissionService {
    List<BoPermission> select() throws Exception;
    void insert(BoPermission object) throws Exception;
    void update(BoPermission object) throws Exception;
    void delete(BoPermission object) throws Exception;
    void move(PermissionMoveSetting permissionMoveSetting) throws Exception;
}
