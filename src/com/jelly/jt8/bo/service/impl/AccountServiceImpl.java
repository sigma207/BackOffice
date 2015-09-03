package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.BoUserDao;
import com.jelly.jt8.bo.model.BoUser;
import com.jelly.jt8.bo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by user on 2015/9/3.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    @Qualifier("BoUserDao")
    private BoUserDao boUserDao;

    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Override
    public List<BoUser> selectChildren(BoUser object) throws Exception {

        return null;
    }
}
