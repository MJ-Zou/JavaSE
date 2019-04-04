package com.thread;

public class 简单工厂 {//了解

    public static void main(String[] args) {
        dog d = animalfactory.createdog();
        d.eat();
        cat c = animalfactory.createcat();
        c.eat();

        dog dd = (dog) animalfactory.createanimal("dog");
        dd.eat();
        cat cc = (cat) animalfactory.createanimal("cat");
        cc.eat();
    }
}

abstract class animal {
    public abstract void eat();
}

class dog extends animal {
    @Override
    public void eat() {
        System.out.println("狗吃肉");
    }
}

class cat extends animal {
    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }
}

class animalfactory {   //动物工厂

    public static dog createdog() {
        return new dog();
    }

    public static cat createcat() {
        return new cat();
    }

    public static animal createanimal(String name) {
        if ("dog".equals(name))
            return new dog();
        else if ("cat".equals(name))
            return new cat();
        else
            return null;
    }
}
