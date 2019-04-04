/*
 * 字符串的建立
 * */
package com.object;

import com.zmj.Student;
import org.junit.Test;

public class string {
    public static void main(String[] args){}


    //字符串的建立
    private static void demo1() {
        String str = "abc";//字符串对象,是常量
        str = "def";//当把"def"赋给str，"abc"变成垃圾
        System.out.println(str);
        System.out.println(str.toString());//重写了toString

        String s1 = new String();//空构造
        System.out.println(s1);

        byte[] arr1 = {97, 98, 99};//字节数组 转成 字符串
        String s2 = new String(arr1);//解码成abc，用类似ascii
        System.out.println(s2);

        byte[] arr2 = {97, 98, 99, 100, 101, 102};
        String s3 = new String(arr2, 2, 3);//从2位置开始选3个解码
        System.out.println(s3);

        char[] arr3 = {'a', 'b', 'c', 'd', 'e'};//字符数组 转成 字符串
        String s4 = new String(arr3);
        System.out.println(arr3);

        String s5 = new String(arr3, 2, 3);//从2位置开始选3个解码
        System.out.println(s5);

        String s6 = new String("abc");
        System.out.println(s6);
    }

    //字符串的比较
    private static void demo2() {
        String s1 = "abc";//记录常量池的地址值
        String s2 = "abc";//常量池已有abc，不再新生成
        //执行两次构造
        String s3 = new String("abc");//记录堆内存的地址值
        String s4 = "a" + "b" + "c";//常量优化机制
        String s = "ab";
        String s5 = s + "c";//指向对象

        System.out.println(s1 == s2);//true，比较地址值
        System.out.println(s1.equals(s2));

        System.out.println(s1 == s3);//false
        System.out.println(s1.equals(s3));

        System.out.println(s1 == s4);//true
        System.out.println(s1.equals(s4));

        System.out.println(s1 == s5);//false
        System.out.println(s1.equals(s5));
    }

    //内容获取
    @Test
    public void demo3() {
        String s1 = "abc";
        String s2 = "Abc";
        String s3 = "abc你好呀呀";
        String s4 = "";
        String s5 = "abababa";
        System.out.println(s1.equals(s2));//比较字符串内容，区分大小写
        System.out.println(s1.equalsIgnoreCase(s2));//比较字符串内容，不区分大小写
        System.out.println(s3.contains(s1));//s3是否包含s1
        System.out.println(s3.startsWith(s1));//s3是否以s1开头
        System.out.println(s3.endsWith(s1));//s3是否以s1结尾
        System.out.println(s4.isEmpty());//s4是否空

        System.out.println(s3.length());//字符个数
        System.out.println(s3.charAt(3));//获取指定位置的字符
        System.out.println(s3.indexOf("你"));//获取指定字符的位置，没有返回-1
        System.out.println(s3.indexOf("bc"));
        System.out.println(s5.indexOf('a', 2));//从指定位置开始找
        System.out.println(s5.lastIndexOf('a'));//从后向前找
        System.out.println(s3.substring(2, 5));//一部分，有头无尾[)

    }

    //遍历
    private static void demo4() {
        String s = "sdghkSDGKJ546321%^&*";
        int low = 0, num = 0, high = 0, other = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c <= 'z' & c >= 'a') {
                low++;
            } else if (c <= 'Z' & c >= 'A') {
                high++;
            } else if (c <= '9' & c >= '0') {
                num++;
            } else other++;
        }
        System.out.println("小写：" + low);
        System.out.println("大写：" + high);
        System.out.println("数字：" + num);
        System.out.println("其他：" + other);
    }

    //转换
    private static void demo5() {
        String s = "你好呀";
        byte[] arr = s.getBytes();//通过UTF-8每个汉字转成3bytes
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();

        char[] arr1 = s.toCharArray();//字符串 转换成 字符数组
        for (int i = 0; i < arr1.length; i++)
            System.out.print(arr1[i] + " ");
        System.out.println();

        String s1 = String.valueOf(arr1);//字符数组 转换成 字符串
        System.out.println(s1);
        String s2 = String.valueOf(100);//100转成字符串
        System.out.println(s2);

        Student st = new Student("aa", 25);
        String s3 = String.valueOf(st);//调用对象的toString方法
        System.out.println(s3);

    }

    //替换
    private static void demo6() {
        String s = "  as  afa  ";
        String s1 = s.replace('a', 'b');//用b换a
        System.out.println(s1);
        String s2 = s.trim();//去掉 两端 的空格
        System.out.println(s2);
    }


    //stringbuffer
    @Test
    public void demo7() {
        //string虽然是引用数据类型，但当做参数传递时和基本数据类型一致
        String s = "abc";
        change(s);//值传递
        System.out.println(s);

        StringBuffer sb = new StringBuffer("abc");
        change(sb);//地址传递
        System.out.println(sb);
    }

    private static void change(String s) {
        s += "aaaa";
    }

    private static void change(StringBuffer s) {
        s.append("aaaa");
    }

}

