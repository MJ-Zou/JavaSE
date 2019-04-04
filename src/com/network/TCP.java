package com.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCP {
    public static void main(String[] args) throws IOException {
        //demo0();//客户端
        //demo1();//服务端

        //**********多线程优化**********//
        //demo2();//客户端
        //demo3();//服务端，字符串反转功能

        //**********文件上传**********//
       demo4();//客户端
        //demo5();//服务端
    }

    //客户端
    private static void demo0() throws IOException {
        /*
        Socket通过ip，端口号连接服务器
        Socket的getInputStream,getOutputStream与服务器IO
        输入流读取服务端的输出流
        输出流写出服务端的输入流
         */
        Socket socket = new Socket("219.216.110.93", 12345);

        InputStream is = socket.getInputStream();//获取客户端的输入流
        OutputStream os = socket.getOutputStream();//获取客户端的输出流

        byte[] arr = new byte[1024];
        int len = is.read(arr);//读取服务器发来的数据
        System.out.println(new String(arr, 0, len));//数据转换成字符串并打印

        os.write("学习挖掘机哪家强？".getBytes());

        socket.close();
    }

    //服务端
    private static void demo1() throws IOException {
        /*
        创建ServerSocket,指定端口号
        ServerSocket的accept方法接收一个客户端请求，得到一个Socket
        Socket的getInputStream,getOutputStream与客户端IO
        输入流读取客户端的输出流
        输出流写出客户端的输入流
         */
        ServerSocket server = new ServerSocket(12345);

        Socket socket = server.accept();//接收客户端的请求

        InputStream is = socket.getInputStream();//获取客户端的输入流
        OutputStream os = socket.getOutputStream();//获取客户端的输出流

        os.write("百度一下，你就知道".getBytes());//服务器向客户端写出数据

        byte[] arr = new byte[1024];
        int len = is.read(arr);//读取客户端发来的数据
        System.out.println(new String(arr, 0, len));//数据转换成字符串并打印

        socket.close();
    }


    //*****************多线程优化********************//
    //客户端
    private static void demo2() throws IOException {
        Socket socket = new Socket("219.216.110.93", 12345);

        BufferedReader br = new BufferedReader(//字节流包装成字符流
                new InputStreamReader(socket.getInputStream()));
        PrintStream ps = new PrintStream(socket.getOutputStream());//有写出换行的方法

        System.out.println(br.readLine());
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        ps.println(str);
        System.out.println(br.readLine());

        socket.close();
    }

    //服务端，反转字符串
    private static void demo3() throws IOException {
        ServerSocket server = new ServerSocket(12345);

        //服务器是多线程的
        while (true) {
            final Socket socket = server.accept();//接收客户端的请求
            new Thread() {
                @Override
                public void run() {
                    try {
                        BufferedReader br = new BufferedReader(//字节流包装成字符流
                                new InputStreamReader(socket.getInputStream()));
                        PrintStream ps = new PrintStream(socket.getOutputStream());//有写出换行的方法

                        ps.println("请输入要反转得的字符串");
                        String str = br.readLine();   //读取客户端的数据
                        ps.println(new StringBuilder(str).reverse().toString());//反转后写到客户端

                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }.start();

        }


    }


    //******************上传文件练习********************//
    //客户端
    private static void demo4() throws IOException {
        /*
        1、建立文件路径
        2、发送本地文件名到服务器        F:\三级网络技术大纲.pdf
        6、服务器上存在，直接退出程序
        7、服务器上不存在，上传到服务器

         */
        System.out.println("*********客户端**********");
        File file;
        String str;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入路径：");
        while (true) {
            str = sc.nextLine();
            file = new File(str);
            if (!file.exists())
                System.out.println("输入的不是路径！重输：");
            else if (file.isDirectory())
                System.out.println("输入文件夹了！重输：");
            else break;
        }

        Socket socket = new Socket("219.216.110.93", 12345);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintStream ps = new PrintStream(socket.getOutputStream());

        ps.println(file.getName());//发送文件名称
        str = br.readLine();
        System.out.println(str);//接收结果

        if (str.equals("服务器已存在该文件！")) {
            socket.close();
            return;
        }
        FileInputStream fis = new FileInputStream(file);
        byte[] arr = new byte[8192];
        int len;
        while ((len = fis.read(arr)) != -1)
            ps.write(arr, 0, len);
        fis.close();
        socket.close();
    }

    //服务端
    private static void demo5() throws IOException {
        /*
        3、建立多线程服务器
        4、读取客户端文件名
        5、返回文件是否存在结果
        8、从客户端接收文件
         */
        System.out.println("**********服务端**********");
        ServerSocket server = new ServerSocket(12345);

        while (true) {
            final Socket socket = server.accept();//接收请求
            new Thread() {
                @Override
                public void run() {
                    try {
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(socket.getInputStream()));
                        PrintStream ps = new PrintStream(socket.getOutputStream());

                        File file = new File(br.readLine());
                        System.out.println(file);
                        if (file.exists()) {//判断文件是否存在
                            ps.println("服务器已存在该文件！"); //发出查询结果
                            socket.close();
                            return;
                        } else {
                            ps.println("正在上传"); //发出查询结果
                        }
                        FileOutputStream fos = new FileOutputStream(file);
                        byte[] arr = new byte[8192];
                        int len;
                        while ((len = socket.getInputStream().read(arr)) != -1)
                            fos.write(arr, 0, len);
                        fos.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }


    }
}
