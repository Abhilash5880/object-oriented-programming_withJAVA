package Lecture2;

// 1. Import Student from Lecture1
import Lecture1.Student;
import Lecture7.week.Week;

public class TestPackage {
    public static void main(String[] args) {
        Student s2 = new Student(102, "Rick", 90.0f);
        System.out.println(s2.name);

        Week week;
        week = Week.Monday;
        week.hello();
        System.out.println(Week.valueOf("Monday"));
        for(Week day : Week.values()) {
            System.out.println(day);
        }
    }

}