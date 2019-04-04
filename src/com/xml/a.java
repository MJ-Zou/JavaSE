package com.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class a {
    public static void main(String args[]) throws Exception {
        demo4();
    }

    //使用do4j解析xml文件
    public static void demo0() throws DocumentException {
        //创建核心对象
        SAXReader reader = new SAXReader();
        //获取dom树
        Document doc = reader.read("D:\\java\\src\\com\\xml\\web.xml");
        //获取根节点
        Element root = doc.getRootElement();
        //获取其他节点
        List<Element> list = root.elements();
        //遍历集合
        for (Element ele : list) {
            //获取servlet-name标签体
            String text = ele.elementText("servlet-name");
            System.out.println(text);
            //获取url-pattern标签体
            System.out.println(ele.elementText("url-pattern"));
        }
        //获取root的version属性值
        String value = root.attributeValue("version");
        System.out.println(value);
    }

    //reflect反射
    public static void demo1() throws Exception {
        //获取class类
        Class clazz = Class.forName("com.xml.HelloMyServlet");
        //通过字节码对象创建一个实例对象
        HelloMyServlet a = (HelloMyServlet) clazz.newInstance();
        a.add();

        //获取方法对象,args是方法执行时的参数
        Method method = clazz.getMethod("add");
        //执行方法
        method.invoke(a);

        //获取有两个参数的方法对象,args是方法执行时的参数
        Method method1 = clazz.getMethod("add", int.class, int.class);
        method1.invoke(a,10,20);
    }

    //服务器软件
    public static void demo3()throws Exception{
        //创建map
        Map<String,String>map=new HashMap<>();
        //放入key和value
        map.put("/hello","com.xml.HelloMyServlet");
        //通过key获取value
        String value=map.get("/hello");
        //创建实例
        Class clazz=Class.forName(value);
        HelloMyServlet a=(HelloMyServlet) clazz.newInstance();
        //调用add方法
        Method method=clazz.getMethod("add");
        method.invoke(a);
    }

    public static void demo4()throws Exception{
        Document doc = new SAXReader().read("D:\\java\\src\\com\\xml\\web.xml");
        //获取标签体
        Element servletClass=(Element) doc.selectSingleNode("//servlet-class");
        Element urlPattern=(Element) doc.selectSingleNode("//url-pattern");
        String classText=servletClass.getText();
        String urlText=urlPattern.getText();
        //定义map
        Map<String,String>map=new HashMap<>();
        //值放入到map
        map.put(urlText,classText);
        //通过key获取value
        String value=map.get("/hello");
        //创建实例
        Class clazz=Class.forName(value);
        HelloMyServlet a=(HelloMyServlet) clazz.newInstance();
        //调用add方法
        Method method=clazz.getMethod("add");
        method.invoke(a);

    }

}

class HelloMyServlet {
    public void add() {
        System.out.println("0add");

    }

    public void add(int i, int j) {
        System.out.println("2add");
        System.out.println(i + j);
    }

    public void add(int i) {
        System.out.println(i + 10);
    }
}
