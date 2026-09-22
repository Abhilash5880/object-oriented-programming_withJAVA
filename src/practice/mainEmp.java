package practice;

public class mainEmp {
    public static void main(String[] args) {
        employee emp1 = new employee(153, "abhilashPalit", "36/11/A Kol");

        System.out.println(emp1.id);
        System.out.println(emp1.name);
        System.out.println(emp1.address);

        employee copy=new employee(emp1);
        System.out.println(copy.id);

    }
}
