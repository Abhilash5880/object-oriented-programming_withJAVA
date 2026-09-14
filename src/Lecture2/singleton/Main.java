package Lecture2.singleton;

public class Main {
    public static void main(String[] args) {
        singleton obj1 = singleton.getInstance();

        singleton obj2 = singleton.getInstance();

        singleton obj3 = singleton.getInstance();

        // all 3 ref variables are pointing to just one object

    }
}