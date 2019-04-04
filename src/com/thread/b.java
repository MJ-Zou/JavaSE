package com.thread;

import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class b {

       /*
       线程五种状态：新建、就绪、运行、阻塞、死亡
        */


    //单例设计模式，保证类内存中只有一个对象，掌握
    @Test
    public  void demo0() {
        /*
        饿汉式：空间换时间，不会创建多个对象
        懒汉式：时间换空间，可能创建多个对象
         */
        singleton s = singleton.gets();
        singleton1 s1 = singleton1.gets();
        singleton2 s2 = singleton2.s;
    }

    //饿汉式
    static class singleton {
        //1、私有构造方法
        private singleton() {
        }

        //2、创建本类对象
        private static singleton s = new singleton();

        //3、对外提供公共的访问方法
        public static singleton gets() {
            return s;
        }
    }

    //懒汉式，单例的延迟加载模式
    static class singleton1 {
        //1、私有构造方法
        private singleton1() {
        }

        //2、创建本类对象(声明。没初始化)
        private static singleton1 s;

        //3、对外提供公共的访问方法
        public static singleton1 gets() {
            if (s == null)
                //线程1等待，线程2等待
                s = new singleton1();
            return s;
        }
    }

    //了解
    static class singleton2 {
        //1、私有构造方法
        private singleton2() {
        }

        //2、声明一个引用
        public final static singleton2 s = new singleton2();

    }


    //多线程Runtime类，单例
    @Test
    public void demo1() throws IOException {
        Runtime r = Runtime.getRuntime();//获取运行时对象，单例
        //r.exec("shutdown -s -t 300");
        r.exec("shutdown -a");
    }


    /*
    sleep必须传入参数，达到时间自动醒来，不释放锁(大家都等)
    wait可以不传参，在时间后开始等待，释放锁(别人继续)
     */

    //多线程Timer类,sleep，掌握
    @Test
    public  void demo2() throws InterruptedException {
        Timer t = new Timer();
        //在指定时间安排指定任务
        //任务，执行时间，每隔多长时间重复执行
        Calendar c=Calendar.getInstance();
        Date d=new Date();
        c.setTime(d);
        t.schedule(new mytimertask(),d, 3000);

        while (true) {
            Thread.sleep(1000);
            System.out.println(new Date());
        }
    }

    static class mytimertask extends TimerTask {
        @Override
        public void run() {
            System.out.println("起床");
        }
    }


    //两个线程间的通信.wait+notify，掌握
    @Test
    public void demo3() {
        final printer p = new printer();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        p.print1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        p.print2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    //等待唤醒
    static class printer {
        int flag = 1;

        void print1() throws InterruptedException {
            synchronized (this) {
                if (flag != 1)
                    this.wait();    //当前线程等待
                flag = 2;
                System.out.print(1);
                System.out.print(2);
                System.out.println(3);
                this.notify();  //随机唤醒单个等待的线程
            }

        }

        void print2() throws InterruptedException {
            synchronized (this) {
                if (flag != 2)
                    this.wait();
                flag = 1;
                System.out.print('a');
                System.out.print('b');
                System.out.println('c');
                this.notify();
            }

        }
    }


    //三个及以上线程通信
    @Test
    public void demo4() {
        final printer1 p = new printer1();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        p.print1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        p.print2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        p.print3();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    //等待唤醒
    static class printer1 {
        int flag = 1;

        void print1() throws InterruptedException {
            synchronized (this) {
                while (flag != 1)
                    this.wait();    //当前线程等待
                flag = 2;
                System.out.print(1);
                System.out.print(2);
                System.out.println(3);
                this.notifyAll();  //随机唤醒单个等待的线程
            }

        }

        void print2() throws InterruptedException {
            synchronized (this) {
                while (flag != 2)
                    this.wait();
                flag = 3;
                System.out.print('a');
                System.out.print('b');
                System.out.println('c');
                this.notifyAll();
            }

        }

        void print3() throws InterruptedException {
            synchronized (this) {
                while (flag != 3)
                    this.wait();
                flag = 1;
                System.out.print('。');
                System.out.print('。');
                System.out.println('。');
                this.notifyAll();
            }

        }
    }


    //互斥锁 ReentrantLock JDK1.5，掌握
    @Test
    public   void demo5() {
        final printer1 p = new printer1();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        p.print1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        p.print2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        p.print3();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    static class printer2 {
        int flag = 1;
        ReentrantLock r = new ReentrantLock();
        Condition c1 = r.newCondition();  //创建监视器
        Condition c2 = r.newCondition();
        Condition c3 = r.newCondition();

        void print1() throws InterruptedException {
            r.lock();       //获取锁
            while (flag != 1)
                c1.await();  //停止1
            flag = 2;
            System.out.print(1);
            System.out.print(2);
            System.out.println(3);
            this.notifyAll();
            c2.signal(); //唤醒2
            r.unlock();         //释放锁
        }

        void print2() throws InterruptedException {
            r.lock();
            while (flag != 2)
                c2.await();
            flag = 3;
            System.out.print('a');
            System.out.print('b');
            System.out.println('c');
            this.notifyAll();
            c3.signal();
            r.unlock();
        }

        void print3() throws InterruptedException {
            r.lock();
            while (flag != 3)
                c3.await();
            flag = 1;
            System.out.print('。');
            System.out.print('。');
            System.out.println('。');
            this.notifyAll();
            c1.signal();
            r.unlock();
        }
    }


    //线程组，了解
    @Test
    public void demo6() {
        myrunnable mr = new myrunnable();//创建Runnable子类对象
        Thread t1 = new Thread(mr, "张三");
        Thread t2 = new Thread(mr, "李四");

        ThreadGroup tg1 = t1.getThreadGroup();
        ThreadGroup tg2 = t2.getThreadGroup();

        System.out.println(tg1.getName());//默认是主线程
        System.out.println(tg2.getName());


        ThreadGroup tg = new ThreadGroup("线程组");//创建新的线程组
        Thread t3 = new Thread(tg, mr, "王五");//将线程t3放在组中
        Thread t4 = new Thread(tg, mr, "赵六");//将线程t4放在组中

        System.out.println(t3.getThreadGroup().getName());//获取组名
        System.out.println(t4.getThreadGroup().getName());
    }

    static class myrunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++)
                System.out.println(Thread.currentThread().getName() + "：" + i);
        }
    }


    //线程池 ExecutorService，了解
    @Test
    public void demo7() {
        ExecutorService pool = Executors.newFixedThreadPool(2);//创建线程池
        pool.submit(new myrunnable());  //将线程放进池并执行
        pool.submit(new myrunnable());

        pool.shutdown();//关闭线程池
    }

    //多线程实现3，了解
    @Test
    public void demo8() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<Integer> f1 = pool.submit(new mycallable(100));
        Future<Integer> f2 = pool.submit(new mycallable(50));

        System.out.println(f1.get());
        System.out.println(f2.get());

        pool.shutdown();
    }

    static class mycallable implements Callable<Integer> {
        private int num;

        mycallable(int num) {
            this.num = num;
        }

        public Integer call() {
            int sum = 0;
            for (int i = 0; i <= num; i++)
                sum += i;
            return sum;
        }
    }
}

