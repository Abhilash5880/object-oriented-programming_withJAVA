package Lecture1;
public class Student {
    // create attributes
    public int rno;
    public float marks;
    public String name;

    //we need a way to add values of the above attributes
    //we need 1 word to access every object
    Student() {
        this(0, null, 0.0f);
    }

    public Student(int rno, String name, float marks) {
        this.rno = rno;
        this.name = name;
        this.marks = marks;
    }

    Student(Student student) {
        this(student.rno, student.name, student.marks);
    }
    void greeting() {
        System.out.println("Hello! My name is " + this.name);
    }

    void changeName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Student obj = new Student(); // create an object (instance)
        obj.rno = 101;
        obj.name = "Abhilash";
        obj.marks = 89.5f;

        //dot operator is used to access the attributes of the object

        System.out.println("Roll no: " + obj.rno);
        System.out.println("Name: " + obj.name);
        System.out.println("Marks: " + obj.marks);

        Student Rick = new Student(); // create an object (instance)
        System.out.println(Rick + " : useless value");
        
        Rick.name = "Rick";
        Rick.rno = 102;
        Rick.marks = 90.5f;

        System.out.println("Roll no: " + Rick.rno); //by default 0
        System.out.println("Name: " + Rick.name); //by default null
        System.out.println("Marks: " + Rick.marks); //by default 0.0f

        Rick.greeting();
        Rick.changeName("Rick Sanchez");
        Rick.greeting();
    }
}


