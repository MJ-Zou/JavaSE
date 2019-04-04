package com.object;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;//util包下的！
import java.util.Random;
import java.math.BigDecimal;
import java.util.Scanner;


public class math {
    public static void main(String[] args) throws ParseException {
        Test1();
    }

    //Math
    private static void demo0() {
        System.out.println(Math.PI);
        System.out.println(Math.abs(-10));//绝对值
        System.out.println(Math.ceil(-0.2));//向上取整，double型
        System.out.println(Math.floor(-0.2));//向下取整，double型
        System.out.println(Math.max(20, 12.5));//最大值
        System.out.println(Math.pow(2, 3));//幂指数2^3.0
        System.out.println(Math.random());//[0.0,1.0)随机小数
        System.out.println(Math.round(12.49));//四舍五入
        System.out.println(Math.sqrt(2));//平方根
    }

    //Random
    private static void demo1() {
        Random r1 = new Random();//随机
        int x = r1.nextInt(100);//产生0-99的
        System.out.println(x);

        Random r2 = new Random(1000);//确定值，种子
        int a = r2.nextInt();
        int b = r2.nextInt();
        System.out.println(a);
        System.out.println(b);
    }

    //System
    private static void demo2() {
        for (int i = 0; i < 10; i++) {
            new demo();
            System.gc();//强行运行垃圾回收器
        }

        System.out.println(System.currentTimeMillis());//1970.1.1至今的毫秒

        int[] src = {11, 22, 33, 44, 55};
        int[] dest = new int[8];
        for (int i = 0; i < dest.length; i++)
            System.out.print(dest[i] + "  ");

        System.out.println();
        System.arraycopy(src, 0, dest, 2, src.length);//数组拷贝

        for (int i = 0; i < dest.length; i++)
            System.out.print(dest[i] + "  ");


        System.exit(0);//退出java虚拟机
        System.out.println("45211");

    }

    //BigInteger
    private static void demo3() {
        int i = 1234567890;
        long l = 1234567890123456789L;

        BigInteger bi1 = new BigInteger("100");
        BigInteger bi2 = new BigInteger("2");
        System.out.println(bi1.add(bi2));// +
        System.out.println(bi1.subtract(bi2));// -
        System.out.println(bi1.multiply(bi2));// *
        System.out.println(bi1.divide(bi2));// /

        BigInteger[] arr = bi1.divideAndRemainder(bi2);//取除数和余数
        for (int j = 0; j < arr.length; j++) {
            System.out.println(arr[j]);
        }

    }

    //BigDecimal
    private static void demo4() {
        System.out.println(2.0 - 1.1);//不精确

        BigDecimal bd1 = new BigDecimal(2.0);
        BigDecimal bd2 = new BigDecimal(1.1);//也不是特别精确
        System.out.println(bd1.subtract(bd2));

        BigDecimal bd3 = new BigDecimal("2.0");//通过字符串构造
        BigDecimal bd4 = new BigDecimal("1.1");//精确
        System.out.println(bd3.subtract(bd4));

        BigDecimal bd5 = BigDecimal.valueOf(2.0);
        BigDecimal bd6 = BigDecimal.valueOf(1.1);//精确！
        System.out.println(bd5.subtract(bd6));
    }

    //Date, DateFormat
    private static void demo5() throws ParseException {
        Date d1 = new Date();//空参构造，当前时间
        System.out.println(d1);

        System.out.println(d1.getTime());//1970.1.1至今的毫秒值
        System.out.println(System.currentTimeMillis());

        Date d2 = new Date(0);//有参0构造 1970.1.1
        System.out.println(d2);

        d1.setTime(1000);//设置毫秒值，改变时间
        System.out.println(d1);

        //DateFormat df=new DateFormat();  //抽象类，不允许实例化
        DateFormat df1 = DateFormat.getDateInstance();   //父类引用指向子类对象

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();//日期格式化类对象
        System.out.println(sdf.format(d));
        SimpleDateFormat sdf1 = new SimpleDateFormat("y年M月d日 h:m:s");//日期格式化类对象
        System.out.println(sdf1.format(d));

        String str = "2000.08.08-08:08:08";
        SimpleDateFormat sdf2 = new SimpleDateFormat("y.M.d-h:m:s");
        Date d3 = sdf2.parse(str);
        System.out.println(d3);
    }

    //Calendar
    private static void demo6() {
        Calendar c = Calendar.getInstance();
        System.out.println(c);//太多信息了
        System.out.println(c.get(Calendar.YEAR) + "年");//通过字段获取年
        System.out.println(c.get(Calendar.MONTH) + 1 + "月");//通过字段获取月，从0月算起
        System.out.println(c.get(Calendar.DAY_OF_MONTH) + "日");
        System.out.println(c.get(Calendar.DAY_OF_WEEK));//周日是第一天

        c.add(Calendar.YEAR, 10);//增加十年
        System.out.println(c.get(Calendar.YEAR) + "年");

        c.set(Calendar.YEAR, 1989);//设置年
        System.out.println(c.get(Calendar.YEAR) + "年");
        c.set(2018, 4, 24);//设置年
    }


    //Date Test
    private static void Test() throws ParseException {
        String str = "1994.10.14";
        SimpleDateFormat sdf = new SimpleDateFormat("y.M.d");
        Date d0 = sdf.parse(str);
        System.out.println("出生日期" + d0);
        long t0 = d0.getTime();

        Date d1 = new Date();
        System.out.println("当前日期" + d1);
        long t1 = d1.getTime();

        long time = t1 - t0;
        System.out.println("出生了" + time / 1000 / 60 / 60 / 24 + "天");


    }

    //Calendar Test
    private static void Test1() {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入年：");
        int year = sc.nextInt();
        Calendar c = Calendar.getInstance();
        c.set(year, 2, 1);
        c.add(Calendar.DAY_OF_YEAR, -1);
        if (c.get(Calendar.DAY_OF_MONTH) == 29)
            System.out.println("闰年");
        else
            System.out.println("不是闰年");
    }
}


class demo {//一个原文件中

    @Override
    public void finalize() {
        System.out.println("垃圾被清扫了");
    }
}
