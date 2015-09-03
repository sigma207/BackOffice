package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.BoUser;
import com.jelly.jt8.bo.model.BoUserRole;

import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
public interface BoUserService {
    BoUser login(String login_id, String password) throws Exception;
    BoUser fastLogin(String login_id) throws Exception;
    List<BoUser> select() throws Exception;
    List<BoUserRole> selectUserRole(int user_id) throws Exception;
    void insert(BoUser object) throws Exception;
    void update(BoUser object) throws Exception;
    void delete(BoUser object) throws Exception;
    void allocateUserRole(BoUser object) throws Exception;
}
