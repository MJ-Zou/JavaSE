package com.io;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class io_1_byte {
    public static void main(String[] args) throws IOException {
        /*
        字节流：抽象父类
            InputStream
            OutputStream
        字符流：抽象父类
            Reader
            Writer
         */
        test2();
    }

    //FileInputStream 字节输入流对象
    private static void demo0() throws IOException {
        FileInputStream fis = new FileInputStream("xxx.txt");//创建流对象
        int x = fis.read();//从硬盘上读取第一个字节
        System.out.println(x);
        int y = fis.read();//从硬盘上读取下一个字节
        System.out.println(y);
        int z = fis.read();
        System.out.println(z);
        int a = fis.read();//结束标记-1
        System.out.println(a);
        fis.close();

        //简洁读取方式
        FileInputStream fis1 = new FileInputStream("xxx.txt");
        int b;
        while ((b = fis1.read()) != -1)
            System.out.println(b);
        fis1.close();
    }

    //FileOutStream 字节输出流对象
    private static void demo1() throws IOException {
        FileOutputStream fos = new FileOutputStream("yyy.txt");
        //清空原有内容，重新输入
        fos.write(97);//int数转成字节写入，去除前三个8位
        fos.write(98);
        fos.write(99);
        fos.close();

        //追加,续写
        FileOutputStream fos1 = new FileOutputStream("yyy.txt", true);
        //不清空，继续输入
        fos1.write(100);
        fos1.close();
    }

    //拷贝1(*核心代码*)慢 40.999s
    private static void demo2() throws IOException {
        Date d1 = new Date();

        FileInputStream fis = new FileInputStream("1.mp3");//1.创建输入流对象
        FileOutputStream fos = new FileOutputStream("1_copy.mp3");//2.创建输出流对象

        int b;
        while ((b = fis.read()) != -1)//3.不断读每一个字节
            fos.write(b);//4.将每一个字节写出

        fis.close();//5.关闭释放资源
        fos.close();

        Date d2 = new Date();
        System.out.println(d2.getTime() - d1.getTime());
    }

    //拷贝2(*available方法*)快，但容易内存溢出 0.010s
    private static void demo3() throws IOException {
        Date d1 = new Date();

        FileInputStream fis = new FileInputStream("1.mp3");//1.创建输入流对象
        FileOutputStream fos = new FileOutputStream("1_copy.mp3");//2.创建输出流对象

        byte[] arr = new byte[fis.available()];//创建与文件大小一样的字节数组
        fis.read(arr);//将文件内的字节存入内存中
        fos.write(arr);//字节数组中的字节数写到文件上

        fis.close();
        fos.close();

        Date d2 = new Date();
        System.out.println(d2.getTime() - d1.getTime());
    }

    //拷贝3(*小数组*)，推荐！ 0.009s
    private static void demo4() throws IOException {
        Date d1 = new Date();

        FileInputStream fis = new FileInputStream("1.mp3");
        FileOutputStream fos = new FileOutputStream("1_copy.mp3");
        byte[] arr = new byte[1024 * 8];

        int len;
        while ((len = fis.read(arr)) != -1)//不加arr，len得到字节的码表值
            fos.write(arr, 0, len);

        fis.close();
        fos.close();

        Date d2 = new Date();
        System.out.println(d2.getTime() - d1.getTime());
    }

    //拷贝4(*Buffer缓冲区*)，推荐！ 0.040s
    private static void demo5() throws IOException {
        Date d1 = new Date();

        FileInputStream fis = new FileInputStream("1.mp3");
        FileOutputStream fos = new FileOutputStream("1_copy.mp3");

        //创建缓冲区对象，对输入流进行包装使其更强大
        //一次从文件读8192个到内存
        //写满8192个到内存再一次写到文件中
        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        int b;
        while ((b = bis.read()) != -1)
            bos.write(b);

        bis.close();
        bos.close();

        Date d2 = new Date();
        System.out.println(d2.getTime() - d1.getTime());
    }

    //flush和close区别（缓冲区）
    private static void demo6() throws IOException {
        /*
        close方法：
            具备刷新flush的功能，关闭流前，将缓冲区内容和写到文件上
        flush方法：
            刷新，将缓存内容写到文件上
        */
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("1.mp3"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("1_copy.mp3"));
        int b;
        while ((b = bis.read()) != -1)
            bos.write(b);
        //这时直接退出少内容！
        bos.flush();//加这句话就完整了！
        bos.close();//这句话可以省略！
    }

    //字节流读写中文
    private static void demo7() throws IOException {
        //读，很可能乱码
        FileInputStream fis = new FileInputStream("x.txt");
        byte[] arr = new byte[3];
        int len;
        while ((len = fis.read(arr)) != -1)
            System.out.println(new String(arr, 0, len));
        fis.close();

        //写，正确，转成字节流
        FileOutputStream fos = new FileOutputStream("x.txt");
        fos.write("我读书少,你不要骗我\n".getBytes());
    }

    //异常处理
    private static void demo8() throws IOException {
        //1.6之前
        FileInputStream fis = null;//路径可能不存在
        FileOutputStream fos = null;//路径可能不存在
        try {
            fis = new FileInputStream("x.txt");
            fos = new FileOutputStream("y.txt");

            int b;
            while ((b = fis.read()) != -1)//可能不可读
                fos.write(b);//可能不可写
        } finally {
            try {
                if (fis != null)
                    fis.close();//可能关不掉
            } finally {
                if (fos != null)
                    fos.close();
            }
        }

        //异常处理，1.7
        try (
                FileInputStream fis1 = new FileInputStream("x.txt");
                FileOutputStream fos1 = new FileOutputStream("y.txt");
        ) {
            int b1;
            while ((b1 = fis1.read()) != -1)
                fos1.write(b1);
        }//自动关闭


    }


    //加密
    private static void test0() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("1.mp3"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("1_copy.mp3"));

        int b;
        while ((b = bis.read()) != -1)
            bos.write(b ^ 123);//异或一个数，再次执行即可解密

        bis.close();
        bos.close();
    }

    //录入文件路径，拷贝到当前项目下
    private static void test1() throws IOException {
        Scanner sc = new Scanner(System.in);
        File file;
        System.out.println("请输入文件的路径");
        while (true) {//检查要文件的路径
            String line = sc.nextLine();
            file = new File(line);
            if (!file.exists())
                System.out.println("路径不存在！");
            else if (file.isDirectory())
                System.out.println("输入文件路径了！");
            else
                break;
        }

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file.getName()));

        int b;
        while ((b=bis.read())!=-1)
            bos.write(b);

        bis.close();
        bos.close();

    }

    //录入数据拷贝到文件x.txt中
    private static void test2() throws IOException {
        Scanner sc=new Scanner(System.in);
        BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("x.txt"));
        while (true){
            String line=sc.nextLine();
            if(line.equals("quit"))
                break;
            else{
                bos.write(line.getBytes());
                bos.write("\n".getBytes());
            }

        }
        bos.close();

    }

}
