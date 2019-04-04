package com.object;

import com.zmj.Student;

public class a /*extends Object*/ { //任何类都继承于Object



    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Student st1 = new Student("张三", 24);
        Student st2 = new Student("张三", 24);

        System.out.println("\n***** HashCode *****");
        int hashcode = obj1.hashCode();//int hashcode
        System.out.println(hashcode);
        System.out.println(st1.hashCode());
        System.out.println(st2.hashCode());

        System.out.println("\n***** getClass *****");
        Class clazz = st1.getClass();//get class 获取该对象的字节码文件
        String name = clazz.getName();
        System.out.println(name);//得到对象的类名

        System.out.println("\n***** toString *****");
        System.out.println(obj1.toString());//类名+@+HashCode十六进制，没意义
        System.out.println(st1.toString());//可以重写这个方法
        System.out.println(st1);//一致

        System.out.println("\n***** Equals *****");
        System.out.println(obj1.equals(obj2));//比较两个对象(地址值)是否相等
        System.out.println(st1==st2);//比较两个对象(地址值)是否相等
        boolean b = st1.equals(st2);//最好重写成(属性)是否相等
        System.out.println(b);
    }
}
