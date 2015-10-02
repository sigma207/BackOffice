package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.BoOrganizationDao;
import com.jelly.jt8.bo.model.BoOrganization;
import com.jelly.jt8.bo.service.BoOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by user on 2015/8/31.
 */
@Service("boOrganizationService")
public class BoOrganizationServiceImpl implements BoOrganizationService {
    @Autowired
    @Qualifier("BoOrganizationDao")
    private BoOrganizationDao boOrganizationDao;

    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Override
    public List<BoOrganization> select() throws Exception {
        List<BoOrganization> list = boOrganizationDao.select();
        return group(list);
    }

    @Override
    public BoOrganization select(int id) throws Exception {
        return boOrganizationDao.select(id);
    }

    @Override
    public List<BoOrganization> selectWithChildren(int id) throws Exception {
        List<BoOrganization> list = boOrganizationDao.selectWithChildren(id);
        return group(list);
    }

    @Override
    public void insert(BoOrganization object) throws Exception {
        Connection conn = null;

        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            boOrganizationDao.insert(conn, object);
            conn.commit();
        }catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                }catch (SQLException se){
                    se.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(BoOrganization object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);

            recursiveDelete(conn, object);
            conn.commit();
        }catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                }catch (SQLException se){
                    se.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(BoOrganization object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            boOrganizationDao.update(conn, object);
            conn.commit();
        }catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                }catch (SQLException se){
                    se.printStackTrace();
                }
            }
        }
    }

    private void recursiveDelete(Connection conn, BoOrganization organization) throws Exception {
        List<BoOrganization> children = organization.getChildren();
        if (children != null) {
            for (BoOrganization child : children) {
                recursiveDelete(conn, child);
            }
        }
        boOrganizationDao.delete(conn, organization);
    }

    /**
     * 將資料依parent分組,可能是tree中的任一node開始,也有可能是從root開始
     * @param organizationList
     * @return
     * @throws Exception
     */
    private List<BoOrganization> group(List<BoOrganization> organizationList) throws Exception{
        List<BoOrganization> treeList = new ArrayList<BoOrganization>();
        Map<String, List<BoOrganization>> groupMap = new HashMap<String,List<BoOrganization>>();
        Set<Integer> keySet = new HashSet<Integer>();
        List<BoOrganization> children = null;
        String key = null;
        String parentKey = null;
        for(BoOrganization organization:organizationList){
            parentKey = String.valueOf(organization.getParentOrganizationId());
            children = groupMap.get(parentKey);
            if(children==null){
                children = new ArrayList<BoOrganization>();
                groupMap.put(parentKey,children);
            }
            children.add(organization);
            keySet.add(organization.getOrganizationId());
        }
        for(BoOrganization organization:organizationList){
            key = String.valueOf(organization.getOrganizationId());
            children = groupMap.get(key);
            if(children!=null ){
                if(!keySet.contains(organization.getParentOrganizationId())) {
                    treeList.add(organization);
                }
                organization.setChildren(children);
            }else{
                if(organization.getParentOrganizationId()==null){
                    treeList.add(organization);
                }
            }
        }
        return treeList;
    }
}
