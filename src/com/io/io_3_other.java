package com.io;

import com.zmj.Student;

import java.io.*;
import java.util.*;

public class io_3_other {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        demo10();
    }

    //序列流 SequenceInputStream，了解
    private static void demo0() throws IOException {
        //太繁琐了
        /*FileInputStream fis1 = new FileInputStream("x.txt");
        FileOutputStream fos = new FileOutputStream("z.txt");
        int b1;
        while ((b1 = fis1.read()) != -1)
            fos.write(b1);
        fis1.close();

        FileInputStream fis2 = new FileInputStream("y.txt");
        int b2;
        while ((b2 = fis2.read()) != -1)
            fos.write(b2);
        fis2.close();
        fos.close();*/

        //序列流整合2个
        FileInputStream fis1 = new FileInputStream("x.txt");
        FileInputStream fis2 = new FileInputStream("y.txt");
        SequenceInputStream sis = new SequenceInputStream(fis1, fis2);//两个流整合成一个流
        FileOutputStream fos = new FileOutputStream("z.txt");

        int b;
        while ((b = sis.read()) != -1)
            fos.write(b);

        sis.close();//关闭这个就行了
        fos.close();

        //序列流整合3+个
        FileInputStream fis3 = new FileInputStream("x.txt");
        FileInputStream fis4 = new FileInputStream("y.txt");
        FileInputStream fis5 = new FileInputStream("z.txt");
        Vector<FileInputStream> v = new Vector<>();//创建集合对象
        v.add(fis3);//将流对象存储进来
        v.add(fis4);
        v.add(fis5);

        Enumeration<FileInputStream> en = v.elements();
        SequenceInputStream sis1 = new SequenceInputStream(en);//枚举中的输入流整合成一个

        FileOutputStream fos1 = new FileOutputStream("zz.txt");
        int b1;
        while ((b1 = sis1.read()) != -1)
            fos1.write(b1);
        sis1.close();
        fos1.close();

    }

    //内存输出流 ByteArrayOutputStream，掌握
    private static void demo1() throws IOException {
        /*
        用于字符读取
         */
        FileInputStream fis = new FileInputStream("x.txt");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//在内存中创建了可以增长的数组

        int b;
        while ((b = fis.read()) != -1)
            baos.write(b);//将读取到的数据逐个写到内存

        System.out.println(baos.toString());//将缓冲区的内容给字符串

        fis.close();
        //内存输出流无需关闭

    }

    //对象操作流 ObjectOutput/InputStream，了解
    private static void demo2() throws IOException, ClassNotFoundException {
        //写出
        //对象需要实现接口 implements Serializable
        Student s1 = new Student("张三", 23);
        Student s2 = new Student("李四", 24);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("x.txt"));
        oos.writeObject(s1);
        oos.writeObject(s2);
        oos.close();

        //读入
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("x.txt"));
        Student s3 = (Student) ois.readObject();
        Student s4 = (Student) ois.readObject();
        //Student s5 = (Student) ois.readObject();  //EOFException
        System.out.println(s3);
        System.out.println(s4);
        ois.close();
    }

    //对象操作流的优化，针对多个对象，了解
    private static void demo3() throws IOException, ClassNotFoundException {
        Student s1 = new Student("张三", 23);
        Student s2 = new Student("李四", 24);
        Student s3 = new Student("王五", 25);
        Student s4 = new Student("赵六", 26);
        ArrayList<Student> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("x.txt"));
        oos.writeObject(list);//把整个集合对象一次写出

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("x.txt"));
        ArrayList<Student> list1;
        list1 = (ArrayList<Student>) ois.readObject();//把整个集合对象一次读取
        System.out.println(list1);

    }

    //打印流 PrintStream/Writer，掌握
    private static void demo4() throws FileNotFoundException {
        // PrintStream字节流
        System.out.println("aaa");
        PrintStream ps = System.out;//获取标准输出流
        ps.println(97);//底层通过Integer.toString
        ps.write(97);//查找码表

        Student s1 = new Student("张三", 23);
        ps.println(s1);//默认调用s1的toString方法

        Student s2 = null;
        ps.println(s2);//打印引用数据类型，是null打印null，否则打印toString

        ps.close();

        // PrintWriter字符流
        PrintWriter pw = new PrintWriter("x.txt");
        pw.println(97);//自动刷出
        pw.write(97);//不能自动刷出
        pw.print(97);//不能自动刷出

        pw.close();
    }

    //标准输入输出流，了解
    private static void demo5() throws IOException {
       /* InputStream is = System.in;
        int x = is.read();
        System.out.println(x);
        //is.close();//不用关
        */

        System.setIn(new FileInputStream("x.txt"));//改变标准输入流
        System.setOut(new PrintStream("y.txt"));//改变标准输出流

        InputStream is1 = System.in;//获取标准键盘输入流，默认指向键盘，改变后指向文件
        PrintStream ps = System.out;//获取标准输出流，默认指向控制台，改变后指向文件

        int b;
        while ((b = is1.read()) != -1)
            ps.write(b);
        is1.close();
        ps.close();
    }

    //两种方式实现键盘录入，了解
    private static void demo6() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//InputStream转换流
        String line = br.readLine();
        System.out.println(line);
        br.close();

        Scanner sc = new Scanner(System.in);
        String line1 = sc.nextLine();
        System.out.println(line1);
    }

    //随机访问流 RandomAccessFile，了解
    private static void demo7() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("x.txt", "rw");
        raf.write(97);
        raf.seek(15);//在指定位置设置指针读写

        raf.write(98);

        //System.out.println(raf.read()); //可读可写
        raf.close();
    }

    //数据输入输出流 Data，了解
    private static void demo8() throws IOException {
        FileOutputStream fos = new FileOutputStream("x.txt");
        fos.write(997);//  00000000 00000011 11100101 取后八位二进制写入
        fos.write(998);
        fos.write(999);
        fos.close();

        FileInputStream fis = new FileInputStream("x.txt");
        System.out.println(fis.read());//229=11100101
        System.out.println(fis.read());
        System.out.println(fis.read());
        fis.close();

        //Data
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("x.txt"));
        dos.writeInt(997);
        dos.writeInt(998);
        dos.writeInt(999);
        dos.close();

        DataInputStream dis = new DataInputStream(new FileInputStream("x.txt"));
        System.out.println(dis.readInt());
        System.out.println(dis.readInt());
        System.out.println(dis.readInt());
        dis.close();


    }

    //Properties 配置文件，了解
    private static void demo9() {
        Properties prop = new Properties();
        prop.put("abc", 132);
        System.out.println(prop);
        prop.setProperty("name", "张三");
        prop.setProperty("tel", "189123456789");
        System.out.println(prop);

        //遍历
        Enumeration<String> names = (Enumeration<String>) prop.propertyNames();
        while (names.hasMoreElements()) {
            String key = names.nextElement();//获取Properties的每一个键
            String value = prop.getProperty(key);
            System.out.println(key + "=" + value);
        }


    }

    //Properties : load(),store()，了解
    private static void demo10() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("config.properties"));//将文件上的键值对读到集合中
        System.out.println(prop);//顺序变了

        prop.setProperty("tel", "132");
        //xxx是对列表参数的描述，null是不描述
        prop.store(new FileOutputStream("config.properties"), "xxx");
        System.out.println(prop);

    }


    //*********************练习************************//
    //输出流面试题，掌握
    private static void test0() throws IOException {
        /*
        文件输入流，调用read(byte[] b),打印a.txt中的,byte大小为5
         */
        FileInputStream fis = new FileInputStream("x.txt");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] arr = new byte[5];
        int len;
        while ((len = fis.read(arr)) != -1)
            baos.write(arr, 0, len);

        System.out.println(baos);
        fis.close();
    }

    //修改标准输入输出流拷贝图片，了解
    private static void test1() throws IOException {
        System.setIn(new FileInputStream("1.mp3"));//改变标准输入流
        System.setOut(new PrintStream("1_copy.mp3"));//改变标准输出流

        InputStream is = System.in;
        PrintStream ps = System.out;

        byte[] arr = new byte[1024];
        int len;
        while ((len = is.read(arr)) != -1)
            ps.write(arr, 0, len);

        is.close();
        ps.close();
    }


}
