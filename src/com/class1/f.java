package com.class1;

/*
匿名内部类
*/
public class f {
    public static void main(String[] args) {
        System.out.println("******有名内部类实现接口******");
        out o = new out();
        o.method();
        o.method2();
        System.out.println("******匿名内部类的应用******");
        peopledemo p = new peopledemo();
        p.method(new people() {
            public void show() {
                System.out.println("show");
            }
        });

    }
}

interface inte {
    public void print();
}

//匿名内部类只针对重写一个方法使用
class out {

    //有名内部类实现接口inte
    class in implements inte {

        public void print() {
            System.out.println("print1");
        }
    }

    public void method() {
        in i = new in();
        i.print();
    }

    public void method2() {//匿名内部类要写在方法里
        System.out.println("*****匿名内部类*****");
        new in().print();
        new inte() {//实现inte接口
            public void print() {//重写抽象方法
                System.out.println("print2");
            }//相当于inte子类对象
        }.print();
    }
}

//*********匿名内部类的应用************
abstract class people {
    abstract public void show();
}

class peopledemo {
    void method(people p) {
        p.show();
    }
}
