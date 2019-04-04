package com.object;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex {
    public static void main(String[] args) {
        demo7();
    }

    //正则
    private static void demo0() {
        System.out.println(checkQQ("002345"));
        String regex = "[1-9]\\d{4,14}";
        System.out.println("k02345".matches(regex));
    }

    //正则 字符类
    private static void demo1() {
        String regex1 = "[abc]";//abc之一的单个字符
        System.out.println("a".matches(regex1));
        String regex2 = "[^abc]";//除了abc的单个字符
        System.out.println("d".matches(regex2));
        String regex3 = "[a-d[m-p]]";//a-d,m-p的单个字符
        System.out.println("6".matches(regex3));
        String regex4 = "[a-z&&[^b-d]]";//a-z,除了b-d的单个字符
        System.out.println("b".matches(regex4));
    }

    //正则 预定义字符类
    private static void demo2() {
        String regex1 = "..";//任何2个字符
        System.out.println("a4".matches(regex1));
        String regex2 = "\\d";//任意数字字符
        System.out.println("9".matches(regex2));
        String regex3 = "\\D";//任意非数字字符
        System.out.println("9".matches(regex3));
        String regex4 = "\\s";//任意空白字符
        System.out.println(" ".matches(regex4));
        System.out.println("    ".matches(regex4));
        String regex5 = "\\S";//任意非空白字符
        System.out.println("h".matches(regex5));
        String regex6 = "\\w";//任意单词字符a-zA-Z_0-9
        System.out.println("_".matches(regex6));

    }

    //正则 数量词
    private static void demo3() {
        String regex1 = "[abc]?";//出现一次或0次
        System.out.println("".matches(regex1));
        String regex2 = "[abc]*";//0次到多次
        System.out.println("abc".matches(regex2));
        String regex3 = "[abc]+";//一次或多次
        System.out.println("abbcc".matches(regex3));
        String regex4 = "[abc]{2}";//恰好n次
        System.out.println("ac".matches(regex4));
        String regex5 = "[abc]{2,}";//至少n次
        System.out.println("aac".matches(regex5));
        String regex6 = "[abc]{2,5}";//n-m次
        System.out.println("aacbbb".matches(regex6));
    }

    //正则 分割
    private static void demo4() {
        String s = "aaa.bbb.ccc.ddd";
        String[] arr2 = s.split("\\.");//通过正则表达式切割字符串
        for (int i = 0; i < arr2.length; i++)
            System.out.println(arr2[i]);

        System.out.println("=====练习=====");
        String str = "91 27 46 38 50";
        String[] arr = str.split(" ");
        int[] narr = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            narr[i] = Integer.valueOf(arr[i]);
        Arrays.sort(narr);
        /*
        String ss = "";//不断产生垃圾，不好
        for (int i = 0; i < narr.length; i++)
            ss += " "+narr[i];
        ss=ss.substring(1,ss.length());
        System.out.println(ss);
        */
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < narr.length; i++)
            if (i == 0)
                sb.append(narr[0]);
            else
                sb.append(" " + narr[i]);
        System.out.println(sb);

    }

    //正则 替换
    private static void demo5() {
        String s = "aaa5fff5hh6xxx";
        String regex = "\\d";//任意数字
        String ss = s.replaceAll(regex, "");
        System.out.println(ss);
    }

    //正则 分组
    private static void demo6() {
        String regex = "(.)\\1(.)\\2";// \\1代表第一组又出现1次,\\2代表第二组又出现1次
        System.out.println("快快乐乐".matches(regex));
        String regex1 = "(..)\\1";// \\1代表第一组又出现1次
        System.out.println("高兴高兴".matches(regex1));

        String s = "sdqqfgkkkjppppkl";//按照叠词切割
        String regex2 = "(.)\\1+";// \\1+代表第一组出现一次或多次
        String[] arr = s.split(regex2);
        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);

        String s1 = "我我.我我我..要..要...学..编编编编.....程";
        String s2 = s1.replaceAll("\\.+", "");
        String s3 = s2.replaceAll("(.)\\1+", "$1");//$1代表第一组中的内容
        System.out.println(s3);
    }

    //pattern matcher概述
    private static void demo7() {
        Pattern p = Pattern.compile("a*b");//获取正则表达式
        Matcher m = p.matcher("aaaaab");//获取匹配器
        boolean b = m.matches();//查看是否匹配
        System.out.println(b);

        System.out.println("aaaaab".matches("a*b"));//等价于上面的

        String s = "哈哈18688888888,13788888888嘻嘻";
        String regex = "1[3578]\\d{9}";//手机号码的正则表达式
        Pattern p1 = Pattern.compile(regex);
        Matcher m1 = p1.matcher(s);
        while (m1.find())
            System.out.println(m1.group());


    }

    private static boolean checkQQ(String s) {
        char[] arr = s.toCharArray();
        if (s.length() < 5 || s.length() > 15)
            return false;
        else if (s.startsWith("0"))
            return false;
        for (int i = 0; i < s.length(); i++) {
            if (arr[i] > '9' || arr[i] < '0')
                return false;
        }
        return true;
    }
}
