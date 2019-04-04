package com.collection;

import com.zmj.Student;
import org.junit.Test;


import java.util.*;

public class list {
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
                底层是哈希表，针对键
                LinkedHashMap
                    底层是哈希表和链表，针对键
            TreeMap
                底层是二叉树，针对键
         */

    }

    //add
    @Test
    public void demo0() {
        Student[] arr = new Student[5];
        arr[0] = new Student("张三", 23);
        arr[1] = new Student("李四", 25);
        arr[2] = new Student("王五", 25);

        Collection c = new ArrayList();//父类引用指向子类对象
        boolean b1 = c.add("abc");
        boolean b2 = c.add(true);
        boolean b3 = c.add(100);
        boolean b4 = c.add(new Student("张三", 23));
        System.out.println(b1);//List 允许可以重复，始终返回ture
        System.out.println(b2);//set 不允许重复，重复返回false
        System.out.println(b3);
        System.out.println(b4);

        System.out.println(c);
    }

    //remove
    @Test
    public void demo1() {
        Collection c = new ArrayList();
        c.add("a");
        c.add("b");
        c.add("c");
        c.add("d");
        c.add("c");
        System.out.println(c);
        System.out.println(c.size());//元素个数

        c.remove("c");//清除指定元素
        System.out.println(c);

        System.out.println(c.contains("c"));//是否包含

        c.clear();//清空全部元素
        System.out.println(c);

        System.out.println(c.isEmpty());//是否空

    }

    //转数组 遍历
    @Test
    public void demo2() {
        Collection c = new ArrayList();
        c.add("a");
        c.add("b");
        c.add("c");
        c.add("d");

        Object[] arr = c.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        Collection c1 = new ArrayList();
        c1.add(new Student("张三", 23));//父类引用指向子类对象
        c1.add(new Student("李四", 24));
        c1.add(new Student("王五", 25));
        c1.add(new Student("赵六", 26));

        Object[] arr1 = c1.toArray();
        for (int i = 0; i < arr1.length; i++) {
            Student s = (Student) arr1[i];
            System.out.println(s.name);
        }

    }

    //带all的
    @Test
    public void demo3() {
        Collection c1 = new ArrayList();
        c1.add("a");
        c1.add("b");
        c1.add("c");
        c1.add("d");
        Collection c2 = new ArrayList();
        c2.add("a");
        c2.add("b");
        c2.add("c");
        c2.add("z");

        System.out.println(c1.contains(c2));//c1是否包含c2

        c1.addAll(c2);//将c2添加到c1中
        System.out.println(c1);
        c1.add(c2);//c2作为一个元素添加到c1
        System.out.println(c1);

        c1.removeAll(c2);//删除交集
        System.out.println(c1);

        c1.addAll(c2);
        c1.retainAll(c2);//取交集
        System.out.println(c1);


    }

    //迭代器 遍历
    @Test
    public void demo4() {
        Collection c1 = new ArrayList();
        c1.add("a");
        c1.add("b");
        c1.add("c");
        c1.add("d");
        Iterator it = c1.iterator();//获取迭代器

        while (it.hasNext()) {//判断是否有下一个元素
            System.out.println(it.next());
        }
        Collection c2 = new ArrayList();
        c2.add(new Student("张三", 23));
        c2.add(new Student("李四", 24));
        c2.add(new Student("王五", 25));
        c2.add(new Student("赵六", 26));
        Iterator it1 = c2.iterator();//获取迭代器
        while (it1.hasNext()) {//判断是否有下一个元素
            System.out.println(((Student) it1.next()).name);
        }
    }

    //List
    @Test
    public void demo5() {
        List list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add(1, "e");//指定位置添加,0<=index<=size
        System.out.println(list);

        Object obj = list.remove(1);//返回被删除的元素,不自动装箱
        System.out.println(obj);
        System.out.println(list);

        Object obj1 = list.get(0);//索引返回当前位置元素
        System.out.println(obj1);//通过索引遍历
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        list.set(1, "bb");//修改指定位置元素
        System.out.println(list);
    }

    //并发修改异常
    @Test
    public void demo6() {
        List list = new ArrayList();
        list.add("a");
        list.add("hello");
        list.add("b");
        list.add("c");

        /*
        Iterator it = list.iterator();//获取迭代器
        while (it.hasNext())
            if (((String) it.next()).equals("hello"))
                list.add("yes");//遍历的同时在增加，即并发修改 异常！
        */

        ListIterator lit = list.listIterator();//获取list特有的迭代器
        while (lit.hasNext()) {
            if (((String) lit.next()).equals("hello"))
                lit.add("yes");//自带函数！遍历的同时在增加，即并发修改，正确！
        }
        System.out.println(list);
    }

    //ListIterator
    @Test
    public void demo7() {
        List list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("world");
        list.add("d");
        list.add("e");

        ListIterator lit = list.listIterator();//获取迭代器
        while (lit.hasNext())
            System.out.println(lit.next());//获取元素并将指针向后移动
        System.out.println("===============");
        while (lit.hasPrevious())
            System.out.println(lit.previous());//获取元素并将指针向前移动
    }

    //Vector
    @Test
    public void demo8() {
        Vector v = new Vector();
        v.addElement("a");
        v.addElement("b");
        v.addElement("c");
        v.addElement("d");

        Enumeration en = v.elements();//获取枚举
        while (en.hasMoreElements())//判断集合中是否有元素
            System.out.println(en.nextElement());//获取集合中的元素
    }

    //LinkedList 链表
    @Test
    public void demo9() {
        LinkedList list = new LinkedList();
        list.addFirst("a");//头插
        list.addFirst("b");
        list.add("c");//尾插
        list.add("d");
        System.out.println(list);
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        list.removeFirst();
        list.removeLast();
        System.out.println(list);

        //栈
        Stack s = new Stack();
        s.in("a");
        s.in("b");
        s.in("c");
        System.out.println(s.isEmpty());
        System.out.println(s.out());
        System.out.println(s.out());
        System.out.println(s.out());
        System.out.println(s.isEmpty());

    }

    //泛型generic
    @Test
    public void demo10() {
        ArrayList<Student> list = new ArrayList();//<>限制存储类型！
        //list.add(100);  //限制
        //list.add(true);
        list.add(new Student("张三", 23));
        list.add(new Student("李四", 24));
        list.add(new Student("王五", 25));
        Iterator<Student> it = list.iterator();//<>限制类型！
        while (it.hasNext()) {
            Student s = it.next();//不用强制转型了
            System.out.println(s.name + s.age);
        }
    }


    //去除字符串中的重复元素
    @Test
    public void test0() {
        List list0 = new ArrayList();
        list0.add(new Student("张三", 23));
        list0.add(new Student("张三", 23));
        list0.add(new Student("张三", 23));
        list0.add(new Student("李四", 24));
        list0.add(new Student("李四", 24));
        list0.add(new Student("李四", 24));
        list0.add(new Student("王五", 25));
        list0.add(new Student("王五", 25));

        System.out.println(list0);
        Iterator it = list0.iterator();
        List list1 = new ArrayList();
        while (it.hasNext()) {
            Student s = (Student) it.next();
            if (!list1.contains(s))//底层依赖equals，需要重写
                list1.add(s);
        }
        System.out.println(list1);

    }

    //LinkedList 模拟栈
    public static class Stack {
        LinkedList list = new LinkedList();

        public void in(Object obj) {//进栈
            list.addLast(obj);
        }

        public Object out() {//出栈
            return list.removeLast();
        }

        public boolean isEmpty() {//是否空
            return list.isEmpty();
        }

    }
}

