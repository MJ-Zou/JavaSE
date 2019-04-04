package com.JDBC;

import org.junit.Assert;
import org.junit.Test;

public class test {

    //测试方法，不能有返回值、参数
    @Test
    public void demo0() {
        System.out.println("aaaaaaaaa");
    }

    @Test
    public void demo1(){
        Assert.assertEquals(8, 5 + 3);
    }

    @Test
    public void demo2(){
        Assert.assertEquals(3, 10./3,0.1);
    }


}
