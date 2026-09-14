package Lecture2.staticExample;

public class Main {
    public static void main(String [] args)
    {
        human Rick=new human(21, "Rick", 0, false);
        human Morty=new human(14, "Morty", 0, true);

        System.out.println(Rick.totalPopulation);
        System.out.println(Morty.totalPopulation);
        
        
    }
    static void hello()
    {
        //greeting();
        //we cannot make a static method call from a non-static context, so we need to create an object of the class to call the greeting method
        Main obj=new Main();
        obj.greeting(); 
        //we can also make the greeting method static to call it directly from the static context
        
    }
   
    void greeting()
    {
        System.out.println("Hello");
    }
}
