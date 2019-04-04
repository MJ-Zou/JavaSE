package com.class0;

public class a {
    public static void main(String[] args) {

        student a = new student();//有名字的对象
        a.show();

        new student().show();//匿名对象，一次性使用


        System.out.println(student.age);//通过类名直接调用


    }

    static {
        System.out.println("主方法的静态代码块");//最先于程序执行
    }

}

//定义一个类,个属性方法默认为public
class student {
    String name = "无名氏";//1、显示初始化
    //静态变量随着类加载，先于对象产生
    public static int age;//静态变量，值成员共享


    student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    student() {
        name = "有名氏";//2、构造函数初始化
        age = 100;
    }

    void show() {//static函数不能调用非static变量
        System.out.print("name:" + name + ' ');
        System.out.println("age:" + age + ' ');//任意函数可调用static
    }

    static {
        System.out.println("类中的静态代码块");//仅类第一次创建时执行一次
    }

    {
        System.out.println("我是构造代码块");//类每生成一个对象执行一次
    }
}