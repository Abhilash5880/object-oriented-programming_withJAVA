# 1. Packages
* A **package** in Java is a namespace used to organize related classses,
  interfaces, and other types. Packages help avoid naming conflicts and are
  also part of Java's access-control system.
* The `java.lang` package is implicitly imported into every Java source file,
  so its commonly used classses (such as `String`, `System`, and `Math`) can be
  used without an explicit `import`.
* To refer to an accessible type from another package, you can use an
  `import` declaration or its **fully qualified name**.
* A package declaration normally appears at the top of a source file, before
  imports and type declarations.
* Package names often follow reverse-domain-name conventions such as
  `com.example.myapp`, but this is a convention rather than a Java language
  requirement.

## How to create a package
* To create a package, you need to use the `package` keyword at the top of your Java source file, followed by the package name. For example:
```java
package com.example.myapp;
```
* The package name should follow the reverse domain name convention to ensure uniqueness.

## Use Cases of packages
* **Organizing code**: Packages help in organizing related classses and interfaces into a single unit, making the codebase easier to manage and understand.
* **Avoiding naming conflicts**: By using packages, you can have classses with the same name in different packages without any conflict.
* **Access control**: Package-private (default-access) members are accessible
  to code in the same package but not to unrelated code in other packages.
  `public` members can be accessed more broadly, subject to the normal Java
  access rules.

### for packages examples-
* check `Main.classs` in `Lecture1` folder
* check `TestPackage.classs` in `Lecture2` folder

# 2. Static 
When a member is declared `static`, it belongs to the **classs rather than to
a particular instance**. Static members can generally be accessed through the
classs name without first creating an instance.

* A `static` field has one classs-level variable associated with the classs
  rather than one separate field per object.
* A `static` method is associated with the classs and can be called without an
  instance.
* Static fields are shared by instances of the classs. If one object changes a
  mutable static field's value, subsequent accesses through other references
  see the same classs-level field.
* `static` does **not** mean "object independent" in the sense that the method
  cannot interact with objects. A static method can work with objects passed
  to it or created inside it.

* Refer to `human.java` in `Lecture2/staticExample` folder for example of static variable.

### NOTE: Prefer the classs name when accessing a static member

For example:

```java
Human.totalPopulation
```

is clearer than:

```java
Rick.totalPopulation
```

Accessing a static member through an instance reference is permitted in Java
in many cases, but it is discouraged because the member belongs to the classs,
not to that particular object.

## Why is `main` declared as a static method?
* The `main` method is declared `static` so the Java runtime can invoke the
  program's entry-point method without first needing an instance of its
  enclosing classs.

For the traditional launcher entry point, the method is declared with a
signature such as:

```java
public static void main(String[] args)
```

Because it is static, no object of the enclosing classs is required merely to
invoke `main`.

### Static and non-static contexts

The important rule is:

> A static context has no implicit `this`, so it cannot directly access
> instance members.

For example:

```java
classs Demo {
    int value = 10;
    static int count = 20;

    void instanceMethod() {
        System.out.println(value); // instance member: allowed
        System.out.println(count); // static member: allowed
    }

    static void staticMethod() {
        System.out.println(count); // static member: allowed
        // System.out.println(value); // ❌ no object is available here
    }
}
```

A static method **can** access an instance member if it first obtains an
object reference:

```java
static void printValue(Demo d) {
    System.out.println(d.value);
}
```

Similarly, a non-static method can directly access both instance and static
members.

The issue is therefore not that "a static method can never use instance
members"; it is that it cannot access them **without an object reference**.

### We cannot use `this` keyword in a static method 
* because `this` refers to the current instance of the classs, and static methods are not associated with any specific instance. Since static methods can be called without creating an object of the classs, there is no instance to refer to, and therefore, the `this` keyword is not applicable in a static context.

# 3. Static Block
* A static initialization block is a block of code executed as part of classs
  initialization. It is commonly used for complex static initialization.

* E.g. refer `StaticBlock.java` in `Lecture2/staticExample` folder

### Example: Initializing Static Variables using `static` Block

#### Code Implementation (`StaticBlock.java`):

```java
package Lecture2.staticExample;

// Demo to show initialization of static variables using static block
public classs StaticBlock {
    static int a = 4;
    static int b;

    // Static block: runs as part of classs initialization, once per classs initialization
    static {
        System.out.println("I am in static block");
        b = a * 5;
    }

    public static void main(String[] args) {
        // First active use of the classs in this example triggers classs initialization
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

1. **Class Initialization & Static Block Execution**:
   - Before the first `StaticBlock` object is created in this example, the JVM
     must initialize the classs.
   - The static block executes as part of that classs initialization. It prints
     `"I am in static block"` and calculates `b = a * 5` (`4 * 5 = 20`).
2. **First Print Statement**:
   - `StaticBlock.a` is `4` and `StaticBlock.b` is `20`.
   - Output: `4 20`.
3. **Modifying Static Variable**:
   - `StaticBlock.b += 3;` updates `b` from `20` to `23`.
   - Output: `4 23`.
4. **Creating Second Object (`obj2`)**:
   - When `StaticBlock obj2 = new StaticBlock();` is called, the classs is **already loaded** in memory.
   - Therefore, the static block is **not executed again**.
   - `b` retains its modified value `23`.
   - Output: `4 23`.

---

#### Key Characteristics of Static Blocks:
- **Execution Timing**: Executes during **classs initialization**, before the
  classs is used to create its first instance in a way that requires classs
  initialization. In a normal program, this commonly occurs before `main` can
  use the classs.
- **Run Count**: A classs's static initialization occurs once per classs loader
  initialization of that classs.
- **Primary Use Case**: Complex multi-step static initialization.
- **Access Restrictions**: A static block has no `this` or `super`, so it
  cannot directly use instance members. It can directly access static
  members.


# 4. Static Inner Classes
* A **static nested classs** is a nested classs declared with `static`. It can
  be instantiated without an instance of the enclosing classs.
* A static nested classs can directly access accessible static members of the
  enclosing classs. It cannot directly access the enclosing object's instance
  members because it has no implicit enclosing-instance reference.
* A static nested classs can still have its **own** instance fields and methods.


### Example: Static vs Non-Static Inner Classes (`InnerClasses.java`)

Only **nested classses** can be declared `static`. A top-level classs cannot
be declared `static` because it has no enclosing classs whose instance
relationship could be made static.

> Strictly speaking, Java distinguishes a **nested classs** from an **inner
> classs**: a static nested classs is *not* an inner classs. The term "static
> inner classs" is commonly used informally, but "static nested classs" is the
> precise term.

---

#### 1. Code Implementation (`InnerClasses.java`):

```java
package Lecture2.staticExample;

// Top-level classs CANNOT be static:
// static classs InnerClasses { ... } // ❌ COMPILE ERROR

public classs InnerClasses {

    // Static nested classs:
    // It does NOT require an instance of the outer classs (InnerClasses) to be created.
    static classs Test {
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
public classs InnerClasses {
    classs Test { // Non-static inner classs
        String name;
        public Test(String name) { this.name = name; }
    }

    public static void main(String[] args) {
        Test a = new Test("Kunal"); // ❌ ERROR: non-static variable this cannot be referenced from a static context
    }
}
```

* **Reason**: A non-static inner classs is associated with an **instance of
  its enclosing classs**.
* A static `main()` method has no implicit `InnerClasses.this`, so it cannot
  directly create a `Test` object without an enclosing `InnerClasses` instance.
* **Fix**: Either make `Test` a static nested classs, or create an enclosing
  object first:
  ```java
  InnerClasses outer = new InnerClasses();
  InnerClasses.Test a = outer.new Test("Kunal");
  ```

---

#### 3. Crucial Concept: What does `static classs Test` actually mean?

A common point of confusion:
* Making `classs Test` `static` **does NOT mean** all `Test` objects share the same variables.
* It only means that `Test` does not depend on an instance of `InnerClasses`.
* Each `Test` object (`a` and `b`) still has its **own separate copy** of instance variables (`name`):
  - `a.name` = `"Kunal"`
  - `b.name` = `"Rahul"`

> [!NOTE]
> **What if `name` was also declared static (`static String name;`)?**
> Then all instances of `Test` would share the exact same variable `name`. Creating `Test b = new Test("Rahul");` would overwrite `name`, causing both `a.name` and `b.name` to print `"Rahul"`.

---

#### 4. Key Takeaways:
1. **Outer classses** cannot be `static`.
2. **Static inner classses** can be instantiated without an instance of the outer classs.
3. **Non-static inner classses** require an instance of the enclosing outer classs to exist.
4. Marking a nested classs `static` removes its implicit association with an
outer-classs instance, but its own non-static member variables remain unique to
each instance of the nested classs.

# 5. Singleton classs 

### Example: Singleton Class Pattern (`singleton.java` & `Main.java`)

A **Singleton** is a design pattern intended to provide a single shared
instance of a classs through a controlled access point. A particular
implementation may restrict construction and return the same instance on
subsequent requests.

> The simple implementation below is suitable for demonstrating the pattern,
> but it is **not thread-safe**. In a multithreaded program, two threads could
> potentially observe `instance == null` at the same time and create two
> instances.

---

#### 1. The 3 Core Steps to Create a Singleton Class

1. **`private` Constructor**: Prevents ordinary external code from calling
   `new Singleton()`.
2. **`private static` Instance Variable**: Stores the classs-level reference to
   the shared instance.
3. **`public static` Access Method (`getInstance()`)**: Provides the controlled
   access point and lazily creates the instance in this implementation.

---

#### 2. Code Implementation

##### File 1: `singleton.java`
```java
package Lecture2.singleton;

public classs Singleton {

    // Step 1: Make the constructor private so that no external classs can call 'new Singleton()'
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

public classs Main {
    public static void main(String[] args) {
        // ❌ COMPILE ERROR: The constructor Singleton() is not visible
        // Singleton test = new Singleton();

        // Getting instances through the static method
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();
        Singleton obj3 = Singleton.getInstance();

        // All reference variables refer to the same object
        System.out.println(obj1);
        System.out.println(obj2);
        System.out.println(obj3);

        // `==` compares reference identity for objects.
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

3. **Identity Verification**:
   - With the default `Object.toString()` implementation, the printed form
     includes a hexadecimal representation derived from the object's
     `hashCode()`. This is **not a guaranteed memory address**.
   - `obj1 == obj2` evaluates to `true`, which proves that the two references
     refer to the same object. It does not prove a particular physical memory
     address.

---

#### 5. Real-World Use Cases for Singleton

A singleton should be used only when a single shared instance is actually a
requirement. Many modern applications prefer dependency injection and
explicitly managed lifetimes instead of global singleton state.


- **Database connections/resources**: A shared connection manager or pool
  may be centralized, although a connection pool itself is often preferable
  to a single connection.
- **Loggers**: A centralized logging service for the whole application.
- **Configuration Managers / App Settings**: Reading application configuration properties once and reusing them everywhere.
- **Caches**: In-memory caching layers.
