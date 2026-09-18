package Lecture6.generics.comparison;
import java.util.Arrays;
import java.util.Comparator;
public class Main {

    public static void main(String[] args) {
        student kunal = new student(12, 89.76f);
        student rahul = new student(5, 99.52f);
        student arpit = new student(2, 95.52f);
        student karan = new student(13, 77.52f);
        student sachin = new student(9, 96.52f);

        student[] list = {kunal, rahul, arpit, karan, sachin};

        System.out.println(Arrays.toString(list));
        Arrays.sort(list, new Comparator <student>() {
            @Override
            public int compare(student o1, student o2) {
                return -(int)(o1.marks - o2.marks);
            }
        });

        Arrays.sort(list, (o1, o2) -> -(int)(o1.marks - o2.marks));

        System.out.println(Arrays.toString(list));

        if (kunal.compareTo(rahul) < 0) {
            System.out.println(kunal.compareTo(rahul));
            System.out.println("Rahul has more marks");
        }

    }
}
