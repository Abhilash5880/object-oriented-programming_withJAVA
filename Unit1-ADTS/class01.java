class class01 {
    // create attributes
    int[] rno = new int[5];
    float[] marks = new float[5];
    String[] name = new String[5];

    public static void main(String[] args) {
        class01 obj = new class01(); // create an object (instance)
        obj.rno[0] = 101;
        obj.name[0] = "Abhilash";
        obj.marks[0] = 89.5f;

        System.out.println("Roll no: " + obj.rno[0]);
        System.out.println("Name: " + obj.name[0]);
        System.out.println("Marks: " + obj.marks[0]);
    }
}