package Lecture6.generics.comparison;

public class student implements Comparable<student>{
    int rollno;
    float marks;

    public student(int rollno, float marks) {
        this.rollno = rollno;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "student rollno="+rollno+" marks="+marks;
    }
    @Override
    public int compareTo(student o) {
        System.out.println("in compareto method");
        int diff=(int)(this.marks-o.marks);

        // if diff == 0: means both are equal
        // if diff < 0: means o is bigger else o is smaller
        return diff;
    }
}
