package Lecture2.staticExample;

public class Main {
    public static void main(String [] args)
    {
        human Rick=new human(21, "Rick", 0, false);
        human Morty=new human(14, "Morty", 0, true);

        System.out.println(Rick.totalPopulation);
        System.out.println(Morty.totalPopulation);
    
    }
}
