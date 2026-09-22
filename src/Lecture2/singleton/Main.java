package Lecture2.singleton;

public class Main {
    public static void main(String[] args) {
        // ❌ COMPILE ERROR: The constructor Singleton() is not visible
        // Singleton test = new Singleton();

        // Getting instances through the static method
        singleton obj1 = singleton.getInstance();
        singleton obj2 = singleton.getInstance();
        singleton obj3 = singleton.getInstance();

        // All reference variables refer to the same object
        System.out.println(obj1);
        System.out.println(obj2);
        System.out.println(obj3);

        // `==` compares reference identity for objects.
        System.out.println(obj1 == obj2 && obj2 == obj3); // true
    }
}