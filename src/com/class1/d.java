package com.class1;
/*
抽象abstract，共性is a
*
接口interface，扩展功能like a
 */

public class d {
    public static void main(String[] args) {
        System.out.println("*****抽象*****");
        //animal a=new animal();  抽象类不能有对象！
        animal a = new cat();
        a.eat();
        a.show();

        System.out.println("*****接口*****");
        inter i = new demo();//多态调用
        i.print();
        System.out.println(inter.num);
    }
}

//抽象类
abstract class animal {

    int num1 = 0;
    final int num2 = 0;

    public void show() {
        System.out.println("haha");
    }

    //抽象方法，没有函数体,
    public abstract void eat();// 不能和final、static、private搭配
}

//实例化
class cat extends animal {
    public void eat() {
        System.out.println("cat eat");//抽象方法必须重写！
    }
}


interface inter {//定义接口类，抽象的，不能实例化
    int num = 10;//只有public static final 的

    //inter(){}; //接口没有构造方法
    public abstract void print();//接口中的方法只有抽象的
    //public  void show(){}; //不能定义非抽象方法
}

//接口间可以继承多个
//类可以继承于多个接口，一个父类
class demo /*extends Object */ implements inter {//通过 implements 类实现接口

    public void print() {//重写所有抽象方法
        System.out.println("print");
    }
}


















