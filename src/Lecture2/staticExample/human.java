package Lecture2.staticExample;

public class human {
    int age;
    String name;
    int salary;
    boolean isMarried;
    
    static long totalPopulation=0;
    
    public human(int age, String name, int salary, boolean isMarried) {
        this.age = age;
        this.name = name;
        this.salary = salary;
        this.isMarried = isMarried;
        human.totalPopulation++;
    }
}
