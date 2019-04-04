package com.JDBC;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class dbutils {
    public static void main(String args[]) throws SQLException {
        demo1();
    }

    //dbutils操作
    public static void demo0() throws SQLException {
        //1、创建queryrunner类
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

        //2、编写sql
        String sql = "insert into users values(?,?,?,?,?);";

        //3、执行aql
        qr.update(sql, 4, "tom", "123 ", "tom@163.com", "2015-09-28");

    }

    //ResultSetHandler
    public static void demo1() throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from users";
/*
        //ArrayHandler:将查询结果的第一条封装成数组返回
        Object[] query = qr.query(sql, new ArrayHandler());
        System.out.println(Arrays.toString(query));

        //ArrayListHandler:每一条都封装成数组，组成list
        List<Object[]> list = qr.query(sql, new ArrayListHandler());
        for (Object[] obj : list)
            System.out.println(Arrays.toString(obj));
*/
        //☆☆BeanHandler:第一条封装成类
        user u=qr.query(sql, new BeanHandler<>(user.class));
        System.out.println(u);

        //☆☆BeanListHandler:每一条封装成类,组成list
        List<user> list0 = qr.query(sql, new BeanListHandler<>(user.class));
        for (user u0 : list0)
            System.out.println(u0);
/*
        //MapHandler:第一条封装成map，字段为key，值为value
        Map<String, Object> map = qr.query(sql, new MapHandler());
        System.out.println(map);

        //☆MapListHandler:每一条封装成map，字段为key，值为value，组成list
        List<Map<String, Object>> list1 = qr.query(sql, new MapListHandler());
        for (Map<String, Object> map1 : list1)
            System.out.println(map1);

        //☆ScalarHandler:针对聚合函数，返回long值
        Object obj=qr.query(sql,new ScalarHandler());
        System.out.println(obj);
*/
    }
}
