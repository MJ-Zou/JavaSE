package com.class0;
/*
继承
 */

public class b {
    public static void main(String[] args) {
        dog g = new dog(0);
        System.out.println("leg:" + g.leg);//优先调用本类属性和方法
        g.show();
        System.out.println("=========");
        dog h = new dog();
        h.show();
    }
}

class animals {
    int leg = -1;//同名


    static {
        System.out.println("父类构造代码块");//先进入方法区
    }

    animals(int leg) {
        this.leg = leg;
        System.out.println("父类有参构造");
    }

    final void print() {//final修饰，不能被继承或子类重写，变量变成常量
    }

    void eat() {
        System.out.println("eat");
    }
}

class dog extends animals {//通过extends关键字继承，只能继承一个父类
    int leg = 1;//同名

    static {
        System.out.println("子类构造代码块");//在父类后进方法区
    }

    dog() {
        this(11);//本类中的构造方法，与super不能同时出现！
        System.out.println("子类空参构造");
    }

    dog(int leg) {
        //构造函数中默认存在语句，访问父类!！空参!！构造函数
        //如果父类没有空参构造函数，需要通过super传参
        super(leg);
        System.out.println("子类有参构造");
    }

    void show() {
        System.out.println(this.leg);//调用本类，没有则父类
        System.out.println(super.leg);//仅调用父类
    }
}

