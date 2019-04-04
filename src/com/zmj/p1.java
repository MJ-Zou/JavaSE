/*
包
 */

package com.zmj;//多级包，定义包名，必须是第一条语句
//一个文件下只能有一个包

public class p1 { //必须是public
    int num=0;
    protected void protect(){ //不同包下无关类无法访问
        System.out.println("hello world!");
    }
    public void pub(){
        System.out.println("pub");
    }
}
