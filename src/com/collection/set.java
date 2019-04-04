package com.collection;

import com.zmj.Student;
import org.junit.Test;

import java.util.*;

public class set {
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
                LinkedHashSet 有序
                    底层是链表
            TreeSet 数字可以排序
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

    //无序性，唯一
    @Test
    public void demo0() {
        HashSet<String> hs = new HashSet<>();
        hs.add("a");
        hs.add("b");
        hs.add("c");
        hs.add("d");
        System.out.println(hs.add("a"));//存储重复元素返回false

        for (String string : hs)  //无序存放
            System.out.println(string);

    }

    //自定义类的唯一性
    @Test
    public void demo1() {
        HashSet<Student> hs = new HashSet<>();
        hs.add(new Student("张三", 23));
        hs.add(new Student("王五", 25));
        hs.add(new Student("李四", 24));
        hs.add(new Student("李四", 24));

        System.out.println(hs);

    }

    //LinkedHashSet 可以保证存储顺序
    @Test
    public void demo2() {
        LinkedHashSet<String> lhs = new LinkedHashSet<>();
        lhs.add("a");
        lhs.add("a");
        lhs.add("a");
        lhs.add("b");
        lhs.add("b");
        lhs.add("b");
        lhs.add("c");
        lhs.add("d");
        System.out.println(lhs);
    }

    //TreeSet 排序
    @Test
    public void demo3() {
        TreeSet<Integer> ts = new TreeSet<>();//排序，保证唯一
        ts.add(3);
        ts.add(1);
        ts.add(2);
        ts.add(1);
        ts.add(2);
        ts.add(3);
        ts.add(3);
        System.out.println(ts);

        TreeSet<Student> ts1 = new TreeSet<>();
        ts1.add(new Student("张三", 23));
        ts1.add(new Student("李四", 13));
        ts1.add(new Student("周七", 13));
        ts1.add(new Student("王五", 43));
        ts1.add(new Student("赵六", 33));
        System.out.println(ts1);//无法比较
    }

    //TreeSet 比较器原理 实现Comparator接口
    @Test
    public void demo4() {
        TreeSet<String> ts = new TreeSet<>(new comarebylen());
        ts.add("aaa");
        ts.add("dkfh");
        ts.add("shskf");
        ts.add("jfj");
        System.out.println(ts);
    }

    //产生10个随机数，不重复
    @Test
    public void test0() {
        Random r = new Random();
        HashSet<Integer> hs = new HashSet<>();
        while (hs.size() < 10)
            hs.add(r.nextInt(20) + 1);
        System.out.println(hs);
    }


    //输入一行数，去除重复的
    private static void test1() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] arr = str.toCharArray();
        System.out.println(arr);
        HashSet<Character> hs = new HashSet<>();
        for (char c : arr) {
            hs.add(c);
        }
        System.out.println(hs);
    }

    //去除集合中重复的
    private static void test2() {
        ArrayList<Character> list = new ArrayList<>();
        list.add('a');
        list.add('a');
        list.add('b');
        list.add('b');
        list.add('c');
        list.add('c');
        System.out.println(list);

        HashSet<Character> hs = new HashSet<>();
        hs.addAll(list);
        list.clear();
        list.addAll(hs);
        System.out.println(list);
    }

    //无序重复的字符串，去重复变有序
    private static void test3() {
        ArrayList<String> list = new ArrayList<>();
        list.add("vvv");
        list.add("bbb");
        list.add("vvv");
        list.add("ffffff");
        list.add("aaa");
        list.add("ddd");
        list.add("vvv");
        list.add("vvv");
        list.add("abx");
        list.add("bbb");
        System.out.println(list);

        TreeSet<String> ts = new TreeSet<>(new Comparator<String>() {//匿名内部类
            @Override
            public int compare(String s1, String s2) {
                int num = s1.compareTo(s2);//内容为主要条件
                return num == 0 ? num : 1;
            }
        });
        ts.addAll(list);
        System.out.println(ts);
    }

    //字符串字符排序,保留重复
    private static void test4() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] arr = str.toCharArray();
        TreeSet<Character> ts = new TreeSet<>(new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                int num = c1 - c2;
                return num == 0 ? -1 : num;
            }
        });
        for (char c : arr)
            ts.add(c);
        System.out.println(ts);
    }

    //输入整数，quit停止，倒序排列输出
    private static void test5() {
        TreeSet<Integer> ts = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                int num = n2 - n1;
                return num == 0 ? 1 : num;
            }
        });
        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();
            if (str.equals("quit"))
                break;

            //添加了异常处理
            try {
                ts.add(Integer.parseInt(str));

            } catch (Exception e) {
                System.out.println("输入有误，重输！");
            }//

        }
        System.out.println(ts);

    }

    //录入3个学生姓名，语文，数学成绩，总成绩排名输出
    private static void test6() {
        Scanner sc = new Scanner(System.in);
        TreeSet<student> ts = new TreeSet<>(new Comparator<student>() {
            @Override
            public int compare(student st1, student st2) {
                int num = st1.all - st2.all;
                return num == 0 ? 1 : num;
            }
        });
        for (int i = 0; i < 3; i++) {

            //添加异常处理
            while (true){
                try{
                    String str = sc.nextLine();
                    String[] arr = str.split(",");
                    ts.add(new student(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2])));
                    break;
                }
                catch (Exception e){
                    System.out.println("格式有误，重输！");
                }
            }


        }
        System.out.println(ts);
    }
}

class student {
    String name;
    int score1;
    int score2;
    int all;

    public student() {
    }

    public student(String name, int score1, int score2) {
        this.name = name;
        this.score1 = score1;
        this.score2 = score2;
        all = score1 + score2;
    }

    @Override
    public String toString() {
        return name + "  " + score1 + "  " + score2 + "\n";
    }
}

//比较器
class comarebylen implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        int num = s1.length() - s2.length();//长度为主要条件
        return num == 0 ? s1.compareTo(s2) : num;//内容为次要条件
    }
}