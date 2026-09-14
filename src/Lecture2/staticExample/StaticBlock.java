package Lecture2.staticExample;
//demo to show initizalization of static variables
public class StaticBlock {
    static int a=4;
    static int b;

    //static block
    static {
        System.out.println("Static block initialized"); //wil only run once when the class is loaded for the first time
        b=a*4;
    }

    public static void main(String[] args) {
        StaticBlock obj=new StaticBlock();
        System.out.println(StaticBlock.a+" "+StaticBlock.b);

        StaticBlock.b+=3;
        System.out.println(StaticBlock.a+" "+StaticBlock.b);

        StaticBlock obj2=new StaticBlock();
        System.out.println(StaticBlock.a+" "+StaticBlock.b);
    }
}
