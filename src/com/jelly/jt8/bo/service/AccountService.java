package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.BoUser;

import java.util.List;

/**
 * Created by user on 2015/9/3.
 */
public interface AccountService {
    List<BoUser> selectChildren(BoUser object) throws Exception;
}
