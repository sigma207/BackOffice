package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.BoUser;

import java.util.List;

/**
 * Created by user on 2015/9/10.
 */
public interface IbAccountService {
    BoUser selectIb(int userId) throws Exception;
    List<BoUser> selectIbChildren(int userId) throws Exception;
    void insert(BoUser object) throws Exception;
    void delete(BoUser object) throws Exception;
    void update(BoUser object) throws Exception;
}
