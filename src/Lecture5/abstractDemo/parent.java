package Lecture5.abstractDemo;

public abstract class parent {

    int age;
    final int VALUE;

    public parent(int age) {//constructor of an abstract class
        this.age = age;
        VALUE = 32456789;
    }

    static void hello(){
        System.out.println("hey");
    }

    void normal() {
        System.out.println("this is a normal method");
    }

    abstract void career();
    abstract void partner();
}