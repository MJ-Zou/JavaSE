package com.reflect;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Objects;

public class reflect {
    public static void main(String[] args) throws Exception {
        demo6();
    }

    //三种方式得到class
    private static void demo0() throws ClassNotFoundException {
        Class clazz1 = Class.forName("com.reflect.Person");//源文件阶段
        Class clazz2 = Person.class;//字节码阶段

        Person p = new Person();
        Class clazz3 = p.getClass();//创建对象阶段

        System.out.println(clazz1 == clazz2);
        System.out.println(clazz2 == clazz3);
    }

    //通过配置文件选择反射
    private static void demo1() throws Exception {
        //不用反射
        Juicer j = new Juicer();//购买榨汁机
        j.run(new Apple());     //榨汁机中放苹果
        j.run(new Orange());    //榨汁机中放橘子

        //用反射+配置文件
        BufferedReader br = new BufferedReader(new FileReader("config.properties"));
        Class clazz = Class.forName(br.readLine());//读取配置文件获取水果类字节码文件
        Fruit f = (Fruit) clazz.newInstance();//父类引用指向子类对象
        Juicer jj = new Juicer();
        jj.run(f);
    }

    //反射获取 构造函数
    private static void demo2() throws Exception {
        Class clazz = Class.forName("com.reflect.Person");

        Person p = (Person) clazz.newInstance();//无参构造对象
        System.out.println(p);

        Constructor c = clazz.getConstructor(String.class, int.class);//获取有参构造
        Person pp = (Person) c.newInstance("张三", 23);//通过有参构造创建对象
        System.out.println(pp);
    }

    //反射获取 成员变量
    private static void demo3() throws Exception {
        Class clazz = Class.forName("com.reflect.Person");
        Constructor c = clazz.getConstructor(String.class, int.class);
        Person p = (Person) c.newInstance("张三", 23);
        System.out.println(p);

        Field f = clazz.getField("name");//获取姓名字段
        f.set(p, "李四");//修改姓名值
        System.out.println(p);

        Field ff = clazz.getDeclaredField("age");//暴力反射获取字段
        ff.setAccessible(true);//去除私有权限
        ff.set(p, 24);
        System.out.println(p);
    }

    //反射获取 成员方法
    private static void demo4() throws Exception {
        Class clazz = Class.forName("com.reflect.Person");
        Constructor c = clazz.getConstructor(String.class, int.class);
        Person p = (Person) c.newInstance("张三", 23);

        Method m = clazz.getMethod("eat");  //获取eat方法，无参
        m.invoke(p);
        Method mm = clazz.getMethod("eat", int.class);//获取eat方法，有参
        mm.invoke(p, 10);
    }

    //反射 泛型检测
    private static void demo5() throws Exception {
        /*
        泛型只在编译期有效，运行期会被擦除
         */
        ArrayList<Integer> list = new ArrayList<>();
        list.add(111);
        list.add(222);
        System.out.println(list);

        Class clazz = Class.forName("java.util.ArrayList");
        Method m = clazz.getMethod("add", Object.class);//获取add方法
        m.invoke(list, "aaa");
        System.out.println(list);
    }

    //反射 动态代理
    private static void demo6() {
        UserImp ui = new UserImp();
        ui.add();
        ui.delete();
        System.out.println("*************");
        MyInvoactionHandler m = new MyInvoactionHandler(ui);
        User u = (User) Proxy.newProxyInstance(ui.getClass().getClassLoader(), ui.getClass().getInterfaces(), m);
        u.add();
        u.delete();
    }


    //********************练习**********************//
    //设置某个对象的某个属性值的方法
    private static void test0() throws Exception {
        Person p = new Person("张三", 23);
        System.out.println(p);
        setProperty(p, "age", 100);
        System.out.println(p);
    }

    private static void setProperty(Object obj, String propertyName, Object value) throws Exception {
        //将obj对象的propertyName设置为value
        Class clazz = obj.getClass();     //获取字节码对象
        Field f = clazz.getDeclaredField(propertyName);   //暴力反射获取字段
        f.setAccessible(true);  //去除私有权限
        f.set(obj, value);
    }

    private static void test1() throws Exception {
        /*
        读取文件中的类并调用其方法
         */
        BufferedReader br = new BufferedReader(new FileReader("config.properties"));
        String str = br.readLine();
        System.out.println(str);

        Class clazz = Class.forName(str);
        Method m = clazz.getMethod("eat");

        Person p = (Person) clazz.newInstance();
        m.invoke(p);
    }

}

class Person {
    public String name = "无名氏";
    private int age = 0;

    Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println("吃饭了");
    }

    public void eat(int num) {
        System.out.println("吃了" + num + "碗饭");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) //判断调用对象和传入对象的字节码文件是否是同一个
            return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}


class Juicer {
    void run(Fruit f) {
        f.squeeze();
    }
}

interface Fruit {   //定义接口抽取相同内容
    void squeeze();
}

class Apple implements Fruit {
    public void squeeze() {
        System.out.println("榨苹果汁");
    }
}

class Orange implements Fruit {
    public void squeeze() {
        System.out.println("榨橘子汁");
    }
}

//动态代理
interface User {
    public void add();

    public void delete();
}

class UserImp implements User {
    @Override
    public void add() {
        //System.out.println("权限校验");
        System.out.println("添加功能");
        //System.out.println("日志记录");
    }

    @Override
    public void delete() {
        //System.out.println("权限校验");
        System.out.println("删除功能");
        //System.out.println("日志记录");
    }
}

class MyInvoactionHandler implements InvocationHandler {
    private Object target;

    public MyInvoactionHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("权限校验");
        method.invoke(target, args);     //执行被代理target对象的方法
        System.out.println("日志记录");
        return null;
    }
}