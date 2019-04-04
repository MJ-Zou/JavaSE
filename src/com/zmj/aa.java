package com.zmj;

import java.util.Scanner;


class aa {//这是类的声明

    public static void main(String[] args) {//这是主方法
        /*
        public ：被jvm调用，所以权限要足够大
        static ：被jvm调用，不需要创建对象，直接类名.调用即可
        void   ：被jvm调用，不需要任何返回值
        main   ：只有这样才能被jvm识别，main不是关键字
        String[] args ：以前是用来接收键盘录入的
        */

        demo1();


    }

    //变量
    private static void demo0() {
        System.out.println("abc");//字符串常量
        System.out.println('a');//字符常量，只能放一个
        System.out.println(123);//整数常量
        System.out.println(12.3);//小数常量
        System.out.println(true);//布尔常量
        System.out.println(0b100);//二进制0b
        System.out.println(0100);//八进制0
        System.out.println(100);
        System.out.println(0x100);//十六进制0x
        float x = 8.8f;//float+f
        long y = 88L;//long+L
        System.out.println('a' + 1 + "hello");//产生新的字符串

        System.out.println((++x == 8.8) && (++y == 88));//&&左为false，右部分不执行
        System.out.println(x);//||左为true，右部分不执行
        System.out.println(y);
        System.out.println(6 & 3);//转换成二进制后按位运算
        System.out.println(5 << 2);//转换成二进制后向左移动2位，5 x 2^2

        Scanner sc = new Scanner(System.in);//创建键盘录入对象
        System.out.println("请输入一个整数：");
        int n = sc.nextInt(); //从键盘得到整数存储
    }

    //数组
    private static void demo1() {
        int[] arr1 = new int[5];//动态初始化，默认是0
        int[] arr2 = {11, 22, 33, 44}; //静态初始化简写，声明时赋值
        show(arr2);

        int[][] arr3 = new int[3][];//二维数组，三个一维数组(长度可以不同！)
        System.out.println(arr3[0]);//一维数组未初始化，为null
        arr3[0] = new int[2];
        arr3[1] = new int[3];
        arr3[2] = new int[4];
        int[][] arr4 = {{1, 2, 3}, {1, 2}, {3}};//二维数组
        show(arr4);
    }

    private static void demo2() {
    }

    private static void demo3() {
    }

    //定义一个方法，必须静态
    private static void show(int[] arr) {
        int num = arr.length;//数组的长度
        for (int i = 0; i < num; i++)
            System.out.println(arr[i]);
    }

    private static void show(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++)
                System.out.print(arr[i][j] + "  ");//不换行输出
            System.out.println();
        }
    }
}
