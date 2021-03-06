package com.jelly.jt8.bo.service.impl;

import com.jelly.jt8.bo.dao.BoPermissionDao;
import com.jelly.jt8.bo.dao.BoPermissionNameDao;
import com.jelly.jt8.bo.model.*;
import com.jelly.jt8.bo.service.BoPermissionService;
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
@Service("boPermissionService")
public class BoPermissionServiceImpl implements BoPermissionService {
    @Autowired
    @Qualifier("BoPermissionDao")
    private BoPermissionDao boPermissionDao;

    @Autowired
    @Qualifier("BoPermissionNameDao")
    private BoPermissionNameDao boPermissionNameDao;

    @Autowired
    @Qualifier("jt8Ds")
    private DataSource jt8Ds;

    @Override
    public List<BoPermission> select() throws Exception {
        List<BoPermission> permissionList = boPermissionDao.select();
        List<BoPermissionName> permissionNameList = boPermissionNameDao.select();
        return treeBoPermission(permissionList,permissionNameList);
    }

    /**
     * 依boRolePermissionList過濾可用的權限
     * @param boRolePermissionList
     * @return
     * @throws Exception
     */
    @Override
    public List<BoPermission> select(List<BoRolePermission> boRolePermissionList) throws Exception {
        List<BoPermission> permissionList = boPermissionDao.select();
        List<BoPermissionName> permissionNameList = boPermissionNameDao.select();
        List<BoPermission> filterPermissionList = new ArrayList<BoPermission>();
        Set<Integer> permissionIdSet = new HashSet<Integer>();
        for(BoRolePermission boRolePermission:boRolePermissionList){
            permissionIdSet.add(boRolePermission.getPermissionId());
        }

        for(BoPermission boPermission:permissionList){
            if(permissionIdSet.contains(boPermission.getPermissionId())){
                filterPermissionList.add(boPermission);
            }
        }
        return treeBoPermission(filterPermissionList,permissionNameList);
    }

    /**
     * 將資料依parent分組,一併將語系的內容存入
     * @param permissionList
     * @param permissionNameList
     * @return
     * @throws Exception
     */
    private List<BoPermission> treeBoPermission(List<BoPermission> permissionList, List<BoPermissionName> permissionNameList) throws Exception{
        List<BoPermission> treeList = new ArrayList<BoPermission>();
        Map<String, BoPermission> treePermissionMap = new HashMap<String, BoPermission>();
        Map<String, List<BoPermissionName>> nameMap = new HashMap<String, List<BoPermissionName>>();
        String key = null;
        List<BoPermissionName> temp = null;
        for (BoPermissionName permissionName : permissionNameList) {
            key = String.valueOf(permissionName.getPermissionId());
            temp = nameMap.get(key);
            if (temp == null) {
                temp = new ArrayList<BoPermissionName>();
                nameMap.put(key, temp);
            }
            temp.add(permissionName);
        }

        Map<String, String> permissionNameMap = null;
        for (BoPermission permission : permissionList) {
            key = String.valueOf(permission.getPermissionId());
            permissionNameMap = new HashMap<String, String>();
            temp = nameMap.get(key);
            for (BoPermissionName permissionName : temp) {
                permissionNameMap.put(permissionName.getLanguage(), permissionName.getName());
            }
            permission.setPermissionNameMap(permissionNameMap);
            if (permission.getParentPermissionId() == null) {
                treeList.add(permission);
                treePermissionMap.put(String.valueOf(permission.getPermissionId()), permission);
            } else {
                BoPermission parentPermission = treePermissionMap.get(String.valueOf(permission.getParentPermissionId()));
                List<BoPermission> children = parentPermission.getChildren();
                if (children == null) {
                    children = new ArrayList<BoPermission>();
                    parentPermission.setChildren(children);
                }
                children.add(permission);
                treePermissionMap.put(String.valueOf(permission.getPermissionId()), permission);
            }
        }
        return treeList;
    }

    @Override
    public void insert(BoPermission object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            boPermissionDao.insert(conn, object);
            Map<String, String> permissionNameMap = object.getPermissionNameMap();
            Set<String> keys = permissionNameMap.keySet();
            BoPermissionName boPermissionName = null;
            for (String key : keys) {
                boPermissionName = new BoPermissionName();
                boPermissionName.setPermissionId(object.getPermissionId());
                boPermissionName.setLanguage(key);
                boPermissionName.setName(permissionNameMap.get(key));
                boPermissionNameDao.insert(conn, boPermissionName);
            }
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
    public void update(BoPermission object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);
            boPermissionDao.update(conn, object);

            Map<String, String> permissionNameMap = object.getPermissionNameMap();
            Set<String> keys = permissionNameMap.keySet();
            BoPermissionName boPermissionName = null;
            for (String key : keys) {
                boPermissionName = new BoPermissionName();
                boPermissionName.setPermissionId(object.getPermissionId());
                boPermissionName.setLanguage(key);
                boPermissionName.setName(permissionNameMap.get(key));
                boPermissionNameDao.update(conn, boPermissionName);
            }
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
    public void delete(BoPermission object) throws Exception {
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);

            recursiveRemovePermission(conn, object);
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

    /**
     * 遞迴刪除權限資料(包含children)
     * @param conn
     * @param permission
     * @throws Exception
     */
    private void recursiveRemovePermission(Connection conn, BoPermission permission) throws Exception {
        List<BoPermission> children = permission.getChildren();
        if (children != null) {
            for (BoPermission child : children) {
                recursiveRemovePermission(conn, child);
            }
        }
        boPermissionNameDao.delete(conn, permission.getPermissionId());
        boPermissionDao.delete(conn, permission);
    }

    /**
     * 變更權限所在Parent底下的順序
     * 需重新計算同一個parent底下的所有權限順序
     * @param permissionMoveSetting
     * @throws Exception
     */
    @Override
    public void move(PermissionMoveSetting permissionMoveSetting) throws Exception {
        List<BoPermission> moveNodes = permissionMoveSetting.getMoveNodes();
        String moveType = permissionMoveSetting.getMoveType();
        String moveAction = permissionMoveSetting.getMoveAction();

        BoPermission targetNode = permissionMoveSetting.getTargetNode();
        BoPermission treeNode = permissionMoveSetting.getTreeNode();
        int sequence = 0;
        int treeIndex = 0;

        for(BoPermission permission:moveNodes){
            if(permission.getPermissionId()==treeNode.getPermissionId()){
                break;
            }
            treeIndex++;
        }

        BoPermission p = moveNodes.remove(treeIndex);

        if(moveAction.equals("moveFirst")){
            moveNodes.add(0,p);
        }else if(moveAction.equals("moveLast")){
            moveNodes.add(p);
        }else if(moveAction.equals("moveUp")){
            moveNodes.add(treeIndex-1,p);
        }else if(moveAction.equals("moveDown")){
            moveNodes.add(treeIndex+1,p);
        }

        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            conn.setAutoCommit(false);

            for(BoPermission permission:moveNodes){
                permission.setSequence( sequence++);
                boPermissionDao.update(conn, permission);
            }
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
}
