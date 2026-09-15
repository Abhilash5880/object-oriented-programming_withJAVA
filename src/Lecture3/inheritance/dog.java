package Lecture3.inheritance;

class dog extends Animal {
    void bark() {
        System.out.println("The dog barks.");
    }

    public class Main {
    public static void main(String[] args) {
        dog dog1 = new dog();
        dog1.eat(); // Inherited method from Animal class
        dog1.bark(); // Method from Dog class
    }
}
}

