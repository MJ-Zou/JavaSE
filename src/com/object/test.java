package com.object;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        demo3();
    }

    //用户名密码的登陆
    private static void demo1() {
        int flag = 0;
        Scanner sc1 = new Scanner(System.in);
        while (true) {

            System.out.println("请输入用户名");
            String s1 = sc1.nextLine();
            System.out.println("请输入密码");
            String s2 = sc1.nextLine();
            if (s1.equals("admin") & s2.equals("admin")) {
                System.out.println("登陆成功");
                break;
            } else {
                System.out.println("错误");
                flag++;
                if (flag == 3) {
                    System.out.println("登录失败");
                    break;
                }
            }
        }
    }

    //第一个大写，其他小写
    private static void demo2() {
        String s = "shgjkdHAJHUJdg";
        String s1 = s.toLowerCase();
        String s2 = s.toUpperCase();
        //String ss=s2.substring(0,1)+s1.substring(1);
        String ss = s.substring(0, 1).toUpperCase().concat(s.substring(1).toLowerCase());
        System.out.println(ss);
    }

    //字符串反转
    private static void demo3(){
        String s="fhasjv652";
        char[] arr=s.toCharArray();
        int num=arr.length;
        for(int i=0;i<num/2;i++)
        {
            char temp=arr[i];
            arr[i]=arr[num-i-1];
            arr[num-i-1]=temp;
        }
        String ss=String.valueOf(arr);
        System.out.println(ss);
    }
}
