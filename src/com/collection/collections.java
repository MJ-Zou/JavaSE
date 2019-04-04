package com.collection;

import com.zmj.BaseStudent;
import com.zmj.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class collections {
    public static void main(String[] args) {
        demo0();
    }


    //排序
    private static void demo0() {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("c");
        list.add("d");
        list.add("b");
        System.out.println(list);
        Collections.sort(list);//将集合排序
        System.out.println(list);
    }

    //二分查找
    private static void demo1() {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("c");
        list.add("d");
        list.add("e");
        System.out.println(list);
        System.out.println(Collections.binarySearch(list, "b"));//负插入点减一
        System.out.println(Collections.binarySearch(list, "d"));
    }

    //获取最大值,反转，随机换序
    private static void demo2() {
        ArrayList<String> list = new ArrayList<>();
        list.add("c");
        list.add("g");
        list.add("a");
        list.add("b");
        System.out.println(Collections.max(list));//最值

        Collections.reverse(list);//反转
        System.out.println(list);

        Collections.shuffle(list);//随机换顺序
        System.out.println(list);
    }

    //泛型固定下边界?
    @Test
    public void demo3() {
        ArrayList<Student> list1 = new ArrayList<>();
        list1.add(new Student("张三", 23));
        list1.add(new Student("李四", 24));

        ArrayList<BaseStudent> list2 = new ArrayList<>();
        list2.add(new BaseStudent("王五", 25));
        list2.add(new BaseStudent("赵六", 26));

        list1.addAll(list2);
        System.out.println(list1);
    }


    //模拟斗地主洗牌发牌
    @Test
    public void test0() {
        //生成扑克，洗牌，发牌，看牌
        String[] num = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
        String[] color = {"红桃", "黑桃", "草花", "方片"};
        ArrayList<String> pocker = new ArrayList<>();
        for (String str1 : num) {
            for (String str2 : color) {
                pocker.add(str2 + str1);
            }
        }

        pocker.add("小王");
        pocker.add("大王");
        System.out.println(pocker.size());//生成

        Collections.shuffle(pocker);//洗牌
        System.out.println(pocker);

        ArrayList<String> gaojin = new ArrayList<>();
        ArrayList<String> longwu = new ArrayList<>();
        ArrayList<String> me = new ArrayList<>();
        ArrayList<String> dipai = new ArrayList<>();

        for (int i = 0; i < pocker.size(); i++) {//发牌
            if (i < 3)
                dipai.add(pocker.get(i));
            else if (i % 3 == 0)
                gaojin.add(pocker.get(i));
            else if (i % 3 == 1)
                longwu.add(pocker.get(i));
            else
                me.add(pocker.get(i));
        }

        //看牌
        System.out.println(gaojin);
        System.out.println(longwu);
        System.out.println(me);
        System.out.println(dipai);


    }

    //模拟斗地主洗牌发牌+排序（高级）
    @Test
    public void test1() {
        //生成扑克，洗牌，发牌，看牌
        String[] num = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
        String[] color = {"红桃", "黑桃", "草花", "方片"};

        HashMap<Integer, String> pocker = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        int index = 0;
        for (String n : num) {
            for (String cl : color) {
                list.add(index);
                pocker.put(index++, cl + n);
            }
        }

        list.add(index);
        pocker.put(index++, "小王");

        list.add(index);
        pocker.put(index, "大王");

        System.out.println(pocker);
        System.out.println(list);

        TreeSet<Integer> gaojin = new TreeSet<>();
        TreeSet<Integer> longwu = new TreeSet<>();
        TreeSet<Integer> me = new TreeSet<>();
        TreeSet<Integer> depai = new TreeSet<>();


        Collections.shuffle(list);
        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            if (i < 3)
                depai.add(list.get(i));
            else if (i % 3 == 0)
                gaojin.add(list.get(i));
            else if (i % 3 == 1)
                longwu.add(list.get(i));
            else
                me.add(list.get(i));
        }


        System.out.println("\n\n高进：");
        for (Integer i : gaojin)
            System.out.print(pocker.get(i) + " ");

        System.out.println("\n\n龙五：");
        for (Integer i : longwu)
            System.out.print(pocker.get(i) + " ");

        System.out.println("\n\n我：");
        for (Integer i : me)
            System.out.print(pocker.get(i) + " ");

        System.out.println("\n\n底牌：");
        for (Integer i : depai)
            System.out.print(pocker.get(i) + " ");
    }
}
