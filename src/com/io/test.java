package com.io;

import com.collection.list;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class test {
    public static void main(String[] args) throws IOException {
        test6();
    }

    //统计文件件的大小
    private static void test0() {
        System.out.println("请输入文件夹路径：");
        File file = GetFile();
        System.out.println(size(file));
    }

    private static long size(File file) {
        File[] files = file.listFiles();
        long n = 0;
        for (File f : files)
            if (f.isFile())
                n += f.length();
            else if (f.isDirectory())
                n += size(f);
        return n;
    }


    //删除文件夹
    private static void test1() {
        System.out.println("请输入要删除的文件夹路径：");
        File file = GetFile();
        delete(file);
    }

    private static void delete(File file) {
        File[] files = file.listFiles();
        for (File f : files)
            if (f.isFile())
                f.delete();
            else if (f.isDirectory())
                delete(f);
        file.delete();
    }


    //复制文件夹
    private static void test2() throws IOException {
        System.out.println("请输入想要被复制的文件夹：");
        File file1 = GetFile();
        System.out.println("你想要复制到哪：");
        File file2 = GetFile();
        if (file1.equals(file2))//！！！避免死循环！！！
            System.out.println("目标文件夹是源文件夹的子文件！");
        else
            copy(file1, file2);
    }

    private static void copy(File file1, File file2) throws IOException {
        File newfile = new File(file2, file1.getName());
        newfile.mkdir();
        File files[] = file1.listFiles();
        for (File f : files)
            if (f.isFile()) {
                BufferedInputStream bis =
                        new BufferedInputStream(new FileInputStream(f.getPath()));
                BufferedOutputStream bos =
                        new BufferedOutputStream(new FileOutputStream(new File(newfile.getPath(), f.getName())));
                int b;
                while ((b = bis.read()) != -1)
                    bos.write(b);
                bis.close();
                bos.close();
            } else if (f.isDirectory())
                copy(f, newfile);
    }


    //文件夹按层级打印
    private static void test3() {
        System.out.println("请输入你想打印的文件夹：");
        File file = GetFile();
        print(file, 0);
    }

    private static void print(File file, int level) {
        File[] files = file.listFiles();
        for (File f : files) {
            for (int i = 0; i <= level; i++)
                System.out.print("\t");
            System.out.println(f.getName());
            if (f.isDirectory())
                print(f, level + 1);

        }


    }


    //不死神兔
    private static void test4() {
        System.out.println(rabit(8));
    }

    private static int rabit(int n) {
        if (n == 1 || n == 2)
            return 1;
        else
            return rabit(n - 1) + rabit(n - 2);
    }


    //1000！的0的个数，尾部0的个数
    private static void test5() {
        BigInteger bi = new BigInteger("1");
        for (int i = 1; i <= 1000; i++) {
            bi = bi.multiply(new BigInteger("" + i));
        }
        System.out.println(bi);

        String str = bi.toString();
        int n = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0')
                n++;
        }
        System.out.println(n);

        StringBuilder sb = new StringBuilder(str);
        String rev = sb.reverse().toString();
        int nn = 0;
        for (int i = 0; i < rev.length(); i++) {
            if (rev.charAt(i) == '0')
                nn++;
            else
                break;
        }
        System.out.println(nn);

    }


    //约瑟夫环，幸运数字
    private static void test6() {
        int num = 8;
        int key = 3;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 0; i < num; i++)
            tm.put(i + 1, null);
        System.out.println(tm.keySet());

        int ans = 1;
        while (tm.size() > 1) {
            for (int i : tm.keySet())
                tm.put(i, ans++);
            Set<Integer>keys=tm.keySet();
            Iterator<Integer>it=keys.iterator();
            Integer i;
            while (it.hasNext()){
                i=it.next();
                if (tm.get(i)%key==0)
                    it.remove();
            }
        }
        System.out.println(tm.keySet());
    }


    //从键盘得到文件夹的路径
    private static File GetFile() {
        Scanner sc = new Scanner(System.in);
        File file;
        while (true) {
            String line = sc.nextLine();
            file = new File(line);
            if (!file.exists())
                System.out.println("不是路径！");
            else if (file.isFile())
                System.out.println("输入文件名了！");
            else
                return file;
        }
    }

}
