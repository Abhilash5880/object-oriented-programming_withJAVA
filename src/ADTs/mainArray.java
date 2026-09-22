package ADTs;

public class mainArray {

    public static void main(String[] args) {
        arrayStack<Integer> stack01 = new arrayStack<>(67);

        stack01.push(42);
        stack01.push(53);
        stack01.push(9);
        stack01.pop();
        stack01.peek();
        System.out.println(stack01.isEmpty());
        stack01.print();
    }
}
