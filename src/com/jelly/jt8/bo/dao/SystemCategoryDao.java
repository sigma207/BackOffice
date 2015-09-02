package com.jelly.jt8.bo.dao;

import com.jelly.jt8.bo.model.SystemCategory;

import java.util.List;

/**
 * Created by user on 2015/9/2.
 */
public interface SystemCategoryDao {
    List<SystemCategory> select() throws Exception;
}
