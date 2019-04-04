package com.zmj;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Comparable<Student>,Serializable {
    public String name;
    public int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {//重写toString
        return "\n姓名：" + name + "   年龄：" + age ;
    }

    /*@Override
    public boolean equals(Object obj) {//重写equals
        System.out.println("执行了吗");
        Student s = (Student) obj;
        return (this.name.equals(s.name) && this.age == s.age);
    }

    @Override
    public int hashCode() {
        final int NUM = 38;
        return name.hashCode() * 38 + age;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);//只有hashcode一样才调用equals方法
    }

    @Override
    public int compareTo(Student o) {
        int num = this.age - o.age;//先比年龄
        return num == 0 ? this.name.compareTo(o.name):num;//再比姓名
        //返回0时只有一个元素，认为相等，不存
        //返回正数时怎么存怎么取，认为大，存在右面
        //返回负数时倒序存取，认为小，存在左面
    }


}
