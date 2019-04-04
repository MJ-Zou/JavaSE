package com.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class login {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String name = sc.nextLine();
        System.out.println("请输入密码：");
        String pwd = sc.nextLine();

        user u = DoLogin(name, pwd);

        if (u != null){
            System.out.println("欢迎" + u.getName());
            System.out.println(u);
        }

        else
            System.out.println("登陆失败");
    }

    private static user DoLogin(String name, String pwd) {
        Connection conn = null;

        /*Statement stmt = null;*/
        PreparedStatement stmt = null;

        ResultSet rs = null;
        user u = null;
        try {
            conn = database.getConnection();

            //容易产生sql注入问题                //pwd=fkdtj' or '1'='1
            /*stmt = conn.createStatement();
            String sql="select * from users where name='" + name + "' and password='" + pwd + "'";
            System.out.println(sql);
            rs = stmt.executeQuery(sql);*/


            String sql = "select * from users where name=? and password=? ";
            //要用prepareStatement！！安全！
            stmt = conn.prepareStatement(sql);
            //给第一个？赋值
            stmt.setString(1, name);
            //给第二个？赋值
            stmt.setString(2, pwd);
            rs = stmt.executeQuery();

            if (rs.next()) {
                u = new user();
                u.setId(rs.getInt("id"));
                u.setBirthday(rs.getDate("birthday"));
                u.setEmail(rs.getString("email"));
                u.setName(rs.getString("name"));
                u.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeAll(conn, stmt, rs);
        }
        return u;
    }
}
