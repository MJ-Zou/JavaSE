package com.collection;

import com.zmj.Student;
import org.junit.Test;

import java.beans.Transient;
import java.util.*;

public class map {
    public static void main(String[] args) {
        /*
          List(存取有序，有索引，可以重复)
            ArrayList
                底层是数组，不安全
            LinkedList
                底层是链表，不安全
            Vector
                底层是数组，安全

          Set(存取无序，无索引，不可以重复)
            HashSet
                底层是哈希算法
                LinkedHashSet
                    底层是链表
            TreeSet
                底层是二叉树

          Map
            HashMap
                底层是链表，针对键
                LinkedHashMap
                    底层是链表，针对键
            TreeMap
                底层是二叉树，针对键
         */
    }

    //Map功能概述
    @Test
    public void demo0() {
        Map<String, Integer> map = new HashMap<>();
        Integer i1 = map.put("张三", 23);
        Integer i2 = map.put("李四", 24);
        Integer i3 = map.put("王五", 25);
        Integer i4 = map.put("赵六", 26);
        Integer i5 = map.put("张三", 26);//相同的键不再存储，覆盖旧的值

        System.out.println(map);

        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println(i4);
        System.out.println(i5);//返回被覆盖的值

        Integer i6 = map.remove("张三");//根据键删除元素
        System.out.println(i6);//返回键的值
        System.out.println(map);

        System.out.println(map.containsKey("张三"));//判断包含
        System.out.println(map.containsValue(25));

        System.out.println(map.values());//获取
        System.out.println(map.keySet());//获取
        System.out.println(map.size());//大小
    }

    //遍历,根据键
    @Test
    public void demo1() {
        //没有iterator
        Map<String, Integer> map = new HashMap<>();
        map.put("张三", 23);
        map.put("李四", 24);
        map.put("王五", 25);
        map.put("赵六", 26);

        //迭代器法 遍历
        Set<String> keyset = map.keySet();//获取所有键的集合
        Iterator<String> it = keyset.iterator();
        while (it.hasNext()) {
            String key = it.next();//获取每一个键
            Integer value = map.get(key);//根据键获取值
            System.out.println(key + value);
        }

        //使用增强for循环
        for (String key : map.keySet())
            System.out.println(key + map.get(key));


    }

    //遍历，根据键值对对象
    @Test
    public void demo2() {
        //双列集合键值对变成单列集合的对象
        //遍历单列集合
        Map<String, Integer> map = new HashMap<>();
        map.put("张三", 23);
        map.put("李四", 24);
        map.put("王五", 25);
        map.put("赵六", 26);

        //迭代器法
        //Map.Entry说明Entry是Map的内部接口，将键和值封装成了Entry对象，并存储在Set集合中
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        //获取每一个对象
        Iterator<Map.Entry<String, Integer>> it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> en = it.next();
            String key = en.getKey();//根据键值对对象获取键
            Integer value = en.getValue();//根据键值对对象获取值
            System.out.println(key + value);
        }

        //增强for循环
        for (Map.Entry<String, Integer> en : map.entrySet())
            System.out.println(en.getKey() + en.getValue());
    }

    //HashMap 键是Student，如何保证唯一
    @Test
    public void demo3() {
        HashMap<Student, String> hm = new HashMap<>();
        hm.put(new Student("张三", 23), "北京");
        hm.put(new Student("张三", 23), "上海");
        hm.put(new Student("李四", 24), "广州");
        hm.put(new Student("王五", 25), "深圳");

        System.out.println(hm);
    }

    //LinkedHashMap
    @Test
    public void demo4() {
        LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();
        lhm.put("张三", 23);
        lhm.put("李四", 24);
        lhm.put("王五", 25);
        lhm.put("赵六", 26);
        System.out.println(lhm);
    }

    //TreeMap键是Student 值是String
    @Test
    public void demo5() {
        TreeMap<Student, String> tm = new TreeMap<>(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int num = s1.name.compareTo(s2.name);//姓名优先比较
                return num == 0 ? s1.age - s2.age : num;
            }
        });
        //Student重写comparator
        tm.put(new Student("张三", 21), "北京");
        tm.put(new Student("赵六", 23), "深圳");
        tm.put(new Student("王五", 23), "广州");
        tm.put(new Student("李四", 24), "上海");

        System.out.println(tm);
    }

    //HashMap嵌套HashMap,遍历
    @Test
    public void demo6() {
        //88期，键是学生类，值是归属地
        //89期，键是学生类，值是归属地
        //88,89放入课堂集合中
        HashMap<Student, String> hm88 = new HashMap<>();
        hm88.put(new Student("张三", 23), "北京");
        hm88.put(new Student("李四", 24), "北京");
        hm88.put(new Student("王五", 25), "上海");
        hm88.put(new Student("赵六", 26), "广州");
        System.out.println(hm88);

        HashMap<Student, String> hm89 = new HashMap<>();
        hm89.put(new Student("唐僧", 1023), "北京");
        hm89.put(new Student("孙悟空", 1024), "北京");
        hm89.put(new Student("猪八戒", 1025), "上海");
        hm89.put(new Student("沙和尚", 1026), "广州");
        System.out.println(hm89);

        HashMap<HashMap<Student, String>, String> hm = new HashMap<>();
        hm.put(hm88, "第88期");
        hm.put(hm89, "第89期");
        System.out.println(hm);

        //根据键遍历
        for (HashMap<Student, String> hmm : hm.keySet())
            for (Student stu : hmm.keySet())
                System.out.println(hm.get(hmm) + stu + hmm.get(stu));
    }

    //*****HashMap,HashTable(！！！面试题！！！！)*****
    @Test
    public void demo7() {
        //HashMap线程不安全，效率高，JDK1.2
        //HashTable线程安全，效率低，JDK1.0
        //HashMap可以存null的键和值
        //HashTable不可以存null的键和值
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put(null, 23);
        hm.put("李四", null);
        System.out.println(hm);

        Hashtable<String, Integer> ht = new Hashtable<>();
        ht.put(null, 23);//报错
        ht.put("李四", null);//报错
        System.out.println(ht);
    }




    //统计字符串中每个字符出现的次数
    @Test
    public void test0() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] arr = str.toCharArray();
        HashMap<Character, Integer> hm = new HashMap<>();
        for (char c : arr) {
            /*if (hm.containsKey(c))
                hm.put(c, hm.get(c)+1);
            else
                hm.put(c,1);*/
            hm.put(c, hm.containsKey(c) ? hm.get(c) + 1 : 1);
        }
        System.out.println(hm);

    }


}
