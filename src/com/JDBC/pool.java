package com.JDBC;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class pool {
    public static void main(String args[]) throws Exception {
        demo3();

    }

    //DBCP 硬编码
    public static void demo0() throws SQLException {
        //创建连接池
        BasicDataSource ds = new BasicDataSource();
        //配置信息
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql:///jdbc");
        ds.setUsername("root");
        ds.setPassword("root");

        Connection conn = ds.getConnection();
        String sql = "insert into users values(?,?,?,?,?);";
        PreparedStatement st = conn.prepareStatement(sql);

        //设置参数
        st.setInt(1, 4);
        st.setString(2, "tom");
        st.setString(3, "123");
        st.setString(4, "tom@163.com");
        st.setString(5, "2015-09-28");

        int i = st.executeUpdate();
        System.out.println(i);
        JdbcUtils.closeResource(conn, st, null);
    }

    //DBCP 通过配置文件
    public static void demo1() throws Exception {
        //存放配置文件
        Properties prop=new Properties();
        prop.load(new FileInputStream("src/database.properties"));

        //创建连接池
        DataSource ds = new BasicDataSourceFactory().createDataSource(prop);
        Connection conn = ds.getConnection();
        String sql = "insert into users values(?,?,?,?,?);";
        PreparedStatement st = conn.prepareStatement(sql);

        //设置参数
        st.setInt(1, 4);
        st.setString(2, "tom");
        st.setString(3, "123");
        st.setString(4, "tom@163.com");
        st.setString(5, "2015-09-28");

        int i = st.executeUpdate();
        System.out.println(i);
        JdbcUtils.closeResource(conn, st, null);
    }

    //C3P0 硬编码
    private static void demo2() throws PropertyVetoException, SQLException {
        ComboPooledDataSource ds=new ComboPooledDataSource();

        ds.setDriverClass("com.mysql.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql:///jdbc");
        ds.setUser("root");
        ds.setPassword("root");

        Connection conn = ds.getConnection();
        String sql = "insert into users values(?,?,?,?,?);";
        PreparedStatement st = conn.prepareStatement(sql);

        //设置参数
        st.setInt(1, 4);
        st.setString(2, "tom");
        st.setString(3, "123");
        st.setString(4, "tom@163.com");
        st.setString(5, "2015-09-28");

        int i = st.executeUpdate();
        System.out.println(i);
        JdbcUtils.closeResource(conn, st, null);
    }

    //C3P0 通过配置文件
    private static void demo3() throws SQLException {
        ComboPooledDataSource ds=new ComboPooledDataSource();
        //也可使用命名配置，没有则使用默认配置
        ds=new ComboPooledDataSource("itcast");
        //properties和xml都可
        Connection conn = ds.getConnection();
        String sql = "insert into users values(?,?,?,?,?);";
        PreparedStatement st = conn.prepareStatement(sql);

        //设置参数
        st.setInt(1, 4);
        st.setString(2, "tom");
        st.setString(3, "123");
        st.setString(4, "tom@163.com");
        st.setString(5, "2015-09-28");

        int i = st.executeUpdate();
        System.out.println(i);
        JdbcUtils.closeResource(conn, st, null);
    }








}