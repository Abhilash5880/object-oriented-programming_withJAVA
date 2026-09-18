package Lecture6.generics;

import java.util.ArrayList;
import java.util.function.Consumer;

public class LambdaFunctions {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int a = 0; a < 10; a++){
            list.add(a+1);
        }
        //list.forEach((item) -> System.out.println(item * 2));

        Consumer<Integer> fun = (item) -> System.out.println(item+" x 2 = "+item*2);
        list.forEach(fun);

        Operation sum=(a, b) -> a+b;
        Operation prod = (a, b) -> a * b;
        Operation sub = (a, b) -> a - b;

        LambdaFunctions myCalculator = new LambdaFunctions();
        System.out.println(myCalculator.operate(5, 3, sum));
        System.out.println(myCalculator.operate(5, 3, prod));
        System.out.println(myCalculator.operate(5, 3, sub));
    }
    private int operate(int a, int b, Operation op) {
        return op.operation(a, b);

        // this is the key part -> everything that has been done till now allows us to push as an argument into parameters
    }
}
interface Operation{
    int operation(int a, int b);
}
