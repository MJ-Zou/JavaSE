package com.object;

import java.util.Arrays;

public class array_int {
    public static void main(String[] args) {
        demo1();
    }

    //Array的函数
    private static void demo0() {
        int[] arr = {5, 4, 3, 2, 1};
        maopao(arr);
        print(arr);

        int[] arr1 = {5, 4, 3, 2, 1};
        xuanze(arr1);
        print(arr1);

        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println(chazhao(arr2, 0));

        int[] arr3 = {55, 44, 33, 22, 11};
        Arrays.sort(arr3);//数组排序

        String s = Arrays.toString(arr3);//数组转字符串
        System.out.println(s);

        System.out.println(Arrays.binarySearch(arr3, 33));//返回位置
        System.out.println(Arrays.binarySearch(arr3, 30));//返回负插入点-1
    }

    //Integer类
    private static void demo1() {
        System.out.println(Integer.toBinaryString(60));//60转换成二进制

        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);

        Integer i1 = new Integer(100);//构造方法
        Integer i2 = new Integer("666");//构造方法
        System.out.println(i2);

        String s1=100+"";//int转成字符串
        String s2=String.valueOf(100);
        String s3=i1.toString();
        String s4=Integer.toString(100);

        int i3=i2.intValue();//Integer转int

        Integer in=100;//自动装箱
        int i4=in+10;//自动拆箱

        Integer i11=new Integer(100);
        Integer i12=new Integer(100);
        System.out.println(i11==i12);//false 比较地址

        Integer i13=127;
        Integer i14=127;
        System.out.println(i13==i14);//true！ -128~127放内在常量池

        Integer i15=128;
        Integer i16=128;
        System.out.println(i15==i16);//false！ -128~127外创建新对象

    }


    //冒泡排序
    private static void maopao(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    //选择排序
    private static void xuanze(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i; j < len; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    //二分查找
    private static int chazhao(int[] arr, int num) {
        int min = 0;
        int max = arr.length - 1;
        int mid = (min + max) / 2;
        while (min <= max) {
            if (arr[mid] > num)
                max = mid - 1;
            else if (arr[mid] < num)
                min = mid + 1;
            else
                return mid;
            mid = (min + max) / 2;
        }
        return -1;
    }

    //打印数组
    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + "  ");
        System.out.println();
    }
}
