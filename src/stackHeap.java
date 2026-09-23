class Person {
    String name;       // instance variable — on HEAP (inside the object)
    int    age;        // instance variable — on HEAP
    Person(String name, int age) { this.name = name; this.age = age; }
}
class stackHeap {
    public static void main(String[] args) {
        // 'args'  fi reference on STACK fi points to array on HEAP
        // 'p'     fi reference on STACK fi points to Person object on HEAP
        // 'score' fi primitive on STACK (value stored directly, not on heap)
        Person p     = new Person("Abhilash", 20);
        int    score = 95;
        System.out.println(p.name + " scored " + score);
        // When main() returns:
        // Stack frame for main() is POPPED and destroyed
        // 'p' reference is gone fi Person object on heap is now UNREACHABLE
        // GC will eventually reclaim that heap memory
    }
}