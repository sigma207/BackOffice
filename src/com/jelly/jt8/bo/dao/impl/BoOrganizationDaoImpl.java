package com.jelly.jt8.bo.dao.impl;

import com.jelly.jt8.bo.dao.BoOrganizationDao;
import com.jelly.jt8.bo.model.BoOrganization;
import com.jelly.jt8.bo.model.BoUser;
import com.jelly.jt8.bo.model.SymbolHoliday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
@Repository("BoOrganizationDao")
public class BoOrganizationDaoImpl extends BaseDao implements BoOrganizationDao {
    private final static String WHERE_ID = " WHERE organization_id = ? ";
    private final static String QUERY_WITH_CHILDREN = "WITH my_organization as\n" +
            "(\n" +
            "  SELECT *\n" +
            "  FROM bo_organization o\n" +
            "  WHERE organization_id = ? \n" +
            "\n" +
            "  UNION ALL\n" +
            "\n" +
            "  SELECT o1.*\n" +
            "  FROM bo_organization o1  \n" +
            "  INNER JOIN my_organization M\n" +
            "  ON M.organization_id = o1.parent_organization_id\n" +
            " )\n" +
            "SELECT organization_id, organization_code, organization_name, parent_organization_id, sequence From my_organization ";
    public BoOrganizationDaoImpl() {
        super(BoOrganization.class);
    }

    @Override
    public List<BoOrganization> select() throws Exception {
        List<BoOrganization> list =  new LinkedList<BoOrganization>();
        selectByObject(jt8Ds.getConnection(),list);
        return list;
    }

    @Override
    public BoOrganization select(int id) throws Exception {
        List<BoOrganization> list =  new LinkedList<BoOrganization>();
        BoOrganization boOrganization = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(selectSQL() + WHERE_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            selectToObject(rs,list);
            if(list.size()>0){
                boOrganization = list.get(0);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return boOrganization;
    }

    @Override
    public List<BoOrganization> selectWithChildren(int id) throws Exception {
        List<BoOrganization> list =  new LinkedList<BoOrganization>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = jt8Ds.getConnection();
            stmt = conn.prepareStatement(QUERY_WITH_CHILDREN);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            selectToObject(rs,list);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public void insert(Connection conn, BoOrganization object) throws Exception {
        int lastKey = insertByObject(conn,object);
        object.setOrganizationId(lastKey);
    }

    @Override
    public void update(Connection conn, BoOrganization object) throws Exception {
        updateByObject(conn, object);
    }

    @Override
    public void delete(Connection conn, BoOrganization object) throws Exception {
        deleteByObject(conn, object);
    }
}
