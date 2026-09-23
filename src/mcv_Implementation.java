// nn MODEL nn manages data and business logic
class Student {
    private String name;
    private String rollNo;
    public Student(String name, String rollNo) {
        this.name   = name;
        this.rollNo = rollNo;
    }
    public String getName()         { return name; }
    public void   setName(String n) { this.name = n; }
    public String getRollNo()         { return rollNo; }
    public void   setRollNo(String r) { this.rollNo = r; }
}
// nn VIEW nn presentation only; no business logic
class StudentView {
    public void printStudentDetails(String name, String rollNo) {
        System.out.println("=== Student Details ===");
        System.out.println("Name   : " + name);
        System.out.println("Roll No: " + rollNo);
    }
}
// nn CONTROLLER nn intermediary; coordinates Model <-> View
class StudentController {
    private Student     model;
    private StudentView view;
    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view  = view;
    }
    // Delegate getters/setters to Model
    public String getStudentName()           { return model.getName(); }
    public void   setStudentName(String n)   { model.setName(n); }
    public String getStudentRollNo()         { return model.getRollNo(); }
    public void   setStudentRollNo(String r) { model.setRollNo(r); }
    // Trigger View update
    public void updateView() {
        view.printStudentDetails(model.getName(), model.getRollNo());
    }
}
// nn MAIN / Client nn
class Main {
    // Simulates fetching data from a database
    private static Student fetchFromDB() {
        return new Student("Abhilash", "101");
    }
    public static void main(String[] args) {
        Student          model      = fetchFromDB();
        StudentView      view       = new StudentView();
        StudentController controller = new StudentController(model, view);
        controller.updateView();           // display initial state
        controller.setStudentName("Riya"); // simulate user update
        controller.updateView();           // display updated state
    }
}
// Output:
// === Student Details ===
// Name   : Abhilash
// Roll No: 101
// === Student Details ===
// Name   : Riya
// Roll No: 101