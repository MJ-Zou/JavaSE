package com.collection;

import com.zmj.Student;
import org.junit.Test;


import java.util.ArrayList;

import static java.util.Arrays.sort;//静态导入
import static java.util.Arrays.toString;//静态导入

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class jdk15 {
    public static void main(String[] args) {

    }

    //增强for循环
    private static void demo0() {
        int[] arr = {11, 22, 33, 44, 55};
        for (int i : arr)//数组遍历
            System.out.println(i);

        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        for (String str : list) //集合遍历
            System.out.println(str);

        ArrayList<Student> list1 = new ArrayList<>();
        list1.add(new Student("张三", 23));
        list1.add(new Student("李四", 24));
        list1.add(new Student("王五", 25));
        for (Student s : list1)
            System.out.println(s);

    }

    //三种迭代能否删除
    private static void demo1() {
        //普通for循环
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("b");
        list.add("d");
        for (int i = 0; i < list.size(); i++)
            if ("b".equals(list.get(i)))
                //list.remove(i);//相邻有漏的
                list.remove(i--);//不漏
        System.out.println(list);

        //迭代器循环
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("b");
        list1.add("c");
        list1.add("b");
        list1.add("d");
        Iterator<String> it = list1.iterator();
        //for(Iterator<String> it=list.iterator();it.hasNext();)另一种写法
        while (it.hasNext())
            if (it.next().equals("b"))
                // list1.remove("b");//并发修改异常！
                it.remove();//调用迭代器的
        System.out.println(list1);

        //增强for循环,不能删除，只能遍历
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        list2.add("b");
        list2.add("c");
        list2.add("b");
        list2.add("d");
        for (String str : list2)
            if (str.equals("b"))
                list2.remove("b");//并发修改异常
        System.out.println(list2);

    }

    //静态导入
    private static void demo2() {
        //导入类中的静态方法
        int[] arr = {55, 22, 33, 44, 11};
        sort(arr);//不用加前缀Arrays了
        //System.out.println(toString(arr));  不是父类
    }

    //可变参数
    private static void demo3() {
        int[] arr = {11, 22, 33, 44, 55};
        print(arr);
        print(11, 22, 33, 44, 55);
    }

    //asList
    private static void demo4() {
        String[] arr = {"a", "b", "c"};
        List<String> list = Arrays.asList(arr);//数组转集合
        // list.add("d");  //不能添加
        System.out.println(list);

        //集合转数组
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add("d");
        String[] arr1 = list1.toArray(new String[6]);//长度自动补齐，null
        for (String str : arr1)
            System.out.println(str);
    }

    //ArrayList嵌套
    @Test
    public void demo5() {
        ArrayList<ArrayList<Student>> list = new ArrayList<>();

        ArrayList<Student> first = new ArrayList<>();
        first.add(new Student("张三", 23));
        first.add(new Student("李四", 24));
        first.add(new Student("王五", 25));

        ArrayList<Student> second = new ArrayList<>();
        second.add(new Student("丽丽", 23));
        second.add(new Student("明明", 24));
        second.add(new Student("红红", 25));

        list.add(first);
        list.add(second);
        System.out.println(list);

        for(ArrayList<Student> a:list)
            for(Student s:a)
                System.out.println(s);
    }


    private static void print(int... arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);
    }


}
