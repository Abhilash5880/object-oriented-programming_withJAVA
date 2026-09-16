package Lecture4.access;

import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        a obj=new a(10, "Hello");


        //need to do a few things
        //1. access the data members
        //2. modify the data members

        // ArrayList<Integer> list=new ArrayList<>();
        // list.DEFAULT_CAPACITY;
        obj.getNum(); 
        //data member is private, so it cannot be accessed outside the class

        //num is not visible directly outside-> it is accessed via getter and setter methods which are public while the variable is private, in it's own class -> but a private variable can be accessed in it's own class, so we can use getter and setter methods to access it from outside the class

       

    }
    
}
