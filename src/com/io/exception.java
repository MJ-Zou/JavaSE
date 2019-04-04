package com.io;

import com.zmj.Student;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class exception {
    public static void main(String[] args) {
        //异常
        test0();
    }

    //异常种类：运行时异常
    private static void demo0() {
        int[] arr = {11, 22, 33, 44, 55};

        arr = null;//空指针异常

        System.out.println(arr[10]);//索引越界异常

        demo d = new demo();
        int x = d.div(10, 0);
        System.out.println(x);//除0异常
    }

    //try...catch
    private static void demo1() {
        /*
        try：检测异常
        catch：捕获异常
        finally：释放资源
         */

        demo d = new demo();
        int[] arr = {11, 22, 33, 44, 55};
        //处理一种异常
        try {
            int x = d.div(10, 0);
            System.out.println(x);//除0异常
        } catch (ArithmeticException a) {//ArithmeticException a=new ArithmeticException()
            System.out.println("出错了，除数为0");
        }

        //处理多种异常
        try {
            int x = d.div(10, 0);
            System.out.println(x);
            System.out.println(arr[10]);
        } catch (ArithmeticException a) {
            System.out.println("出错了，除数为0");
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("索引越界");
        }
        /*JDK7
        catch (ArithmeticException |ArrayIndexOutOfBoundsException a){
        }
        */ catch (Exception a/*父类*/) {//大的放后面
            System.out.println("出错了");
        }


    }

    //编译期异常
    private static void demo2() {
        try {//编译期异常，必须用try catch预处理，否则报错
            FileInputStream fis = new FileInputStream("xxx.txyt");
        } catch (Exception e) {
        }
    }

    //Throwable的常见方法
    private static void demo3() {
        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());//获取异常信息
            System.out.println(e.toString());//打印异常类名+异常信息
            e.printStackTrace();//默认处理异常的方式
        }
        System.out.println("11111111");
    }

    //throws
    private static void demo4() {
        demo d = new demo();
        d.setAge(-1);
        System.out.println(d.getAge());
    }

    //throw throws 区别
    private static void demo5() {
        /*
        throw:出现状况，不能运行，需要跳转时，用throw将异常抛出
        throws:用在方法声明后面，接异常类名
              多个类名用，隔开
              表示抛出异常
        throw:用在方法体内，接异常对象名
              只能抛一个异常
         */

    }

    //finally，final，finalize
    private static void demo6() {
        /*
        final修饰类，不能被继承
            修饰方法，不能被重写
            修饰变量，只能赋值一次

        finally是try语句中的一个语句体，不能单独使用，用来释放资源

        finalize是一个方法，垃圾回收器
         */
        int x;
        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            System.out.println("除数为0");
            return;//执行前看看有没有finally，
            // 有就先建立返回路径，再执行finally，再执行return
        } finally {
            System.out.println("执行了吗");
        }
    }


    //练习,输入int整数，其他类型的报错
    private static void test0() {
        Scanner sc = new Scanner(System.in);
        String str;
        int x;
        while (true) {
            str = sc.nextLine();
            try {

                x = Integer.parseInt(str);
                System.out.println(Integer.toBinaryString(x));
                break;
            } catch (Exception e) {
                try {
                    new BigInteger(str);
                    System.out.println("输入的数太大了");
                } catch (Exception ee) {
                    try {
                        new BigDecimal(str);
                        System.out.println("输入小数了");
                    } catch (Exception eee) {
                        System.out.println("输入的不是数");
                    }
                }
            }
        }
    }


}

class demo {
    private int age;

    public int getAge() {
        return age;
    }

    //运行时异常，不需预处理；Exception为编译时异常，必须预处理
    public void setAge(int age) {
        if (age > 0 && age < 100)
            this.age = age;
        else
            //throw new RuntimeException("年龄非法");
            throw new AgeOutOfBoundsException("年龄非法");
    }

    public int div(int a, int b) {
        return a / b;
    }
}

//自定义异常
class AgeOutOfBoundsException extends RuntimeException {//运行时异常

    public AgeOutOfBoundsException() {
        super();
    }

    public AgeOutOfBoundsException(String message) {
        super(message);
    }
}



