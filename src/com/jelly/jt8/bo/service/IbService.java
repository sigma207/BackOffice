package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.BoUser;

import java.sql.Connection;
import java.util.List;

/**
 * Created by user on 2015/9/7.
 */
public interface IbService {
    List<BoUser> select(int parentUserId) throws Exception;
    void insert(BoUser object) throws Exception;
    void delete(BoUser object) throws Exception;
    void update(BoUser object) throws Exception;
}
