package com.network;

/*
三要素：
UDP：面向无连接，数据不安全，速度快。不区分客户端与服务端
TCP：面向连接（三次握手），数据安全，速度略低。分为客户端和服务端
三次握手：客户端向服务端发起请求，服务端响应请求，传输数据
*/


import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDP {
    public static void main(String[] args) throws IOException {
        //demo0();    //发送
        //demo1();    //接收

        //*******优化*******//
        //demo2();    //发送
        //demo3();    //接收

        //*******一个窗口多线程*******//
        //demo4();
    }

    //发送
    private static void demo0() throws IOException {
        String str = "你好呀";
        DatagramSocket socket = new DatagramSocket();//创建码头,Socket
        DatagramPacket packet =      //创建集装箱,Packet
                new DatagramPacket(str.getBytes(), str.getBytes().length, InetAddress.getByName("127.0.01"), 6666);

        socket.send(packet);    //发货，发出数据
        socket.close();     //关闭码头
    }

    //接收
    private static void demo1() throws IOException {
        DatagramSocket socket = new DatagramSocket(6666);//创建码头,Socket
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);//创建集装箱,Packet
        socket.receive(packet);//接货，接收数据

        byte[] arr = packet.getData();//获取数据
        int len = packet.getLength();//获取有效字节个数
        System.out.println(new String(arr, 0, len));

        socket.close();
    }


    //***********优化一下************//
    //优化发送
    private static void demo2() throws IOException {
        System.out.println("你想发送什么？");

        Scanner sc = new Scanner(System.in);
        DatagramSocket socket = new DatagramSocket();//创建码头,Socket
        while (true) {
            String str = sc.nextLine();
            if (str.equals("quit"))
                break;

            DatagramPacket packet = new DatagramPacket(  //创建集装箱,Packet
                    str.getBytes(), str.getBytes().length, InetAddress.getByName("127.0.01"), 6666);
            socket.send(packet);    //发货，发出数据
        }

        socket.close();     //关闭码头
    }

    //优化接收
    private static void demo3() throws IOException {
        DatagramSocket socket = new DatagramSocket(6666);//创建码头,Socket
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);//创建集装箱,Packet

        while (true) {
            socket.receive(packet);//接货，接收数据

            byte[] arr = packet.getData();//获取数据
            int len = packet.getLength();//获取有效字节个数

            String ip = packet.getAddress().getHostAddress();
            int pot = packet.getPort();

            System.out.println(ip + ":" + pot + ":" + new String(arr, 0, len));
        }
    }


    //***********收发在一个窗口************//
    private static void demo4() {
        new receive().start();
        new send().start();
    }

    static class receive extends Thread {
        @Override
        public void run() {
            try {
                demo3();//接收
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class send extends Thread {
        @Override
        public void run() {
            try {
                demo2();//发送
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}




