package com.jelly.jt8.bo.model;

/**
 * Created by user on 2015/8/20.
 * 因web前端使用restangular框架
 * 需指定一個欄位當restful的id
 */
public class BaseModel {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
