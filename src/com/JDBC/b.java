package com.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class b {
    public static void main(String[] args) throws Exception {
        demo2();
        demo3();
        demo4();
        demo1();
    }

    //查询方法
    private static void demo1() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = database.getConnection();
            stmt = conn.createStatement();
            //查询executeQuery()
            rs = stmt.executeQuery("select * from users");
            List<user> list = new ArrayList<>();
            while (rs.next()) {
                user u = new user();
                u.setId(rs.getInt("id"));
                u.setBirthday(rs.getDate("birthday"));
                u.setEmail(rs.getString("email"));
                u.setName(rs.getString("name"));
                u.setPassword(rs.getString("password"));
                list.add(u);
            }
            for (user u : list) {
                System.out.println(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeAll(conn, stmt, rs);
        }
    }

    //添加方法
    private static void demo2() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = database.getConnection();
            stmt = conn.prepareStatement("insert into users values(?,?,?,?,?)");
            stmt.setInt(1, 4);
            stmt.setString(2, "tom");
            stmt.setString(3, "123");
            stmt.setString(4, "tom@163.com");
            stmt.setString(5, "2015-09-28");
            //executeUpdate()
            int i = stmt.executeUpdate();
            if (i > 0) System.out.println("修改了" + i + "行");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeAll(conn, stmt, null);
        }
    }

    //更改方法
    private static void demo3() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = database.getConnection();

            stmt = conn.prepareStatement(
                    "UPDATE users SET NAME=?,PASSWORD=?,email=? WHERE id=?");
            stmt.setString(1, "jerry");
            stmt.setString(2, "333");
            stmt.setString(3, "jerry@163.com");
            stmt.setInt(4, 4);
            //更新executeUpdate()
            int i = stmt.executeUpdate();
            if (i > 0) System.out.println("修改了" + i + "行");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeAll(conn, stmt, null);
        }
    }

    //删除方法
    private static void demo4() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = database.getConnection();
            stmt = conn.prepareStatement("DELETE FROM users WHERE id=?");
            stmt.setInt(1, 4);
            //删除executeUpdate()
            int i = stmt.executeUpdate();
            if (i > 0) System.out.println("修改了" + i + "行");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeAll(conn, stmt, null);
        }
    }
}
