package Lecture7;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class collections {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        list2.add(34);
        list2.add(78);
        list2.add(55);
        list2.add(89);

        System.out.println(list2);

        List<Integer> vector = new Vector<>();
        vector.add(67);
        vector.add(90);
        vector.add(0);
        vector.add(7);

        System.out.println(vector);
    }
}
