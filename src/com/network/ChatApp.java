package com.network;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatApp {
    public static void main(String[] args) throws IOException {
        new GUIChat();
    }
}

class GUIChat extends Frame {


    //****************************构造区*********************************//
    private TextField tf;    //ip地址
    private TextArea viewText;  //聊天内容
    private TextArea sendText;  //编辑发送
    private Button send;
    private Button log;
    private Button clear;
    private Button shake;
    private DatagramSocket socket;
    private BufferedWriter bw;

    GUIChat() throws IOException {
        init();
        south();
        center();
        event();
    }

    //****************************功能区*********************************//
    //设置初始化
    private void init() throws IOException {
        this.setLocation(500, 250);
        this.setSize(400, 600);
        new recieve().start();
        try {
            socket = new DatagramSocket();
            bw = new BufferedWriter(new FileWriter("config.txt", true));
        } catch (SocketException e) {
            e.printStackTrace();
        }
        this.setVisible(true);

    }

    //设置下部
    private void south() {
        Panel south = new Panel();  //创建南边的Panel

        tf = new TextField(15); //创建文本字段存储ip地址
        tf.setText("255.255.255.255");

        send = new Button("Send"); //创建按钮
        log = new Button("Record");
        clear = new Button("ClearAll");
        shake = new Button("Shake");

        south.add(tf);
        south.add(send);
        south.add(log);
        south.add(clear);
        south.add(shake);
        this.add(south, BorderLayout.SOUTH);//将Panel放在frame 的南边
    }

    //设置中部
    private void center() {
        Panel center = new Panel();//创建中间的Panel

        viewText = new TextArea();//显示文本区域
        viewText.setEditable(false);//设置为不可编辑
        viewText.setBackground(Color.white);//设置背景颜色
        viewText.setFont(new Font("aa", Font.PLAIN, 15));//设置字体大小

        sendText = new TextArea(5, 1);//发送文本区域
        sendText.setFont(new Font("aa", Font.PLAIN, 15));//设置字体大小

        center.setLayout(new BorderLayout());//设置边界布局管理器
        center.add(sendText, BorderLayout.SOUTH);//发送文本区域在南边
        center.add(viewText, BorderLayout.CENTER);//发送文本区域在南边
        this.add(center, BorderLayout.CENTER);
    }

    //设置事件
    private void event() {
        //关闭功能
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                socket.close();
                try {
                    bw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });
        //发送功能
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    send();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        //记录功能
        log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    log();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        //清屏功能
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewText.setText("");
            }
        });
        //震动功能
        shake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    send(new byte[]{-1}, tf.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        //快捷键
        sendText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                //回车+ctrl 换行
                if (e.getKeyCode() == KeyEvent.VK_ENTER && e.isControlDown())
                    sendText.append("");
                    //回车 发送
                else if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    try {
                        send();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }


            }
        });
    }

    //****************************方法区*********************************//
    //接收方法
    private class recieve extends Thread {
        @Override
        public void run() {
            try {
                DatagramSocket socket = new DatagramSocket(9999);
                DatagramPacket packet = new DatagramPacket(new byte[8192], 8192);

                while (true) {
                    socket.receive(packet);//接货，接收数据

                    byte[] arr = packet.getData();//获取数据
                    int len = packet.getLength();//获取有效字节个数

                    if (arr[0] == -1 || len == 1) {//检测是否收到震动的指令
                        shake();
                        continue;
                    }

                    String ip = packet.getAddress().getHostAddress();
                    String message = new String(arr, 0, len);
                    String str = getTime() + ip + "对我说:\n" + message + "\n";

                    viewText.append(str);//在聊天窗口显示
                    bw.write(str);//写到数据库
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //发送方法
    private void send() throws IOException {
        String message = sendText.getText();//获取发送区域的内容
        String ip = tf.getText();   //获取ip地址
        ip = ip.trim().length() == 0 ? "255.255.255.255" : ip;//不写ip就是广播

        String str = getTime() + "你对" + (ip.equals("255.255.255.255") ? "所有人" : ip)
                + "说:\n" + message + "\n";
        bw.write(str);//写到聊天记录数据库
        viewText.append(str);//加到聊天窗口
        sendText.setText("");//编辑窗口清空

        send(message.getBytes(), ip);
    }

    private void send(byte[] arr, String ip) throws IOException {
        DatagramPacket packet = new DatagramPacket(       //发送聊天内容
                arr, arr.length, InetAddress.getByName(ip), 9999);
        socket.send(packet);
    }

    //显示聊天记录方法
    private void log() throws IOException {
        viewText.setText("");
        bw.flush();//刷新缓冲区
        BufferedReader br = new BufferedReader(new FileReader("config.txt"));
        String line;    //每次从聊天记录读取一行并加到聊天窗口
        while ((line = br.readLine()) != null)
            viewText.append(line + "\n");
        br.close();
    }

    //震动功能
    private void shake() throws InterruptedException {
        int x = this.getLocation().x;
        int y = this.getLocation().y;

        for (int i = 0; i < 10; i++) {
            this.setLocation(x + 20, y + 20);
            Thread.sleep(5);
            this.setLocation(x - 20, y - 20);
            Thread.sleep(5);
            this.setLocation(x + 20, y - 20);
            Thread.sleep(5);
            this.setLocation(x - 20, y + 20);
            Thread.sleep(5);
            this.setLocation(x, y);
        }

    }

    //获取当前时间
    private String getTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("y/M/d h:m:s");
        return sdf.format(d) + "  ";
    }

}