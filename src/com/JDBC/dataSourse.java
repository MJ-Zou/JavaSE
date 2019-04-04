package com.JDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class dataSourse {

    static LinkedList<Connection> pool = new LinkedList<>();

    static {
        //初始化的时候 放入3个连接
        for (int i = 0; i < 3; i++) {
            try {
                Connection conn = JdbcUtils.getConnection();
                pool.addLast(conn);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //从连接池获取连接的方法
    public static Connection getConnection() {
        //判断list是否为空
        if (pool.isEmpty()) {
            //再添加2个进去
            for (int i = 0; i < 3; i++) {
                try {
                    Connection conn = JdbcUtils.getConnection();
                    pool.addLast(conn);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("从池中获取连接");
        return pool.removeFirst();
    }

    //归还连接
    public static void addBack(Connection conn) {
        System.out.println("从池中归还连接");
        //将conn放入到list的最后面
        pool.addLast(conn);
    }

}


