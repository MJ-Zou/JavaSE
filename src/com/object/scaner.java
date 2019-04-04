package com.object;

import java.util.Scanner;

public class scaner {
    public static void main(String[] args) {
        int i;
        Scanner sc = new Scanner(System.in);//键盘录入
        System.out.println("输入整数");
        if (sc.hasNextInt()) {//判断录入的是不是整数
            i = sc.nextInt();
            System.out.println("你刚才输入的是" + i);
        } else
            System.out.println("输入有误！");

        Scanner aa = new Scanner(System.in);//键盘录入
        System.out.println("请输入一个整数");
        int a = aa.nextInt();
        System.out.println(a);
        Scanner bb = new Scanner(System.in);//键盘录入
        System.out.println("请输入一个字符串");
        String b = bb.nextLine();
        System.out.println(b);

    }
}
