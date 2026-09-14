package Lecture2.singleton;

public class singleton {
    //class that we can create only 1 object of...

    //if that is the case we cannot allow others from calling the constructor of the class, so we make it private
    //also stop others from creating objects of the class by making the constructor private
    private singleton () //cannot be called from outside the class
    {
        System.out.println("Constructor called");

    }

    private static singleton instance;

    public static singleton getInstance() {
        // check whether 1 obj only is created or not
        if (instance == null) {
            instance = new singleton();
        }

        return instance;
    }

}
