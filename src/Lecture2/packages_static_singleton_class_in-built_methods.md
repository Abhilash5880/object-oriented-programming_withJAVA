# 1. Packages
* A package in Java is a way of organizing classes and interfaces into a logical group. It helps in avoiding naming conflicts and provides access control.
* Packages are used to create a namespace for the classes and interfaces they contain.
* The `java.lang` package is automatically imported into every Java program, so its classes can be used without explicit import statements.
* To use a class from another package, you need to import it using the `import` statement.
* You can also use the fully qualified name of a class to avoid importing it.

## How to create a package
* To create a package, you need to use the `package` keyword at the top of your Java source file, followed by the package name. For example:
```java
package com.example.myapp;
```
* The package name should follow the reverse domain name convention to ensure uniqueness.

## Usecases of packages
* **Organizing code**: Packages help in organizing related classes and interfaces into a single unit, making the codebase easier to manage and understand.
* **Avoiding naming conflicts**: By using packages, you can have classes with the same name in different packages without any conflict.
* **Access control**: Packages provide a way to control access to classes and members. Classes in the same package can access each other's package-private members, while classes in different packages cannot.

### for packages examples-
* check `Main.class` in `Lecture1` folder
* check `TestPackage.class` in `Lecture2` folder

# 2. Static 
When a memeber is declared static it can be accessed before any of the object of the clas is created and without reference to any object. Static members are shared among all instances of a class.

* Object independent methods and variables are called static methods and variables. They can be accessed without creating an instance of the class.

* Static variables are shared among all instances of a class, meaning that if one instance changes the value of a static variable, it will be reflected in all other instances.

* Refer to `human.java` in `Lecture2/staticExample` folder for example of static variable.

### NOTE: Whenever accessing/modifying static variable, it is recommended to use class name instead of object name. 
For example, `human.totalPopulation` instead of `Rick.totalPopulation`.

## Why is `main` declared as a static method?
* The `main` method is declared as static because it serves as the entry point of the program. 

When the Java Virtual Machine (JVM) starts executing a Java program, it needs to call the `main` method without creating an instance of the class. By declaring it as static, the JVM can invoke the `main` method directly using the class name, allowing the program to start without any object instantiation.

### NOTE: we cannot make a static method call from a non-static context, so we need to create an object of the class to call the greeting method

* a static method can only access other static methods and static variables directly. It cannot access instance methods or instance variables without creating an object of the class.

* Basically static methods do not depend on instance so we cannot have something inside it which depends on instances.

### But we can access static methods and variables from non-static methods and variables.

* a non static methods does not limit a non-static method to access static methods and variables. It can access both static and non-static members of the class.

### We cannot use `this` keyword in a static method 
* because `this` refers to the current instance of the class, and static methods are not associated with any specific instance. Since static methods can be called without creating an object of the class, there is no instance to refer to, and therefore, the `this` keyword is not applicable in a static context.

# 3. Static Block
* A static block is a block of code that is executed when the class is loaded into memory. It is used to initialize static variables or perform any other static initialization tasks.

* E.g. refer `StaticBlock.java` in `Lecture2/staticExample` folder

### Example: Initializing Static Variables using `static` Block

#### Code Implementation (`StaticBlock.java`):

```java
package Lecture2.staticExample;

// Demo to show initialization of static variables using static block
public class StaticBlock {
    static int a = 4;
    static int b;

    // Static Block: will only run ONCE when the class is loaded for the first time
    static {
        System.out.println("I am in static block");
        b = a * 5;
    }

    public static void main(String[] args) {
        // Creating first object -> triggers class loading and runs static block
        StaticBlock obj = new StaticBlock();
        System.out.println(StaticBlock.a + " " + StaticBlock.b); // Output: 4 20

        // Modifying the static variable
        StaticBlock.b += 3;
        System.out.println(StaticBlock.a + " " + StaticBlock.b); // Output: 4 23

        // Creating second object -> static block will NOT run again
        StaticBlock obj2 = new StaticBlock();
        System.out.println(StaticBlock.a + " " + StaticBlock.b); // Output: 4 23
    }
}
```

#### Output:
```text
I am in static block
4 20
4 23
4 23
```

---

#### Step-by-Step Explanation:

1. **Class Loading & Static Block Execution**:
   - When `new StaticBlock()` is encountered for the first time, the JVM loads the `StaticBlock` class.
   - The static block executes **once** immediately during class loading. It prints `"I am in static block"` and calculates `b = a * 5` (`4 * 5 = 20`).
2. **First Print Statement**:
   - `StaticBlock.a` is `4` and `StaticBlock.b` is `20`.
   - Output: `4 20`.
3. **Modifying Static Variable**:
   - `StaticBlock.b += 3;` updates `b` from `20` to `23`.
   - Output: `4 23`.
4. **Creating Second Object (`obj2`)**:
   - When `StaticBlock obj2 = new StaticBlock();` is called, the class is **already loaded** in memory.
   - Therefore, the static block is **not executed again**.
   - `b` retains its modified value `23`.
   - Output: `4 23`.

---

#### Key Characteristics of Static Blocks:
- **Execution Timing**: Executes before the `main()` method or any constructor is invoked.
- **Run Count**: Runs only **once** during the class's entire lifecycle.
- **Primary Use Case**: Used for complex multi-step static initialization or loading native libraries/drivers (e.g., JDBC drivers).
- **Access Restrictions**: Can only directly access static variables and call static methods. Cannot use `this` or `super`.


# 4. Static Inner Classes
* A static inner class is a nested class that is declared as static. It can be instantiated without an instance of the outer class. 

* Static inner classes can access static members of the outer class but cannot access instance members directly.


### Example: Static vs Non-Static Inner Classes (`InnerClasses.java`)

Only **nested / inner classes** can be declared `static`. Top-level (outer) classes cannot be static because being static requires an enclosing class context.

---

#### 1. Code Implementation (`InnerClasses.java`):

```java
package Lecture2.staticExample;

// Top-level class CANNOT be static:
// static class InnerClasses { ... } // ❌ COMPILE ERROR

public class InnerClasses {

    // Static nested class:
    // It does NOT require an instance of the outer class (InnerClasses) to be created.
    static class Test {
        String name;

        public Test(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static void main(String[] args) {
        // Since Test is static, we can instantiate it inside static main() 
        // without creating an instance of InnerClasses
        Test a = new Test("Kunal");
        Test b = new Test("Rahul");

        System.out.println(a.name); // Kunal
        System.out.println(b.name); // Rahul

        // If toString() is overridden:
        System.out.println(a); // Kunal
    }
}
```

#### Output:
```text
Kunal
Rahul
Kunal
```

---

#### 2. Why does a Non-Static Inner Class fail inside `main()`?

If `Test` is **not** static:
```java
public class InnerClasses {
    class Test { // Non-static inner class
        String name;
        public Test(String name) { this.name = name; }
    }

    public static void main(String[] args) {
        Test a = new Test("Kunal"); // ❌ ERROR: non-static variable this cannot be referenced from a static context
    }
}
```

* **Reason**: A non-static inner class is tied to an **instance of its outer class**. 
* Because `main()` is `static`, it runs without any instance of `InnerClasses`. Hence, you cannot create a non-static `Test` object directly inside `main()`.
* **Fix**: Either make `class Test` `static`, or instantiate the outer class first:
  ```java
  InnerClasses outer = new InnerClasses();
  InnerClasses.Test a = outer.new Test("Kunal");
  ```

---

#### 3. Crucial Concept: What does `static class Test` actually mean?

A common point of confusion:
* Making `class Test` `static` **does NOT mean** all `Test` objects share the same variables.
* It only means that `Test` does not depend on an instance of `InnerClasses`.
* Each `Test` object (`a` and `b`) still has its **own separate copy** of instance variables (`name`):
  - `a.name` = `"Kunal"`
  - `b.name` = `"Rahul"`

> [!NOTE]
> **What if `name` was also declared static (`static String name;`)?**
> Then all instances of `Test` would share the exact same variable `name`. Creating `Test b = new Test("Rahul");` would overwrite `name`, causing both `a.name` and `b.name` to print `"Rahul"`.

---

#### 4. Key Takeaways:
1. **Outer classes** cannot be `static`.
2. **Static inner classes** can be instantiated without an instance of the outer class.
3. **Non-static inner classes** require an instance of the enclosing outer class to exist.
4. Marking an inner class `static` isolates it from the outer class instance, but its own non-static member variables remain unique to each instance of the inner class.

# 5. Singleton class 

### Example: Singleton Class Pattern (`singleton.java` & `Main.java`)

A **Singleton Class** is a class designed so that **only one instance (object)** can ever be created throughout the entire execution of the program. Any subsequent request for an object returns the exact same existing instance.

---

#### 1. The 3 Core Steps to Create a Singleton Class

1. **`private` Constructor**: Prevents any other class from creating an object using the `new` keyword (`new Singleton()` won't be allowed outside).
2. **`private static` Instance Variable**: Holds the single reference to the created object.
3. **`public static` Getter Method (`getInstance()`)**: Provides a global access point that returns the single instance (creates it if it doesn't already exist).

---

#### 2. Code Implementation

##### File 1: `singleton.java`
```java
package Lecture2.singleton;

public class Singleton {

    // Step 1: Make the constructor private so that no external class can call 'new Singleton()'
    private Singleton() {
        System.out.println("Singleton instance created!");
    }

    // Step 2: Create a private static variable to hold the single instance
    private static Singleton instance;

    // Step 3: Provide a public static method to access the single instance
    public static Singleton getInstance() {
        // Check whether an object is already created or not
        if (instance == null) {
            instance = new Singleton(); // Only creates the object once
        }
        return instance;
    }
}
```

##### File 2: `Main.java`
```java
package Lecture2.singleton;

public class Main {
    public static void main(String[] args) {
        // ❌ COMPILE ERROR: The constructor Singleton() is not visible
        // Singleton test = new Singleton();

        // Getting instances through the static method
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();
        Singleton obj3 = Singleton.getInstance();

        // All reference variables point to the exact same object in the heap memory
        System.out.println(obj1);
        System.out.println(obj2);
        System.out.println(obj3);

        // Verification: check if all references point to the identical address
        System.out.println(obj1 == obj2 && obj2 == obj3); // true
    }
}
```

---

#### 3. Output:
```text
Singleton instance created!
Lecture2.singleton.Singleton@2f92e0f4
Lecture2.singleton.Singleton@2f92e0f4
Lecture2.singleton.Singleton@2f92e0f4
true
```

---

#### 4. How It Works (Memory & Execution Flow):

1. **`Singleton.getInstance()` is called for `obj1`**:
   - `instance` is initially `null`.
   - The condition `instance == null` evaluates to `true`.
   - `new Singleton()` is invoked, printing `"Singleton instance created!"`.
   - `instance` now stores the reference to this newly created object and returns it to `obj1`.

2. **`Singleton.getInstance()` is called for `obj2` & `obj3`**:
   - `instance` is **no longer `null`** (it already points to the object in heap memory).
   - The `if (instance == null)` condition evaluates to `false`.
   - It directly returns the existing `instance`.
   - The constructor is **never called again**.

3. **Memory Address Verification**:
   - Printing `obj1`, `obj2`, and `obj3` outputs the exact same hashcode (e.g. `@2f92e0f4`).
   - `obj1 == obj2` evaluates to `true`, proving that all references point to the same memory location in heap.

---

#### 5. Real-World Use Cases for Singleton:
- **Database Connections**: Managing a single shared connection pool.
- **Loggers**: A centralized logging service for the whole application.
- **Configuration Managers / App Settings**: Reading application configuration properties once and reusing them everywhere.
- **Caches**: In-memory caching layers.
