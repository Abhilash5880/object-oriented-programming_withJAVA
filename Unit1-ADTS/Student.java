
// public class Main {
//     public static void main(String[] args) {
//         // just declaring
// //        Student kunal;
// //        kunal = new Student();

//         Student kunal = new Student(15, "Kunal Kushwaha", 85.4f);

// //        kunal.rno = 13;
// //        kunal.name = "Kunal Kushwaha";
// //        kunal.marks = 88.5f;


// //        kunal.changeName("Shoe lover");
// //        kunal.greeting();

// //
//         System.out.println(kunal.rno);
//         System.out.println(kunal.name);
//         System.out.println(kunal.marks);

//         Student random = new Student(kunal);
//         System.out.println(random.name);

//         Student random2 = new Student();
//         System.out.println(random2.name);

//         Student one = new Student();
//         Student two = one;

//         one.name = "Something something";

//         System.out.println(two.name);

//     }
// }

// create a class
// for every single student
public class Student {
    // create attributes
    int rno;
    float marks;
    String name;

    //we need a way to add values of the above attributes
    //we need 1 word to access eveyr object
    Student() {
        this(0, null, 0.0f);
    }

    Student(int rno, String name, float marks) {
        this.rno = rno;
        this.name = name;
        this.marks = marks;
    }

    Student(Student student) {
        this(student.rno, student.name, student.marks);
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
    }
}


