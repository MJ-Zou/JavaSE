package com.class0;
/*
多态
 */

//多态：弊端：不能使用子类独有的方法，属性
//      优势：提高维护性，扩展性

public class c {
    public static void main(String[] args) {
        animal a = new cat();//多态：父类引用指向子类对象
        cat c = new cat();
        System.out.println("******静态方法*****");
        a.method();//静态方法：编译运行看左边(父类)
        c.method();
        System.out.println("******多态变量*****");
        System.out.println(a.num);//！变量！：编译，运行全看左边(父类)
        System.out.println(c.num);
        System.out.println("******多态方法*****");
        a.eat();//！方法！：编译看左边(父类)，运行看右边(子类)！！动态绑定
        c.eat();
        System.out.println("******多态转型*****");
        //a.fly() 不存在！！
        cat cc = (cat) a;//向下转型
        cc.fly();
        System.out.println("******多态应用*****");
        eat(new cat());
        eat(new doog());

    }

    public static void eat(animal a) { //作为参数用多态最好，不利于调用子类特有方法
        if (a instanceof cat) { //如果a是cat类型
            cat c = (cat) a;
            c.eat();//执行特有方法
            c.fly();
        } else
            a.eat();
    }
}

class animal {//父类animal
    int num = 0;

    static void method() {
        System.out.println("animal method");
    }

    void eat() {
        System.out.println("animal eat");
    }
}


class cat extends animal {//子类cat
    int num = 1;

    static void method() {
        System.out.println("cat method");
    }

    void eat() {
        System.out.println("cat eat");
    }

    void fly() {
        System.out.println("fly");
    }
}


class doog extends animal {//子类doog
    void eat() {
        System.out.println("dog eat");
    }
}