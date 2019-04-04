package com.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class a {
    public static void main(String[] args) throws Exception {
        demo3();
    }

    //JDBC初体验
    private static void demo0() throws Exception {
        //①注册驱动，反射方法
        Class.forName("com.mysql.jdbc.Driver");

        //②桥梁，获取连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc",
                "root", "root");

        //③货车，得到执行sql语句的对象
        Statement stmt = conn.createStatement();

        //④卸货，执行sql语句，返回结果
        ResultSet rs = stmt.executeQuery("select * from users");

        //处理结果
        while (rs.next()) {
            System.out.println(rs.getObject(1));
            System.out.println(rs.getObject(2));
            System.out.println(rs.getObject(3));
            System.out.println(rs.getObject(4));
            System.out.println(rs.getObject(5));
            System.out.println("------------------------");
        }

        //关闭资源
        rs.close();
        stmt.close();
        conn.close();
    }

    //一、getConnection 三种方式
    private static void demo1() throws Exception {
        //注册驱动，反射方法
        Class.forName("com.mysql.jdbc.Driver");


        //桥梁
        //获取连接Connection，url协议/数据库名称+用户名+密码
        //方式1，常用
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc",
                "root", "root");
        //方式2
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");
        Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", info);
        //方式3
        Connection conn3 = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jdbc?user=root&password=root");


        //货车
        //得到执行sql语句的对象Statement
        Statement stmt = conn.createStatement();

        //卸货
        //执行sql语句并返回结果
        ResultSet rs = stmt.executeQuery("select * from users");

        //处理结果
        while (rs.next()) {
            System.out.println(rs.getObject(1));
            System.out.println(rs.getObject(2));
            System.out.println(rs.getObject(3));
            System.out.println(rs.getObject(4));
            System.out.println(rs.getObject(5));
            System.out.println("------------------------");
        }
        //关闭资源
        rs.close();
        stmt.close();
        conn.close();
    }

    //二、executeUpdate 传递sql语句
    private static void demo2() throws Exception {
        //注册驱动，反射方法
        Class.forName("com.mysql.jdbc.Driver");
        //桥梁
        //获取连接Connection，url协议/数据库名称+用户名+密码
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc",
                "root", "root");
        //货车
        //得到执行sql语句的对象Statement
        Statement stmt = conn.createStatement();


        //卸货
        //executeUpdate
        int i;
        i = stmt.executeUpdate(
                "insert into users values(4,'tom','123','tom@163.com','2015-09-28')");
        if (i > 0) System.out.println("修改了" + i + "行");

        i = stmt.executeUpdate(
                "update users set name='jerry',password='333',email='jerry@163.com' where id=4");
        if (i > 0) System.out.println("修改了" + i + "行");

        i = stmt.executeUpdate(
                "DELETE FROM users WHERE id=4");
        if (i > 0) System.out.println("修改了" + i + "行");


        //关闭资源
        stmt.close();
        conn.close();
    }

    //三、ResultSet 结果集
    private static void demo3() throws Exception {
        //注册驱动，反射方法
        Class.forName("com.mysql.jdbc.Driver");
        //桥梁
        //获取连接Connection，url协议/数据库名称+用户名+密码
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc",
                "root", "root");
        //货车
        //得到执行sql语句的对象Statement
        Statement stmt = conn.createStatement();
        //卸货
        //执行sql语句并返回结果
        ResultSet rs = stmt.executeQuery("select * from users");


        //处理结果
        //①依次字段显示
        while (rs.next()) {
            System.out.println(rs.getObject(1));
            System.out.println(rs.getObject(2));
            System.out.println(rs.getObject(3));
            System.out.println(rs.getObject(4));
            System.out.println(rs.getObject(5));
            System.out.println("------------------------");
        }
        rs.beforeFirst();//光标移动到开头

        //②指定字段名显示
        while (rs.next()) {
            System.out.println(rs.getObject("id"));
            System.out.println(rs.getObject("name"));
            System.out.println(rs.getObject("email"));
            System.out.println(rs.getObject("birthday"));
            System.out.println(rs.getObject("password"));
            System.out.println("------------------------");
        }
        rs.beforeFirst();//光标移动到开头

        //③放到类对象的集合里
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
        System.out.println("------------------------");

        rs.previous();//游标上移
        rs.previous();
        user u = new user();
        u.setId(rs.getInt("id"));
        u.setBirthday(rs.getDate("birthday"));
        u.setEmail(rs.getString("email"));
        u.setName(rs.getString("name"));
        u.setPassword(rs.getString("password"));
        list.add(u);
        System.out.println(u);

        //关闭资源
        rs.close();
        stmt.close();
        conn.close();
    }

    //四、合理释放资源
    private static void demo4() throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //注册驱动，反射方法
            Class.forName("com.mysql.jdbc.Driver");

            //桥梁
            //获取连接Connection，url协议/数据库名称+用户名+密码

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc",
                    "root", "root");

            //货车
            //得到执行sql语句的对象Statement
            stmt = conn.createStatement();

            //卸货
            //执行sql语句并返回结果
            rs = stmt.executeQuery("select * from users");

            //处理结果
            while (rs.next()) {
                System.out.println(rs.getObject(1));
                System.out.println(rs.getObject(2));
                System.out.println(rs.getObject(3));
                System.out.println(rs.getObject(4));
                System.out.println(rs.getObject(5));
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
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


}

class user {
    //ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //Birthday
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    private int id;
    private String name;
    private String password;
    private String email;
    private Date birthday;

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

