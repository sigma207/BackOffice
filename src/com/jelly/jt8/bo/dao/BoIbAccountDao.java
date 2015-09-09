package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.BoIbAccount;

import java.util.List;

/**
 * Created by user on 2015/9/9.
 */
public interface BoIbAccountDao {
    List<BoIbAccount> select() throws Exception;
}
