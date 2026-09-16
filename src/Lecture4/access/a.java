package Lecture4.access;

public class a {
    protected int num; //restricting access to the data member
    String name;
    int[] arr;

     //using getters and setters to access and modify the data members
    public int getNum() {
        return num;
    } //this is getter -> gets hte value of the data member
    //num is not visible directly outside-> it is accessed via getter and setter methods which are public while the variable is private, in it's own class -> but a private variable can be accessed in it's own class, so we can use getter and setter methods to access it from outside the class 

    public void setNum(int num) {
        this.num = num;
    } //this is setter -> sets the value of the data member

    public a(int num, String name) {
        this.num = num;
        this.name = name;
        this.arr = new int[num];
    }
}