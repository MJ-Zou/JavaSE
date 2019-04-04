package com.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class io_2_char {
    public static void main(String[] args) throws IOException {
        test3();
    }

    //FileReader 字符流输入流
    private static void demo0() throws IOException {
        FileReader fr = new FileReader("x.txt");
        int x;
        while ((x = fr.read()) != -1) {//通过项目的码表一次读取一个字符
            System.out.print((char) x);
        }

        fr.close();
    }

    //FileWriter 字符流输出流
    private static void demo1() throws IOException {
        FileWriter fw = new FileWriter("x.txt");
        fw.write("大家好，嘻嘻嘻爱！！！");
        fw.write(97);
        fw.close();
    }

    //Copy1 拷贝,不可以拷贝非纯文本的
    private static void demo2() throws IOException {
        //字节转字符输入，没有码表对应的转成'?'
        //字符转字节输出
        FileReader fr = new FileReader("x.txt");
        FileWriter fw = new FileWriter("x_copy.txt");

        int c;
        while ((c = fr.read()) != -1)
            fw.write(c);

        fr.close();
        fw.close();//Writer有2K的缓冲区，需要关流
    }

    //Copy2 拷贝,字符数组
    private static void demo3() throws IOException {

        FileReader fr = new FileReader("x.txt");
        FileWriter fw = new FileWriter("x_copy.txt");

        char[] arr = new char[1024];//数据读到字符数组
        int len;
        while ((len = fr.read(arr)) != -1)
            fw.write(arr, 0, len);//字符数组写到文件上

        fr.close();
        fw.close();//Writer有2K的缓冲区，需要关流
    }

    //Copy3，带缓冲区的
    private static void demo4() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("x.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("x_copy.txt"));

        int c;
        while ((c = br.read()) != -1)
            bw.write(c);
        br.close();
        bw.close();

    }

    //缓冲区的：ReadLine NewLine
    private static void demo5() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("x.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("x_copy.txt"));

        String line;
        while ((line = br.readLine()) != null) {//读取一行
            bw.write(line);//不换行
            bw.newLine();//新的一行
            //  \r\n仅支持windows
        }
        br.close();
        bw.close();
    }

    //行号 LineNumberReader
    private static void demo6() throws IOException {
        LineNumberReader lnr = new LineNumberReader(new FileReader("x.txt"));
        String line;
        lnr.setLineNumber(100);//设置初始行号，不是从该行开始读
        while ((line = lnr.readLine()) != null) {
            System.out.print(lnr.getLineNumber());//得到行号
            System.out.println(" : " + line);
        }
    }

    //装饰设计模式
    private static void demo7() {
        /*
        耦合性不强，被装饰类的变化与装饰类无关
         */
        neew n = new neew(new old());
        n.code();

    }

    //使用指定码表读写字符??
    private static void demo8() throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(new FileInputStream("utf-8.txt"), "utf-8"));
        BufferedWriter bw =
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream("GBK.txt"), "GBK"));

        int c;
        while ((c = br.read()) != -1)
            bw.write(c);

        br.close();
        bw.close();

    }

    //递归
    private static void demo9() {
        /*
        弊端：不能调用次数过多，容易栈内存溢出
        好处：不用知道循环次数

        构造方法不能使用递归，不一定必须有返回值
         */
        System.out.println(fun(8000));
    }

    //递归函数
    private static int fun(int i) {
        if (i == 1)
            return 1;
        else
            return i * fun(--i);

    }

    //***************练习********************//
    //文本各行反转 第一与倒第一行反转
    private static void test0() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("x.txt"));
        ArrayList<String> list = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null)
            list.add(line);
        br.close();
        //流对象 晚开早关
        BufferedWriter bw = new BufferedWriter(new FileWriter("x_copy.txt"));
        for (int i = list.size(); i > 0; i--) {
            bw.write(list.get(i - 1));
            bw.newLine();
        }

        bw.close();


    }

    //统计文本上字符出现的个数
    private static void test1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("x.txt"));
        TreeMap<Character, Integer> tm = new TreeMap<>();
        int i;
        while ((i = br.read()) != -1) {
            char c = (char) i;
            tm.put(c, tm.containsKey(c) ? tm.get(c) + 1 : 1);
        }
        br.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter("x_num.txt"));
        for (char c : tm.keySet()) {
            switch (c) {
                case '\t':
                    bw.write("\\t:" + tm.get(c));
                    break;
                case '\n':
                    bw.write("\\n:" + tm.get(c));
                    break;
                case '\r':
                    bw.write("\\r:" + tm.get(c));
                    break;
                case ' ':
                    bw.write("空格:" + tm.get(c));
                    break;
                default:
                    bw.write(c + ":" + tm.get(c));
            }
            bw.newLine();
        }
        bw.close();


    }

    //试用版软件
    private static void test2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("config.txt"));
        String line = br.readLine();
        br.close();

        Integer n = Integer.parseInt(line);
        if (n > 0)
            System.out.println("你还有" + --n + "次机会");
        else {
            System.out.println("你没机会了");
            return;
        }


        BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
        bw.write(n.toString());
        bw.close();


    }

    //递归练习，打印路径下所有.java文件名
    private static void test3() {
        Scanner sc = new Scanner(System.in);
        File file;
        while (true) {
            System.out.println("请输入路径：");
            file = new File(sc.nextLine());
            if (!file.exists())
                System.out.println("不是路径！");
            else if (file.isFile())
                System.out.println("输入文件了！");
            else break;
        }
        bianli(file);
    }

    //递归
    private static void bianli(File file) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile()) {
                if (f.getName().endsWith(".java"))
                    System.out.println(f);
            } else
                bianli(f);
        }
    }
}

//装饰设计模式
interface coder {
    public void code();
}

class old implements coder {

    @Override
    public void code() {
        System.out.println("原有");
    }
}

class neew implements coder {
    //1、获取别装饰类的引用
    private old o;

    //2、在构造方法中传入被装饰类的对象
    neew(old o) {
        this.o = o;
    }

    //3、对原有的功能进行升级
    @Override
    public void code() {
        o.code();
        System.out.println("新的");
    }
}
