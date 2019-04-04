package com.thread;

/*掌握
Vector是线程安全的，ArrayList是是线程不安全的
StringBuffer是线程安全的，StringBuilder是线程不安全的
Hashtable是线程安全的，HashMap是线程不安全的
 */

import org.junit.Test;

import java.util.Timer;

public class a {

    //多线程实现1，继承Thread，优先使用
    @Test
    public void demo0() {
        /*
        优点：直接使用Thread类方法，代码简单
        缺点：已有父类不能使用该方法
         */
        MyThread mt = new MyThread();//4、创建Thread类的子对象;
        mt.start();//5、开启线程

        for (int i = 0; i < 1000; i++)
            System.out.println("bbbbbbb");

    }

    static class MyThread extends Thread {//1、继承Thread

        public void run() { //2、重写run方法
            for (int i = 0; i < 1000; i++)  //3、将要执行的代码写在run方法中
                System.out.println("aaaaaaaaaaaaa");
        }
    }


    //多线程实现2，实现Runnable接口
    @Test
    public void demo1() {
        /*
        优点：适用于已有父类的
        缺点：需要先获取线程对象，代码复杂
         */
        MyRunnable mr = new MyRunnable();   //4、创建Runnable的子类对象
        //Runnable target=mr;
        Thread t = new Thread(mr);//5、将其当做传递给Thread的构造函数
        t.start();  //6、开启线程

        for (int i = 0; i < 1000; i++)
            System.out.println("bbbbbbb");
    }

    static class MyRunnable implements Runnable {//1、定义一个类实现Runnable
        @Override
        public void run() { //2、重写run方法
            for (int i = 0; i < 1000; i++) //3、将要执行的代码写在runn方法中
                System.out.println("aaaaaaaaaaaaa");
        }
    }


    //匿名内部类 实现线程
    @Test
    public void demo2() {   //1、继承Thread类
        new Thread() {
            @Override
            public void run() {     //2、重写run方法
                for (int i = 0; i < 1000; i++)
                    System.out.println("aaaaaaaa");//3、将要执行的代码写在run方法中
            }
        }.start();      //4、开启线程

        new Thread(new Runnable() {//1、将Runnable的子类对象传递给Thread的构造方法
            @Override
            public void run() {     //2、重写run方法
                for (int i = 0; i < 1000; i++) //3、将要执行的代码写在runn方法中
                    System.out.println("bbbbbbb");
            }
        }).start();//4、开启线程
    }

    //名字
    @Test
    public void demo3() {
        new Thread("张三") {//通过构造方法给name赋值
            @Override
            public void run() {
                System.out.println(this.getName() + ":aaaaaaaa");
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                this.setName("李四");//通过getName()
                System.out.println(this.getName() + ":bbbbbbbb");
            }
        }.start();
    }

    //获取当前的线程对象 currentThread
    public static void demo4() {
        new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ":aaa");
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {     //Thread.currentThread获取正在执行的线程
                System.out.println(Thread.currentThread().getName() + ":bbb");
            }
        }).start();

        Thread.currentThread().setName("我是主线程");
        System.out.println(Thread.currentThread().getName());//获取主线程名字
    }

    //休眠线程 Thread.sleep
    public static void demo5() throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(1000);//暂停1000毫秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("aaaa");
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("bbb");
                }
            }
        }.start();

    }

    //守护线程 Daemon
    @Test
    public void demo6() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++)
                    System.out.println(getName() + ":aaaaaaaaa");
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++)
                    System.out.println(getName() + ":bbb");
            }
        };

        t2.setDaemon(true);//t1执行完毕之后，t2停止执行
        t1.start();
        t2.start();
    }

    //加入线程 join
    @Test
    public void demo7() {
        final Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++)
                    System.out.println(getName() + ":aaaaaaaaa");
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    if (i == 2) {
                        try {
                            //t1.join();    //让t1插队执行完
                            t1.join(1);//让t1插队执行1毫秒
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(getName() + ":bb");
                }
            }
        };

        t1.start();
        t2.start();
    }


    //礼让线程 yield,了解
    @Test
    public void demo8() {
        //现象不明显
        new MyThread1().start();
        new MyThread1().start();
    }

    static class MyThread1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                if (i % 10 == 0)
                    Thread.yield();//让出cpu
                System.out.println(getName() + "：" + i);
            }
        }
    }


    //设置线程优先级，了解
    @Test
    public void demo9() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++)
                    System.out.println(getName() + ":aaaaaaaaa");
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++)
                    System.out.println(getName() + ":bb");
            }
        };

        /*
        t1.setPriority(1);
        t2.setPriority(10);   //设置最大优先级1-10
        t1.start();
        t2.start();
        */

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);  //设置最大优先级
        t1.start();
        t2.start();

    }


    //同步代码块  synchronized
    @Test
    public void demo10() {
        final printer p1 = new printer();

        new Thread() {
            @Override
            public void run() {
                while (true)
                    p1.print1();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true)
                    p1.print2();
            }
        }.start();
    }

    static class printer {
        demo d = new demo();

        public void print1() {
            synchronized (d) {  //同步代码块，锁机制，防止穿插
                System.out.print("1");
                System.out.print("2");
                System.out.print("3");
                System.out.print("4");
                System.out.print("5");
                System.out.print("\n");
            }
        }

        public void print2() {
            synchronized (d) {//锁对象是任意的，不能使匿名对象，因为不是同一个
                System.out.print("a");
                System.out.print("b");
                System.out.print("c");
                System.out.print("d");
                System.out.print("e");
                System.out.print("\n");
            }
        }
    }

    static class demo {
    }


    //多线程(同步方法)
    @Test
    public void demo11() {
        final printer2 p1 = new printer2();

        new Thread() {
            @Override
            public void run() {
                while (true)
                    p1.print1();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true)
                    p1.print2();
            }
        }.start();
    }

    static class printer2 {
        public synchronized void print1() { //同步方法只需在方法上加synchronized关键字
            System.out.print("1");
            System.out.print("2");
            System.out.print("3");
            System.out.print("4");
            System.out.print("5");
            System.out.print("\n");
        }

        //非静态的同步方法的锁对象是:this
        //静态的同步方法的锁对象是:类名.class
        public void print2() {
            synchronized (this) {  //锁对象是任意的，不能使匿名对象，因为不是同一个
                System.out.print("a");
                System.out.print("b");
                System.out.print("c");
                System.out.print("d");
                System.out.print("e");
                System.out.print("\n");
            }

        }
    }


    //线程安全问题
    @Test
    public void demo12() {
        new ticket().start();
        new ticket().start();
        new ticket().start();
        new ticket().start();
    }

    static class ticket extends Thread {
        static int num = 100;

        @Override
        public void run() {
            while (true) {
                synchronized (ticket.class) {  //必须锁上！不锁会串位
                    if (num <= 0)
                        break;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName() + ":" + num--);
                }
            }

        }
    }


    //线程安全Runnable
    @Test
    public void demo13() {
        myticket mt = new myticket();
        new Thread(mt).start();
        new Thread(mt).start();
        new Thread(mt).start();
        new Thread(mt).start();
    }

    static class myticket implements Runnable {
        int num = 100;

        @Override
        public void run() {
            while (true) {
                synchronized (this) {  //必须锁上！
                    if (num <= 0)
                        break;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + num--);
                }
            }
        }
    }


    //死锁，避免同步代码块嵌套，了解
    static String s1 = "筷子左";
    static String s2 = "筷子右";

    @Test
    public void demo14() {
        new Thread() {
            @Override
            public void run() {
                while (true)
                    synchronized (s1) {
                        System.out.println(getName() + ":获取筷子左，等待右");
                        synchronized (s2) {
                            System.out.println(getName() + ":获取筷子右。开吃\n");
                        }
                    }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true)
                    synchronized (s2) {
                        System.out.println(getName() + ":获取筷子右，等待左");
                        synchronized (s1) {
                            System.out.println(getName() + ":获取筷子左，开吃\n");
                        }
                    }
            }
        }.start();
    }

    public static void main(String[]ars){
        new mt1().start();
        new mt2().start();
    }

    public static class mt1 extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    System.out.println("1");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class mt2 extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    System.out.println("2");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
