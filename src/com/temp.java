package com;

import java.util.List;

public class temp {


    public static class A {
        void f() {
            System.out.println("aaaa");
        }
    }

    public static class B extends A {


        void f() {
            super.f();
            System.out.println("bbbb");
        }
    }

    public void fun() {
        System.out.println();
    }

    public void fun(int a) {
        a++;
    }


    public static void main(String[] args) {
        new A().f();
        new B().f();
    }
}
