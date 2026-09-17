package Lecture5.abstractDemo;

public class
Main {
    public static void main(String[] args) {
        Son son = new Son(30);
        son.career();

        son.normal();

        parent daughter = new Daughter(28);
        daughter.career();

        parent.hello();
//        Parent mom = new Parent(45);
    }
}

