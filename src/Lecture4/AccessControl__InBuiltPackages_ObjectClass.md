# Access Control (Access Modifiers) in Java

Access control defines the **visibility and accessibility** of classes, constructors, methods, and variables across different classes and packages. It is fundamental to **Encapsulation** and **Data Hiding**.

---

## 1. The 4 Access Modifiers Summary Table

| Modifier | Same Class | Same Package (Subclass) | Same Package (Non-subclass) | Different Package (Subclass) | Different Package (Non-subclass / World) |
| :--- | :---: | :---: | :---: | :---: | :---: |
| **`private`** | ✅ **Yes** | ❌ No | ❌ No | ❌ No | ❌ No |
| **Default** *(No modifier)* | ✅ **Yes** | ✅ **Yes** | ✅ **Yes** | ❌ No | ❌ No |
| **`protected`** | ✅ **Yes** | ✅ **Yes** | ✅ **Yes** | ✅ **Yes** *(via subclass only)* | ❌ No |
| **`public`** | ✅ **Yes** | ✅ **Yes** | ✅ **Yes** | ✅ **Yes** | ✅ **Yes** |

---

## 2. Detailed Breakdown of Modifiers

### 1. `private`
- **Scope**: Accessible **only inside the class** where it is declared.
- **Purpose**: **Data Hiding / Security**. Prevents external code from tampering with critical object state directly.
- **How to access**: Using **public Getters and Setters**.

### 2. Default (Package-Private / No keyword)
- **Scope**: Accessible by any class within the **same package**.
- Completely invisible to any class outside the package (even subclasses cannot access it).

### 3. `protected`
- **Scope**: 
  - Accessible anywhere in the **same package**.
  - Accessible in a **different package ONLY via inheritance** (subclass).
- **Tricky Rule (Interview Favorite)**: A subclass in another package can only access `protected` members through a reference of that **subclass type** (or via `super`), **never** through a parent reference (`new A().protectedMember` fails).

### 4. `public`
- **Scope**: Accessible **from anywhere** in the program (any class in any package).

---

## 3. Code Implementation (`a.java` & `Main.java`)

### File 1: Class Definition (`a.java` / `A.java`)
```java
package Lecture4.access;

public class A {
    // 1. private: Only accessible inside class A
    private int num;

    // 2. default: Accessible anywhere in the 'Lecture4.access' package
    String name;

    // 3. protected: Accessible in this package + subclasses in any package
    protected int[] arr;

    // Parameterized Constructor
    public A(int num, String name) {
        this.num = num;
        this.name = name;
        this.arr = new int[num];
    }

    // Getter: provides read-only access to private 'num'
    public int getNum() {
        return num;
    }

    // Setter: provides controlled write access (can add validation)
    public void setNum(int num) {
        if (num > 0) {
            this.num = num;
        }
    }
}
```

### File 2: Execution in Same Package (`Main.java`)
```java
package Lecture4.access;

public class Main {
    public static void main(String[] args) {
        A obj = new A(10, "Kunal");

        // 1. Accessing 'num' (private):
        // System.out.println(obj.num); ❌ COMPILE ERROR: num has private access in A
        System.out.println(obj.getNum()); // ✅ Allowed via public getter: 10

        // Modifying 'num' (private):
        obj.setNum(25); // ✅ Allowed via public setter
        System.out.println(obj.getNum()); // 25

        // 2. Accessing 'name' (default):
        System.out.println(obj.name); // ✅ Allowed: same package

        // 3. Accessing 'arr' (protected):
        System.out.println(obj.arr.length); // ✅ Allowed: same package
    }
}
```

---

## 4. Subclass Access Across Different Packages (`protected` Deep Dive)

Consider `A` in package `Lecture4.access`, and a subclass `SubClass` in a **different package** `Lecture4.other`:

```java
package Lecture4.other;

import Lecture4.access.A;

public class SubClass extends A {

    public SubClass(int num, String name) {
        super(num, name);
    }

    public static void main(String[] args) {
        SubClass obj = new SubClass(45, "SubClassUser");

        // ✅ Allowed: 'arr' is protected and accessed via SubClass reference in a subclass
        System.out.println(obj.arr.length);

        // ❌ COMPILE ERROR:
        // A parentObj = new A(10, "Parent");
        // System.out.println(parentObj.arr); // Cannot access protected member via Parent reference from another package!
    }
}
```

---

## 5. Important Rules & Best Practices

1. **Top-Level Class Modifiers**:
   - A top-level class can only be **`public`** or **default** (package-private). It **cannot** be `private` or `protected`.
   - Only **inner/nested classes** can be declared `private` or `protected`.
   - A `.java` file can contain at most **one** `public` top-level class, and its name must exactly match the file name.

2. **Why use Getters and Setters? (Encapsulation)**:
   - **Data Validation**: You can reject invalid inputs (e.g., preventing negative balance or invalid age).
   - **Read-Only / Write-Only Properties**: By providing only a getter (no setter), a field becomes effectively read-only.
   - **Internal Flexibility**: You can change how a property is stored internally without breaking external code.
   - Only subclasses or classes in the same package can access `protected` members, while `private` members are strictly confined to their own class.


# 2. In-Built Packages in Java

A **Package** in Java is simply a directory / folder that groups related classes, interfaces, and sub-packages together. It helps prevent naming conflicts and controls access.

Java provides a vast standard library categorized into **Built-in Packages** (prefixed with `java.*` or `javax.*`).

---

## 1. Top In-Built Packages Overview

```
                          ┌───────────────────────────┐
                          │    Java Standard Library   │
                          └─────────────┬─────────────┘
         ┌───────────────┬──────────────┼──────────────┬───────────────┐
         ▼               ▼              ▼              ▼               ▼
    java.lang        java.util       java.io        java.net       java.math
 (Auto-imported)    (Utilities &   (File/Stream   (Networking &   (Arbitrary
  Core Classes)     Collections)       I/O)          Sockets)      Precision)
```

---

## 2. Detailed Breakdown of Essential In-Built Packages

### 1. `java.lang` (Core Language Package)
- **Special Feature**: It is **automatically imported** into every Java source file by default. You never need to write `import java.lang.*;`.
- **Purpose**: Provides fundamental classes essential to the design of the Java programming language.
- **Key Classes**:
  - `Object`: The ultimate root / superclass of every class in Java.
  - `String`, `StringBuilder`, `StringBuffer`: Immutable and mutable sequence of characters.
  - `Math`: Mathematical functions (`Math.max()`, `Math.min()`, `Math.sqrt()`, `Math.PI`).
  - `System`: System resources, standard input/output (`System.out.println()`), garbage collection (`System.gc()`), and time (`System.currentTimeMillis()`).
  - **Wrapper Classes**: `Integer`, `Double`, `Boolean`, `Character`, `Float`, `Long`, `Byte`, `Short` (used in autoboxing/unboxing and Generics).
  - `Thread`, `Runnable`: Multi-threading support.
  - `Throwable`, `Exception`, `Error`: The base of Java's exception hierarchy.

---

### 2. `java.util` (Utility & Collection Framework)
- **Purpose**: Contains the Java Collections Framework, legacy collection classes, event models, date/time facilities, and utility helpers.
- **Key Classes & Interfaces**:
  - `Scanner`: Reading user input from console or files (`new Scanner(System.in)`).
  - `Arrays`: Helper methods for arrays (`Arrays.sort()`, `Arrays.toString()`, `Arrays.binarySearch()`).
  - `Collections`: Helper algorithms for collection objects (`Collections.sort()`, `Collections.reverse()`).
  - **Collections Framework**:
    - `List` implementations: `ArrayList`, `LinkedList`, `Vector`, `Stack`.
    - `Set` implementations: `HashSet`, `LinkedHashSet`, `TreeSet`.
    - `Map` implementations: `HashMap`, `LinkedHashMap`, `TreeMap`.
    - `Queue` / `Deque` implementations: `PriorityQueue`, `ArrayDeque`.
  - `Random`: Generating random numbers.
  - `Optional`: Container object to avoid `NullPointerException` (Java 8+).

---

### 3. `java.io` (Standard Input / Output)
- **Purpose**: Provides system input and output through data streams, serialization, and the file system.
- **Key Classes**:
  - `File`: Represents file and directory pathnames.
  - `BufferedReader` & `InputStreamReader`: Fast character input reading (often preferred over `Scanner` in competitive programming).
  - `FileInputStream` / `FileOutputStream`: Reading/writing raw byte streams.
  - `FileReader` / `FileWriter`: Reading/writing character streams.
  - `PrintStream` / `PrintWriter`: Formatting and printing data.
  - `Serializable`: Marker interface enabling object serialization.

---

### 4. `java.nio` (New I/O / Non-blocking I/O)
- **Purpose**: Introduced in Java 1.4 and upgraded in Java 7 (`NIO.2`) for high-performance, non-blocking, buffer-oriented file and socket operations.
- **Key Classes**:
  - `Path` & `Paths`: Modern replacement for `java.io.File`.
  - `Files`: Utility methods for copying, reading, writing, and manipulating files and directories.
  - `ByteBuffer`, `Channels`: Low-level, high-throughput streaming.

---

### 5. `java.math` (High-Precision Mathematics)
- **Purpose**: Performing arithmetic, scale manipulation, rounding, comparison, and hashing with arbitrary precision numbers where primitive types (`int`, `long`, `double`) would overflow or lose precision.
- **Key Classes**:
  - `BigInteger`: For arbitrarily large integers (e.g. calculating 100! or cryptographic operations).
  - `BigDecimal`: For arbitrarily precise signed decimal numbers (indispensable for financial and monetary calculations).
  - `RoundingMode`: Specifies rounding behaviors.

---

### 6. `java.net` (Networking)
- **Purpose**: Provides classes for implementing networking applications (TCP sockets, UDP, HTTP URLs).
- **Key Classes**:
  - `URL`, `URI`: Parsing web addresses.
  - `HttpURLConnection`: Connecting to HTTP endpoints and APIs.
  - `Socket`, `ServerSocket`: Building client-server socket connections.
  - `InetAddress`: Handling IP addresses.

---

### 7. `java.time` (Modern Date & Time API)
- **Purpose**: Introduced in Java 8 to replace the outdated, thread-unsafe `java.util.Date` and `java.util.Calendar`.
- **Key Classes**:
  - `LocalDate`: Date without time (`2026-09-16`).
  - `LocalTime`: Time without date (`11:15:30`).
  - `LocalDateTime`: Combined date and time.
  - `Duration` & `Period`: Measuring time intervals.
  - `DateTimeFormatter`: Formatting and parsing dates.

---

### 8. `java.sql` (JDBC Database Access)
- **Purpose**: Java Database Connectivity (JDBC) for executing SQL queries and managing database transactions.
- **Key Interfaces/Classes**: `Connection`, `DriverManager`, `Statement`, `PreparedStatement`, `ResultSet`.

---

## 3. Package Import Rules & Best Practices

1. **Specific Class Import (Recommended)**:
   ```java
   import java.util.ArrayList; // Only imports ArrayList (clean & explicit)
   ```

2. **Wildcard Import (`*`)**:
   ```java
   import java.util.*; // Imports all classes directly inside java.util
   ```
   > [!NOTE]
   > The `*` wildcard imports all classes in that specific package, but **does NOT import sub-packages** recursively (e.g., `import java.*` does **not** import `java.util.Scanner`).

3. **Handling Naming Conflicts (Fully Qualified Name)**:
   When two packages contain a class with the exact same name (e.g., `java.util.Date` and `java.sql.Date`):
   ```java
   java.util.Date utilDate = new java.util.Date();
   java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
   ```

4. **Static Import**:
   Allows members (fields and methods) declared as `static` to be used without specifying the class name:
   ```java
   import static java.lang.Math.*;

   public class Test {
       public static void main(String[] args) {
           double radius = 5.0;
           double area = PI * pow(radius, 2); // No need to write Math.PI or Math.pow()
       }
   }
   ```

# 3. Object class in Java

The **`Object` class** is the **root (superclass) of the entire class hierarchy** in Java. Every class in Java directly or indirectly inherits from `Object`.

```
                        ┌──────────────────┐
                        │ java.lang.Object │ (Ultimate Root)
                        └────────┬─────────┘
                                 │
            ┌────────────────────┼────────────────────┐
            ▼                    ▼                    ▼
        String                Number              YourClass
                           ┌─────┴─────┐        (Implicitly
                           ▼           ▼         extends Object)
                        Integer      Double
```

- If a class does not explicitly use `extends`, the compiler automatically adds `extends Object`.
- Even if a class extends another class (`class B extends A`), class `A` extends `Object`, making `Object` the ancestor of all classes.
- **Top-level Polymorphism**: An `Object` reference can hold a reference to **any** Java object or array (`Object ref = new String("Hello");`).

---

## 1. Important Methods of the `Object` Class

The `Object` class defines **11 methods** that are inherited by every single Java class:

| Method Signature | Can be Overridden? | Primary Purpose |
| :--- | :---: | :--- |
| `public String toString()` | ✅ Yes | Returns a human-readable string representation of the object. |
| `public int hashCode()` | ✅ Yes | Returns a distinct integer hash value for hashing data structures. |
| `public boolean equals(Object obj)` | ✅ Yes | Checks whether two objects are logically equal. |
| `public final Class<?> getClass()` | ❌ **No (`final`)** | Returns the runtime `Class` object (metadata, reflection). |
| `protected Object clone()` | ✅ Yes | Creates and returns a field-by-field copy of the object. |
| `protected void finalize()` | ✅ *(Deprecated)* | Called by Garbage Collector before reclaiming memory. |
| `public final void wait()` | ❌ **No (`final`)** | Causes the current thread to wait until another thread wakes it up. |
| `public final void notify()` | ❌ **No (`final`)** | Wakes up a single thread waiting on the object's monitor. |
| `public final void notifyAll()` | ❌ **No (`final`)** | Wakes up all threads waiting on the object's monitor. |

---

## 2. Deep Dive: The Core Methods You Must Know

### 1. `toString()`
- **Default Implementation**:
  ```java
  getClass().getName() + '@' + Integer.toHexString(hashCode())
  ```
  *(e.g., `Lecture4.access.A@2f92e0f4`)*
- **Usage**: Automatically called whenever an object reference is printed via `System.out.println(obj)` or concatenated with a String (`"Object is: " + obj`).
- **Best Practice**: Always override `toString()` in custom classes to provide readable, meaningful attribute values for debugging.

```java
class Student {
    String name;
    int rollNo;

    public Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', rollNo=" + rollNo + "}";
    }
}
```

---

### 2. `hashCode()`
- Returns a unique **32-bit signed integer** representing the memory address or state of the object.
- **Why is it important?** It determines the bucket location when storing objects inside hash-based collections (`HashMap`, `HashSet`, `Hashtable`).
- **Default Behavior**: Converts the internal memory address into an integer (though it is **not** the raw memory address itself, since Java does not allow direct pointer access).

---

### 3. `equals(Object obj)`
- **Default Implementation**:
  ```java
  public boolean equals(Object obj) {
      return (this == obj); // Compares memory addresses (references)
  }
  ```
- **Difference Between `==` and `.equals()`**:
  - `==` operator: Compares whether two references point to the **exact same memory location in heap**.
  - `.equals()` method: Can be overridden to compare **content / state equality** (e.g., whether two `Student` objects have the same `rollNo`).

```java
Student s1 = new Student("Kunal", 1);
Student s2 = new Student("Kunal", 1);

System.out.println(s1 == s2);      // false (different heap memory addresses)
System.out.println(s1.equals(s2)); // true (if equals() is overridden to compare rollNo)
```

---

### 4. The `equals()` and `hashCode()` Contract (Crucial Interview Rule)

If you override `equals()`, **you MUST override `hashCode()` as well**.

1. **Rule 1**: If `obj1.equals(obj2)` is `true`, then `obj1.hashCode()` **must be equal to** `obj2.hashCode()`.
2. **Rule 2**: If `obj1.hashCode() == obj2.hashCode()`, `obj1.equals(obj2)` **does NOT have to be true** (hash collision).
3. **What happens if you break this contract?**
   - Objects will fail to be found in hash-based collections like `HashSet` or `HashMap`, causing silent bugs where duplicates are added or lookups return `null`.

---

### 5. `getClass()`
- Returns the `Class` object that represents the runtime class of the instance.
- Cannot be overridden because it is marked **`final`**.
- Commonly used in **Reflection** and to inspect class metadata:
  ```java
  Student s = new Student("Kunal", 1);
  System.out.println(s.getClass().getName());      // Student
  System.out.println(s.getClass().getSimpleName());  // Student
  ```

---

### 6. `clone()`
- Creates a new copy of the object without invoking its constructor.
- **Requirement**: The class must implement the `Cloneable` marker interface; otherwise, calling `super.clone()` throws `CloneNotSupportedException`.
- **Shallow Copy vs Deep Copy**:
  - By default, `clone()` creates a **shallow copy** (primitives are copied, but object references inside the object still point to the same original memory references).

---

### 7. Thread Communication Methods (`wait()`, `notify()`, `notifyAll()`)
- Why are these methods defined in `Object` rather than in `Thread`?
  - Because in Java, **every object has a monitor / lock**. Threads acquire the lock of an object to access `synchronized` blocks.
  - Must always be called from inside a `synchronized` block/method; otherwise, Java throws an `IllegalMonitorStateException`.

---

## 3. The `instanceof` Operator vs `getClass()`

```java
Object obj = new Student("Kunal", 1);

// 1. instanceof: Checks if object is an instance of class or ANY subclass/interface
System.out.println(obj instanceof Student); // true
System.out.println(obj instanceof Object);  // true

// 2. getClass(): Checks for the EXACT runtime class (excludes subclasses)
System.out.println(obj.getClass() == Student.class); // true
System.out.println(obj.getClass() == Object.class);  // false
```
