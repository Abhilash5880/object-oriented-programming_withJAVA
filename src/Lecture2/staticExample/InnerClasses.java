package Lecture2.staticExample;
//outside classes cannot be static, only inner classes can be static
public class InnerClasses {
    static class test{
        String name;

        public test(String name)
        {
            this.name=name;
        }
    }

    public static void main(String[] args) {
        test a = new test("test");
        test b = new test("test2");

        System.out.println(a.name);
        System.out.println(b.name);
    }
}
