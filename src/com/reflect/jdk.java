package com.reflect;

public class jdk {
    public static void main(String[] args) {
        demo3();
    }

    //自定义枚举
    private static void demo0() {
        //方式1
        Week1 mon = new Week1().MON;
        Week1 tue = new Week1().TUE;
        Week1 wed = new Week1().WED;

        //方式2
        Week2 mon2 = Week2.MON;
        System.out.println(mon2.name);

        //方式3
        Week3 mon3 = Week3.MON;
        mon3.show();
    }

    //JDK5 enum枚举
    private static void demo1() {
        /*
        枚举项必须第一行，私有构造
         */
        //方式1
        week1 mon = week1.MON;
        System.out.println(mon);

        //方式2
        week2 mon2 = week2.MON;
        System.out.println(mon2.name);

        //方式3
        week3 mon3 = week3.MON;
        mon3.show();

    }

    //enum常见方法
    private static void demo2() {
        //switch+枚举
        week3 mon = week3.MON;
        switch (mon) {
            case MON:
                System.out.println("星期一");
                break;
            case TUE:
                System.out.println("星期二");
                break;
        }

        //返回枚举项的位置编号
        week2 mon2 = week2.MON;
        week2 tue2 = week2.TUE;
        week2 wed2 = week2.WED;
        System.out.println(mon2.ordinal());
        System.out.println(tue2.ordinal());
        System.out.println(wed2.ordinal());

        //比较编号
        System.out.println(mon2.compareTo(tue2));

        System.out.println(mon2.name());//获取实例名称
        System.out.println(mon2.toString());//重写之后

        week2 t = week2.valueOf(week2.class, "TUE");//通过字节码文件获取枚举项
        System.out.println(t);

        week2[] arr = week2.values();//得到每一个枚举项
        for (week2 w : arr)
            System.out.println(w.name());

    }

    //JDK7
    private static void demo3() {
        System.out.println(0b110);//二进制字面量
        System.out.println(100_000);//数字下划线
    }

    //JDK8
    private static void demo4(){

    }

}

//**************自定义枚举**************//
//方式1
class Week1 {
    public static final Week1 MON = new Week1();
    public static final Week1 TUE = new Week1();
    public static final Week1 WED = new Week1();

    public Week1() {
    }
}

//方式2
class Week2 {
    public static final Week2 MON = new Week2("星期一");
    public static final Week2 TUE = new Week2("星期二");
    public static final Week2 WED = new Week2("星期三");

    public String name;

    private Week2(String name) {
        this.name = name;
    }
}

//方式3
abstract class Week3 {
    public static final Week3 MON = new Week3("星期一") {
        public void show() {
            System.out.println("星期一");
        }
    };
    public static final Week3 TUE = new Week3("星期二") {
        public void show() {
            System.out.println("星期二");
        }
    };
    public static final Week3 WED = new Week3("星期三") {
        public void show() {
            System.out.println("星期三");
        }
    };

    abstract void show();

    public String name;

    private Week3(String name) {
        this.name = name;
    }
}


//**************enum枚举**************//
//方式1
enum week1 {
    MON, TUE, WED;
}

//方式2
enum week2 {
    MON("星期一"), TUE("星期二"), WED("星期三");
    public String name;

    private week2(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

//方式3
enum week3 {
    MON("星期一") {
        public void show() {
            System.out.println("星期一");
        }
    },
    TUE("星期二") {
        public void show() {
            System.out.println("星期二");
        }
    },
    WED("星期三") {
        public void show() {
            System.out.println("星期三");
        }
    };
    public String name;

    public abstract void show();

    private week3(String name) {
        this.name = name;
    }


}



