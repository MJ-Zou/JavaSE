package com.reflect;

public class 模板设计模式 {
    public static void main(String[] args) {
        demo0();
    }

    private static void demo0() {
        System.out.println(new Demo().getTime());
    }
}

abstract class GetTime {
    final public long getTime() {
        long start = System.currentTimeMillis();
        code();
        long end = System.currentTimeMillis();
        return end - start;
    }

    abstract public void code();
}

class Demo extends GetTime {
    @Override
    public void code() {
        for (int i = 0; i < 1000000; i++) {
            System.out.println("x");
        }
    }
}
