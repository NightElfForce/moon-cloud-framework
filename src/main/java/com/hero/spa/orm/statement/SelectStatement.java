package com.hero.spa.orm.statement;

import com.hero.spa.orm.sqlResolver.Resolver;
import com.hero.spa.orm.connectionPool.ConnectionPoolImpl;

import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yinx
 */
public class SelectStatement implements Statement {
    @Override
    public Object getResult(Resolver resolver, Parameter[] parameters, Object[] args) {
        Connection conn = ConnectionPoolImpl.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(resolver.getSql());
            rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnSize = md.getColumnCount();
            List<Map> result = new ArrayList<>();
            while (rs.next()) {
                Map map = new HashMap();
                for (int i = 1; i <= columnSize; i++) {
                    map.put(md.getColumnName(i), rs.getObject(i));
                }
                result.add(map);
            }
            ConnectionPoolImpl.releaseConnection(conn);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
