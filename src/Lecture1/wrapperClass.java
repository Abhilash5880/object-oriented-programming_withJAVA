package Lecture1;
public class wrapperClass {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        Integer num1=45;
        Integer num2=49;
        swap(a, b);
        swap(num1, num2);

        final int bonus=2;
        //bonus=3; //error: cannot assign a value to final variable bonus
    }

    static void swap(Integer a, Integer b) {
        Integer temp = a;
        a = b;
        b = temp;
        System.out.println("a = " + a + ", b = " + b);
    }
}