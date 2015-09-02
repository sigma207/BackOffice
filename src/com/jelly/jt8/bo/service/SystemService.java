package com.jelly.jt8.bo.service;

import com.jelly.jt8.bo.model.Exchange;
import com.jelly.jt8.bo.model.SystemCategory;

import java.util.List;

/**
 * Created by user on 2015/9/2.
 */
public interface SystemService {
    List<Exchange> selectExchange() throws Exception;
    List<SystemCategory> selectSystemCategory() throws Exception;
}
