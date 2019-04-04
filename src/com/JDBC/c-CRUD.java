package com.JDBC;

import java.sql.*;
import java.util.ResourceBundle;

class database {
    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;

    static {
        //导入配置文件database.properties
        ResourceBundle rb = ResourceBundle.getBundle("database");
        //从文件中获取各个参数
        driverClass = rb.getString("driverClass");
        url = rb.getString("url");
        username = rb.getString("username");
        password = rb.getString("password");

        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //得到连接的方法
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, username, password);
    }

    //关闭资源的方法
    public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }
}
