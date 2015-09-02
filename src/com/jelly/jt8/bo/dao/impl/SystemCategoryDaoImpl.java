package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.SystemCategoryDao;
import com.jelly.jt8.bo.model.SystemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/9/2.
 */
@Repository("SystemCategoryDao")
public class SystemCategoryDaoImpl extends BaseDao implements SystemCategoryDao {
    public SystemCategoryDaoImpl() {
        super(SystemCategory.class);
    }

    @Override
    public List<SystemCategory> select() throws Exception {
        List<SystemCategory> list = new LinkedList<SystemCategory>();
        selectByObject(jt8Ds.getConnection(), list);
        return list;
    }
}
