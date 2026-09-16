package Lecture2.singleton;

import java.util.ArrayList;
import java.util.Collections;

import Lecture4.access.a;

public class SubClass extends a {

    public SubClass(int num, String name) {
        super(num, name);
    }

    public static void main(String[] args) {
        a obj = new a(45, "Kunal Kushwaha");
//        int n = obj.num;
    }
}

class SubSubclass extends SubClass {

    public SubSubclass(int num, String name) {
        super(num, name);
    }

    public static void main(String[] args) {
        SubSubclass obj = new SubSubclass(45, "Kunal Kushwaha");
        int n = obj.num; //not visible because num is private in class a, so it cannot be accessed outside the class a, even if we are in a subclass of a, because private members are not inherited by subclasses

        //once we make num protected in class a, then it will be visible in the subclass of a, because protected members are inherited by subclasses, and they are visible in subclasses, even if they are in different packages
    }
}

class SubClass2 extends a {

    public SubClass2(int num, String name) {
        super(num, name);
    }

    public static void main(String[] args) {
        SubClass2 obj = new SubClass2(45, "Kunal Kushwaha");
//        int n = obj.num;
    }
}
