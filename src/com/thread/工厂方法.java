package com.thread;

public class 工厂方法 {
    public static void main(String[] args) {
        dogfactory df = new dogfactory();
        dog d = (dog) df.createanimal();
        d.eat();
    }
}


interface factory {
    public animal createanimal();
}

class catfactory implements factory {
    @Override
    public animal createanimal() {
        return new cat();
    }
}

class dogfactory implements factory {
    @Override
    public animal createanimal() {
        return new dog();
    }
}