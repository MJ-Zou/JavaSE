package com.class1;

/*
内部类
 */
public class e {
    public static void main(String[] args) {
        com.zmj.p1 a = new com.zmj.p1();//通过包名调用，麻烦
        //a.protect();//不同包下无关类无法访问

        outer.inner oi = new outer().new inner();//创建内部类对象
        oi.method();

        //outer.pinner oi=new outer().pinner();  //不可以创建私有内部类对象
        outer pi = new outer();//通过外部类函数访问
        pi.method();

        outer.sinner si = new outer.sinner();//静态内部类的创建
        si.method();
        outer.sinner.show();//静态内部类的静态方法 直接调用
    }
}


class outer {//外部类
    private int num = 0;

    public void method() {//访问私有内部类
        pinner i = new pinner();
        i.method();
    }

    //内部类
    class inner {
        int num = 10;

        public void method() {
            int num = 100;
            System.out.println(num);//访问函数内
            System.out.println(this.num);//访问本类
            System.out.println(outer.this.num);//访问外部
        }
    }

    //私有内部类
    private class pinner {

        public void method() {
            System.out.println(num);//可以访问外部类的私有
        }
    }

    //静态内部类
    static class sinner {

        public void method() {
            System.out.println("static");
        }

        public static void show() {
            System.out.println("static");
        }
    }
}