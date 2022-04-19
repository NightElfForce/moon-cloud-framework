package com.hero.spa.orm.connectionPool;

import com.hero.spa.core.exception.SpaException;
import com.hero.spa.core.ioc.context.BeansContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author yinx
 */
public class ConnectionPoolImpl {

    public static String driverName = null;
    public static String url = null;
    public static String userName = null;
    public static String password = null;
    public static int maxConnections = 50;
    public static int initConnections = 5;
    public static int maxActiveConnections = 100;

    private static List<Connection> freeConnection = new Vector<Connection>();
    private static List<Connection> activeConnection = new Vector<Connection>();
    private static AtomicInteger countConne = new AtomicInteger(0);


    public static void init() {
        try {
            Object o = BeansContext.getBean("MySqlConfig");
            Class aClass = o.getClass();
            driverName = aClass.getMethod("getDriverName").invoke(o).toString();
            url = aClass.getMethod("getUrl").invoke(o).toString();
            userName = aClass.getMethod("getUserName").invoke(o).toString();
            password = aClass.getMethod("getPassword").invoke(o).toString();
        } catch (Exception e) {
            System.out.print("数据库配置异常，请检查！");
        }
        if (initConnections > 0) {
            for (int i = 0; i < initConnections; i++) {
                AddOneConnectToFree();
            }
        }
    }

    private static void AddOneConnectToFree() {
        Connection connection = newConnection();
        if (connection != null) {
            freeConnection.add(connection);
        } else {
            throw new SpaException("数据库连接异常！请检查参数！");
        }
    }

    private static Connection newConnection() {
        try {
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(url, userName, password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static synchronized Connection getConnection() {
        try {
            Connection connection = null;
            if (countConne.get() < maxActiveConnections) {
                if (freeConnection.size() > 0) {
                    connection = freeConnection.remove(0);
                } else {
                    connection = newConnection();
                }

                if (connection != null && !connection.isClosed()) {
                    activeConnection.add(connection);
                    countConne.getAndIncrement();
                } else {
                    connection = getConnection();
                }

            } else {
                connection = getConnection();
            }
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void releaseConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                if (freeConnection.size() < maxConnections) {
                    freeConnection.add(connection);
                } else {
                    connection.close();
                }
                activeConnection.remove(connection);
                countConne.getAndDecrement();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
