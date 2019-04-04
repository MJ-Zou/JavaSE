package com.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Date;

public class file {
    public static void main(String[] args) throws Exception {
        demo5();
    }

    //File构造方法
    private static void demo0() {
        //绝对路径
        File file = new File("E:\\黑马JAVA\\01、第一阶段java基础\\day19\\video\\001_今日内容.avi");
        System.out.println(file.exists());

        //相对路径
        File file1 = new File("xxx.txt");
        System.out.println(file1.exists());

        //目录+文件
        String parent = "E:\\黑马JAVA\\01、第一阶段java基础\\day19\\video";
        String child = "001_今日内容.avi";
        File file2 = new File(parent, child);
        System.out.println(file2.exists());

        File parents = new File("E:\\黑马JAVA\\01、第一阶段java基础\\day19\\video");
        String childs = "001_今日内容.avi";
        File file3 = new File(parents, childs);
        System.out.println(parents.exists());
        System.out.println(file3.exists());
    }

    //创建文件
    private static void demo1() throws IOException {
        File file = new File("yyy");
        System.out.println(file.createNewFile());//没有就创建文件

        File dir1 = new File("aaa.aaa");
        System.out.println(dir1.mkdir());//创建一个文件夹

        File dir2 = new File("ccc\\ddd");
        System.out.println(dir2.mkdirs());//创建多级文件夹
    }

    //重命名，删除
    private static void demo2() {
        //重命名，移动
        File file1 = new File("yyy");
        File file2 = new File("ooo.txt");
        System.out.println(file1.renameTo(file2));//改名/改名+剪切

        //删除文件
        File file3 = new File("ooo.txt");
        System.out.println(file3.delete());//删除，不走回收站

        //删除文件夹
        File file4 = new File("aaa.aaa");
        System.out.println(file4.delete());

        File file5 = new File("ccc");
        System.out.println(file5.delete());//删除的文件夹必须为空！
    }

    //只读写设置，判断
    private static void demo3() throws IOException {
        File dir1 = new File(".idea");
        System.out.println(dir1.isDirectory());//是否是文件夹

        File dir2 = new File("java.iml");
        System.out.println(dir2.isFile());//是否是文件

        File file = new File("zzz");
        file.createNewFile();
        file.setReadable(false);//设置可读性
        System.out.println(file.canRead());//Windows下始终可读
        file.setWritable(false);
        System.out.println(file.canWrite());//Windows可以设置不可写

        File file1 = new File("0.txt");
        System.out.println(file1.isHidden());//是否隐藏
    }

    //获取
    private static void demo4() {
        File file1 = new File("java.iml");
        File file2 = new File("D:\\java\\java.iml");
        System.out.println(file1.getAbsolutePath());//获取绝对路径
        System.out.println(file2.getAbsolutePath());

        System.out.println(file1.getPath());//获取构造方法中传入的路径
        System.out.println(file2.getPath());

        System.out.println(file1.getName());//获取文件名

        System.out.println(file1.length());//文件所占字节数

        System.out.println(file1.lastModified());//最后修改的毫秒数
        System.out.println(new Date(file1.lastModified()));

        File dir = new File("D:\\java\\.idea");
        String[] arr = dir.list();//获取文件夹里的 文件或文件夹名 string数组
        for (String str : arr)//仅获取文件名
            System.out.println(str);

        File[] subfiles = dir.listFiles();//获取文件夹里的 文件或文件夹名 file数组
        for (File file : subfiles)//获取文件对象
            System.out.println(file);


    }

    //文件名称过滤器
    private static void demo5(){
        File dir = new File("D:\\");
        String[]arr=dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                //System.out.println(dir);得到目录
                //System.out.println(name);//得到文件名
                File file=new File(dir,name);
                return file.isFile()&&file.getName().endsWith(".docx");
            }
        });
        for (String str:arr)
            System.out.println(str);
    }

    //输出指定目录下指定后缀的文件名
    private static void test0() {
        File dir = new File("D:\\");

        //这只表面
        String[] arr = dir.list();
        for (String str : arr)
            if (str.endsWith(".docx"))
                System.out.println(str);

        //这个好，可以深入
        File[]subfiles=dir.listFiles();
        for(File subfile:subfiles)
            if(subfile.isFile()&&subfile.getName().endsWith(".docx"))
                System.out.println(subfile);
    }


}
