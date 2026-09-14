package Lecture2;

// 1. Import Student from Lecture1
import Lecture1.Student;

public class TestPackage {
    public static void main(String[] args) {
        Student s2 = new Student(102, "Rick", 90.0f);
        System.out.println(s2.name);
    }
}