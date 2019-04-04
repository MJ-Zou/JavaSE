package com.network;

import java.awt.*;
import java.awt.event.*;

public class GUI {
    public static void main(String[] args) {
        Frame f = new Frame("窗口1");//新建窗体
        f.setSize(400, 600);    //设置大小
        f.setLocation(300, 50);     //设置相对于左上角的位置
        f.setIconImage(Toolkit.getDefaultToolkit().createImage("ASCII.jpg"));//图标

        Button b1 = new Button("button1");
        Button b2 = new Button("button2");
        f.add(b1);
        f.add(b2);
        f.setLayout(new FlowLayout());//设置布局管理器

        //窗体监听
        //f.addWindowListener(new MyWindowListener());// 不好
        //f.addWindowListener(new MyWindowAdapter());  好
        f.addWindowListener(new WindowAdapter() {//匿名内部类，最简单
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //鼠标监听
        b1.addMouseListener(new MouseAdapter() {//匿名内部类
            @Override
            public void mouseClicked(MouseEvent e) {//单击
                System.exit(0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {//释放
                System.exit(0);
            }
        });


        //键盘监听
        b1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {//释放
                System.out.println(e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_SPACE)//只在空格时退出
                    System.exit(0);
            }
        });


        //动作监听
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//左键和空格
                System.exit(0);
            }
        });

        f.setVisible(true);//显示窗体
    }

}


//窗体监听，只重写需要的方法，好！
class MyWindowAdapter extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}

//窗体监听,必须重写所有抽象方法，不好
class MyWindowListener implements WindowListener {
    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }


}


//适配器设计模式，掌握
interface 和尚 {
    void 打坐();

    void 念经();

    void 习武();
}

abstract class 中间过渡 implements 和尚 {//适配器类，不需要子对象

    @Override
    public void 打坐() {
    }

    @Override
    public void 念经() {
    }

    @Override
    public void 习武() {
    }
}

class 鲁智深 extends 中间过渡 {
    @Override
    public void 习武() {  //只重写需要的就行
        System.out.println("大闹野猪林");
    }
}

