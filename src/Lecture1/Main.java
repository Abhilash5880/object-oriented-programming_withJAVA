package Lecture1;

public class Main {

    public static void main(String[] args) {
        // We do NOT need to import Student because we are in package Lecture1!
        Student s1 = new Student(101, "Abhilash", 95.5f);
        System.out.println("Same package access: " + s1.name);
    }
}